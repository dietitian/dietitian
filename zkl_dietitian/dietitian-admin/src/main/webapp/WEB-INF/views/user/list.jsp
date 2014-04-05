<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<html>
<head>
<title>用户管理</title>
<script type="text/javascript">
	function createUser() {
		window.location.href = "${pageContext.request.contextPath}/user/create";
	}
	function deleteUser(id,name) {
		if(confirm("您确认删除用户'"+name+"'吗？")){
			$.get("${pageContext.request.contextPath}/user/delete/" + id, function(
					data) {
				if ("success" == data.result) {
					alert("删除成功");
					window.location.reload();
				} else {
					alert("删除失败");
				}
			});
		}
	}
</script>
</head>
<body>
	<div id="legend">
          <legend><font color="blue">安全管理&gt;&gt;用户管理

</font></legend>
</div>
	
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
			<form class="form-inline" role="form" action="${pageContext.request.contextPath}/user/list" method="post">
				 <div class="form-group">
				    名称:
				  </div>
				  <div class="form-group">
				    <label class="sr-only" >名称：</label>
				    <input type="text" class="form-control" name="name" value="${sessionScope.userSearch.name}">
				  </div>
				  <!--  <div class="form-group">
				    路径：
				  </div>
				  <div class="form-group">
				    <label class="sr-only" >路径：</label>
				    <input type="text" class="form-control" name="page" >
				  </div> -->
				 
				   <button class="button button-rounded" type="submit">
				     <i class="icon-search"></i> 查找</button>   
			</form>
				<display:table name="userList.list" id="user"
					requestURI="${pageContext.request.contextPath}/user/list"
					pagesize="${pageSize}" partialList="true"
					size="${userList.fullListSize}" class="table">
					<display:column property="realname" title="姓名" />
					<display:column property="name" title="用户名" />
					<display:column property="gender" title="性别" decorator="zkl.dietitian.utils.displaytag.SexDecractor" />
					<display:column property="telephone" title="电话" />
					<display:column property="email" title="邮箱" />
					<display:column property="age" title="年龄" />
					<display:column property="description" title="描述" />
					<display:column title="操作">
						<a href="${pageContext.request.contextPath}/user/update/${user.id}"><i
							class="icon-edit icon-large"></i></a>
						&nbsp;&nbsp;&nbsp;<a href="javascript:deleteUser('${user.id}','${user.name}')"><i
							class="icon-trash icon-large"></i></a>
						&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/user/managerRole/${user.id}">授权</a>
					</display:column>
				</display:table>
			<br />
			<a href="${pageContext.request.contextPath}/user/create">
				<button class="button button-rounded">新增</button>
			</a>
			</div>
		</div>
	</div>
</body>
</html>
