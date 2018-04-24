<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>资料管理</title>
    <meta name="decorator" content="default"/>
    <script form="text/javascript">
        function page(n, s) {
            if (n) $("#pageNo").val(n);
            if (s) $("#pageSize").val(s);
            $("#searchForm").attr("action", "${ctx}/power/powerData");
            $("#searchForm").submit();
            return false;
        }
       
    </script>
</head>
<body>

<%-- <ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/verify/document/list">设备数据查询</a></li>

</ul> --%>
 <div style="margin-top:3%"/>
<form:form id="searchForm" name="searchForm" modelAttribute="powerDataEntity" action="${ctx}/verify/document/list" method="post"
           class="breadcrumb form-search ">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
    <ul class="ul-form">
      <li><label>设备名称：&nbsp;</label>
      <form:select path="sbbId" class="input-medium" ismultiple="true">

                    <form:options items="${deviceList}"
                        itemLabel="sbbName" itemValue="sbbId" htmlEscape="false" />
                </form:select> 
       </li>
          <li><label class="control-label">归属区域:</label></li>
			 <li>
                <sys:treeselect id="qyId" name="qyId" value="${office.area.id}" labelName="area.name" labelValue="${office.area.name}"
					title="区域" url="/sys/area/treeData"  cssClass="input-mini required"/>
			</li>
        <li><label>时间：&nbsp;</label><input id="dateFrom" name="dateFrom" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                  --
                  <input id="dateTo" name="dateTo" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
        </li>

       <li class="btns">&nbsp;&nbsp;
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
           <a class="btn btn-primary" href="${ctx}/power/powerData/exportFile"> 导出Excel</a>
        </li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<div style="margin-top:1%"/>
<sys:message content="${message}"/>
<div class="clearfix"></div>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>编号</th><th>设备名称</th><th>温度A</th><th>温度B</th><th>温度C</th><th>温度N</th><th>电流A</th><th>电流B</th><th>电流C</th><th>电压A</th><th>电压B</th><th>电压C</th><th>用电量</th><th>漏电</th><th>功率因数</th><th>有功功率</th><th>时间</th>
       
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="powerData" varStatus="vs">
        <tr>
            <%--这里的序号与对应的数据不是绑定的--%>
            <td>${page.pageSize*(page.pageNo-1)+vs.index+1}</td>
             <td>${powerData.sbbName}</td>
            <td>
               ${powerData.temperatureA}
            </td>
            <td>
               ${powerData.temperatureB}
            </td>
            <td>${powerData.temperatureC}</td><td>${powerData.temperatureN}</td><td>${powerData.currentA}</td><td>${powerData.currentB}</td><td>${powerData.currentC}</td><td>${powerData.voltageA}</td><td>${powerData.voltageB}</td><td>${powerData.voltageC}</td><td>${powerData.electricalDegree}</td><td>${powerData.leakageElectricity}</td>
                <td>
                        ${powerData.powerFactor}
                </td>
                <td>
                        ${powerData.activePower}
                </td>
                <td><fmt:formatDate value="${powerData.createDate}" type="both"/></td>
                
    
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>