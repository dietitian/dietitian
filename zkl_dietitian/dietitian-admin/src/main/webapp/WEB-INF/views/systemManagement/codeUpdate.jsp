<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告修改</title>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/static/ckeditor/ckeditor.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/static/ckeditor/config.js"></script>

<script type="text/javascript">
	window.onload = function(){
			var isValid = $("#isValid").attr("value");
			if(isValid == 1){
				$("input[name='isValid']")[0].checked = true;
			}else{
				$("input[name='isValid']")[1].checked = true;
			}
		}
    function backs(){
            var codeType = document.getElementById("codeType").value;
            window.location.href="${pageContext.request.contextPath}/code/list?codeType="+codeType;
        }
</script>	
</head>
<body>
	<div id="legend">
          <legend><font color="blue">系统管理&gt;&gt;代码表维护&gt;&gt;代码项修改</font></legend>
</div>
	<form method="post"
		action="${pageContext.request.contextPath}/code/doUpdate"
		class="form-horizontal">
		<p>
		<input type="hidden" id="isValid" value="${code.isValid}" />
		<input type="hidden" name="id" value="${code.id}" />
		<input type="hidden" name="createTime" value="${code.createTime}" />
		<input type="hidden" name="createPerson" value="${code.createPerson}" />
		
		<div class="control-group">
		代码类型：<input type="text" name="codeType" value="${code.codeType}" disabled="disabled"/>
			   <input type="hidden" name="codeType" value="${code.codeType}" />
		</div>
		<br />
		<div class="control-group">
			代码编号：<input type="text" name="codeId" value="${code.codeId}"/>
		</div>
		<br />
		<div class="control-group">
			代码名称：<input type="text" name="codeName" value="${code.codeName}">
		</div>
		<br />
		<div class="control-group">
			是否有效：
	         <label>
		                <input type="radio" name="isValid" value="1" >
		                是</label>
		                &nbsp;&nbsp;&nbsp;&nbsp;
		              <label>
		                <input type="radio" name="isValid" value="0" >
		                否</label>
		</div>
		<br/>
			<div class="control-group">
				描述：
				<textarea cols="80" id="descriptionEditor" name="description" rows="10">${code.description}</textarea>
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
	<input type="hidden" id="codeType" value="${code.codeType}">
	</form>
</body>
</html>
