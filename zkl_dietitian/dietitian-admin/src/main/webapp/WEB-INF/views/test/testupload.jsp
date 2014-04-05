<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<form class="form-horizontal"  action="${pageContext.request.contextPath}/upload/doUpload" method="post" 
					name="selectfile" enctype="multipart/form-data"
				>
				    <fieldset>
				    <div class="control-group">
				
				          <!-- Text input-->
				          <label class="control-label" for="input01">名称</label>
				          <div class="controls">
				            <input type="text" name="name" class="input-xlarge">
				          </div>
				        </div>
				
				    <div class="control-group">
				          <label class="control-label">文件</label>
				
				          <!-- File Upload -->
				          <div class="controls">
				            <input class="input-file" id="fileInput" type="file" name="testFile">
				          </div>
				        </div>
				
				    <div class="control-group">
				          <div class="controls">
				             <input type="submit" ></input>
				          </div>
				        </div>
				
				    </fieldset>
				  </form>
			</div>
		</div>
	</div>
</body>
</html>