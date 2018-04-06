<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>日统计</title>
<link rel="stylesheet" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <script src="${ctxStatic}/highchart/modules/exporting.js"></script>
    <meta name="decorator" content="default"/>
    <script form="text/javascript">

        $(function () {
        	 $("#condition").val("${showDataEntity.condition}");
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
        	    
        	                        // set up the updating of the chart each second
        	                        var series = this.series[0];
        	                       var i=0;
        	                        setInterval(function() {
        	                        
        	                            $.ajax({
        	                                type: 'POST',
        	                                dataType: "json",
        	                                async: false,
        	                                url : '${ctx}/power/monitor/getData',
        	                                data:{'time':time,'sbbId':$('#sbbId1').val(),'condition':$('#condition1').val()},
        	                                success : function(data){
        	                                    	time=data.time;
        	                                    	
        	                                	 series.addPoint([data.data[0].x, data.data[0].y], true, true);
        	                                }
        	                            });
        	                           /*  var x = (new Date()).getTime(), // current time
        	                                y = 1,
        	                                z=Math.random();
        	                            series.addPoint([x, y], true, true); */
        	                           
        	                        }, 298000);
        	                    }
        	                }
        	            },
        	            title: {
        	                text: '实时数据监测图'
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

<%-- <ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/power/monitor/realTimeDataLine"> 实时数据监测</a></li>
   
      

</ul> --%>
 <div style="margin-top:3%"/>
  
<form:form id="searchForm" name="searchForm" modelAttribute="showDataEntity" action="${ctx}/power/monitor/realTimeDataLine" method="post"
           class="breadcrumb form-search ">

    <table style="margin-top: 10px" class="table table-striped  table-condensed">
        <tr> <td><label>设备名称：</label> <form:select path="sbbId" class="input-medium" ismultiple="true">

                    <form:options items="${deviceList}"
                        itemLabel="sbbName" itemValue="sbbId" htmlEscape="false" />
                </form:select> </td>
        <td>
        <label>维度：</label>
        </td>
       <td>&nbsp;&nbsp;
            <!-- <select id="condition" name="condition" class="selectpicker bs-select-hidden"  data-hide-disabled="true"  data-actions-box="true">

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
  </select> -->
    <form:select path="condition" class="input-large">
              <form:options items="${fns:getDictList('condition')}"
                itemLabel="label" itemValue="value" htmlEscape="false" />
             </form:select> 
          
                   </td>
   <td>  <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></td>
  </tr>  </table>
</form:form>
 <div class="clearfix"></div>
<sys:message content="${message}"/>
<input id="sbbId1" value="${showDataEntity.sbbId}" type="hidden" htmlEscape="false" maxlength="50"/>
<input id="condition1" value="${showDataEntity.condition}" type="hidden" htmlEscape="false" maxlength="50"/>
<div class="clearfix"></div>
<div id="container" style="min-width: 310px; height: 300px; margin: 0 auto"></div>

</body>
</html>