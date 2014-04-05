<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script  src="${pageContext.request.contextPath}/static/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.js"></script>
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
<title>用户授权</title>
<script language="javascript">

function addRoles(id){
	
    var r=document.getElementsByName("nid");  
    var ids = "";
   for(var i=0;i<r.length;i++){
        if(r[i].checked){
       	 if(i == (r.length-1)){
       	 ids = ids+r[i].value;
       	 }else{
       		 ids = ids+r[i].value+",";
         	}
      }
   }  
  
	    $.post("${pageContext.request.contextPath}/user/addRoles",{ids:ids,id:id},function(data){
	
	    	if ("success" == data.result) {
				alert("授权成功");
				window.location.reload();
			} else {
				alert("授权失败");
			}
	
	       
	   }); 
 
}
function deleteRoles(id){
	
    var r=document.getElementsByName("hid");  
    var ids = "";
   for(var i=0;i<r.length;i++){
        if(r[i].checked){
       	 if(i == (r.length-1)){
       	 ids = ids+r[i].value;
       	 }else{
       		 ids = ids+r[i].value+",";
         	}
      }
   }  
   if(confirm("您确认删除吗？")){
	   $.post("${pageContext.request.contextPath}/user/deleteRoles",{ids:ids,id:id},function(data){
	       
		   if ("success" == data.result) {
				alert("销权成功");
				window.location.reload();
			} else {
				alert("销权失败");
			}
	       
	   }); 
   }
}
</script>
</head>

<body>

<div class="container-fluid">
    <div id="legend">
          <legend><font color="blue">安全管理&gt;&gt;用户管理&gt;&gt;用户授权
    </font></legend>
</div>
	<div class="span12" align="center">
		<h3>
			用户名称：${user.name}
		</h3>
	</div>
	<div class="row-fluid">
		<div class="span6">
			<div class="row-fluid">
				<div class="span12">
			        <div align="center">
			        <h3>
						可选角色
					</h3>
					</div>
					<div class="container-fluid">
					<form class="form-inline" role="form">
						<div class="form-group">
							<input type="email" class="form-control" id="exampleInputEmail2" placeholder="名称:">
					   </div>
					   <div class="form-group">
							<input type="password" class="form-control" id="exampleInputPassword2" placeholder="状态:">
					   </div>
					   <div class="form-group">
							<button class="button button-rounded" type="submit">
				     <i class="icon-search"></i> 查找</button> 
					   </div>
					</form>
					</div>
					<div class="container-fluid">
						<div class="row-fluid">
							<div class="span12">
								<display:table name="noRoles" id="role" pagesize="5" >
								
									<display:column property="id" title="" style="width:5%;" decorator="zkl.dietitian.utils.displaytag.CheckBoxDecractor" >
										
									</display:column>
									<display:column property="name" title="名称" />
									<display:column property="description" title="描述" />
								</display:table>
							</div>
							<div class="span4">
							</div>
							<div class="span4">
								<a href="javascript:addRoles('${user.id}')" >
								 	<button class="btn btn-block btn-info" type="button">授权按钮</button>
								 </a>
							</div>
							<div class="span4">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
			<div class="span6">
			   <div class="row-fluid">
					<div class="span12" >
				        <div align="center">
							<h3>
								已有角色
							</h3>
						</div>
						<div class="container-fluid">
						<form class="form-inline" role="form">
							<div class="form-group">
								<input type="email" class="form-control" id="exampleInputEmail2" placeholder="名称:">
						   </div>
						   <div class="form-group">
								<input type="password" class="form-control" id="exampleInputPassword2" placeholder="状态:">
						   </div>
						   <div class="form-group">
								<button class="button button-rounded" type="submit">
				     <i class="icon-search"></i> 查找</button> 
						   </div>
						</form>
						</div>
						<div class="container-fluid">
							<div class="row-fluid">
								<div class="span12">
									<display:table name="user.roles" id="role" pagesize="5" >
									
										<display:column property="id" title="" style="width:5%;" decorator="zkl.dietitian.utils.displaytag.CheckBoxDecractorB" >
											
										</display:column>
										<display:column property="name" title="名称" />
										<display:column property="description" title="描述" />
										
									</display:table>
								</div>
								<div class="span4">
								</div>
								<div class="span4">
									<a href="javascript:deleteRoles('${user.id}')" >
								 		<button class="btn btn-block btn-info" type="button">销权按钮</button>
									 </a>
									
								</div>
								<div class="span4">
								</div>
							</div>
						</div>
					</div>
					
				</div>
		   </div>
	</div>

</div>

</body>
</html>
