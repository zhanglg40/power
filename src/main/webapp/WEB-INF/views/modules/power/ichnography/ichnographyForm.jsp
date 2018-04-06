<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>设备管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
        	
            $("#content").focus();
            $("#inputForm").validate({
                rules: {
                   
                },
                messages: {
                    
                },
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
    <li><a href="${ctx}/power/ichnography/list">设备列表</a></li>
    <li class="active"><a href="${ctx}/power/ichnography/form?id=${ichnographyEntity.id}">设备<shiro:hasPermission name="power:ichnography:edit">${not empty ichnographyEntity.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="im:airport:edit"></shiro:lacksPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="ichnographyEntity" action="${ctx}/power/ichnography/save" method="post" class="form-horizontal" enctype="multipart/form-data">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>


        <div class="control-group">
          <label class="control-label">设备名称:</label>
          <div class="controls">
                <form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
          </div>
       </div>
        
                  <div class="control-group">
        <label class="control-label">区域位置:</label>
        <div class="controls">
    
       
              <sys:treeselect id="area" name="area.id" value="${ichnographyEntity.area.id}" labelName="area.name" labelValue="${ichnographyEntity.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="required"/>
        </div></div>
         
		<div class="control-group">
			<label class="control-label">平面图上传:</label>
			<div class="controls">
				<form:hidden id="nameImage" path="url" htmlEscape="false" maxlength="455" class="input-xlarge"/>
				<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="500" maxHeight="400"/>
			</div>
		</div>
    <div class="form-actions">
        <shiro:hasPermission name="power:ichnography:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
        <a href="${ctx}/power/ichnography/list"><input id="btnCancel" class="btn" type="button" value="返回" /></a>
    </div>
</form:form>
</body>
</html>