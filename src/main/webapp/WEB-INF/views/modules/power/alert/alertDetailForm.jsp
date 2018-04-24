<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报警数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/data/alertDetail/">报警数据列表</a></li>
		<li class="active"><a href="${ctx}/data/alertDetail/form?id=${alertDetail.id}">报警数据<shiro:hasPermission name="data:alertDetail:edit">${not empty alertDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="data:alertDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="alertDetail" action="${ctx}/data/alertDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
        <form:hidden path="sbbId"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">设备名称：</label>
			<div class="controls">
				<form:input readonly="true"  path="sbbName" htmlEscape="false" class="input-xlarge required"/>

			</div>
		</div>


		<div class="control-group">
			<label class="control-label">温度A：</label>
			<div class="controls">
				<form:input path="temperatureA" type="number" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">温度b：</label>
			<div class="controls">
				<form:input path="temperatureB" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">温度c：</label>
			<div class="controls">
				<form:input path="temperatureC" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">温度N：</label>
			<div class="controls">
				<form:input path="temperatureN" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电流A：</label>
			<div class="controls">
				<form:input path="currentA" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电流B：</label>
			<div class="controls">
				<form:input path="currentB" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电流C：</label>
			<div class="controls">
				<form:input path="currentC" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电压A：</label>
			<div class="controls">
				<form:input path="voltageA" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电压B：</label>
			<div class="controls">
				<form:input path="voltageB" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电压C：</label>
			<div class="controls">
				<form:input path="voltageC" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
<%--		<div class="control-group">
			<label class="control-label">electrical_degree：</label>
			<div class="controls">
				<form:input path="electricalDegree" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">漏电流：</label>
			<div class="controls">
				<form:input path="leakageElectricity" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="data:alertDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>