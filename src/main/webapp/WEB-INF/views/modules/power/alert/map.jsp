<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>地图</title>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=QjyPWS1Y9fbRaCnAPsgUeWRjHeUpEUDS"></script>
    <script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
    <style type="text/css">
        body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    </style>
</head>
<body>

<div style="margin-top:3%"/>

<div id="allmap"></div>
</body>
</html>
<script form="text/javascript">

    $(function () {
        // 百度地图API功能
        var map = new BMap.Map("allmap");    // 创建Map实例
        map.centerAndZoom(new BMap.Point(120.654, 28.215), 11);   // 初始化地图,设置中心点坐标和地图级别
        //添加地图类型控件
        map.addControl(new BMap.MapTypeControl({
            mapTypes: [
                BMAP_NORMAL_MAP,
                BMAP_HYBRID_MAP
            ]
        }));
        map.setCurrentCity("温州");          // 设置地图显示的城市 此项是必须设置的
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
        $.ajax({
            type : "POST",
            url : "${ctx}/power/alert/deviceList",
            async:false,
            //  dataType : "text",
            success : function(data) {
                var markers = new Array();
               for(var n=0;n<data.length;n++){


                   var myIcon = new BMap.Icon("${ctxStatic}/img/point.png", new BMap.Size(100,57));
                   var point = new BMap.Point(data[n].longitude,data[n].latitude);  // 创建点坐标
                   debugger;
                   if(data[n].alert=='0')
                   markers[n] = new BMap.Marker(point,{icon:myIcon}); // 创建标注
                   else
                       markers[n] = new BMap.Marker(point); // 创建标注
                   map.addOverlay(markers[n]);
                   var label = new BMap.Label(data[n].name, {
                       offset: new BMap.Size(15, -25)
                   });
                   label.setStyle({
                       width: "120px",
                       color: 'black',
                       background: 'green',
                       border: '1px solid "#ff8355"',
                       borderRadius: "5px",
                       textAlign: "center",
                       height: "26px",
                       lineHeight: "26px"
                   });
                   markers[n].setLabel(label); //为标注添加一个标签


                  // var infoWindow = new BMap.InfoWindow(data[n].name);  // 创建信息窗口对象
                   addInfo("<table><tr><td>设备名称</td><td colspan='3'>"+ data[n].name + "</td><td>时间</td><td colspan='2'>"+ data[n].CD + "</td></tr>" +
                           "<tr><td>温度A</td><td>"+data[n].TA +  "</td><td>温度B</td><td>"+ data[n].TB + "</td><td>温度C</td><td>"+ data[n].TC + "</td><td>温度N</td>" +
                           "<td>"+ data[n].TN + "</td></tr>" +
                           "<tr><td>电流A</td><td>"+data[n].CA +  "</td><td>电流B</td><td>"+ data[n].CB + "</td><td>电流C</td><td>"+data[n].CC +  "</td></tr>" +
                           "<tr><td>电压A</td><td>"+ data[n].VA + "</td><td>电压B</td><td>"+ data[n].VB + "</td><td>电压C</td><td>"+ data[n].VC + "</td></tr>" +
                           "<td>电度</td><td>"+ data[n].ED + "</td><td>漏电流</td><td>"+ data[n].LE + "</td><td>功率因数</td><td>"+ data[n].PF + "</td><td>有功功率</td><td>"+ data[n].AP + "</td></tr>"
                           +"</table>",markers[n]);
            /*       markers[n].addEventListener("mouseover", funct1ion(){

                       this.openInfoWindow(infoWindow,point); //开启信息窗口
                   });
                /!*   markers[n].addEventListener("click", function(){

                       this.openInfoWindow(infoWindow,point); //开启信息窗口
                   });*!/
                   markers[n].addEventListener("mouseout", function(){
                       this.closeInfoWindow(infoWindow,point); //开启信息窗口
                   });*/
                   window.map = map;//将map变量存储在全局

               }
            }
        })

    })
    function addInfo(txt,marker){
        var opts = {
            width : 450,     // 信息窗口宽度
            height: 160     // 信息窗口高度
            //title : "实时数据" , // 信息窗口标题
        }
        var infoWindow = new BMap.InfoWindow(txt,opts);
        marker.addEventListener("click", function(){this.openInfoWindow(infoWindow);});
        marker.addEventListener("mouseover", function(){this.openInfoWindow(infoWindow);});
        marker.addEventListener("mouseout", function(){this.openInfoWindow(infoWindow);});
    }
</script>