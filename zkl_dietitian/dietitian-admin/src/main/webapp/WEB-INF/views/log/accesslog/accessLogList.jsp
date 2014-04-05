<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<html>
<head>
<title>日志管理</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div id="legend">
          <legend><font color="blue">访问日志&gt;&gt;日志管理</font></legend>
        </div>
	
	
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
			<form class="form-inline" role="form" action="${pageContext.request.contextPath}/accesslog/list" method="post">
				 <div class="form-group">
				    名称:
				  </div>
				  <div class="form-group">
				    <label class="sr-only" >名称：</label>
				    <input type="text" class="form-control" name="visitTime" value="${sessionScope.accesslogSearch.visitTime}">
				  </div>
				  <button class="button button-rounded" type="submit">
				     <i class="icon-search"></i> 查找
				  </button>   
			</form>
				<display:table name="accessLogList.list" id="accesslog"
					requestURI="${pageContext.request.contextPath}/accesslog/list"
					pagesize="${pageSize}" partialList="true"
					size="${accessLogList.fullListSize}" class="table">
					<display:column property="urlAbsoluteUri" title="访问资源" />
					<display:column property="userHostAddress" title="用户ip" />
					<display:column property="userAgent" title="浏览器的原始信息" />
					<display:column property="httpMethod" title="数据传输方法" />
					<display:column property="visitTime" title="访问时间" />
				</display:table>
			</div>
		</div>
	</div>
</body>
</html>
