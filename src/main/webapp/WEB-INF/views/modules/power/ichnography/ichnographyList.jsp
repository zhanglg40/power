<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>平面图管理</title>
    <meta name="decorator" content="default"/>
    <script form="text/javascript">
        function page(n, s) {
            if (n) $("#pageNo").val(n);
            if (s) $("#pageSize").val(s);
            $("#searchForm").attr("action", "${ctx}/power/ichnography/list");
            $("#searchForm").submit();
            return false;
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
    <li class="active"><a href="${ctx}/power/ichnography/list">平面图列表</a></li>
    <shiro:hasPermission name="power:ichnography:edit">
        <li><a href="${ctx}/power/ichnography/form">平面图添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" name="searchForm" modelAttribute="ichnographyEntity" action="${ctx}/verify/document/list" method="post"
           class="breadcrumb form-search ">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
    <ul class="ul-form">
      <li><label>平面图名称：&nbsp;</label></li><li><form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
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
        <th>编号</th><th>平面图名称</th><th>所属区域</th>
        <shiro:hasPermission name="power:ichnography:edit">
            <th>操作</th>
            <th>设备管理</th>
        </shiro:hasPermission>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="ichnography" varStatus="vs">
        <tr>
            <%--这里的序号与对应的数据不是绑定的--%>
            <td>${page.pageSize*(page.pageNo-1)+vs.index+1}</td>
             <td>${ichnography.name}</td>
            <td>
              ${ichnography.area.name}
            </td>
            
            <shiro:hasPermission name="power:ichnography:edit">
                <td>
                    <a href="${ctx}/power/ichnography/form?id=${ichnography.id}">修改</a>
                   
                    <a href="${ctx}/power/ichnography/delete?id=${ichnography.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
                        <shiro:hasPermission name="power:ichnography:edit">
                <td>
                    <a href="${ctx}/data/ichnographyDevice/list?ichnographyId=${ichnography.id}">设备管理</a>

                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>