<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<html >
<head>
<meta charset="utf8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script  src="${pageContext.request.contextPath}/static/jquery/jquery-1.9.1.min.js"></script>
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

<title>资源管理</title>
<script type="text/javascript">


	function deletePrivilege(id,name) {
		
		 if(confirm("您确删除名称为 : '"+name+"' 这条记录吗？")){
			$.get("${pageContext.request.contextPath}/privilege/delete/" + id, function(
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

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div id="legend">
          <legend><font color="blue">安全管理&gt;&gt;资源管理</font></legend></div>
		  	<display:table name="privilegeList.list" id="privilege"
				requestURI="${pageContext.request.contextPath}/privilege/list"
				pagesize="${pageSize}" partialList="true"
				size="${privilegeList.fullListSize}" class="table">
				<display:column property="name" title="名称" />
				<display:column property="path" title="路径" />
				<display:column property="generateMenu" title="是否生成菜单" decorator="zkl.dietitian.utils.displaytag.IsOrNotDecrator"/>
				<display:column property="description" title="描述" />
				<display:column title="操作">
					<!-- <a data-toggle="modal" data-target="#addModal"  ><i
						class="icon-edit icon-large"></i></a> -->
						<a href="${pageContext.request.contextPath}/privilege/update/${privilege.id}"><i
				class="icon-edit icon-large"></i></a>
					&nbsp;&nbsp;&nbsp;<a href="javascript:deletePrivilege('${privilege.id}','${privilege.name}')"><i
						class="icon-trash icon-large"></i></a>
				</display:column>
			</display:table>
			<br />
				<button class="button button-rounded" data-toggle="modal" data-target="#addModal">
				   新增
				</button>
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
	          <form class="form-horizontal" id="PrivilegeFrom" action="${pageContext.request.contextPath}/privilege/addPrivilege" method="post">
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
				          <!-- Search input-->
				          <label class="control-label">资源路径</label>
				          <div class="controls">
				            <input type="text" placeholder="" class="input-xlarge search-query" name="path" id=""path"" check-type="required" required-message="路径不能为空！" >
				            <p class="help-block"></p>
				          </div>
				        </div>
				    <div class="control-group">
				          <!-- Textarea -->
				          <label class="control-label">描述</label>
				          <div class="controls">
				            <div class="textarea">
				                  <textarea type="" class="" rows="3" name="description" id="description" > </textarea>
				            </div>
				          </div>
				        </div>
				   	 <div class="control-group">
				          <!-- Select Basic -->
				          <label class="control-label">生成菜单</label>
				          <div class="controls">
				            <select class="input-xlarge" name="generateMenu" id="generateMenu">
						      <option value="1">生成</option>
						      <option value="0" selected="selected">不生成</option>
						     </select>
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
</div>
</body>
</html>
