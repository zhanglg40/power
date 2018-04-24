<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报警数据管理</title>
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
		<li class="active"><a href="${ctx}/data/alertDetail/">报警数据列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="alertDetail" action="${ctx}/data/alertDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
            <li><label>设备名称：&nbsp;</label>
                <form:select path="sbbId" class="input-medium" ismultiple="true">

                    <form:options items="${deviceList}"
                                  itemLabel="sbbName" itemValue="sbbId" htmlEscape="false" />
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
				<th>设备名称</th>

				<th>温度A</th>
				<th>温度B</th>
				<th>温度C</th>
				<th>温度N</th>
				<th>电流A</th>
				<th>电流B</th>
				<th>电流C</th>
				<th>电压A</th>
				<th>电压B</th>
				<th>电压C</th>

				<th>漏电流</th>
				<shiro:hasPermission name="data:alertDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="alertDetail">
			<tr>
				<td><a href="${ctx}/data/alertDetail/form?id=${alertDetail.id}">
					${alertDetail.sbbName}
				</a></td>

				<td>
					${alertDetail.temperatureA}
				</td>
				<td>
					${alertDetail.temperatureB}
				</td>
				<td>
					${alertDetail.temperatureC}
				</td>
				<td>
					${alertDetail.temperatureN}
				</td>
				<td>
					${alertDetail.currentA}
				</td>
				<td>
					${alertDetail.currentB}
				</td>
				<td>
					${alertDetail.currentC}
				</td>
				<td>
					${alertDetail.voltageA}
				</td>
				<td>
					${alertDetail.voltageB}
				</td>
				<td>
					${alertDetail.voltageC}
				</td>

				<td>
					${alertDetail.leakageElectricity}
				</td>
				<shiro:hasPermission name="data:alertDetail:edit"><td>
    				<a href="${ctx}/data/alertDetail/form?sbbName=${alertDetail.sbbName}&sbbId=${alertDetail.sbbId}&id=${alertDetail.id}">修改</a>

				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>