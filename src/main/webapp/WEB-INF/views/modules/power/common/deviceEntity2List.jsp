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
		<li class="active"><a href="${ctx}/common/deviceEntity2/">设备列表</a></li>
		<shiro:hasPermission name="common:deviceEntity2:edit"><li><a href="${ctx}/common/deviceEntity2/form">设备添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="deviceEntity2" action="${ctx}/common/deviceEntity2/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备名称：</label>
				<form:input path="sbbName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>所属区域：</label>
				<sys:treeselect id="area" name="area.id" value="${deviceEntity2.area.id}" labelName="area.name" labelValue="${deviceEntity2.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>监测类型：</label>
				<form:input path="monitoringType" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>设备名称</th>
				<th>设备码</th>
				<th>所属区域</th>
				<th>监测类型</th>
				
				<th>监测区域1</th>
				<th>监测区域2</th>
				<th>安装日期</th>
				<shiro:hasPermission name="common:deviceEntity2:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="deviceEntity2">
			<tr>
				<td><a href="${ctx}/common/deviceEntity2/form?id=${deviceEntity2.id}">
					${deviceEntity2.sbbName}
				</a></td>
				<td>
					${deviceEntity2.sbbId}
				</td>
				<td>
					${deviceEntity2.area.name}
				</td>
				<td>
					${deviceEntity2.monitoringType}
				</td>

				<td>
					${deviceEntity2.}
				</td>
				<td>
					${deviceEntity2.}
				</td>
				<td>
					<fmt:formatDate value="${deviceEntity2.installDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="common:deviceEntity2:edit"><td>
    				<a href="${ctx}/common/deviceEntity2/form?id=${deviceEntity2.id}">修改</a>
					<a href="${ctx}/common/deviceEntity2/delete?id=${deviceEntity2.id}" onclick="return confirmx('确认要删除该设备吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>