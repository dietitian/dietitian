<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>  
<%@ taglib uri="http://www.springmodules.org/tags/commons-validator" prefix="validator" %> 
<validator:javascript formName="user" staticJavascript="true" xhtml="true" cdata="false" />  

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增用户</title>
<script type="text/javascript">
  $(function(){

    //1. 简单写法：
    $("#form1").validation();
    $("#submit1").on('click',function(event){
        // 2.最后要调用 valid()方法。
        if ($("#form1").valid()==false){
          alert('填写信息不完整。');
          return false;
        }
      });

	$("#name").blur(function(){
		 var name = $.trim($('#name').val());;
		 
		if(name == null || name == ""){
			$("#nameSearch").html("");
		}else{
			 $.post("${pageContext.request.contextPath}/user/hasUser",{name:name},function(data){
				 if ("success" == data.result) {
			    		$("#nameSearch").css("color","blue");
				    	$("#nameSearch").html("用户可以使用。");
				    	
					} else if("hasUser"== data.result){
						$("#nameSearch").css("color","red");
						$("#nameSearch").html("用户已经存在！");
					}else{
						$("#nameSearch").css("color","red");
						$("#nameSearch").html("系统错误！请联系管理员！");
					}
			   }); 
		}
		
		});




    
  })
  
  function backs(){
            window.location.href="${pageContext.request.contextPath}/user/list";
        }
  
    
  </script>
</head>
<body>
	<div id="legend">
          <legend><font color="blue">安全管理&gt;&gt;用户管理&gt;&gt;新增

</font></legend>
	<!-- 普通表单，springmvc表单验证 -->
	<form method="post" id="form1"
		action="${pageContext.request.contextPath}/user/doCreate"
		onsubmit="return validateUser(this);"
		>
		<div class="form-group">
			<spring:bind path="user.realname">
				<label for="realname" class="col-sm-1 control-label">姓名：</label>
				<div class="col-sm-2">
				<input type="text" id="realname" name="realname" value="${user.realname}" check-type="required" required-message="请填写你的大名。"/>
				 </div>
					 <c:if test="${status.error}">
			          <font color="#FF0000">
			        		  错误:
			           <c:forEach items="${status.errorMessages}" var="error">
			                <c:out value="${error}"/>
			           </c:forEach>
			          </font>
			        </c:if>
			       
			</spring:bind>
		</div>
		<br /><br />
		<div class="form-group">
			<spring:bind path="user.name">
				<label for="name" class="col-sm-1 control-label">用户名：</label>
				<div class="col-sm-2">
				<input type="text" id="name" name="name" value="${user.name}" check-type="required" required-message="请填写你的大名。"/>
				<p id="nameSearch"></p>
				 </div>
					 <c:if test="${status.error}">
			          <font color="#FF0000">
			        		  错误:
			           <c:forEach items="${status.errorMessages}" var="error">
			                <c:out value="${error}"/>
			           </c:forEach>
			          </font>
			        </c:if>
			       
			</spring:bind>
		</div>
		<br /><br />
		<div class="form-group">
			<spring:bind path="user.password">
				<label for="name" class="col-sm-1 control-label">密码：</label>
				<div class="col-sm-2">
				<input type="password" id="password" name="password" value="${user.password}" check-type="required" required-message="请填写登录密码"/>
				 </div>
					 <c:if test="${status.error}">
			          <font color="#FF0000">
			        		  错误:
			           <c:forEach items="${status.errorMessages}" var="error">
			                <c:out value="${error}"/>
			           </c:forEach>
			          </font>
			        </c:if>
			</spring:bind>
		</div>
		<br /><br />
		<div class="form-group">
			<spring:bind path="user.telephone">
			<label for="telephone" class="col-sm-1 control-label">手机号码：</label>
			<div class="col-sm-2">
			<input type="text" name="telephone" id="telephone" value="${user.telephone}" check-type="required"  required-message="电话为必填项"/>
			</div>
			<c:if test="${status.error}">
			          <font color="#FF0000">
			        		  错误:
			           <c:forEach items="${status.errorMessages}" var="error">
			                <c:out value="${error}"/>
			           </c:forEach>
			          </font>
			        </c:if>
			</spring:bind>
		</div><br /><br />
		<div class="form-group">
			<spring:bind path="user.email">
			<label for="email" class="col-sm-1 control-label">邮箱：</label>
			<div class="col-sm-2">
			<input type="text" name="email" id="email" value="${user.email}" check-type="mail" mail-message="邮箱格式不正确" />
			</div>
			<c:if test="${status.error}">
			          <font color="#FF0000">
			        		  错误:
			           <c:forEach items="${status.errorMessages}" var="error">
			                <c:out value="${error}"/>
			           </c:forEach>
			          </font>
			        </c:if>
			</spring:bind>
		</div><br /><br />
		<div class="control-group">
	          <!-- Select Basic -->
	          <label class="control-label">性别：</label>
	          <div class="controls">
	            <select class="input-xlarge" name="gender" id="gender">
	            	<option value="1" selected="selected">男</option>
			      	<option value="0">女</option>
			     </select>
	          </div>
	      </div>
	     <br></br>
		<div class="form-group">
			<spring:bind path="user.age">
			<label for="age" class="col-sm-1 control-label">年龄：</label>
			<div class="col-sm-2">
			<input type="text" name="age" id="age" value="${user.age}" check-type="required" minlength="2"/>
			</div>
			<c:if test="${status.error}">
			          <font color="#FF0000">
			        		  错误:
			           <c:forEach items="${status.errorMessages}" var="error">
			                <c:out value="${error}"/>
			           </c:forEach>
			          </font>
			        </c:if>
			</spring:bind>
		</div><br /><br />
		<div class="form-group">
			<label>描述：</label>
			<textarea cols="80" id="descriptionEditor" name="description" rows="10"></textarea>
			<ckeditor:replace replace="descriptionEditor" basePath="${pageContext.request.contextPath}/static/ckeditor/" />
		</div>
		<div class="control-group">
			<button class="button button-rounded" type="submit" id="submit1">
				<i class="icon-ok"></i> 确认
			</button>
			&nbsp; &nbsp; &nbsp;
		    <button class="button button-rounded" type="button" onclick="backs();">
				     <i class="icon-undo"></i> 返回
			</button>
		</div>
	</form>
	
	<!-- spring form表单  后台hibernate验证-->
	<%-- <form:form method="post" modelAttribute="user"
		action="${pageContext.request.contextPath}/user/doCreate">
		<div class="control-group">
			姓名：<form:input path="name"/><form:errors path="name"/>
		</div>
		<br />
		<div class="control-group">
			年龄：<form:input path="age"/><form:errors path="age"/>
		</div>
		<div class="control-group">
			描述：<form:textarea path="description" id="descriptionEditor"/><form:errors path="description"/>
			<ckeditor:replace replace="descriptionEditor" basePath="${pageContext.request.contextPath}/static/ckeditor/" />
		</div>
		<p>
			<button class="button button-rounded" type="submit">
				<i class="icon-ok"></i> 确认
			</button>
			&nbsp; &nbsp; &nbsp; <a
				href="${pageContext.request.contextPath}/user/list">
				<button class="button button-rounded" type="button">
					<i class="icon-undo"></i> 返回
				</button>
			</a>
		</p>
	</form:form> --%>
	
	
	<!-- 基本表单 -->
	<%-- <form method="post"
		action="${pageContext.request.contextPath}/user/doCreate">
		<div class="control-group">
			姓名：<input type="text" name="name" />
		</div>
		<br />
		<div class="control-group">
			年龄：<input type="text" name="age" />
		</div>
		<div class="control-group">
			描述：
			<textarea cols="80" id="descriptionEditor" name="description" rows="10"></textarea>
			<ckeditor:replace replace="descriptionEditor" basePath="${pageContext.request.contextPath}/static/ckeditor/" />
		</div>
		<p>
			<button class="button button-rounded" type="submit">
				<i class="icon-ok"></i> 确认
			</button>
			&nbsp; &nbsp; &nbsp; <a
				href="${pageContext.request.contextPath}/user/list">
				<button class="button button-rounded" type="button">
					<i class="icon-undo"></i> 返回
				</button>
			</a>
		</p>
	</form> --%>
</body>
</html>
