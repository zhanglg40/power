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
        var chart,
            categories = ['温度A', '温度B', '温度C', '温度N',
                          ];
        categories1 = ['电流A', '电流B', '电流C'];
        categories2 = ['电压A', '电压B', '电压C'];
        var tmp='${tmpList}';
        var tmpList=tmp.split(',');
      
        var tmp1='${tmp1List}';
        var tmp1List=tmp1.split(',');
        var vol='${volsList}';
        var volsList=vol.split(',');
        var vol1='${vols1List}';
        var vols1List=vol1.split(',');
        var curs='${cursList}';
        var cursList=curs.split(',');
        var curs1='${curs1List}';
        var curs1List=curs1.split(',');
       
        var dataTmp=[];//保存转换后的整型字符串  
        var data1Tmp=[];
        var dataVol=[];
        var data1Vol=[];
        var dataCur=[];
        var data1Cur=[];
        //方法一  
        tmpList.forEach(function(data,index,arr){  
        	dataTmp.push(+data);  
        });  
        tmp1List.forEach(function(data,index,arr){  
        	data1Tmp.push(+data);  
        }); 
        volsList.forEach(function(data,index,arr){  
        	dataVol.push(+data);  
        });  
        vols1List.forEach(function(data,index,arr){  
        	data1Vol.push(+data);  
        }); 
        cursList.forEach(function(data,index,arr){  
        	dataCur.push(+data);  
        });  
        curs1List.forEach(function(data,index,arr){  
        	data1Cur.push(+data);  
        }); 
        $(document).ready(function() {
            $('#container').highcharts({
                chart: {
                    type: 'bar'
                },
                title: {
                    text: '温度'
                },
                subtitle: {
                    text: ''
                },
                xAxis: [{
                    categories: categories,
                    reversed: false,
                    labels: {
                        step: 1
                    }
                }, { // mirror axis on right side
                    opposite: true,
                    reversed: false,
                    categories: categories,
                    linkedTo: 0,
                    labels: {
                        step: 1
                    }
                }],
                yAxis: {
                    title: {
                        text: null
                    },
                    labels: {
                        formatter: function(){
                            return (Math.abs(this.value) ) ;
                        }
                    },
                    min: -40,
                    max: 40
                },
        
                plotOptions: {
                    series: {
                        stacking: 'normal'
                    }
                },
        
                tooltip: {
                    formatter: function(){
                        return '<b>'+ this.series.name +'</b><br/><b> '+ this.point.category +'</b><br/>'+
                            '值: '+ Math.abs(this.point.y);
                    }
                },
        
                series: [{
                    name: '${devName}',
                    data: dataTmp
                }, {
                    name: '${devName1}',
                    data: data1Tmp
                }]
            });
            $('#container1').highcharts({
                chart: {
                    type: 'bar'
                },
                title: {
                    text: '电流'
                },
                subtitle: {
                    text: ''
                },
                xAxis: [{
                   categories: categories1,
                    reversed: false,
                    labels: {
                        step: 1
                    }
                }, { // mirror axis on right side
                    opposite: true,
                    reversed: false,
                    categories: categories1,
                    linkedTo: 0,
                    labels: {
                        step: 1
                    }
                }],
                yAxis: {
                    title: {
                        text: null
                    },
                    labels: {
                        formatter: function(){
                            return (Math.abs(this.value) ) ;
                        }
                    },
                    min: -5,
                    max: 5
                },
        
                plotOptions: {
                    series: {
                        stacking: 'normal'
                    }
                },
        
                tooltip: {
                    formatter: function(){
                        return '<b>'+ this.series.name +'</b><br/><b> '+ this.point.category +'</b><br/>'+
                            '值: '+ Math.abs(this.point.y);
                    }
                },
        
                series: [{
                    name: '${devName}',
                    data:dataCur
                }, {
                    name: '${devName1}',
                    data:data1Cur
                }]
            });
       
            $('#container2').highcharts({
                chart: {
                    type: 'bar'
                },
                title: {
                    text: '电压'
                },
                subtitle: {
                    text: ''
                },
                xAxis: [{
                    categories: categories2,
                    reversed: false,
                    labels: {
                        step: 1
                    }
                }, { // mirror axis on right side
                    opposite: true,
                    reversed: false,
                    categories: categories2,
                    linkedTo: 0,
                    labels: {
                        step: 1
                    }
                }],
                yAxis: {
                    title: {
                        text: null
                    },
                    labels: {
                        formatter: function(){
                            return (Math.abs(this.value) ) ;
                        }
                    },
                    min: -300,
                    max: 300
                },
        
                plotOptions: {
                    series: {
                        stacking: 'normal'
                    }
                },
        
                tooltip: {
                    formatter: function(){
                        return '<b>'+ this.series.name +'</b><br/><b> '+ this.point.category +'</b><br/>'+
                            '值：'+ Math.abs(this.point.y);
                    }
                },
        
                series: [{
                    name: '${devName}',
                    data: dataVol
                }, {
                    name: '${devName1}',
                    data: data1Vol
                }]
            });
        
        
        
        
        });
        
    });

    </script>
</head>
<body>

<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/power/gap/dataGapBar">设备对比</a></li>
   
    

</ul>
 
  
<form:form id="searchForm" name="searchForm" modelAttribute="showDataEntity" action="${ctx}/power/gap/dataGapBar" method="post"
           class="breadcrumb form-search ">

       <li><label>设备名称：</label> <form:select path="sbbId" class="input-medium" ismultiple="true">

                    <form:options items="${deviceList}"
                        itemLabel="sbbName" itemValue="sbbId" htmlEscape="false" />
                </form:select> </li>
                       <li><label>设备名称：</label> <form:select path="sbbCId" class="input-medium" ismultiple="true">

                    <form:options items="${deviceList}"
                        itemLabel="sbbName" itemValue="sbbId" htmlEscape="false" />
                </form:select> </li>
                   <li>  <input id="btnSubmit" class="btn btn-primary" type="submit" value="比较" /></li>
</form:form>

<sys:message content="${message}"/>
<div class="clearfix"></div>


<table class="table table-striped table-bordered table-condensed">
<tr>
      <td> <div id="container"  class="col-mid-6" style="min-width: 550px; max-width: 700px; height: 200px; margin: 0 auto"></div></td>
      <td> <div id="container1"  class="col-mid-6" style="min-width: 550px; max-width: 700px; height: 200px; margin: 0 auto"></div></td>
 </tr> 
 <tr>
      <td> <div id="container2"  class="col-mid-6" style="min-width: 550px; max-width: 700px; height: 200px; margin: 0 auto"></div></td>
      <td> <div id="container3"  class="col-mid-6" style="min-width: 550px; max-width: 700px; height: 200px; margin: 0 auto"></div></td>
 </tr>
 
 </table>        

  


</body>
</html>