<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>月统计</title>
<link rel="stylesheet" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <script src="${ctxStatic}/highchart/modules/exporting.js"></script>
    <meta name="decorator" content="default"/>
    <script form="text/javascript">
    var type='${type}';
        function page() {
           
            $("#searchForm").attr("action", "${ctx}/monitoring/monitoringData/monthList/Line?type="+type);
            $("#searchForm").submit();
            return false;
        }

        $(function () {
        	$('#sel'+type).addClass("active");
        	$('#dayFrom').val('${entity.dayFrom}');
        	$('#dayTo').val('${entity.dayTo}');
            var val;
            var placelist=[];
            debugger;
            $.ajax({
                type : "POST",
                url : "${api}/other/monthList",
                async:false,
                data:{"sbbId":$('#sbbId').val(),"dateTo":$('#dayTo').val(),"dateFrom":$('#dayFrom').val()},
                //  dataType : "text",
                success : function(data) {
                    val=data;
                    for(var n=0;n<val.data.length;n++){
                        placelist.push([val.data[n].day,parseFloat(val.data[n].value)]);
                    }
                }
            })
            $('#container').highcharts({
                chart: {
                    type: '${showtype}'
                },
                title: {
                    text: '其他设备月趋势图-${view}'
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
                        []
                    ,
                    plotBands: [{ // visualize the weekend
                        from: 0,
                        to: 24,
                        color: '#FDF5E6'
                    }]
                },
                yAxis: {
                    title: {
                        text:  val.mType+'量'
                    }
                },
                tooltip: {
                    shared: true,
                    valueSuffix: ' '
                },
                credits: {
                    enabled: false
                },
                plotOptions: {
                    areaspline: {
                        fillOpacity: 0.5
                    }
                },
                series: [{
                    name: val.areaName,
                    color:'#EEC591',
                    data:placelist
                }]
            });

        });
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
  
<form:form id="searchForm" name="searchForm" modelAttribute="entity" action="${ctx}/monitoring/monitoringData/monthList/${showType}" method="post"
           >

    <table style="margin-top: 10px" class="table table-striped  table-condensed">
    <tr>
      <td style="width:20%;text-align:center">
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
        <td style="width:80px;text-align: right"><label>设备名称：</label></td><td> <form:select path="sbbId" class="input-medium" ismultiple="true">

                    <form:options items="${deviceList}"
                        itemLabel="sbbName" itemValue="sbbId" htmlEscape="false" />
                </form:select> </td>
        <td style="width:80px;text-align: right"><label>日期：&nbsp;&nbsp;</label></td><td><input id="dayFrom" name="dayFrom" type="text" readonly="readonly" maxlength="20" style="height:30px;width:110px" class="Wdate"
                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
        --
        <input id="dayTo" name="dayTo" type="text" readonly="readonly" maxlength="20" style="height:30px;width:110px" class="Wdate"
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