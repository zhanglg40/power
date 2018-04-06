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
        	  $("#monitoringType").change(function () {
        		  var id = $(this).val();
        		  $.ajax({
                      type: "POST",
                      url:"${ctx}/deviceApi/list",
                      data:{"monitoringType":id},// 你的formid
                      dataType:"json",
                      async: false,
                      error: function(request) {
                          alert("Connection error");
                      },
                      success: function(data) {
                          var list = data;
                          var code = "";
                          for (i = 0; i<list.length; i++) {
                              code = code + "<option value='"+ list[i].sbbId + "'>" + list[i].sbbName+"</option>";
                          }
                          console.log(code);
                          $("#sbbId").empty();
                          $("#sbbId").append(code);
                         
                      }
                  });
        		 
        		
        	    });
        	var data=${data};
            var time='${monitoringData.dateTo}';
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
        	                        var series1 = this.series[1];
        	                       var i=0;
        	                        setInterval(function() {
        	                        
        	                            $.ajax({
        	                                type: 'POST',
        	                                dataType: "json",
        	                                async: false,
        	                                url : '${ctx}/monitoring/monitoringData/getData',
        	                                data:{'time':time,'sbbId':$('#sbbId1').val()},
        	                                success : function(data){
        	                                    	time=data.time;
        	                                    	
        	                                    	for(var i=0;i<data.data.length;i++){
        	                                    	
        	                                    		if(data.data[i].type=="1"){
        	                                    			series.addPoint([data.data[i].x, data.data[i].y], true, true);
        	                                    		}else{
        	                                    			series1.addPoint([data.data[i].x, data.data[i].y], true, true);
        	                                    		}
        	                                    	}
        	                                	 
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
        	                text: '其他数据实时数据监测图'
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
    <li class="active"><a href="${ctx}/monitoring/monitoringData/line"> 实时数据监测</a></li>
   
      

</ul> --%>
 
  <div style="margin-top:3%"/>
<form:form id="searchForm" name="searchForm" modelAttribute="monitoringData" action="${ctx}/monitoring/monitoringData/line" method="post"
           class="breadcrumb form-search ">

     <table style="margin-top: 10px" class="table table-striped  table-condensed">
        <tr>
        <td>
        <label>监测类型：</label>
        <form:select path="monitoringType" class="input-large">
              <form:options items="${fns:getDictList('monitoring_type')}"
                itemLabel="label" itemValue="value" htmlEscape="false" />
             </form:select> 
        </td>
         <td><label>设备名称：</label> <form:select path="sbbId" class="input-medium" ismultiple="true">
                   <%--  <form:option value="" label="请选择" /> --%>
                    <form:options items="${deviceList}"
                        itemLabel="sbbName" itemValue="sbbId" htmlEscape="false" />
                </form:select> </td>
       
       
   <td>  <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></td>
  </tr>  </table>
</form:form>
 <div class="clearfix"></div>
<sys:message content="${message}"/>
<input id="sbbId1" value="${monitoringData.sbbId}" type="hidden" htmlEscape="false" maxlength="50"/>
<div class="clearfix"></div>
<div id="container" style="min-width: 310px; height: 300px; margin: 0 auto"></div>

</body>
</html>