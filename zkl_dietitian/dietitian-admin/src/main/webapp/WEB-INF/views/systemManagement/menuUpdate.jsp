<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单修改</title>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/static/ckeditor/ckeditor.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/static/ckeditor/config.js"></script>
	
<script type="text/javascript">
	window.onload = function(){
			var state = $("#state").attr("value");
			if(state == 1){
				$("input[name='state']")[0].checked = true;
			}else{
				$("input[name='state']")[1].checked = true;
			}
		}
	function backs(){
		var parentId = document.getElementById("parentId").value;
        window.location.href="${pageContext.request.contextPath}/menu/list?parentId="+parentId;
    }
</script>
</head>
<body>
	<div id="legend">
          <legend><font color="blue">系统管理&gt;&gt;菜单管理&gt;&gt;修改</font></legend>
    </div>

	<form method="post"
		action="${pageContext.request.contextPath}/menu/doUpdate"
		class="form-horizontal">
		<p>
			<input type="hidden" id="state" value="${menu.state}" />
			<input type="hidden" name="id" value="${menu.id}" /> 
			<input type="hidden" name="createTime" value="${menu.createTime}" /> 
			<input type="hidden" name="createPerson" value="${menu.createPerson}" />
			
		<div class="control-group">
			父级编号：<input type="text" name="parentId" value="${menu.parentId}" readonly="readonly"/>
		</div>
		<br />
		<div class="control-group">
			菜单名称：<input type="text" name="name" value="${menu.name}">
		</div>
		<br />
		<div class="control-group">
			url：<input type="text" name="path" value="${menu.path}">
		</div>
		<br />
		<div class="control-group">
			状态：
	         <label>
		                <input type="radio" name="state" value="1" >
		                启用</label>
		                &nbsp;&nbsp;&nbsp;&nbsp;
		              <label>
		                <input type="radio" name="state" value="0" >
		                停用</label>
		</div>
		<br />
		<br />
		<div class="control-group">
			排序：<input type="text" name="sort" />
		</div>
		<div class="control-group">
			描述：
			<textarea cols="80" id="descriptionEditor" name="description"
				rows="10">${menu.description}</textarea>
			<ckeditor:replace replace="descriptionEditor"
				basePath="${pageContext.request.contextPath}/static/ckeditor/" />
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
		<input type="hidden" value="${menu.parentId}" id="parentId">
	</form>
</body>
</html>
