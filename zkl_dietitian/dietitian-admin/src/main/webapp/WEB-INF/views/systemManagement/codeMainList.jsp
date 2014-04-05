<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代码表管理</title>
<script type="text/javascript">
	function deleteObj(id,codeType) {
		if(window.confirm("你确定要删除该信息吗？")){
			$.get("${pageContext.request.contextPath}/codeMain/delete?id="+ id+"&codeType="+codeType, function(
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
	window.onload = function(){
		for(var i = 0;i < $(".isValid").length;i++){
			var zhi = $(".isValid")[i].innerHTML;
			if(zhi == '1'){
				$(".isValid")[i].innerHTML="是";
			}else{
				$(".isValid")[i].innerHTML="否";
			}
		}
	}
</script>
</head>

<body>

	<div id="legend">
          <legend><font color="blue">系统管理&gt;&gt;代码表管理

</font></legend>
</div>
	<display:table name="codeMainList.list" id="codeMain" class="table" 
		requestURI="${pageContext.request.contextPath}/codeMain/list"
		pagesize="${pageSize}" partialList="true"
		size="${codeMainList.fullListSize}">
		<display:column property="codeType" title="代码类型" />
		<display:column property="codeName" title="代码名称" />
		<display:column property="createTime" title="创建时间" />
		<display:column property="updateTime" title="最后修改时间" />
		<display:column property="isValid" title="是否有效" class="isValid"/>
		<display:column property="createPerson" title="创建人" />
		<display:column title="操作">
			<a href="${pageContext.request.contextPath}/codeMain/update/${codeMain.id}"><i
				class="icon-edit icon-large"></i></a>
			&nbsp;&nbsp;&nbsp;<a href="javascript:deleteObj('${codeMain.id}','${codeMain.codeType }')"><i
				class="icon-trash icon-large"></i></a>
			&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/codeMain/describe/${codeMain.id}"><i
				class="icon-search"></i></a>
			&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/code/list?codeType=${codeMain.codeType}"><i
				class="icon-cogs"></i></a>				
		</display:column>
	</display:table>
	<br />
	<div>
		<a href="${pageContext.request.contextPath}/codeMain/create">
			<button class="button button-rounded">新增</button>
		</a>
	</div>

</body>
</html>
