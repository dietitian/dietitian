<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代码表明细</title>
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
            window.location.href="${pageContext.request.contextPath}/codeMain/list";
        }

</script>	
</head>
<body>
	
	<div id="legend">
          <legend><font color="blue">系统管理&gt;&gt;代码表管理&gt;&gt;代码表明细</font></legend>
</div>
	<form method="post"
		action="${pageContext.request.contextPath}/codeMain/doUpdate"
		class="form-horizontal">
		<p>
		<input type="hidden" id="isValid" value="${codeMain.isValid}" />
		<div class="control-group">
			代码类型：<input type="text" name="codeType" value="${codeMain.codeType}"
				disabled="disabled" />
		</div>
		<br />
		<div class="control-group">
			代码名称：<input type="text" name="codeName" value="${codeMain.codeName}"
				disabled="disabled" />
		</div>
		<br />
		<div class="control-group">
			创建时间：<input type="text" name="createTime" value="${codeMain.createTime}"
				disabled="disabled" />
		</div>
		<br />
		<div class="control-group">
			创建人：<input type="text" name="createPerson"
				value="${codeMain.createPerson}" disabled="disabled" />
		</div>
		<br />
		<div class="control-group">
			最后修改时间：<input type="text" name="updateTime"
				value="${codeMain.updateTime}" disabled="disabled" />
		</div>
		<br />
		<div class="control-group">
			修改人：<input type="text" name="updatePerson"
				value="${codeMain.updatePerson}" disabled="disabled" />
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
		<br />
		<div class="control-group">
			描述：
			<textarea disabled="disabled" cols="80" id="descriptionEditor"
				name="description" rows="10" >${codeMain.description}</textarea>
			<ckeditor:replace replace="descriptionEditor"
				basePath="${pageContext.request.contextPath}/static/ckeditor/" />
		</div>
		<p>
		
				<button class="button button-rounded" type="button" onclick="backs();">
					<i class="icon-undo"></i> 返回
				</button>
	
		</p>
	</form>
</body>
</html>
