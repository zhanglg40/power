<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html class="login-bg">
<head>

	<title>${fns:getConfig('productName')} 登录</title>
<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	    <!-- bootstrap -->
    <link href="${ctxStatic}/login/css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="${ctxStatic}/login/css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="${ctxStatic}/login/css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/login/css/layout.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/login/css/elements.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/login/css/icons.css" />

    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/login/css/lib/font-awesome.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="${ctxStatic}/login/css/compiled/signin.css" type="text/css" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />
	

    
	
	<script type="text/javascript">
		$(document).ready(function() {
			  var $btns = $(".bg-switch .bg");
	            $btns.click(function (e) {
	                e.preventDefault();
	                $btns.removeClass("active");
	                $(this).addClass("active");
	                var bg = $(this).data("img");

	                $("html").css("background-image", "url('${ctxStatic}/login/img/bgs/" + bg + "')");
	            });
   		$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
					validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				} 
			});   
		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
	</script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
 <!-- background switcher -->
    <div class="bg-switch visible-desktop">
        <div class="bgs">
            <a href="#" data-img="landscape.jpg" class="bg active">
                <img src="${ctxStatic}/login/img/bgs/landscape.jpg" />
            </a>
            <a href="#" data-img="blueish.jpg" class="bg">
                <img src="${ctxStatic}/login/img/bgs/blueish.jpg" />
            </a>            
            <a href="#" data-img="7.jpg" class="bg">
                <img src="${ctxStatic}/login/img/bgs/7.jpg" />
            </a>
            <a href="#" data-img="8.jpg" class="bg">
                <img src="${ctxStatic}/login/img/bgs/8.jpg" />
            </a>
            <a href="#" data-img="9.jpg" class="bg">
                <img src="${ctxStatic}/login/img/bgs/9.jpg" />
            </a>
            <a href="#" data-img="10.jpg" class="bg">
                <img src="${ctxStatic}/login/img/bgs/10.jpg" />
            </a>
            <a href="#" data-img="11.jpg" class="bg">
                <img src="${ctxStatic}/login/img/bgs/11.jpg" />
            </a>
        </div>
    </div>
    <div class="row-fluid login-wrapper">
        <a>
            <img class="logo" src="${ctxStatic}/login/img/logo.png" />
        </a>

        <div class="span4 box">
		<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
            <div class="content-wrap">
                <h6>登 录</h6>
                <input class="span12" id="username" name="username" type="text" placeholder="请输入用户名" />
                <input class="span12" id="password" name="password"  type="password" placeholder="请输入密码" />
               <c:if test="${isValidateCodeLogin}"><div class="validateCode">
		    	<label class="input-label mid" for="validateCode">验证码</label>
		     	<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
		        </div></c:if>
                <div class="remember">
                <input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/> 
                    <label for="remember-me">记住我（公共场所慎用）</label>
                </div>
                <input class="btn-glow primary login" type="submit" value="登 录"/>
                
            </div>
			</form>
        </div>

        <div class="span4 no-account">
           <p>Copyright &copy;2017-2018</p>
        </div>
    </div>
	<!-- scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="${ctxStatic}/login/js/bootstrap.min.js"></script>
    <script src="${ctxStatic}/login/js/theme.js"></script>

	<%-- <h1 class="form-signin-heading">${fns:getConfig('productName')}</h1>
	<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
		<label class="input-label" for="username">登录名</label>
		<input type="text" id="username" name="username" class="input-block-level required" value="${username}">
		<label class="input-label" for="password">密码</label>
		<input type="password" id="password" name="password" class="input-block-level required">
		<c:if test="${isValidateCodeLogin}"><div class="validateCode">
			<label class="input-label mid" for="validateCode">验证码</label>
			<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
		</div></c:if>
		<label for="mobile" title="手机登录"><input type="checkbox" id="mobileLogin" name="mobileLogin" ${mobileLogin ? 'checked' : ''}/></label>
		<input class="btn btn-large btn-primary" type="submit" value="登 录"/>&nbsp;&nbsp;
		<label for="rememberMe" title="下次不需要再登录"><input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/> 记住我（公共场所慎用）</label>
		<div id="themeSwitch" class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">${fns:getDictLabel(cookie.theme.value,'theme','默认主题')}<b class="caret"></b></a>
			<ul class="dropdown-menu">
			  <c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
			</ul>
			<!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
		</div>
	</form>
	<div class="footer">
		Copyright &copy; 2012-${fns:getConfig('copyrightYear')} <a href="${pageContext.request.contextPath}${fns:getFrontPath()}">${fns:getConfig('productName')}</a> - Powered By <a href="http://jeesite.com" target="_blank">JeeSite</a> ${fns:getConfig('version')} 
	</div> --%>
	<%-- <script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script> --%>
</body>
</html>