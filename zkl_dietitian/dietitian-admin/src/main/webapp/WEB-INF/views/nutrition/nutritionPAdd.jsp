<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>食品分类表新增</title>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/static/ckeditor/ckeditor.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/static/ckeditor/config.js"></script>
<script type="text/javascript">
    function backs(){
            window.location.href="${pageContext.request.contextPath}/nutrition/listp?time=1";
        }
</script>
</head>
<body>
	<div id="legend">
          <legend><font color="blue">营养数据&gt;&gt;食品分类&gt;&gt;新增</font></legend>
        </div>
	<form method="post"
		action="${pageContext.request.contextPath}/nutrition/addNutritionp">
		<div class="control-group">
			自身编号：<input type="text" name="pcategory" />
		</div>
		<br />
		<div class="control-group">
			名称：<input type="text" name="name" />
		</div>
		<br />
		<div class="control-group">
			描述：
			<textarea cols="80" id="descriptionEditor" name="description" rows="10"></textarea>
			<ckeditor:replace replace="descriptionEditor" basePath="${pageContext.request.contextPath}/static/ckeditor/" />
		</div>
		<p>
			<button class="button button-rounded" type="submit">
				<i class="icon-ok"></i> 确认
			</button>
			&nbsp; &nbsp; &nbsp;
				<button class="button button-rounded" type="button" onclick="backs();">
					<i class="icon-undo"></i> 返回
				</button>
		</p>
	</form>
</body>
</html>
