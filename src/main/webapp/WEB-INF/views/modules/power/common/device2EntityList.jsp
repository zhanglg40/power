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
		<li class="active"><a href="${ctx}/common/device2Entity/">设备列表</a></li>
		<shiro:hasPermission name="common:device2Entity:edit"><li><a href="${ctx}/common/device2Entity/form">设备添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="device2Entity" action="${ctx}/common/device2Entity/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>设备名称：</label>
				<form:input path="sbbName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>所属区域：</label>
				<sys:treeselect id="area" name="area.id" value="${device2Entity.area.id}" labelName="area.name" labelValue="${device2Entity.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>监测类型：</label>
			     <form:select path="monitoringType" class="input-large">
              <form:options items="${fns:getDictList('monitoring_type')}"
                itemLabel="label" itemValue="value" htmlEscape="false" />
             </form:select> 
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
                <c:choose>
                    <c:when test="${page.orderBy eq ''}">
                        <th class="sort-column sbb_name">设备名称<i class="icon icon-arrow-down"></i><i class="icon icon-arrow-up"></i></th>
                    </c:when>
                    <c:otherwise>
                        <th class="sort-column sbb_name">设备名称</th>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${fn:contains(page.orderBy,'sbb_ID')}">
                        <th class="sort-column sbb_ID">设备码</th>
                    </c:when>
                    <c:otherwise>
                        <th class="sort-column sbb_ID">设备码<i class="icon icon-arrow-down"></i><i class="icon icon-arrow-up"></i></th>
                    </c:otherwise>
                </c:choose>
				<th>所属区域</th>
				<th>监测类型</th>
			
				<th>监测区域1</th>
				<th>监测区域2</th>
                <c:choose>
                    <c:when test="${fn:contains(page.orderBy,'install_date')}">
                        <th class="sort-column install_date">安装日期</th>
                    </c:when>
                    <c:otherwise>
                        <th class="sort-column install_date">安装日期<i class="icon icon-arrow-down"></i><i class="icon icon-arrow-up"></i></th>
                    </c:otherwise>
                </c:choose>
				<shiro:hasPermission name="common:device2Entity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="device2Entity">
			<tr>
				<td><a href="${ctx}/common/device2Entity/form?id=${device2Entity.id}">
					${device2Entity.sbbName}
				</a></td>
				<td>
					${device2Entity.sbbId}
				</td>
				<td>
					${device2Entity.area.name}
				</td>
				<td>
				 ${fns:getDictLabel(device2Entity.monitoringType, 'monitoring_type', '')}
				
				</td>

				<td>
					${device2Entity.areaName1}
				</td>
				<td>
					${device2Entity.areaName2}
				</td>
				<td>
					<fmt:formatDate value="${device2Entity.installDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="common:device2Entity:edit"><td>
    				<a href="${ctx}/common/device2Entity/form?id=${device2Entity.id}">修改</a>
					<a href="${ctx}/common/device2Entity/delete?id=${device2Entity.id}" onclick="return confirmx('确认要删除该设备吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>