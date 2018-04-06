package com.thinkgem.jeesite.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.api.ResponseBean;
import com.thinkgem.jeesite.common.config.ErrorCode;


/**
 * 对请求接口进行基本信息验证类 excludedPages * .* .+ 三种均为不验证数据
 */
public class BasicVerifyFilter implements Filter {

    private static final boolean  IS_VERIFY_CONTENT_SIGN   = false;// 是否验证签名

    private boolean isExcludedPage         = false;// 是否有例外请求链接
    private boolean isAllowAllPage         = false;// 是否有例外请求链接
    private Set<String> excludedPageSet    = new HashSet<String>();
    private List<String> excludedPageList  = new ArrayList<String>();// 例外列表

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @SuppressWarnings("unused")
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

//        HttpServletRequest req = (HttpServletRequest)request;
//        if (req.getHeader("Origin") != null) {// 允许跨域请求
//            HttpServletResponse rep = (HttpServletResponse) response;
//            rep.addHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
//            rep.addHeader("Access-Control-Allow-Credentials", "true");
//            rep.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
//        }

        if (response instanceof HttpServletResponse) {// 允许跨域请求
            HttpServletResponse res = (HttpServletResponse) response;
            res.setContentType("text/html;charset=UTF-8");  
            res.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");  
            res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
            res.setHeader("Access-Control-Max-Age", "0");  
            res.setHeader("Access-Control-Allow-Headers",  
                "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");  
            res.setHeader("Access-Control-Allow-Credentials", "true");  
            res.setHeader("XDomainRequestAllowed", "1");          }
        if (isAllowAllPage || IS_VERIFY_CONTENT_SIGN == false) {// 所有请求通过， 不做数据验证
            filterChain.doFilter(request, response);
            return;
        } else {// 对基本数据域进行验证
            if (isExcludedPage) { // 有例外请求链接
                HttpServletRequest req = (HttpServletRequest) request;
                String uri = req.getServletPath();
                if (excludedPageSet.size()>0) {
                    if (excludedPageSet.contains(uri)) {// 直接包含
                        filterChain.doFilter(request, response);
                        return;
                    }
                    for (String page : excludedPageList) {// 遍历例外url数组
                        if (uri.matches(page)) {// 在过滤url之外
                            filterChain.doFilter(request, response);
                            return;
                        }
                    }
                }
            }
            String content = request.getParameter("content");
            String sign    = request.getParameter("sign");
          /*  if (content != null && sign != null) { // 基本参数不为NULL
                BaseBean baseBean = JSON.parseObject(content, BaseBean.class);
                if (SignUtil.verify(content, sign)) {// 签名验证通过
                    filterChain.doFilter(request, response);
                    return;
                }
            }*/
            ResponseBean responseBean = new ResponseBean();
            responseBean.setStatus(ErrorCode.STATUS_CODE_4003);
            responseBean.setMessage("数据签名未通过");

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getOutputStream().write(JSON.toJSONString(responseBean).getBytes("UTF-8"));
            response.getOutputStream().flush();
            response.getOutputStream().close();
//            request.setAttribute("message", "content及sign不允许为空，签名验证未通过");
//            request.getRequestDispatcher("/WEB-INF/views/errors/401.jsp").forward(request, response);// 跳转到验证错误页面
            return;
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
        String excludedPages = filterConfig.getInitParameter("excludedPages");
        if (excludedPages != null && excludedPages.length() != 0) {
            if (excludedPages.equals("*")
                    || excludedPages.equals(".*")
                        || excludedPages.equals(".+")
                            || excludedPages.equals("/")) {// 全部不验证
                isAllowAllPage = true;
            } else {
                String[] pageArray = excludedPages.split(";");
                for (int i = 0, len = pageArray.length; i < len; i++) {
                    excludedPageSet.add(pageArray[i]);
                    excludedPageList.add(pageArray[i]);
                }
                isExcludedPage = true;
            }
        }
    }
}
