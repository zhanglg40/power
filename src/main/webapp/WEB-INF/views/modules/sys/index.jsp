<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>首页</title>
	<!-- <meta name="decorator" content="default"/> -->
	<script type="text/javascript">

	</script>
</head>
<link href="${ctxStatic}/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="${ctxStatic}/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL PLUGIN STYLES -->
<link href="${ctxStatic}/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/layout2/css/layout.css" rel="stylesheet" type="text/css"/>
<%-- <link href="${ctxStatic}/layout2/css/themes/grey.css" rel="stylesheet" type="text/css" id="style_color"/> --%>
<link href="${ctxStatic}/layout2/css/custom.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=QjyPWS1Y9fbRaCnAPsgUeWRjHeUpEUDS"></script>
<body>
	<h5 class="page-title">
				首页</h5>
		<div class="row">
			<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
				<!-- BEGIN DASHBOARD STATS -->
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<a class="dashboard-stat dashboard-stat-light blue-soft" href="#">
						<div class="visual">
							<i class="fa fa-comments"></i>
						</div>
						<div class="details">
							<div class="more">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${user.name}
							</div>
							
							<div class="desc">
								上次登录时间： <fmt:formatDate value="${user.oldLoginDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
							</div>
							<div class="desc">
								上次登录IP： ${user.oldLoginIp}
							</div>
						</div>
						</a>
					</div>
				<div class="col-lg-4 col-md-4  col-sm-4 col-xs-4">
						<a class="dashboard-stat dashboard-stat-light red-soft" href="#">
						<div class="visual">
							<i class="fa fa-trophy"></i>
						</div>
						<div class="details">
							<div class="number">

							</div>
							<div class="desc">

							</div>
						</div>
						</a>
					</div>
					<%--<div class="col-lg-4 col-md-4  col-sm-4 col-xs-4">
                    <a class="dashboard-stat dashboard-stat-light green-soft" href="#">
                    <div class="visual">
                        <i class="fa fa-shopping-cart"></i>
                    </div>
                    <div class="details">
                        <div class="number">
                             1
                        </div>
                        <div class="desc">
                             今日访问用户
                        </div>
                    </div>
                    </a>
                </div>--%>

				</div>
				<!-- END DASHBOARD STATS -->
				<div class="clearfix">
				</div>
			    <div class="row">
					<div class="col-md-6 col-sm-6">
						<!-- BEGIN PORTLET-->
						<div class="portlet light calendar ">
							<div class="portlet-title ">
								<div class="caption">
									<i class="icon-calendar font-green-sharp"></i>
									<span class="caption-subject font-green-sharp bold uppercase">基础信息</span>
								</div>
							</div>
							<div class="portlet-body">
								<div class="form-group">
											<label class="col-md-5 control-label">电力设备数量：</label>
											<div class="col-md-7">
												<span class="help-inline">
												${deviceCount} </span>
											</div>
										</div>
											<div class="form-group">
											<label class="col-md-5 control-label">粉尘设备数量：</label>
											<div class="col-md-7">
												<span class="help-inline">
												${FCCount}  </span>
											</div>
										</div>
											<div class="form-group">
											<label class="col-md-5 control-label">水位设备数量：</label>
											<div class="col-md-7">
												<span class="help-inline">
												${SWCount} </span>
											</div>
										</div>
							</div>
						</div>
						<!-- END PORTLET-->
					</div>
					<div class="col-md-6 col-sm-6">
					<div class="portlet light calendar ">
					    <div class="portlet-title">
								<div class="caption">
									<i class="icon-share font-red-sunglo"></i>
									<span class="caption-subject font-red-sunglo bold uppercase">地图</span>
								</div>
								<div class="actions">
								
								</div>
							</div>
							<div class="portlet-body">
							  <div id="allmap" style="width:100%;height:200px"></div>
							</div>
						<!-- <div id="allmap" style="width:100px;height:100px"></div> -->
						</div>
					</div>
				</div>
				<div class="clearfix">
				</div>
				<div class="row">
					<div class="col-md-12 col-sm-12">
					<div class="portlet light calendar ">
					    <div class="portlet-title">
								<div class="caption">
									<span class="caption-subject font-blue-sunglo bold uppercase">实时数据</span>
								</div>
								<div class="actions">
								
								</div>
							</div>
							<div class="portlet-body">
							 <div id="container" style="min-width: 310px; height: 200px; margin: 0 auto"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
			   <div class="portlet-title">
								<div class="caption">
									<i class="icon-bubble font-red-sunglo"></i>
									<span class="caption-subject font-red-sunglo bold uppercase">最近10次报警</span>
								</div>
								
							</div>
		                    	<ul class="chats">
		                    		<c:forEach items="${alertList}" var="aler">
										<li class="in">
											<div class="message">
												<span class="arrow">
												</span>
												<a href="#" class="name">
												${aler.sbbName}  </a>
												<span class="datetime">
												<fmt:formatDate value="${aler.alertTime}" type="both"/></span>
												<span class="body">
												${aler.alertType} </span>
											</div>
										</li>
										</c:forEach>
									</ul>
			</div>
		</div>
</body>
</html>
<script src="${ctxStatic}/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${ctxStatic}/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>

<script src="${ctxStatic}/highchart/highcharts.src.js" type="text/javascript"></script>
<script src="${ctxStatic}/highchart/modules/exporting.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例
	map.centerAndZoom(new BMap.Point(120.634, 28.275), 11); map.centerAndZoom(new BMap.Point(120.634, 28.275), 11);   // 初始化地图,设置中心点坐标和地图级别
	//添加地图类型控件
	map.addControl(new BMap.MapTypeControl({
		mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]}));	  
	map.setCurrentCity("永嘉县");           // 设置地图显示的城市 此项是必须设置的
	// map.enableScrollWheelZoom(false);     //开启鼠标滚轮缩放
});
</script>
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