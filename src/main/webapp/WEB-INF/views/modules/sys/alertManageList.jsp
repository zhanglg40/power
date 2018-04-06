<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报警类型管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/alertManage/">报警类型列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="alertManage" action="${ctx}/sys/alertManage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>类型</th>
				<th>临界值</th>
				<th>提示语</th>
				<shiro:hasPermission name="sys:alertManage:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="alertManage">
			<tr>
				<td><a href="${ctx}/sys/alertManage/form?id=${alertManage.id}">
				  ${fns:getDictLabel(alertManage.alertType, 'alertTyle', '')} 
				</a></td>
				<td>
					${alertManage.value}
				</td>
				<td>
					${alertManage.tips}
				</td>
				<shiro:hasPermission name="sys:alertManage:edit"><td>
    				<a href="${ctx}/sys/alertManage/form?id=${alertManage.id}">修改</a>
					<a href="${ctx}/sys/alertManage/delete?id=${alertManage.id}" onclick="return confirmx('确认要删除该报警类型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>