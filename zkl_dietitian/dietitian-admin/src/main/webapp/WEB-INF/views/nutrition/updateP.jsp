<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>食品分类修改</title>
<script type="text/javascript">

function backs(){
    window.location.href="${pageContext.request.contextPath}/nutrition/listp?time=1";
}

</script>
</head>
<body>
	<div id="legend">
          <legend><font color="blue">营养数据&gt;&gt;食品分类&gt;&gt;修改</font></legend>
</div>
	<form method="post"
		action="${pageContext.request.contextPath}/nutrition/doUpdatep"
		class="form-horizontal">
		<p>
			<input type="hidden" name="id" value="${nutrition.id}" />
			<div class="control-group">
				名称：<input type="text" name="name" value="${nutrition.name}" />
			</div>
			<br />
			<div class="control-group">
				自身编号：<input type="text" name="pcategory" value="${nutrition.pcategory}" />
			</div>
			<br />
			<div class="control-group">
				<label>描述：</label>
			<textarea cols="80" id="descriptionEditor" name="description" rows="10"  >${nutrition.description}</textarea>
			<ckeditor:replace replace="descriptionEditor" basePath="${pageContext.request.contextPath}/static/ckeditor/" />
			</div>
		</p>
		<p>
			<button class="button button-rounded" type="submit">
				<i class="icon-ok"></i> 确认
			</button>
			&nbsp; &nbsp; &nbsp; <a
				href="${pageContext.request.contextPath}/nutrition/listp?time=1">
				<button class="button ">
					<i class="icon-undo"></i> 返回
				</button>
			</a>
		</p>
	</form>
</body>
</html>
