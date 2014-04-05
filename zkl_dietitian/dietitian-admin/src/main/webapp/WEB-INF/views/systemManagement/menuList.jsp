<%@page import="java.awt.MenuContainer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${contextPath}/static/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="${contextPath}/static/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="${contextPath}/static/js/jquery.ztree.exhide-3.5.js"></script>
<title>菜单管理</title>

<script type="text/javascript">

	
	//删除
	function deletemenu(id) {
		alert(id);
		if (window.confirm("你确定要删除该信息吗？")) {
			$.get("${pageContext.request.contextPath}/menu/delete/" + id,
					function(data) {
						if ("success" == data.result) {
								alert("删除成功！");
							window.location.reload();
						} else {
							alert("我还子节点！不能删我！");
						}
					});
		}
	};

	//数据
	var param =[
				<%
			JSONArray jsonArray = (JSONArray) request.getAttribute("menuListTree");
			int i;
			for (i = 0; i < jsonArray.size(); i++) {
				JSONObject obj = (JSONObject) jsonArray.get(i);
				String id = obj.getString("id");
				String name = obj.getString("name");
				String pId = obj.getString("pId");
				String state = obj.getString("state");
				if (state.equals("1")) {
					state = "false";
				} else {
					state = "true";
				}
				String url = obj.getString("url");%>
					<%if (i == (jsonArray.size() - 1)) {%>
					{ id:<%=id%>, pId:<%=pId%>, name:"<%=name%>", urlPath:"<%=url%>", title:"<%=name%>",isHidden:<%=state%>}
					<%} else {%>
					{ id:<%=id%>, pId:<%=pId%>, name:"<%=name%>", urlPath:"<%=url%>",title:"<%=name%>",isHidden:<%=state%>},
					<%}%>
	 			<%}%>
	 			];

	
	//初始化
	 window.onload = function() {
		console.log($("#treeDemo"));
		for (var i = 0; i < $(".state").length; i++) {
			var state = $(".state")[i].innerHTML;
			if (state == '1') {
				$(".state")[i].innerHTML = "启用";
			} else {
				$(".state")[i].innerHTML = "停用";
			}
		}
	} 


		
	//tree
	 $(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, param);
			setTitle();
			count();
		});
		
	 function beforeClick(treeId, treeNode, clickFlag) {
			window.location.href='${pageContext.request.contextPath}/menu/list?parentId='+treeNode.id;
		}
		
	var setting = {
			view: {
				dblClickExpand: false,
				showLine: true,
				selectedMulti: false
			},
			data: {
				simpleData: {
					enable:true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: ""
				}
			},
			callback: {
				beforeClick: beforeClick
			}
		};


	
	function setTitle(node) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = node ? [ node ] : zTree.transformToArray(zTree.getNodes());
		for (var i = 0, l = nodes.length; i < l; i++) {
			var n = nodes[i];
			n.title = "[" + n.id + "] isFirstNode = " + n.isFirstNode
					+ ", isLastNode = " + n.isLastNode;
			zTree.updateNode(n);
		}
	}
	function count() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"), hiddenCount = zTree
				.getNodesByParam("isHidden", true).length;
		$("#hiddenCount").text(hiddenCount);
	}
	
</script>
</head>

<body>
<div class="container-fluid">
		<div id="legend">
          <legend><font color="blue">系统管理&gt;&gt;菜单管理  </font></legend>
    </div>
	<div class="row-fluid">
		<div class="span3">
				<div style="BORDER-RIGHT: #999999 1px dashed;background-color: #f0f6e4;height: 500px">
						<ul id="treeDemo" class="ztree" style="overflow:auto;"></ul>
				</div>
		</div>
		<div class="span9">
			<display:table name="menuList.list" id="menu" class="table"
					requestURI="${pageContext.request.contextPath}/menu/list"
					pagesize="${pageSize}" partialList="true"
					size="${menuList.fullListSize}">
					<display:column property="name" title="菜单名称" />
					<display:column property="path" title="url" />
					<display:column property="sort" title="排序" />
					<display:column property="state" title="状态" class="state" />
					<display:column property="createTime" title="创建时间" />
					<display:column property="createPerson" title="创建人" />
					<display:column title="操作">
						<a
							href="${pageContext.request.contextPath}/menu/update/${menu.id}"><i
							class="icon-edit icon-large"></i></a>
			&nbsp;&nbsp;&nbsp;<a href="javascript:deletemenu(${menu.id})"><i
							class="icon-trash icon-large"></i></a>
			&nbsp;&nbsp;&nbsp;<a
							href="${pageContext.request.contextPath}/menu/describe/${menu.id}"><i
							class="icon-search"></i></a>
					</display:column>
				</display:table> <br />
				<div>
					<a href="${pageContext.request.contextPath}/menu/create?parentId=${parentId}">
						<button class="button button-rounded">新增</button>
					</a>
				</div>
		</div>
	</div>
</body>
</html>
