<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代码表维护新增</title>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/static/ckeditor/ckeditor.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/static/ckeditor/config.js"></script>
	<script type="text/javascript">
    function backs(){
          var codeType = document.getElementById("codeType").value;
            window.location.href="${pageContext.request.contextPath}/code/list?codeType="+codeType;
        }
</script>
</head>
<body>
	<div id="legend">
          <legend><font color="blue">代码表管理&gt;&gt;代码表维护&gt;&gt;新增</font></legend>
        </div>
	<form method="post"
		action="${pageContext.request.contextPath}/code/doCreate">
		<div class="control-group">
			代码类型：<input type="text" name="codeType" disabled="disabled" value="${codeType}"/>
				   <input type="hidden" name="codeType" value="${codeType}"/>
		</div>
		<br />
		<div class="control-group">
			代码编号：<input type="text" name="codeId" />
		</div>
		<br />
		<div class="control-group">
			代码名称：<input type="text" name="codeName" />
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
		<input type="hidden" value="${codeType}" id="codeType">
	</form>
</body>
</html>
