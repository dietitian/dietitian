<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<html >
<head>
<meta charset="utf8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
<title>角色管理</title>
<link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script  src="${pageContext.request.contextPath}/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
function addFunctions(id){
	
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
  
	    $.post("${pageContext.request.contextPath}/role/addFunctions",{ids:ids,id:id},function(data){
	
	    	if ("success" == data.result) {
				alert("授权成功");
				window.location.reload();
			} else {
				alert("授权失败");
			}
	
	       
	   }); 
 
}
function deleteFunctions(id){
	
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
	   $.post("${pageContext.request.contextPath}/role/deleteFunctions",{ids:ids,id:id},function(data){
	       
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
	<div class="row-fluid">
		<div class="span12">
			<div id="legend">
          <legend><font color="blue">安全管理&gt;&gt;角色管理&gt;&gt;授权</font></legend>
</div>
		  <table class="table table-bordered table-hover" contenteditable="true">
                  <thead>
                    <tr>
                      
                     <th colspan="2"><center>已有功能</center></th>
                    </tr>
                  </thead>
                  <tbody>
                  <c:if test="hModuleSet == null">
                  
                  sfdaf
                  </c:if>
                  <c:forEach items="${hModuleList}" var="module">
	                    <tr>
	                      <td >
		                      <center>
			                      <div style="border:solid 1px red;width:100px; height:20px">
			                      	<input type="checkbox" name="nmodule" value="${module.id}" />${module.name}
			                      </div>
		                      </center> 
	                      </td>
	                      <td colspan="5">
							  <div>
								    <c:forEach items="${module.functions}" var="function">
									  <input type="checkbox" name="hid" value="${function.id}" />${function.name}
									</c:forEach>
							  </div>
						  </td>
	                      
	                    </tr>
                  </c:forEach>
                    <tr >
                      
                      <td colspan="2">
						  <div class="row-fluid">
				               <div class="span12">
									<div class="span4">
									</div>
									<div class="span4">
										<a href="javascript:deleteFunctions('${role.id}')" >
											 <button class="btn btn-block btn-info" type="button">销权按钮</button>
										</a>
									</div>
									<div class="span4">
									</div>
								</div>
							</div>
					  </td>
                    </tr>
                    <thead>
					 <tr>
                      <th colspan="2"><center>待选功能</center></th>
                      
                     
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${nModulelist}" var="module">
	                    <tr>
	                      <td >
		                      <center>
			                      <div style="border:solid 1px red;width:100px; height:20px">
			                      	<input type="checkbox" name="nmodule" value="${module.id}" />${module.name}
			                      </div>
		                      </center> 
	                      </td>
	                      <td colspan="5">
							  <div>
								    <c:forEach items="${module.functions}" var="function">
									  <input type="checkbox" name="nid" value="${function.id}" />${function.name}
									</c:forEach>
							  </div>
						  </td>
	                      
	                    </tr>
                  </c:forEach>
                    <tr >
                     
                      <td colspan="2">
					  <div class="row-fluid">
		               <div class="span12">
						<div class="span4">
						</div>
						<div class="span4">
							<a href="javascript:addFunctions('${role.id}')" >
							 <button class="btn btn-block btn-info" type="button">授权按钮</button>
							</a>
						</div>
						<div class="span4">
						</div>
						</div>
					  </td>
                    </tr>
                   
                  </tbody>
                </table>
	
		</div>
	</div>
</div>
</body>
</html>
