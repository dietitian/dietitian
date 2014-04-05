<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>营养数据</title>
<script type="text/javascript">


</script>
</head>
<body>
	<div id="legend">
          <legend><font color="blue">营养数据&gt;&gt;食品营养&gt;&gt;修改</font></legend>
</div>
	<form method="post"
		action="${pageContext.request.contextPath}/nutrition/doUpdate"
		class="form-horizontal">
		<p>
			<input type="hidden" name="id" value="${nutrition.id}" />
			<div class="control-group">
				名称：<input type="text" name="name" value="${nutrition.name}" />
			</div>
			<br />
			<div class="control-group">
				地区：<input type="text" name="region" value="${nutrition.region}" />
			</div>
			<br />
			<div class="control-group">
				可食部分：<input type="text" name="edible_part" value="${nutrition.edible_part}" />
			</div>
			<br />
			<div class="control-group">
				能量：<input type="text" name="energy" value="${nutrition.energy}" />
			</div>
			<br />
			<div class="control-group">
				水份：<input type="text" name="moisture" value="${nutrition.moisture}" />
			</div>
			<br />
			<div class="control-group">
				蛋白质：<input type="text" name="protein" value="${nutrition.protein}" />
			</div>
			<br />
			<div class="control-group">
				脂肪：<input type="text" name="fat" value="${nutrition.fat}" />
			</div>
			<br />
			<div class="control-group">
				膳食纤维：<input type="text" name="df" value="${nutrition.df}" />
			</div>
			<br />
			<div class="control-group">
				碳水化合物：<input type="text" name="carbohydrate" value="${nutrition.carbohydrate}" />
			</div>
			<br />
			<div class="control-group">
				视黄醇当量：<input type="text" name="vax" value="${nutrition.vax}" />
			</div>
			<br />
			<div class="control-group">
				硫胺素VB1：<input type="text" name="vb1" value="${nutrition.vb1}" />
			</div>
			<br />
			<div class="control-group">
				核黄素VB2：<input type="text" name="vb2" value="${nutrition.vb2}" />
			</div>
			<br />
			<div class="control-group">
				尼克酸（烟酸VPP）：<input type="text" name="vb3" value="${nutrition.vb3}" />
			</div>
			<br />
			<div class="control-group">
				维生素E：<input type="text" name="ve" value="${nutrition.ve}" />
			</div>
			<br />
			<div class="control-group">
				钠：<input type="text" name="na" value="${nutrition.na}" />
			</div>
			<br />
			<div class="control-group">
				钙：<input type="text" name="ca" value="${nutrition.ca}" />
			</div>
			<br />
			<div class="control-group">
				铁：<input type="text" name="fe" value="${nutrition.fe}" />
			</div>
			<br />
			<div class="control-group">
				维生素C：<input type="text" name="vc" value="${nutrition.vc}" />
			</div>
			<br />
			<div class="control-group">
				胆固醇：<input type="text" name="cholesterol" value="${nutrition.cholesterol}" />
			</div>
			<br />
			<div class="control-group">
				类别：<input type="text" name="category" value="${nutrition.category}" />
			</div>
			<br />
			<div class="control-group">
				类：<input type="text" name="types" value="${nutrition.types}" />
			</div>
			
			
		</p>
		<p>
			<button class="button button-rounded" type="submit">
				<i class="icon-ok"></i> 确认
			</button>
			&nbsp; &nbsp; &nbsp; <a
				href="${pageContext.request.contextPath}/nutrition/list?pcategory=${nutrition.category}">
				<button class="button">
					<i class="icon-undo"></i> 返回
				</button>
			</a>
		</p>
	</form>
</body>
</html>
