<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限资源修改</title>
<script type="text/javascript">

window.onload = function () {
	  document.getElementsByName("generateMenu")[0].value = "${privilege.generateMenu}";//后台的request属性中的值
	 //这样就选中了
	};

</script>
</head>
<body>
	<div id="legend">
          <legend><font color="blue">安全管理&gt;&gt;资源管理&gt;&gt;修改</font></legend>
</div>
	<form method="post"
		action="${pageContext.request.contextPath}/privilege/doUpdate"
		class="form-horizontal">
		<p>
			<input type="hidden" name="id" value="${privilege.id}" />
			<div class="control-group">
				名称：<input type="text" name="name" value="${privilege.name}" />
			</div>
			<br />
			<div class="control-group">
				路径：<input type="text" name="path" value="${privilege.path}" />
			</div>
			<div class="control-group">
				是否生成菜单：<select name="generateMenu">
							<option value=""></option>
							<option  value="1">生成</option>
							<option  value="0">不生成</option>
						</select>
			</div>
			<div class="control-group">
				描述：
				<textarea cols="80"  name="description" rows="5">${privilege.description}</textarea>
			</div>
		</p>
		<p>
			<button class="button button-rounded" type="submit">
				<i class="icon-ok"></i> 确认
			</button>
			&nbsp; &nbsp; &nbsp; <a
				href="${pageContext.request.contextPath}/privilege/list">
				<button class="button ">
					<i class="icon-undo"></i> 返回
				</button>
			</a>
		</p>
	</form>
</body>
</html>
