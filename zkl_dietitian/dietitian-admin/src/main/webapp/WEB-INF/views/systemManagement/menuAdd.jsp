<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单新增</title>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/static/ckeditor/ckeditor.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/static/ckeditor/config.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var parentId = $("#parentId").val();
	if(parentId==""||parentId==null){
		$("#parentId").val(0)
		}
	
});
function backs(){
	var parentId = document.getElementById("parentId").value;
    window.location.href="${pageContext.request.contextPath}/menu/list?parentId="+parentId;
}
</script>
</head>
<body>
	
	<div id="legend">
          <legend><font color="blue">系统管理&gt;&gt;菜单管理&gt;&gt;新增

</font></legend>
</div>
	<form method="post"
		action="${pageContext.request.contextPath}/menu/doCreate">
		<div class="control-group">
			父级编号：<input id="parentId" type="text" name="parentId" value="${parentId}" readonly="readonly"/>
		</div>
		<br />
		<div class="control-group">
			菜单名称：<input type="text" name="name" />
		</div>
		<br />
		<div class="control-group">
			url：<input type="text" name="path" />
		</div>
		<br />
		<div class="control-group">
			是否有效：
	         <label>
		                <input type="radio" name="state" value="1" >
		                是</label>
		                &nbsp;&nbsp;&nbsp;&nbsp;
		              <label>
		                <input type="radio" name="state" value="0" >
		                否</label>
		</div>
		<br />
		<div class="control-group">
			排序：<input type="text" name="sort" />
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
		<input type="hidden" id="parentId" value="${parentId}">
	</form>
</body>
</html>
