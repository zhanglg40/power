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
		<li class="active"><a href="${ctx}/sms/web/taskForm">任务添加</a></li>
	</ul><br/>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="smsTask" action="${ctx}/sms/web/saveTask" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">关键字:</label>
			<div class="controls">
				<form:input path="keyWord" htmlEscape="false" maxlength="50" class="required"  placeholder="关键字以竖线（“|”） 分割"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地域类型:</label>
			<div class="controls">
				<select style="width:130px" name="placeType">
					<option value="0">全国</option>
					<option value="1">省份</option>
					<option value="2">地级市</option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地域:</label>
			<div class="controls">
				<form:input path="place" htmlEscape="false" maxlength="50" placeholder="全国则不填"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>