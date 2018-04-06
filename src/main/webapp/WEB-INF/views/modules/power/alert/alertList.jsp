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
            $("#searchForm").attr("action", "${ctx}/power/alert");
            $("#searchForm").submit();
            return false;
        }
       
    </script>
</head>
<body>

<%-- <ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/power/alert/list">报警数据查询</a></li>

</ul> --%>
<div style="margin-top:3%"/>
<form:form id="searchForm" name="searchForm" modelAttribute="alertEntity" action="${ctx}/verify/document/list" method="post"
           class="breadcrumb form-search ">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
    <ul class="ul-form">
      <li><label>设备名称：&nbsp;</label>
      <form:select path="sbbId" class="input-medium" ismultiple="true">
           <form:option value="" label="请选择" />
                    <form:options items="${deviceList}"
                        itemLabel="sbbName" itemValue="sbbId" htmlEscape="false" />
                </form:select> 
       </li>
          
        <li><label>时间：&nbsp;</label><input id="dateFrom" name="dateFrom" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                  --
                  <input id="dateTo" name="dateTo" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
                  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
        </li>

       <li class="btns">&nbsp;&nbsp;
            <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
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
        <th>编号</th><th>设备名称</th><th>设备类型</th><th>报警时间</th><th>报警类型</th><th>处理情况</th>
       
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="powerData" varStatus="vs">
        <tr>
            <%--这里的序号与对应的数据不是绑定的--%>
            <td>${page.pageSize*(page.pageNo-1)+vs.index+1}</td>
             <td>${powerData.sbbName}</td>
            <td>
               ${powerData.sbbType}
            </td>
              <td><fmt:formatDate value="${powerData.alertTime}" type="both"/></td>
            
            <td>
               ${powerData.alertType}
            </td>
  <td>
               ${powerData.delName}
            </td>
              
    
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>