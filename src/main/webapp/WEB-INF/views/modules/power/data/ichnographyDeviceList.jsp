<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理</title>
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
		<li class="active"><a>设备列表</a></li>
		<li><a href="${ctx}/data/ichnographyDevice/form?ichnographyId=${ichnographyDevice.ichnographyId}">设备添加</a></li>
	</ul>
	<form:form id="searchForm" style="display:none" modelAttribute="ichnographyDevice" action="${ctx}/data/ichnographyDevice/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>ichnography_name：</label>
				<form:input path="ichnographyName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>sbb_name：</label>
				<form:input path="sbbName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>ichnography_id：</label>
				<form:input path="ichnographyId" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>sbb_id：</label>
				<form:input path="sbbId" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>平面图名称</th>
				<th>设备名称</th>
				<th>起点坐标</th>
				<th>终点坐标</th>
				
				<shiro:hasPermission name="data:ichnographyDevice:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ichnographyDevice">
			<tr>
				<td><a href="${ctx}/data/ichnographyDevice/form?id=${ichnographyDevice.id}">
					${ichnographyDevice.ichnographyName}
				</a></td>
				<td>
					${ichnographyDevice.sbbName}
				</td>
				<td>
					(${ichnographyDevice.xAxesStart},${ichnographyDevice.yAxesStart})
				</td>
				<td>
					(${ichnographyDevice.xAxesEnd},${ichnographyDevice.yAxesEnd})
				</td>
				
				<shiro:hasPermission name="data:ichnographyDevice:edit"><td>
    				<a href="${ctx}/data/ichnographyDevice/form?id=${ichnographyDevice.id}">修改</a>
					<a href="${ctx}/data/ichnographyDevice/delete?id=${ichnographyDevice.id}" onclick="return confirmx('确认要删除该设备吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>