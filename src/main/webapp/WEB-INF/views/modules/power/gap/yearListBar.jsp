<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>年统计</title>
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
        	$('#dayFrom').val('${gapEntity.dayFrom}');
        	$('#dayTo').val('${gapEntity.dayTo}');
            var hour='${dayList}';
          var hourList=hour.split(',');
        
           // var hourList=['2017-01-01','2017-01-02','2017-01-02'];
            var gaps='${gapList}';
            var gapList=gaps.split(',');
            var dataIntArr=[];//保存转换后的整型字符串  
            
            //方法一  
            gapList.forEach(function(data,index,arr){  
                dataIntArr.push(+data);  
            });  
           // alert(dataIntArr);
            $('#container').highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: '用电量年趋势图-柱状图'
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
                    ,
                    plotBands: [{ // visualize the weekend
                        from: 0,
                        to: 24,
                        color: '#FFE4E1'
                    }]
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
                series: [{
                    name: '${sbbName}',
                    color:'#D2691E',
                    data:dataIntArr
                }]
            });
        });

    </script>
</head>
<body>

<ul class="nav nav-tabs">
       <li ><a href="${ctx}/power/gap/yearList/Line">设备年统计-曲线图</a></li>
   
        <li class="active"><a href="${ctx}/power/gap/yearList/Bar">设备年统计-柱状图</a></li>

</ul>
 
  
<form:form id="searchForm" name="searchForm" modelAttribute="gapEntity" action="${ctx}/power/gap/yearList/${showType}" method="post"
           >

    <table style="margin-top: 10px">
    <tr>
        <td style="width:80px;text-align: right"><label>设备名称：</label></td><td> <form:select path="sbbId" class="input-medium" ismultiple="true">

                    <form:options items="${deviceList}"
                        itemLabel="sbbName" itemValue="sbbId" htmlEscape="false" />
                </form:select> </td>
        <td style="width:80px;text-align: right"><label>日期：&nbsp;&nbsp;</label></td><td><input id="dayFrom" name="dayFrom" type="text" readonly="readonly" maxlength="20" style="height:30px;margin-top:7px" class="Wdate"
                  onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
        --
        <input id="dayTo" name="dayTo" type="text" readonly="readonly" maxlength="20" style="height:30px;margin-top:7px" class="Wdate"
                  onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
        </td>
       <td class="btns">&nbsp;&nbsp;
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
        </td>
       </tr>
    </table>
</form:form>
<hr>
 <div class="clearfix"></div>
<sys:message content="${message}"/>
<div class="clearfix"></div>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

</body>
</html>