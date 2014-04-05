<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能修改</title>

</head>
<body>
	<div id="legend">
          <legend><font color="blue">安全管理&gt;&gt;功能管理&gt;&gt;修改</font></legend>
</div>
	<form method="post"
		action="${pageContext.request.contextPath}/function/doUpdate"
		class="form-horizontal">
		<p>
			<input type="hidden" name="id" value="${function.id}" />
			<div class="control-group">
				名称：<input type="text" name="name" value="${function.name}" />
			</div>
			
			<div class="control-group">
				描述：
				<textarea cols="80"  name="description" rows="5">${function.description}</textarea>
			</div>
		</p>
		<p>
			<button class="button button-rounded" type="submit">
				<i class="icon-ok"></i> 确认
			</button>
			&nbsp; &nbsp; &nbsp; 
			<a 
				href="${pageContext.request.contextPath}/function/list">
				<button class="button button-rounded " >
					<i class="icon-undo"></i> 返回
				</button>
			</a>
		</p>
	</form>
</body>
</html>
