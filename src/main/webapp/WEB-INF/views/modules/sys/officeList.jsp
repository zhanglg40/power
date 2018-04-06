<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, rootId = "${not empty office.id ? office.id : '0'}";
			addRow("#treeTableList", tpl, data, rootId, true);
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
							type: getDictLabel(${fns:toJson(fns:getDictList('sys_office_type'))}, row.type)
						}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
        function page( ) {

            $("#searchForm").attr("action", "${ctx}/sys/office/list");
            $("#searchForm").submit();
            return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/office/list?id=${office.id}&parentIds=${office.parentIds}">机构列表</a></li>
		<shiro:hasPermission name="sys:office:edit"><li><a href="${ctx}/sys/office/form?parent.id=${office.id}">机构添加</a></li></shiro:hasPermission>
	</ul>
    <form:form id="searchForm" name="searchForm" modelAttribute="office" action="${ctx}/sys/office/list" method="post"
               class="breadcrumb form-search ">
        <input id="parentIds" name="parentIds" type="hidden" value="${office.parentIds}"/>
        <sys:tableSort id="orderBy" name="orderBy" value="${office.orderBy}" callback="page();"/>
    </form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
            <c:choose>
                 <c:when test="${fn:contains(office.orderBy,'name')}">
                     <th class="sort-column name">机构名称</th>
                   </c:when>
                <c:otherwise>
                    <th class="sort-column name">机构名称<i class="icon icon-arrow-down"></i><i class="icon icon-arrow-up"></i></th>

                </c:otherwise>
            </c:choose>
             <th>归属区域</th>

            <c:choose>

                <c:when test="${fn:contains(office.orderBy,'code')}">
                     <th class="sort-column code">机构编码</th>
                </c:when>
                <c:otherwise>
                    <th class="sort-column code">机构编码<i class="icon icon-arrow-down"></i><i class="icon icon-arrow-up"></i></th>
                </c:otherwise>
            </c:choose>
            <th>机构类型</th><th>备注</th><shiro:hasPermission name="sys:office:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/sys/office/form?id={{row.id}}">{{row.name}}</a></td>
			<td>{{row.area.name}}</td>
			<td>{{row.code}}</td>
			<td>{{dict.type}}</td>
			<td>{{row.remarks}}</td>
			<shiro:hasPermission name="sys:office:edit"><td>
				<a href="${ctx}/sys/office/form?id={{row.id}}">修改</a>
				<a href="${ctx}/sys/office/delete?id={{row.id}}" onclick="return confirmx('要删除该机构及所有子机构项吗？', this.href)">删除</a>
				<a href="${ctx}/sys/office/form?parent.id={{row.id}}">添加下级机构</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>