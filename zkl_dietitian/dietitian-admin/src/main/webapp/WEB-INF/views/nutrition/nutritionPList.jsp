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

<title>食品营养表</title>
<script type="text/javascript">

	function deleteNutrition(id,name) {
		
		 if(confirm("您确删除名称为 : '"+name+"' 这条记录吗？")){
			$.get("${pageContext.request.contextPath}/nutrition/deletep/" + id, function(
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
          <legend><font color="blue">营养数据&gt;&gt;食品分类表</font></legend></div>
		  	<display:table name="nutritionList.list" id="nutrition"
				requestURI="${pageContext.request.contextPath}/nutrition/list"
				pagesize="${pageSize}" partialList="true"
				size="${nutritionList.fullListSize}" class="table">
				<display:column property="pcategory" title="自身编号" />
				<display:column property="name" title="名称" />
				<display:column property="description" title="说明" />
				<display:column title="操作">
					<!-- <a data-toggle="modal" data-target="#addModal"  ><i
						class="icon-edit icon-large"></i></a> -->
						<a href="${pageContext.request.contextPath}/nutrition/updatep/${nutrition.id}"><i
				class="icon-edit icon-large"></i></a>
					&nbsp;&nbsp;&nbsp;<a href="javascript:deleteNutrition('${nutrition.id}','${nutrition.name}')"><i
						class="icon-trash icon-large"></i></a>
						&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/nutrition/list?pcategory=${nutrition.pcategory}" >
							食品营养元素
						</a>
				</display:column>
			</display:table>
			<br />
			<div class="span12">
				<a href="${pageContext.request.contextPath}/nutrition/addNutritionInitp" style="margin-left:auto;margin-right:auto;">
				<button class="button button-rounded">新增</button>
			</a>
			</div>
		</div>
		<div class="span12">
			<form class="form-inline" action="${pageContext.request.contextPath}/nutrition/upload" method="post" enctype="multipart/form-data">
				<fieldset>
				请选择文件: <input type="file" name="nutritionFile" /> <button type="submit" class="btn">提交</button> 食品营养表上传excle格式
				</fieldset>
			</form>
		</div>
	</div>
	      <div class="modal-footer">
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	 </div><!-- /.modal -->
</div>
</body>
</html>
