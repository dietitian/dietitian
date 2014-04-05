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
	function backs(){
	    window.location.href="${pageContext.request.contextPath}/nutrition/listp?time=1";
	}
	
	function deleteNutrition(id,name) {
		
		 if(confirm("您确删除名称为 : '"+name+"' 这条记录吗？")){
			$.get("${pageContext.request.contextPath}/nutrition/delete/" + id, function(
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
          <legend><font color="blue">营养数据&gt;&gt;食品营养表</font></legend></div>
		  	<display:table name="nutritionList.list" id="nutrition"
				requestURI="${pageContext.request.contextPath}/nutrition/list"
				pagesize="${pageSize}" partialList="true"
				size="${nutritionList.fullListSize}" class="table">
				<display:column property="name" title="名称" />
				<display:column property="region" title="地区" />
				<display:column property="edible_part" title="可食部分" />
				<display:column property="energy" title="能量" />
				<display:column property="moisture" title="水份" />
				<display:column property="protein" title="蛋白质" />
				<display:column property="fat" title="脂肪" />
				<display:column property="df" title="膳食纤维" />
				<display:column property="carbohydrate" title="碳水化合物" />
				<display:column property="vax" title="视黄醇当量" />
				<display:column property="vb1" title="硫胺素VB1" />
				<display:column property="vb2" title="核黄素VB2" />
				<display:column property="vb3" title="尼克酸（烟酸VPP）" />
				<display:column property="ve" title="维生素E" />
				<display:column property="na" title="钠" />
				<display:column property="ca" title="钙" />
				<display:column property="fe" title="铁" />
				<display:column property="vc" title="维生素C" />
				<display:column property="cholesterol" title="胆固醇" />
				<display:column title="操作">
					<!-- <a data-toggle="modal" data-target="#addModal"  ><i
						class="icon-edit icon-large"></i></a> -->
						<a href="${pageContext.request.contextPath}/nutrition/update?id=${nutrition.id}&pcategory=${nutrition.category}"><i
				class="icon-edit icon-large"></i></a>
					&nbsp;&nbsp;&nbsp;<a href="javascript:deleteNutrition('${nutrition.id}','${nutrition.name}')"><i
						class="icon-trash icon-large"></i></a>
				</display:column>
			</display:table>
			<br />
			<a href="${pageContext.request.contextPath}/nutrition/addNutritionInit?pcategory=${pcategory}">
				<button class="button button-rounded">新增</button>
			</a>
			&nbsp; &nbsp; &nbsp;
		    <button class="button button-rounded" type="button" onclick="backs();">
				     <i class="icon-undo"></i> 返回
			</button>
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
