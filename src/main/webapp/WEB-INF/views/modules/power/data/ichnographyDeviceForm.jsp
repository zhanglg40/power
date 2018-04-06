<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			 var c = document.getElementById("myCanvas");  
	            var cxt = c.getContext("2d");  
	            var img = new Image();  
	          //  alert("${ichnographyEntity.url}");
	            img.src = "${ichnographyEntity.url}";  
	            img.onload = function () {  
	                cxt.drawImage(img, 0, 0,680,368);  
	            }  
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
		  function windowTocanvas(canvas, x, y) {
				var bbox = canvas.getBoundingClientRect();
				return {
					x: x - bbox.left * (canvas.width / bbox.width), 
					y: y - bbox.top * (canvas.height / bbox.height)
				};

			}
		 window.onload=function(){
			var canvas=document.getElementById("myCanvas");
			
			canvas.onclick=function(event){
				
				var loc=windowTocanvas(canvas,event.clientX,event.clientY)
				var x=parseInt(loc.x);
				var y=parseInt(loc.y);
				document.getElementById("input_window").value=event.clientX+"--"+event.clientY;
			    document.getElementById("input_canvas").value=x+"--"+y;
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/data/ichnographyDevice/list?ichnographyId=${ichnographyDevice.ichnographyId}">设备列表</a></li>
		<li class="active"><a href="${ctx}/data/ichnographyDevice/form?ichnographyId=${ichnographyDevice.ichnographyId}&id=${ichnographyDevice.id}">设备<shiro:hasPermission name="data:ichnographyDevice:edit">${not empty ichnographyDevice.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="data:ichnographyDevice:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	 <div>
	 <div style="float:left">
	
    <canvas id="myCanvas" width="680" height="368" style="border: 1px solid #f00">Your browser does not support the canvas element.  
    </canvas>  
	</div>
	    <div style="float:right">
		<div class="" align="center"style=" z-index: 1; top: 100px; left: 100px;">	
			</div>
	   <div class=""align="center"style=" z-index: 1; top: 30px; left: 100px;">	
	   	<input type="text" id="input_window" readonly="readonly" style="display:none" value="" /> <br />
	          点击获取X，Y值：<input type="text" id="input_canvas" value="" /> 
	   </div>
	   </div>
	  </div>
	  <div class="clearfix"></div>
	  <div>
	<form:form id="inputForm" modelAttribute="ichnographyDevice" action="${ctx}/data/ichnographyDevice/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group" hidden>
			<label class="control-label">ichnography_id：</label>
			<div class="controls">
				<form:input path="ichnographyId" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备名称：</label>
			<div class="controls">
				      <form:select path="sbbId" class="input-medium required" ismultiple="true">

                    <form:options items="${deviceList}"
                        itemLabel="sbbName" itemValue="sbbId" htmlEscape="false" />
                </form:select> 
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">起点坐标：</label>
			<div class="controls">
				X:<form:input path="xAxesStart" htmlEscape="false" maxlength="10" class="input-mini required"/>
				Y:<form:input path="yAxesStart" htmlEscape="false" maxlength="10" class="input-mini required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">终点坐标：</label>
			<div class="controls">
				X:<form:input path="xAxesEnd" htmlEscape="false" maxlength="10" class="input-mini required"/>
				Y:<form:input path="yAxesEnd" htmlEscape="false" maxlength="10" class="input-mini required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="data:ichnographyDevice:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	</div>
</body>
</html>