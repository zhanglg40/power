<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	function writeText(txt)
	{
	 document.getElementById("desc").innerHTML=txt;
	}
	function show(sbbId){
		 $.ajax({
             type: 'POST',
             dataType: "json",
             async: false,
             url : '${ctx}/power/ichnography/getLastData',
             data: {"id":sbbId},
             success : function(data){
            	 $("#mlist").empty();
           
                 	if(data.data!=null&&data.data.length!=0){
                 		var html="  <table class=\"table table-striped table-bordered table-condensed\"><tr>";
                 		 html=html+"<td>设备名称</td><td>最后获取时间</td><th>温度A</th><th>温度B</th><th>温度C</th><th>温度N</th><th>电流A</th><th>电流B</th><th>电流C</th><th>电压A</th><th>电压B</th><th>电压C</th><th>电度</th><th>漏电</th></tr>";
                 		
                 			 var str="<tr>";
                 			 if(typeof(data.data['sbbName'])=="undefined"){
                                 str= str+"<td>-</td>"
                             }else
                             str= str+"<td>"+data.data['sbbName']+"</td>";
                 			 if(typeof(data.data['createDate'])=="undefined"){
                                 str= str+"<td>-</td>"
                             }else
                             str= str+"<td>"+data.data['createDate']+"</td>";
                 			 
                 			 if(typeof(data.data['temperatureA'])=="undefined"){
                                 str= str+"<td>-</td>"
                             }else
                             str= str+"<td>"+data.data['temperatureA']+"</td>";
                 			 if(typeof(data.data['temperatureB'])=="undefined"){
                                 str= str+"<td>-</td>"
                             }else
                             str= str+"<td>"+data.data['temperatureB']+"</td>";
                            
                             if(typeof(data.data['temperatureC'])=="undefined"){
                                 str= str+"<td>-</td>"
                             }else
                             str= str+"<td>"+data.data['temperatureC']+"</td>";
							  if(typeof(data.data['temperatureN'])=="undefined"){
                                 str= str+"<td>-</td>"
                             }else
                             str= str+"<td>"+data.data['temperatureN']+"</td>";
							  if(typeof(data.data['currentA'])=="undefined"){
	                                 str= str+"<td>-</td>"
	                             }else
	                             str= str+"<td>"+data.data['currentA']+"</td>";
								  if(typeof(data.data['currentB'])=="undefined"){
	                                 str= str+"<td>-</td>"
	                             }else
	                             str= str+"<td>"+data.data['currentB']+"</td>";
	 if(typeof(data.data['currentC'])=="undefined"){
	                                 str= str+"<td>-</td>"
	                             }else
	                             str= str+"<td>"+data.data['currentC']+"</td>";
	 if(typeof(data.data['voltageA'])=="undefined"){
         str= str+"<td>-</td>"
     }else
     str= str+"<td>"+data.data['voltageA']+"</td>";
	  if(typeof(data.data['voltageB'])=="undefined"){
         str= str+"<td>-</td>"
     }else
     str= str+"<td>"+data.data['voltageB']+"</td>";
if(typeof(data.data['voltageC'])=="undefined"){
         str= str+"<td>-</td>"
     }else
     str= str+"<td>"+data.data['voltageC']+"</td>";
if(typeof(data.data['electricalDegree'])=="undefined"){
    str= str+"<td>-</td>"
}else
str= str+"<td>"+data.data['electricalDegree']+"</td>";
 if(typeof(data.data['leakageElectricity'])=="undefined"){
    str= str+"<td>-</td>"
}else
str= str+"<td>"+data.data['leakageElectricity']+"</td>";
                             str= str+"</tr>";
                 		html=html+str+"</table>";
                 		$("#mlist").html(html);
                 		
                 			 $('#myModal').modal('show');
                 		
                 	}else{
                 		 $('#myModal').modal('hide');
                 		
                 	}
             }
         });
		 $('#myModal').modal('show');
	}
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
	<img src="${ichnographyEntity.url}" border="0" usemap="#planetmap" alt="Planets" style="width:680px;height:368px;" />
   <map name="planetmap" id="planetmap">
   <c:forEach items="${deviceList}" var="device" varStatus="vs">
      <area   style="color:red" shape="rect" coords="${device.xAxesStart},${device.yAxesStart},${device.xAxesEnd},${device.yAxesEnd}" onmouseover="writeText('${device.sbbName}<br/>经度:${device.longitude}<br/>纬度:${device.latitude}<br/>设备类型:${device.sbbType}<br/>安装日期:${device.installDate}')"
      href="#" title="wqeqwe"  alt="rect_red" onclick="show('${device.sbbId}')" />
   </c:forEach>
   </map>
   <p id="desc">
</p>
<div style="width: 900px;display:none;left:30%" class="modal fade" id="myModal"  tabindex="-2" role="dialog" aria-labelledby="myModalLabel" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">数据</h4>
            </div>
            <div id="mlist" class="modal-body" style="height: 200px">
                <table class="table table-striped table-bordered table-condensed"><tr>
                    <td>编号</td><td>设备名称</td><td>设备类型</td><td>报警时间</td><td>报警类型</td>
                    <td>处理</td>
                </tr><tr>
                    <td><label>1</label></td><td><label id="senderName"></label></td><td ><label id="senderUserName"></label></td>
                    <td><label id="senderUserLoginName"></label></td><td><label id="identity"></label>

                </tr></table>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal  -->
</div>
</body>
</html>