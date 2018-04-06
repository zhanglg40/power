<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>区域-日统计</title>
    <script src="${ctxStatic}/highchart/modules/exporting.js"></script>
    <meta name="decorator" content="default2"/>
    <script form="text/javascript">
    var type='${type}';
    function page() {
        
        $("#searchForm").attr("action", "${ctx}/power/gap/dayArea/Line?type="+type);
        $("#searchForm").submit();
        return false;
    }

        $(function () {
        	$('#sel'+type).addClass("active");
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
                    type: '${viewtype}'
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
        function sel(id){
        	if(id==type){
        		$('#sel'+type).removeClass("active");
        		return;
        	}
        	$('#sel'+type).removeClass("active");
        	//$('#sel'+id).addClass("active");
        	type=id;
        }
        function change(url){
        	this.location.href=url;
        }
    </script>
      <style type="text/css">
      .grey-steel {border:1px solid #7AC5CD;}

    </style>
</head>
<body>

<%-- <ul class="nav nav-tabs">
    <c:if test="${type eq 'areaspline'}"> 
    <li  class="active"><a href="${ctx}/power/gap/yearArea/Line">区域年统计-曲线图</a></li>
   
        <li><a href="${ctx}/power/gap/yearArea/Bar">区域年统计-柱状图</a></li>
</c:if>
    <c:if test="${type eq 'column'}"> 
    <li><a href="${ctx}/power/gap/yearArea/Line">区域年统计-曲线图</a></li>
   
        <li  class="active"><a href="${ctx}/power/gap/yearArea/Bar">区域月统计-柱状图</a></li>
</c:if>
</ul> --%>
  <div style="margin-top:3%"/>
  
<form:form id="searchForm" name="searchForm" modelAttribute="gapEntity" action="${ctx}/power/gap/yearArea/${showType}" method="post"
           >

<table style="margin-top: 10px" class="table table-striped  table-condensed">
    <tr>
      <td style="width:20%;text-align:center">
    		<div class="btn-group" data-toggle="buttons">
				<a class="btn grey-steel btn-sm" onclick="change('/power_cloud_manager/a/power/gap/yearArea/Line')" href="/power_cloud_manager/a/power/gap/monthList/Line">
				  <input name="options" class="toggle" id="option1" type="radio">&nbsp年统计&nbsp</a>
				
				<a class="btn grey-steel btn-sm" onclick="change('/power_cloud_manager/a/power/gap/monthArea/Line')">
				  <input name="options" class="toggle" id="option2" type="radio">&nbsp月统计&nbsp
				  </a>
				 
				 <a class="btn grey-steel btn-sm active">
				 
				  <input name="options" class="toggle" id="option3" type="radio">&nbsp日统计&nbsp</a>
				</div>
			</td>
      <td ><label>区域名称：</label></td><td>
 <sys:treeselect id="area" name="area.id" value="${gapEntity.area.id}" labelName="area.name" labelValue="${gapEntity.area.name}"
					title="区域" url="/sys/area/treeData"  cssClass="input-mini required"/>

        </td>
        <td style="width:80px;text-align: right"><label>日期：&nbsp;&nbsp;</label></td><td><input id="checkDate" name="checkDate" type="text" readonly="readonly" maxlength="20" style="height:25px;" class="Wdate"
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
    </table>
</form:form>
<hr>
 <div class="clearfix"></div>
<sys:message content="${message}"/>
<div class="clearfix"></div>
<div id="container" style="min-width: 310px; height: 300px; margin: 0 auto"></div>

</body>
</html>