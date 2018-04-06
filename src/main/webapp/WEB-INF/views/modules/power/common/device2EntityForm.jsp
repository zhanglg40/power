<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理</title>
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
		<li><a href="${ctx}/common/device2Entity/">设备列表</a></li>
		<li class="active"><a href="${ctx}/common/device2Entity/form?id=${device2Entity.id}">设备<shiro:hasPermission name="common:device2Entity:edit">${not empty device2Entity.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="common:device2Entity:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="device2Entity" action="${ctx}/common/device2Entity/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">设备名称：</label>
			<div class="controls">
				<form:input path="sbbName" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备码：</label>
			<div class="controls">
				<form:input path="sbbId" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属区域：</label>
			<div class="controls">
				<sys:treeselect id="area" name="area.id" value="${device2Entity.area.id}" labelName="area.name" labelValue="${device2Entity.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">监测类型：</label>
			<div class="controls">
				     <form:select path="monitoringType" class="input-large">
              <form:options items="${fns:getDictList('monitoring_type')}"
                itemLabel="label" itemValue="value" htmlEscape="false" />
             </form:select> 
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">监测区域1：</label>
			<div class="controls">
			<form:select path="areaId1" class="input-medium" >
                    <form:option value="" label="请选择" />
                    <form:options items="${areaList}"
                        itemLabel="name" itemValue="code" htmlEscape="false" />
                </form:select>
				<span class="help-inline"><font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">监测区域2：</label>
			<div class="controls">
						<form:select path="areaId2" class="input-medium" >
                    <form:option value="" label="请选择" />
                    <form:options items="${areaList}"
                        itemLabel="name" itemValue="code" htmlEscape="false" />
                </form:select>
                <span class="help-inline"><font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">安装日期：</label>
			<div class="controls">
				<input name="installDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${device2Entity.installDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
				<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="common:device2Entity:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>