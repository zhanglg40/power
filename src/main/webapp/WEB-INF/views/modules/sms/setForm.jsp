<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sms/set/save">任务添加</a></li>
	</ul><br/>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="smsSetEntity" action="${ctx}/sms/set/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		

		<div class="control-group">
			<label class="control-label">短信内容:</label>
			<div class="controls">
				<form:textarea cssStyle="width:350px;height: 100px;"  path="smsContent" htmlEscape="false" maxlength="150" />
			</div>
		</div>
				<div class="control-group">
			<label class="control-label">报警时限:</label>
			<div class="controls">
				<form:input path="sendTime" type="number" htmlEscape="false" maxlength="50" />分钟
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;

		</div>
	</form:form>
</body>
</html>