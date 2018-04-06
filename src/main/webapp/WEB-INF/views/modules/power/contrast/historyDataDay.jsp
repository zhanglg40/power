<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>日统计</title>
<link rel="stylesheet" href="${ctxStatic}/bootstrap/3.3.5/css/bootstrap.css">
<script src="${ctxStatic}/highchart/modules/data.js"></script>
    <script src="${ctxStatic}/highchart/modules/exporting.js"></script>
    <meta name="decorator" content="default"/>
    <style type="text/css">
		#searchForm { 
		font:12px verdana, arial, sans-serif; /* 设置文字大小和字体样式 */
		}
		#searchForm, #searchForm li {
		list-style:none; /* 将默认的列表符号去掉 */
		padding:0; /* 将默认的内边距去掉 */
		margin:0; /* 将默认的外边距去掉 */
}

</style>
    <script form="text/javascript">
        function page(n, s) {
            if (n) $("#pageNo").val(n);
            if (s) $("#pageSize").val(s);
            $("#searchForm").attr("action", "${ctx}/verify/document/list");
            $("#searchForm").submit();
            return false;
        }

        $(function () {
        	$('#dateFrom').val('${historyDataEntity.dateFrom}');
        	
          
         
            
           
            $('#container').highcharts({
                data: {
                    table: document.getElementById('datatable')
                },
                chart: {
                    type: 'spline'
                },
                title: {
                    text: '历史数据对比-曲线图'
                },
                yAxis: {
                    allowDecimals: false,
                    title: {
                        text: 'Units'
                    }
                },
                tooltip: {
                    formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                            this.point.y +' '+ this.point.name.toLowerCase();
                    }
                }
            });
        });

    </script>
</head>
<body>

<ul class="nav nav-tabs" id="menu">
    <li class="active"><a href="${ctx}/power/contrast/historyData">历史数据对比-曲线图</a></li>
   
        <li><a href="${ctx}/power/contrast/historyData/listBar">历史数据对比-柱状图</a></li>

</ul>
 

<form:form id="searchForm" name="searchForm" modelAttribute="historyDataEntity" action="${ctx}/power/contrast/historyData/dayList" method="post" >

    <ul class="ul-form">
    
         <li style="margin-top:20px;"><label>设备名称：</label> <select id="sbbList" name="sbbList" class="selectpicker" multiple data-hide-disabled="true" data-size="5">
                     <c:forEach items="${deviceList}" var="device">
                     <option value="${device.sbbId}">${device.sbbName}</option>
                      </c:forEach>
                <select> <label>维度：</label>
         <select id="condition" name="condition" class="selectpicker bs-select-hidden"  data-hide-disabled="true"  data-actions-box="true">

    <optgroup label="温度">
      <option  value="temperatureA" selected>温度A</option>
      <option value="temperatureB">温度B</option>
      <option value="temperatureC">温度C</option>
      <option value="temperatureN">温度N</option>
    </optgroup>
    <optgroup label="电流">
      <option value="电流A">电流A</option>
      <option value="电流B">电流B</option>
      <option value="电流C">电流C</option>
    </optgroup>
     <optgroup label="电压">
      <option value="电压A">电压A</option>
      <option value="电压B">电压B</option>
      <option value="电压C">电压C</option>
    </optgroup>
    <optgroup label="用电量">
     <option value="电度">用电量</option>
      <option value="leakageElectricity">漏电</option>
     </optgroup>
  </select>
           <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
         </li>       
                
                
        <li style="margin-top:20px;"><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期：&nbsp;&nbsp; </label><input id="dateFrom" name="dateFrom" type="text" readonly="readonly" maxlength="30" class="Wdate"
              style="height:34px;width:220px"     onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                 <label> &nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;</label><input id="dateTo" name="dateTo" type="text" readonly="readonly" maxlength="20" class="Wdate"
                 style="height:34px;width:220px"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
       
          
        </li>
        <li class="clearfix"></li>
    </ul>
</form:form>

<sys:message content="${message}"/>
<div class="clearfix"></div>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
<table id="datatable" hidden>
 <thead>
  <c:forEach items="${listth}" var="th">
     <th>${th}</th>
  </c:forEach>
 </thead>
   <c:forEach items="${tdList}" var="td" >
     <tr>
       <c:forEach items="${td}" var="item">
       <td>${item}</td>
       </c:forEach>
    </tr>
  </c:forEach>
</table>
</body>
</html>