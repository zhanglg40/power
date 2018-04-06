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
            $('#container').highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: '用电量日趋势图-柱图'
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
                        color: 'rgba(68, 170, 213, .2)'
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
                    data:dataIntArr
                }]
            });
        });

    </script>
</head>
<body>

<ul class="nav nav-tabs">
    <li><a href="${ctx}/power/gap/dayList">日统计-曲线图</a></li>
   
        <li class="active"><a href="${ctx}/power/gap/dayListBar">日统计-柱状图</a></li>

</ul>
  <select id="first-disabled" class="selectpicker bs-select-hidden" multiple data-hide-disabled="true" data-live-search="true" data-actions-box="true">

    <optgroup label="温度">
      <option selected>温度A</option>
      <option>温度B</option>
      <option>温度C</option>
      <option>温度N</option>
    </optgroup>
    <optgroup label="电流">
      <option>电流A</option>
      <option>电流B</option>
      <option>电流C</option>
    </optgroup>
     <optgroup label="电压">
      <option>电压A</option>
      <option>电压B</option>
      <option>电压C</option>
    </optgroup>
    <optgroup >
     <option value="电度">用电量</option>
      <option>漏电</option>
     </optgroup>
  </select>
    <select id="first-disabled2" class="selectpicker" multiple data-hide-disabled="true" data-size="5">
    <option>Apple</option>
    <option>Banana</option>
    <option>Orange</option>
    <option>Pineapple</option>
    <option>Apple2</option>
    <option>Banana2</option>
    <option>Orange2</option>
    <option>Pineapple2</option>
    <option>Apple2</option>
    <option>Banana2</option>
    <option>Orange2</option>
    <option>Pineapple2</option>
  </select>
  
<form:form id="searchForm" name="searchForm" modelAttribute="gapEntity" action="${ctx}/power/gap/dayList" method="post"
           class="breadcrumb form-search ">

    <ul class="ul-form">
        <li> <li><label>设备名称：</label> <form:select path="sbbId" class="input-medium" ismultiple="true">

                    <form:options items="${deviceList}"
                        itemLabel="sbbName" itemValue="sbbId" htmlEscape="false" />
                </form:select> </li></li>
        <li><label>日期：&nbsp;&nbsp;</label><input id="createDate" name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
        </li>
       <li class="btns">&nbsp;&nbsp;
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
        </li>
        <li class="clearfix"></li>
    </ul>
</form:form>

<sys:message content="${message}"/>
<div class="clearfix"></div>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

</body>
</html>