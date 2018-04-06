<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sms/web/taskList");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sms/web/taskList">任务列表</a></li>
		<%-- <li><a href="${ctx}/sms/web/taskForm">任务添加</a></li> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="smsTask" action="${ctx}/sms/taskList" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>编号：</label><form:input path="id" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>名称：</label><form:input path="keyWord" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>地域：</label><form:input path="place" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li>
				<label>流程类型：&nbsp;</label>
				<select style="width:130px" name="status">
					<option value="">全部任务</option>
					<option value="0">初始状态</option>
					<option value="1">处理中</option>
					<option value="2">处理完成</option>
				</select>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<%-- <sys:message content="${message}"/> --%>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column">编号</th>
				<th class="sort-column" style="width: 20%">名称</th>
				<th class="sort-column" style="width: 10%">城市</th>
				<th class="sort-column" style="width: 5%">总用户数量</th>
				<th class="sort-column" style="width: 5%">计划处理数量</th>
				<th class="sort-column" style="width: 5%">已处理数量</th>
				<th class="sort-column">任务创建时间</th>
				<th class="sort-column">任务开始时间</th>
				<th class="sort-column">任务结束时间</th>
				<th class="sort-column">状态</th>
				<th class="sort-column">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="smsTest">
			<tr>
				<td>${smsTest.id}</td>
				<td>${smsTest.keyWord}</td>
				<td>
					<c:choose>  
						<c:when test="${empty smsTest.place}">全国</c:when>
						<c:otherwise>${smsTest.place}</c:otherwise>
					</c:choose>
				</td>
				
				
				<td>
					<c:choose>  
						<c:when test="${empty smsTest.totalQuantity}">0</c:when>
						<c:otherwise>${smsTest.totalQuantity}</c:otherwise>
					</c:choose>
				</td>
				<td>
					<div style="float:left">
						<c:choose>  
							<c:when test="${empty smsTest.plannedQuantity}">0</c:when>
							<c:otherwise>${smsTest.plannedQuantity}</c:otherwise>
						</c:choose>
					</div>
					
				</td>
				<td>
					<c:choose>  
						<c:when test="${empty smsTest.processingQuantity}">0</c:when>
						<c:otherwise>${smsTest.processingQuantity}</c:otherwise>
					</c:choose>
				</td>
				<td><fmt:formatDate value='${smsTest.createDate}' pattern="yyyy-MM-dd  HH:mm:ss"/></td>
				<td><fmt:formatDate value='${smsTest.beginTime}' pattern="yyyy-MM-dd  HH:mm:ss"/></td>
				<td><fmt:formatDate value='${smsTest.endTime}' pattern="yyyy-MM-dd  HH:mm:ss"/></td>
				
				<td>
					<c:choose>  
						<c:when test="${smsTest.status =='0'}">初始状态</c:when>
						<c:when test="${smsTest.status =='1'}">处理中</c:when>
						<c:when test="${smsTest.status =='2'}">处理完成</c:when>
						<c:otherwise>状态异常</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>  
						<c:when test="${smsTest.status =='0'}">
							<a href="${ctx}/sms/web/changeTaskPQForm?id=${smsTest.id}">修改</a> <a href="${ctx}/sms/rest/taskStart?id=${smsTest.id}" style="color: red;">开始发送</a>
						</c:when>
						<c:when test="${smsTest.status =='1'}"></c:when>
						<c:otherwise>
							<a href="${ctx}/sms/web/changeTaskPQForm?id=${smsTest.id}">修改</a>
						</c:otherwise>
					</c:choose>
    				
					<%-- <a href="${ctx}/sys/user/delete?id=${smsTest.id}" onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a> --%>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>