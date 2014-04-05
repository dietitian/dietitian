<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<html>
<head>
<meta  charset="utf8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
<link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script  src="${pageContext.request.contextPath}/static/jquery/jquery-1.9.1.min.js"></script>
<title>功能管理</title>
<script type="text/javascript">
	function deleteFunction(id,name) {
		  if(confirm("您确删除名称为 : '"+name+"' 这条记录吗？")){
			$.get("${pageContext.request.contextPath}/function/delete/" + id, function(
					data) {
				if ("success" == data.result) {
					alert("删除成功");
					window.location.reload();
				} else {
					alert("删除失败");
				}
			});
		}  
	}
</script>
</head>
<body>
   <div id="legend">
          <legend><font color="blue">安全管理&gt;&gt;功能管理</font></legend>
</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
			<form class="form-inline" role="form" action="${pageContext.request.contextPath}/function/list" method="post">
				 <div class="form-group">
				    名称:
				  </div>
				  <div class="form-group">
				    <label class="sr-only" >名称：</label>
				    <input type="text" class="form-control" name="name" value="${sessionScope.functionSearch.name}">
				  </div>
				  <!--  <div class="form-group">
				    路径：
				  </div>
				  <div class="form-group">
				    <label class="sr-only" >路径：</label>
				    <input type="text" class="form-control" name="page" >
				  </div> -->
				 
				  <button class="button button-rounded" type="submit">
				     <i class="icon-search"></i> 查找</button>  	
			</form>
				<display:table name="functionList.list" id="function"
				requestURI="${pageContext.request.contextPath}/function/list"
				pagesize="${pageSize}" partialList="true"
				size="${functionList.fullListSize}" class="table">
				
				<display:column property="name" title="名称" />
				<display:column property="description" title="描述" />
				<display:column title="操作">
					<!-- <a data-toggle="modal" data-target="#addModal"  ><i
						class="icon-edit icon-large"></i></a> -->
						<a href="${pageContext.request.contextPath}/function/update/${function.id}"><i
				class="icon-edit icon-large"></i></a>
					<!-- <button  data-toggle="modal" data-target="#myModal">
				   			<i class="icon-edit icon-large"></i>
					</button> -->	
					&nbsp;&nbsp;&nbsp;<a href="javascript:deleteFunction('${function.id}','${function.name}')"><i
						class="icon-trash icon-large"></i></a>
					&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/function/managerPrivilege/${function.id}">授权</a>
				</display:column>
			</display:table>
			<br />
			<button class="button button-rounded" data-toggle="modal" data-target="#addModal">
				   新增
			</button>
			</div>
		</div>
	</div>
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style=" height:600px;">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="myModalLabel">新增权限资源</h4>
	      </div>
	      <div class="modal-body" style=" height:400px;">
	          <form class="form-horizontal" id="PrivilegeFrom" action="${pageContext.request.contextPath}/function/doCreate" method="post">
				    <fieldset>
				    <div class="control-group">
				          <!-- Text input-->
				          <label class="control-label" for="input01">名称</label>
				          <div class="controls">
				            <input type="text" placeholder="" class="input-xlarge" name="name" id="name">
				            <p class="help-block"></p>
				          </div>
				        </div>
				    
				    <div class="control-group">
				          <!-- Textarea -->
				          <label class="control-label">描述</label>
				          <div class="controls">
				            <div class="textarea">
				                  <textarea type="" class="" cols="60" rows="3" name="description" id="description" > </textarea>
				            </div>
				          </div>
				        </div>
				   	 
				    </fieldset>
				     
				    <!--  <input type="submit" class="btn btn-primary" value="提交"></input>
				      <input type="button" class="btn btn-default" value="关闭" onclick="closePrivilegeForm()"></input> -->
				      <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				     <button type="submit" class="btn btn-primary" >保存</button>
				  </form>
	      </div>
	      <div class="modal-footer">
	       
	        
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	 </div><!-- /.modal -->
</body>
</html>
