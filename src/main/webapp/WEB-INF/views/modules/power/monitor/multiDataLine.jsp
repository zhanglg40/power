<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>日统计</title>
<link rel="stylesheet" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <script src="${ctxStatic}/highchart/modules/exporting.js"></script>
    <meta name="decorator" content="default"/>
    <script form="text/javascript">
        function page(n, s) {
            if (n) $("#pageNo").val(n);
            if (s) $("#pageSize").val(s);
            $("#searchForm").attr("action", "${ctx}/verify/document/list");
            $("#searchForm").submit();
            return false;
        }
        $(function () {
        	var data=${data};
        	 var time='${showDataEntity.dateTo}';
        	 $(document).ready(function() {
        	        Highcharts.setOptions({
        	            global: {
        	                useUTC: false
        	            }
        	        });
        	    
        	        var chart;
        	        $('#container').highcharts({
        	            chart: {
        	                type: 'spline',
        	                animation: Highcharts.svg, // don't animate in old IE
        	                marginRight: 10,
        	                events: {
        	                    load: function() {
        	                    	var serie = this.series;
        	                        // set up the updating of the chart each second
        	                       // var series = this.series[0];
        	                       // var series1 = this.series[1];
        	                        setInterval(function() {
        	                        	
        	                        	 $.ajax({
         	                                type: 'POST',
         	                                dataType: "json",
         	                                async: false,
         	                                url : '${ctx}/power/monitor/getmultiData',
         	                                data:{'time':time,'sbbId':$('#sbbId1').val(),'condition':$('#condition1').val()},
         	                                success : function(data){
         	                                
         	                                	for(var i=0;i<data.data.length;i++){
         	                                		
         	                                		var type=data.data[i].type;
         	                                		
         	                                		time=data.time;
         	                                		
         	                                		var series = serie[type];
         	                                		
            	                                	 series.addPoint([data.data[i].x, data.data[i].y], true, true);
         	                                	}
         	                                    	
         	                                }
         	                            });
        	                        }, 21000);
        	                    }
        	                }
        	            },
        	            title: {
        	                text: '实时数据监测'
        	            },
        	            xAxis: {
        	                type: 'datetime',
        	                tickPixelInterval: 150
        	            },
        	            yAxis: {
        	                title: {
        	                    text: '${title}'
        	                },
        	                plotLines: [{
        	                    value: 0,
        	                    width: 1,
        	                    color: '#808080'
        	                }]
        	            },
        	            tooltip: {
        	                formatter: function() {
        	                        return '<b>'+ this.series.name +'</b><br/>'+
        	                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
        	                        Highcharts.numberFormat(this.y, 2);
        	                }
        	            },
        	            legend: {
        	                enabled: false
        	            },
        	            exporting: {
        	                enabled: false
        	            },
        	            series: data
        	        });
        	    });
        	    
        });

    </script>
</head>
<body>

						
							
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/power/monitor/realTimeDataContrast"> 实时数据对比</a></li>
   
      

</ul>
 
  
<form:form id="searchForm" name="searchForm" modelAttribute="showDataEntity" action="${ctx}/power/monitor/realTimeDataContrast" method="post"
           class="breadcrumb form-search ">

    <table  class="table table-striped table-condensed">
        <tr> <td><label>设备名称：</label> 
                
                 <select id="sbbId" name="sbbId" class="selectpicker" multiple data-hide-disabled="true" data-size="5">
                     <c:forEach items="${deviceList}" var="device">
                     <option value="${device.sbbId}">${device.sbbName}</option>
                      </c:forEach>
                 </td>
        <td>
        <label>维度：</label>
        </td>
       <td>&nbsp;&nbsp;
            <select id="condition" name="condition" class="selectpicker bs-select-hidden"  data-hide-disabled="true"  data-actions-box="true">
  
     <optgroup label="温度">
      <option  value="temperatureA">温度A</option>
      <option value="temperatureB">温度B</option>
      <option value="temperatureC">温度C</option>
      <option value="temperatureN">温度N</option>
    </optgroup>
    <optgroup label="电流">
      <option value="currentA">电流A</option>
      <option value="currentB">电流B</option>
      <option value="currentC">电流C</option>
    </optgroup>
     <optgroup label="电压">
      <option value="voltageA">电压A</option>
      <option value="voltageB">电压B</option>
      <option value="voltageC">电压C</option>
    </optgroup>
    <optgroup label="用电量">
     <option value="electricalDegree">用电量</option>
      <option value="leakageElectricity">漏电</option>
     </optgroup>
  </select>
           
                   </td>
   <td>  <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></td>
    <td rowspan="5"></td></tr>  </table>
</form:form>
 <div class="clearfix"></div>
<sys:message content="${message}"/>
<input id="sbbId1" value="${showDataEntity.sbbCId}" type="hidden" htmlEscape="false" maxlength="50"/>
<input id="condition1" value="${showDataEntity.condition}" type="hidden" htmlEscape="false" maxlength="50"/>
<div class="clearfix"></div>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

</body>
</html>