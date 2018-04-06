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
                text: '报警数据构成对比'
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

<%-- <ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/power/alert/alertPie">报警数据构成对比</a></li>
   
    

</ul> --%>
 <div style="margin-top:3%"/>
  
<form:form id="searchForm" name="searchForm" modelAttribute="alertEntity" action="${ctx}/power/alert/alertPie" method="post"
           class="breadcrumb form-search ">
           <ul class="ul-form">
 <li><label class="control-label">报警类型:</label></li>
			 <li>
               <form:select path="alertType" class="input-medium" ismultiple="true">
                    <form:option value="" label="请选择" />
                     <form:option value="alert_type" label="报警类型" />
                     <form:option value="sbb_name" label="设备名称" />
                     <form:option value="sbb_type" label="设备类型" />
                     <form:option value="del_flag" label="处理情况" />
                    
                </form:select> 
			</li>
       <li class="btns">&nbsp;&nbsp;
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
        </li>
        <li class="clearfix"></li>
        </ul>
</form:form>
 <div style="margin-top:3%"/>
<sys:message content="${message}"/>
<div class="clearfix"></div>
<div id="container" style="min-width: 310px; height: 300px; margin: 0 auto"></div>

</body>
</html>