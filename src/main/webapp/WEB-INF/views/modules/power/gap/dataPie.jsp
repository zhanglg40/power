<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>日统计</title>

    <meta name="decorator" content="default"/>
    <script form="text/javascript">
    $(function () {
    	var data=${data};

        $('#container').highcharts({
            chart: {
                type: 'pie',
                options3d: {
    				enabled: true,
                    alpha: 45,
                    beta: 0
                }
            },
            title: {
                text: '数据构成对比'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    depth: 35,
                    dataLabels: {
                        enabled: true,
                        format: '{point.name}'
                    }
                }
            },
            series: [{
                type: 'pie',
                name: '设备',
                data: data
            }]
        });
    });

    </script>
</head>
<body>

<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/power/gap/dataPie">数据构成对比</a></li>
   
    

</ul>

  
<form:form id="searchForm" name="searchForm" modelAttribute="showDataEntity" action="${ctx}/power/gap/DataPie" method="post"
           class="breadcrumb form-search ">
           <ul class="ul-form">
 <li><label class="control-label">归属区域:</label></li>
			 <li>
             <sys:treeselect id="area" name="area.id" value="${office.area.id}" labelName="area.name" labelValue="${office.area.name}"
					title="区域" url="/sys/area/treeData"  cssClass="input-mini required"/>
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