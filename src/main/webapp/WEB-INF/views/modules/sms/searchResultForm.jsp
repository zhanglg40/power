<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sms/web/SmsSearchForm">查询手机号</a></li>
	</ul><br/>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="smsTask" action="${ctx}/sms/web/saveTask" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">关键字:</label>
			<div class="controls">
				<form:input path="keyWord" htmlEscape="false" maxlength="50" class="required"  placeholder="关键字以竖线（“|”） 分割" readonly="true"/>
			</div>
		</div>
		<form:hidden path="placeType"/>
		<form:hidden path="place"/>
		<div class="control-group">
			<label class="control-label">手机号数量:</label>
			<div class="controls">
				<form:input path="totalQuantity" htmlEscape="false" maxlength="50" class="required"  placeholder="关键字以竖线（“|”） 分割" readonly="true"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="新建查询任务"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>