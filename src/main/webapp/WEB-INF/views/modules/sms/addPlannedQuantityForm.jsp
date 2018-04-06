<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/sms/web/taskList">任务列表</a></li>
		<li class="active"><a>修改计划处理量</a></li>
	</ul><br/>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="smsTask" action="${ctx}/sms/web/saveTask" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label">任务编号:</label>
			<div class="controls">
				<form:input path="id" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务名称:</label>
			<div class="controls">
				<form:input path="keyWord" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总用户数量:</label>
			<div class="controls">
				<form:input path="totalQuantity" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		
		
		<c:choose>  
			<c:when test="${not empty smsTask.totalQuantity}">
				<div class="control-group">
					<label class="control-label">计划处理数量:</label>
					<div class="controls">
						<form:input path="plannedQuantity" htmlEscape="false"  class="required"  maxlength="50" type="number" max="${smsTask.totalQuantity}"/>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="control-group">
					<label class="control-label">计划处理数量:</label>
					<div class="controls">
						<form:input path="plannedQuantity" value="0" htmlEscape="false" maxlength="50" type="number" readonly="true" placeholder="不可写"/>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
		
		
		<div class="control-group">
			<label class="control-label">开始时间:</label>
			<div class="controls">
				<input id="beginTime" name="beginTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${smsTask.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
		</div>
	</form:form>
</body>
</html>