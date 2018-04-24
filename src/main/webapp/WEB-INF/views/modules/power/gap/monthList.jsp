<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>日统计</title>
<link rel="stylesheet" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <script src="${ctxStatic}/highchart/modules/exporting.js"></script>
    <meta name="decorator" content="default"/>
    <script form="text/javascript">
    var type='${type}';
        function page() {
           
            $("#searchForm").attr("action", "${ctx}/power/gap/monthList/Line?type="+type);
            $("#searchForm").submit();
            return false;
        }

        $(function () {
        	$('#sel'+type).addClass("active");
        	$('#dayFrom').val('${gapEntity.dayFrom}');
        	$('#dayTo').val('${gapEntity.dayTo}');
            var gaps='${gapList}';
            var gapList=gaps.split(',');

            

            $('#container').highcharts({
                chart: {
                    type: '${showtype}'
                },
                title: {
                    text: '用电量月趋势图-${view}'
                },
                legend: {
                    layout: 'vertical',
                    align: 'left',
                    verticalAlign: 'top',
                    x: 150,
                    y: 100,
                    floating: true,
                    borderWidth: 1,
                    backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
                },
                xAxis: {
                    categories:[]
                      //  hourList

                },
                yAxis: {
                    title: {
                        text: '用电量'
                    }
                },
                tooltip: {
                    shared: true,
                    valueSuffix: ' 度'
                },
                credits: {
                    enabled: false
                },
                plotOptions: {
                    areaspline: {
                        fillOpacity: 0.5
                    }
                },
                series: []
            },function(c){
                var sbbType=$('#sbbType').val();
                switch (sbbType){
                    case 'temperature':
                        c.yAxis[0].setTitle({text:'温度(摄氏度)'});
                        getDataList(c,$('#sbbId').val(),$('#dayFrom').val(),$('#dayTo').val(),'temperatureA','温度A');
                        getDataList(c,$('#sbbId').val(),$('#dayFrom').val(),$('#dayTo').val(),'temperatureB','温度B');
                        getDataList(c,$('#sbbId').val(),$('#dayFrom').val(),$('#dayTo').val(),'temperatureC','温度C');
                        getDataList(c,$('#sbbId').val(),$('#dayFrom').val(),$('#dayTo').val(),'temperatureN','温度N');
                        break;
                    case 'current':
                        c.yAxis[0].setTitle({text:'电流(安培)'});
                        getDataList(c,$('#sbbId').val(),$('#dayFrom').val(),$('#dayTo').val(),'currentA','电流A');
                        getDataList(c,$('#sbbId').val(),$('#dayFrom').val(),$('#dayTo').val(),'currentB','电流B');
                        getDataList(c,$('#sbbId').val(),$('#dayFrom').val(),$('#dayTo').val(),'currentC','电流C');
                        break;
                    case 'voltage':
                        c.yAxis[0].setTitle({text:'电压(伏特)'});
                        getDataList(c,$('#sbbId').val(),$('#dayFrom').val(),$('#dayTo').val(),'voltageA','电压A');
                        getDataList(c,$('#sbbId').val(),$('#dayFrom').val(),$('#dayTo').val(),'voltageB','电压B');
                        getDataList(c,$('#sbbId').val(),$('#dayFrom').val(),$('#dayTo').val(),'voltageC','电压C');
                        break;

                    case 'electricalDegree':
                        c.yAxis[0].setTitle({text:'用电量(度)'});
                        getDataList(c,$('#sbbId').val(),$('#dayFrom').val(),$('#dayTo').val(),'electricalDegree','电度');
                        break;
                    case 'leakageElectricity':
                        c.yAxis[0].setTitle({text:'漏电流(毫安培)'});
                        getDataList(c,$('#sbbId').val(),$('#dayFrom').val(),$('#dayTo').val(),'leakageElectricity','漏电流');
                        break;
                    case 'activePower':
                        c.yAxis[0].setTitle({text:'有功功率(瓦)'});
                        getDataList(c,$('#sbbId').val(),$('#dayFrom').val(),$('#dayTo').val(),'activePower','有功功率');
                        break;
                    case 'powerFactor':
                        c.yAxis[0].setTitle({text:'功率因数'});
                        getDataList(c,$('#sbbId').val(),$('#dayFrom').val(),$('#dayTo').val(),'powerFactor','功率因数');
                        break;
                }
            });
        });
    function getDataList(chart,sbbId,dateFrom,dayto,sbbType,title) {
        $.ajax({
            type: 'POST',
            dataType: "json",
            async: false,
            data:{'sbbId':sbbId,'dateFrom':dateFrom,'dateTo':dayto,'itemType':sbbType},
            url : '${api}/gap/monthList',

            success : function(data){
                var placelist=[];

                for(var i=0;i<data.data.length;i++){
                    placelist.push([data.data[i].day,parseFloat(data.data[i].value)])
                }
                chart.addSeries({
                    name:title,
                    data:placelist
                });

            }
        })
    }


        function sel(id){
        	if(id==type){
        		$('#sel'+type).removeClass("active");
        		return;
        	}
        	$('#sel'+type).removeClass("active");
        	//$('#sel'+id).addClass("active");
        	type=id;
        }
    </script>
         <style type="text/css">
      .grey-steel {border:1px solid #7AC5CD;}

    </style>
</head>
<body>

  <div style="margin-top:3%"/>
  
<form:form id="searchForm" name="searchForm" modelAttribute="gapEntity" action="${ctx}/power/gap/monthList/${showType}" method="post"
           >

    <table style="margin-top: 10px;background: #f5f5f5" class="table table-striped  table-condensed">
    <tr>
      <td style="width:30%; ">
    		<div class="btn-group" data-toggle="buttons">
				<a class="btn grey-steel btn-sm" href="/power_cloud_manager/a/power/gap/yearList/Line">
				  <input name="options" class="toggle" id="option1" type="radio">&nbsp年统计&nbsp</a>
				
				<a class="btn grey-steel btn-sm active" >
				  <input name="options" class="toggle" id="option2" type="radio">&nbsp月统计&nbsp
				  </a>
				 
				 <a class="btn grey-steel btn-sm" href="/power_cloud_manager/a/power/gap/dayList">
				 
				  <input name="options" class="toggle" id="option3" type="radio">&nbsp日统计&nbsp</a>
				</div>
			</td>
        <td style="width:30%; "><label>设备名称：</label> <form:select path="sbbId" class="input-medium" ismultiple="true">
                    <form:option value="" label="请选择" />
                    <form:options items="${deviceList}"
                        itemLabel="sbbName" itemValue="sbbId" htmlEscape="false" />
                </form:select> </td>

        <td style="width:23%; ">
    		<div class="btn-group" data-toggle="buttons">
				<a id="sel1" class="btn grey-steel btn-sm" onclick="sel('1')">
				  <input name="options" class="toggle" id="line" type="radio">&nbsp曲线图&nbsp</a>
				<a id="sel2"  class="btn grey-steel btn-sm" onclick="sel('2')">
				  <input name="options" class="toggle" id="bar" type="radio">&nbsp柱状图&nbsp</a>
			</div>
			</td>

       </tr>
        <tr>
            <td style="width:23%; "><label>类型：</label>
                <form:select path="sbbType" >
                    <form:options items="${fns:getDictList('power_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
                </form:select></td>
            <td style="width:80px; "><label>选择月份：&nbsp;&nbsp;</label>
            <input id="dayFrom" name="dayFrom" type="text" readonly="readonly" maxlength="20" style="height:30px;width:110px" class="Wdate"
                                                                                                     onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
        </td>       <td class="btns">&nbsp;&nbsp;
            <input id="btnSubmit" class="btn btn-primary" type="submit" onclick="page()"  value="查询" />
        </td>
        </tr>
    </table>
</form:form>
<hr>
 <div class="clearfix"></div>
<sys:message content="${message}"/>
<div class="clearfix"></div>
<div id="container" style="min-width: 310px; height: 300px; margin: 0 auto"></div>

</body>
</html>