<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据管理</title>
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
		<%-- <li class="active"><a href="${ctx}/monitoring/monitoringData/">监测数据列表</a></li>
		 --%>
	</ul>
	<div style="margin-top:30px"></div>
	<form:form id="searchForm" modelAttribute="monitoringData" action="${ctx}/monitoring/monitoringData/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备名称：</label>
				<form:input path="sbbId" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>时间范围：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${monitoringData.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${monitoringData.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>监测类型：</label>
            <form:select path="type" class="input-large">
              <form:options items="${fns:getDictList('monitoring_type')}"
                itemLabel="label" itemValue="value" htmlEscape="false" />
             </form:select> 
			</li>
<%-- 			<li><label>area_id：</label>
				<sys:treeselect id="areaId" name="areaId" value="${monitoringData.areaId}" labelName="" labelValue="${monitoringData.areaName}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li> --%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed" style="margin-top:20px">
		<thead>
			<tr>
				<th>设备名称</th>
				<th>数据值</th>
				<th>采集时间</th>
				<th>采集类型</th>
				<th>采集区域</th>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="monitoringData">
			<tr>
				<td>
					${monitoringData.sbbName}
				</td>
				<td>
					${monitoringData.monitoringData}
				</td>
				<td>
					<fmt:formatDate value="${monitoringData.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					 ${fns:getDictLabel(monitoringData.type, 'monitoring_type', '')}
				</td>
				<td>
					${monitoringData.areaName}
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>