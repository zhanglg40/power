<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>设备管理</title>
    <meta name="decorator" content="default"/>
    <script form="text/javascript">
        function page(n, s) {
            if (n) $("#pageNo").val(n);
            if (s) $("#pageSize").val(s);
            $("#searchForm").attr("action", "${ctx}/power/device/list");
            $("#searchForm").submit();
            return false;
        }
        function  orderByName( orderby) {
            $("#searchForm").attr("action", "${ctx}/power/device/list");
            $("#searchForm").submit();
        }
        function preview(id){
            document.searchForm.target="mywin";
            window.open('','mywin',"menubar=0,toolbar=0,status=0,resizable=1,left=0,top=0,scrollbars=1,width=" +(screen.availWidth-10) + ",height=" + (screen.availHeight-50) + "\"");
            document.searchForm.action="${ctx}/verify/document/preview?id="+id;
            document.searchForm.submit(); //提交表单
        }
    </script>
   
</head>
<body>

<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/power/device/list">设备列表</a></li>
    <shiro:hasPermission name="power:device:edit">
        <li><a href="${ctx}/power/device/form">设备添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" name="searchForm" modelAttribute="deviceEntity" action="${ctx}/verify/document/list" method="post"
           class="breadcrumb form-search ">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
    <ul class="ul-form">
      <li><label>设备名称：&nbsp;</label><form:input path="sbbName" htmlEscape="false" maxlength="100" class="input-medium"/>
       </li>

       <li class="btns">&nbsp;&nbsp;
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
        </li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<shiro:hasPermission name="vrf:document:edit">
    <a href="${ctx}/verify/document/form?pageNo=${page.pageNo}&pageSize=${page.pageSize}">
        <input class="btn btn-primary" type="button" style="margin-left:8px;margin-bottom:8px" value="+ 上传" />
    </a>
</shiro:hasPermission>
<sys:message content="${message}"/>
<div class="clearfix"></div>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>编号</th>
        <c:choose>
            <c:when test="${page.orderBy eq ''}">
                <th class="sort-column sbb_name">设备名称<i class="icon icon-arrow-down"></i><i class="icon icon-arrow-up"></i></th>
            </c:when>
            <c:otherwise>
               <th class="sort-column sbb_name">设备名称</th>
            </c:otherwise>
        </c:choose>


        <th>经度</th><th>纬度</th><th>物联网号码</th>
        <th>区域位置</th><th>设备类型</th><th>安装时间</th><th>报警通知手机号</th>
        <shiro:hasPermission name="power:device:edit">
            <th>操作</th>
        </shiro:hasPermission></tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="device" varStatus="vs">
        <tr>
            <%--这里的序号与对应的数据不是绑定的--%>
            <td>${page.pageSize*(page.pageNo-1)+vs.index+1}</td>
             <td>${device.sbbName}</td>
            <td>
              ${device.longitude}
            </td>
            <td>
              ${device.latitude}
            </td>   
                                                <td>
              ${device.internetNo}
            </td> 
                        <td>
              ${device.qyName}
            </td>           
                        <td>
              ${device.sbbType}
            </td>  
    
            <td><fmt:formatDate value="${device.createDate}" type="both"/></td>
                <td>
                        ${device.reveiveNo}
                </td>
                <shiro:hasPermission name="power:device:edit">
                <td>
                    <a href="${ctx}/power/device/form?id=${device.sbbId}">修改</a>
                   
                    <a href="${ctx}/power/device/delete?id=${device.sbbId}&pageNo=${page.pageNo}&pageSize=${page.pageSize}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>