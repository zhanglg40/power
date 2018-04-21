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
    <li><a href="${ctx}/power/device/list">设备列表</a></li>
    <li class="active"><a href="${ctx}/power/device/form?id=${deviceEntity.id}">设备<shiro:hasPermission name="power:device:edit">${not empty deviceEntity.sbbId?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="im:airport:edit"></shiro:lacksPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="deviceEntity" action="${ctx}/power/device/save" method="post" class="form-horizontal" enctype="multipart/form-data">
    <form:hidden path="sbbId"/>
    <sys:message content="${message}"/>


        <div class="control-group">
          <label class="control-label">设备名称:</label>
          <div class="controls">
                <form:input path="sbbName" htmlEscape="false" maxlength="50" class="required"/>
          </div>
       </div>
        <div class="control-group">
          <label class="control-label">经度:</label>
          <div class="controls">
                <form:input type="number" path="longitude" htmlEscape="false" maxlength="50" class=""/>
          </div>
       </div>
       <div class="control-group">
          <label class="control-label">纬度:</label>
          <div class="controls">
                <form:input type="number"  path="latitude" htmlEscape="false" maxlength="50" class=""/>
          </div>
       </div>
              <div class="control-group">
          <label class="control-label">物联网号码:</label>
          <div class="controls">
                <form:input path="internetNo" htmlEscape="false" maxlength="50" class=""/>
          </div>
       </div>
    <div class="control-group">
        <label class="control-label">机构编码:</label>
        <div class="controls">
            <form:input path="reveiveNo" htmlEscape="false" maxlength="50" class=""/>

        </div>
    </div>
                     <div class="control-group">
          <label class="control-label">设备类型:</label>
          <div class="controls">
                <form:input path="sbbType" htmlEscape="false" maxlength="50" class=""/>
          </div>
       </div>
                  <div class="control-group">
        <label class="control-label">区域位置:</label>
        <div class="controls">
    
       
              <sys:treeselect id="qyId" name="qyId" value="${deviceEntity.qyId}" labelName="qyName" labelValue="${deviceEntity.qyName}"
					title="区域" url="/sys/area/treeData" cssClass="required"/>
        </div></div>
 
           <div class="control-group">
        <label class="control-label">安装日期:</label>
        <div class="controls">
            <input id="createDate" name="createDate" readonly="readonly"
                   maxlength="200" class="input-large Wdate required"
                   value="<fmt:formatDate value="${deviceEntity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});" />
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="form-actions">
         <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
        <a href="${ctx}/power/device/list"><input id="btnCancel" class="btn" type="button" value="返回" /></a>
    </div>
</form:form>
</body>
</html>