<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="${pageContext.request.contextPath }/static/images/favicon.ico">
<meta charset="utf-8">
<title><decorator:title default="管理模板页" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- basic styles -->
<link href="${contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link rel="stylesheet" href="${contextPath}/static/bootstrap/css/font-awesome.min.css">
<link rel="stylesheet" href="${contextPath}/static/bootstrap/css/daterangepicker.css">
<link rel="stylesheet" href="${contextPath}/static/bootstrap/css/buttons.css">
<link rel="stylesheet" href="${contextPath}/static/bootstrap/css/bootstrap-datetimepicker.min.css">
<!--[if IE 7]>
		  <link rel="stylesheet" href="${contextPath}/bootstrap/static/css/font-awesome-ie7.min.css" />
		<![endif]-->

<!-- page specific plugin styles -->

<!-- fonts -->
<!-- 
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300"> -->

<!-- ace styles -->

<link rel="stylesheet" href="${contextPath}/static/bootstrap/css/ace.min.css">

<!--[if lte IE 8]>
		  <link rel="stylesheet" href="${contextPath}/static/bootstrap/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->
    <style type="text/css" media="all">
       @import url("${pageContext.request.contextPath}/static/css/screen.css");
    </style>

<!-- ace settings handler -->

<script src="${contextPath}/static/bootstrap/js/ace-extra.min.js"></script>

<!--左侧隐藏 换肤-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
		<script src="${contextPath}/static/js/html5shiv.js"></script>
		<script src="${contextPath}/static/bootstrap/js/respond.min.js"></script>
		<![endif]-->

	<!--[if !IE]> -->

	<script src="${contextPath}/static/jquery/jquery.min.js"></script>

	<!-- <![endif]-->

	<!--[if IE]>
<script src="static/jquery/jquery.min.js"></script>
<![endif]-->

	<!--[if !IE]> -->

	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='${contextPath}/static/jquery/jquery-2.0.3.min.js'>"
								+ "<"+"/script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${contextPath}/static/jquery/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
	<decorator:head />
</head>

<body>
	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed');
			} catch (e) {
			}
		</script>
		<div class="navbar-container" id="navbar-container">
			<%@ include file="include/navbar.jsp" %>
		</div>
		<!-- /.container -->
	</div>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed');
			} catch (e) {
			}
		</script>
		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>
			<div class="sidebar" id="sidebar">
				<%@ include file="include/sidebar.jsp" %>
			</div>
			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed');
						} catch (e) {
						}
					</script>
					<ul class="breadcrumb">
						<li>
							<i class="icon-home home-icon"></i>
							<a href="${contextPath}/">主页</a>
						</li>
					</ul>
					<!-- .breadcrumb -->
				</div>
				<div class="page-content">
					<decorator:body />
				</div>
				<!-- PAGE CONTENT ENDS -->
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>

	<!-- /.main-container-inner -->

	<a href="#" id="btn-scroll-up"
		class="btn-scroll-up btn btn-sm btn-inverse"> <i
		class="icon-double-angle-up icon-only bigger-110"></i>
	</a>

	<!-- /.main-container -->

	<!-- basic scripts -->

	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='${contextPath}/static/jquery/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<script src="${contextPath}/static/bootstrap/js/bootstrap.min.js"></script>


	<!-- page specific plugin scripts -->

	<!-- ace scripts -->

	<script src="${contextPath}/static/bootstrap/js/ace-elements.min.js"></script>
	<!--换肤-->
	<script src="${contextPath}/static/bootstrap/js/ace.min.js"></script>
	<!--手风琴-->
   <script src="${contextPath}/static/bootstrap/js/fuelux.spinner.min.js"></script>
   <!--按钮-->
   <script src="${contextPath}/static/bootstrap/js/buttons.js"></script>
   <!--日期-->
   <script src="${contextPath}/static/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
   <!-- 表单验证 -->
    <script src="${contextPath}/static/bootstrap/js/bootstrap3-validation.js"></script>
</body>
</html>