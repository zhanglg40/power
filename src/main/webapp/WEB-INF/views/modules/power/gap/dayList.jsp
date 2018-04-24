<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>日统计</title>
<link rel="stylesheet" href="${ctxStatic}/bootstrap/3.3.5/css/bootstrap.css">
    <script src="${ctxStatic}/highchart/modules/exporting.js"></script>
    <meta name="decorator" content="default"/>
    <script form="text/javascript">
        var type='${type}';
        function page() {
          
            $("#searchForm").attr("action", "${ctx}/power/gap/dayList?type="+type);
            $("#searchForm").submit();
            return false;
        }

        $(function () {
        	$('#sel'+type).addClass("active");
        	$('#checkDate').val('${gapEntity.checkDate}');
        	
            var hour='${hourList}';
            var hourList=hour.split(',');
            var gaps='${gapList}';
            var gapList=gaps.split(',');
            var dataIntArr=[];//保存转换后的整型字符串  
            
            //方法一  
            gapList.forEach(function(data,index,arr){  
                dataIntArr.push(+data);  
            });  
           // alert(dataIntArr);
            var chart=$('#container').highcharts({
                chart: {
                    type: '${showtype}'
                },
                title: {
                    text: '用电量日趋势图-${view}'
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
                    categories:
                        hourList

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
                }
            },function(c){
                var sbbType=$('#sbbType').val();
                switch (sbbType){
                    case 'temperature':
                        c.yAxis[0].setTitle({text:'温度(摄氏度)'});
                        getDataList(c,$('#sbbId').val(),$('#checkDate').val(),'temperatureA','温度A');
                        getDataList(c,$('#sbbId').val(),$('#checkDate').val(),'temperatureB','温度B');
                        getDataList(c,$('#sbbId').val(),$('#checkDate').val(),'temperatureC','温度C');
                        getDataList(c,$('#sbbId').val(),$('#checkDate').val(),'temperatureN','温度N');
                        break;
                    case 'current':
                        c.yAxis[0].setTitle({text:'电流(安培)'});
                        getDataList(c,$('#sbbId').val(),$('#checkDate').val(),'currentA','电流A');
                        getDataList(c,$('#sbbId').val(),$('#checkDate').val(),'currentB','电流B');
                        getDataList(c,$('#sbbId').val(),$('#checkDate').val(),'currentC','电流C');
                        break;
                    case 'voltage':
                        c.yAxis[0].setTitle({text:'电压(伏特)'});
                        getDataList(c,$('#sbbId').val(),$('#checkDate').val(),'voltageA','电压A');
                        getDataList(c,$('#sbbId').val(),$('#checkDate').val(),'voltageB','电压B');
                        getDataList(c,$('#sbbId').val(),$('#checkDate').val(),'voltageC','电压C');
                        break;

                    case 'electricalDegree':
                        c.yAxis[0].setTitle({text:'用电量(度)'});
                        getDataList(c,$('#sbbId').val(),$('#checkDate').val(),'electricalDegree','电度');
                        break;
                    case 'leakageElectricity':
                        c.yAxis[0].setTitle({text:'漏电流(毫安培)'});
                        getDataList(c,$('#sbbId').val(),$('#checkDate').val(),'leakageElectricity','漏电流');
                        break;
                    case 'activePower':
                        c.yAxis[0].setTitle({text:'有功功率(瓦)'});
                        getDataList(c,$('#sbbId').val(),$('#checkDate').val(),'activePower','有功功率');
                        break;
                    case 'powerFactor':
                        c.yAxis[0].setTitle({text:'功率因数'});
                        getDataList(c,$('#sbbId').val(),$('#checkDate').val(),'powerFactor','功率因数');
                        break;
                }

            });

        });
        function getDataList(chart,sbbId,dayto,sbbType,title) {
            $.ajax({
                type: 'POST',
                dataType: "json",
                async: false,
                data:{'sbbId':sbbId,'dateTo':dayto,'itemType':sbbType},
                url : '${api}/gap/dayList',

                success : function(data){
                    var placelist=[];

                    for(var i=0;i<data.data.length;i++){
                        placelist.push([data.data[i].time,parseFloat(data.data[i].value)])
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

<%-- <ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/power/gap/dayList">日统计-曲线图</a></li>
   
        <li><a href="${ctx}/power/gap/dayListBar">日统计-柱状图</a></li>

</ul> --%>
 <div style="margin-top:3%"/>
  
<form:form id="searchForm" name="searchForm" modelAttribute="gapEntity" action="${ctx}/power/gap/dayList" method="post"
           >

    <table style="margin-top: 10px;background: #f5f5f5" class="table table-striped  table-condensed">
    <tr>
     <td style="width:20%;text-align:center">
    		<div class="btn-group" data-toggle="buttons">
				<a class="btn grey-steel btn-sm" href="/power_cloud_manager/a/power/gap/monthList/Line">
				  <input name="options" class="toggle" id="option1" type="radio">&nbsp年统计&nbsp</a>
				<a class="btn grey-steel btn-sm" href="/power_cloud_manager/a/power/gap/monthList/Line">
				  <input name="options" class="toggle" id="option2" type="radio">&nbsp月统计&nbsp
				  </a>
				 <label class="btn grey-steel btn-sm active">
				  <input name="options" class="toggle" id="option3" type="radio">&nbsp日统计&nbsp</label>
				</div>
			</td>

        <td style="width:27%;text-align: right"><label>日期：&nbsp;&nbsp;</label><input id="checkDate" name="checkDate" type="text" readonly="readonly" maxlength="20" style="height:30px;" class="Wdate"
                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
        </td>
             <td style="width:23%;text-align:center">
    		<div class="btn-group" data-toggle="buttons">
				<a id="sel1" class="btn grey-steel btn-sm" onclick="sel('1')">
				  <input name="options" class="toggle" id="line" type="radio">&nbsp曲线图&nbsp</a>
				<a id="sel2"  class="btn grey-steel btn-sm" onclick="sel('2')">
				  <input name="options" class="toggle" id="bar" type="radio">&nbsp柱状图&nbsp</a>
			</div>
			</td>
       <td class="btns">&nbsp;&nbsp;
            <input id="btnSubmit" class="btn btn-primary" onclick="page()" type="submit" value="查询" />
        </td>
       </tr>
        <tr>
            <td style="width:23%"><label>设备名称：</label> <form:select path="sbbId" class="input-medium" ismultiple="true">
                <form:options items="${deviceList}"
                              itemLabel="sbbName" itemValue="sbbId" htmlEscape="false" />
            </form:select> </td>
            <td style="width:23%;text-align: right"><label>类型：</label>
                <form:select path="sbbType" >
                <form:options items="${fns:getDictList('power_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
            </form:select></td>
            <td></td><td></td>
        </tr>
    </table>
</form:form>
<hr>
 <div class="clearfix"></div>
<sys:message content="${message}"/>
<div class="clearfix"></div>
<div style="width: 9410px;overflow:auto;position:absolute; height:400px; ">
<div id="container" style="min-width: 810px; height: 300px; margin: 0 auto"></div>
</div>
</body>
</html>