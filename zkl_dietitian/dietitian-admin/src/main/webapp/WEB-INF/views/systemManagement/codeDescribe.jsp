<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代码项明细</title>
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
	<input type="hidden" id="isValid" value="${code.isValid}" />
	<div id="legend">
          <legend><font color="blue">系统管理&gt;&gt;代码表管理&gt;&gt;代码项明细</font></legend>
</div>
	<form method="post"
		action="${pageContext.request.contextPath}/code/doUpdate"
		class="form-horizontal">
		<p>
		<div class="control-group">
			代码类型：<input type="text" name="codeType" value="${code.codeType}"
				disabled="disabled" />
		</div>
		<br />
		<div class="control-group">
			代码编号：<input type="text" name="codeId" value="${code.codeId}"
				disabled="disabled" />
		</div>
		<br />
		<div class="control-group">
			代码名称：<input type="text" name="codeName" value="${code.codeName}"
				disabled="disabled" />
		</div>
		<br />
		<div class="control-group">
			创建时间：<input type="text" name="createTime" value="${code.createTime}"
				disabled="disabled" />
		</div>
		<br />
		<div class="control-group">
			创建人：<input type="text" name="createPerson"
				value="${code.createPerson}" disabled="disabled" />
		</div>
		<br />
		<div class="control-group">
			最后修改时间：<input type="text" name="updateTime"
				value="${code.updateTime}" disabled="disabled" />
		</div>
		<br />
		<div class="control-group">
			修改人：<input type="text" name="updatePerson"
				value="${code.updatePerson}" disabled="disabled" />
		</div>
		<br />
		<div class="control-group">
			是否有效：
	         <label>
		                <input type="radio" name="isValid" value="1" disabled="disabled">
		                是</label>
		                &nbsp;&nbsp;&nbsp;&nbsp;
		              <label>
		                <input type="radio" name="isValid" value="0" disabled="disabled">
		                否</label>
		</div>
		<br />
		<div class="control-group">
			描述：
			<textarea disabled="disabled" cols="80" id="descriptionEditor"
				name="description" rows="10">${code.description}</textarea>
			<ckeditor:replace replace="descriptionEditor"
				basePath="${pageContext.request.contextPath}/static/ckeditor/" />
		</div>
		<p>	
		<button class="button button-rounded" type="button" onclick="backs();">
			<i class="icon-undo"></i> 返回
		</button>
		</p>
		<input type="hidden" id="codeType" value="${code.codeType}">
	</form>
</body>
</html>
