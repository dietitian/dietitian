<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath }/static/images/favicon.ico">
<script src="${pageContext.request.contextPath }/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/style.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/static/css/style_grey.css" />
<script type="text/javascript">
	$(function(){
		$("#loginBtn").click(function(){
			 var name = $('#loginformidInput').val();
			 var password = $('#loginformpwdInput').val();
			 var checkCode = $('#loginformcodeInput').val();
			if(name!=null && name!="" &&
					password!=null && password!="" &&
					checkCode!=null && checkCode!="" 
				){

				 $.post("${pageContext.request.contextPath}/user/login",$("#loginform").serialize(),function(data){
						
				    	if ("success" == data.result) {
							window.location.href ="${pageContext.request.contextPath}/";
						} else if("checkCodeError"== data.result){
							$('#loginError').html("验证码不正确");
							document.getElementById('loginformvCode').src = '${pageContext.request.contextPath }/validatecode.jsp?'+Math.random();
						}else if("passWordError"== data.result){
							$('#loginError').html("密码不正确");
							document.getElementById('loginformvCode').src = '${pageContext.request.contextPath }/validatecode.jsp?'+Math.random();
						}else if("nameError"== data.result){
							$('#loginError').html("用户不存在");
							document.getElementById('loginformvCode').src = '${pageContext.request.contextPath }/validatecode.jsp?'+Math.random();
						}else{
							$('#loginError').html("系统错误，请联系管理员！");
							document.getElementById('loginformvCode').src = '${pageContext.request.contextPath }/validatecode.jsp?'+Math.random();
						}
				
				       
				   }); 

			}else{
				$('#loginError').html("请检查登录信息");
			}
			
		});

		$('#loginformidInput').blur(function(){
			checkName();
			
		});
		
		$('#loginformpwdInput').blur(function(){
			checkPassword();
		});
		
		$('#loginformcodeInput').blur(function(){
			
			checkCodeInput();
		});
	});
	function checkName(){
		
		 var name = $('#loginformidInput').val();
		 //alert(name);
		 if(name == null || name == "" ){
			$('#loginError').html("账号不能为空");
			return false;
		 }else{
			$('#loginError').html("");
			return true;
		}  
	}

	function checkPassword(){
		
		 var name = $('#loginformpwdInput').val();
		 //alert(name);
		 if(name == null || name == "" ){
			$('#loginError').html("密码不能为空");
			return false;
		 }else{
			$('#loginError').html("");
			return true;
		}  
	}
	function checkCodeInput(){
		
		 var name = $('#loginformcodeInput').val();
		 //alert(name);
		 if(name == null || name == "" ){
			$('#loginError').html("验证码不能为空");
			return false;
		 }else{
			$('#loginError').html("");
			return true;
		} 
	}
	
			
</script>
<style>
input[type=text] {
	width: 80%;
	height: 25px;
	font-size: 12pt;
	font-weight: bold;
	margin-left: 45px;
	padding: 3px;
	border-width: 0;
}

input[type=password] {
	width: 80%;
	height: 25px;
	font-size: 12pt;
	font-weight: bold;
	margin-left: 45px;
	padding: 3px;
	border-width: 0;
}

#loginformcodeInput {
	margin-left: 1px;
	margin-top: 1px;
}

#loginformvCode {
	margin: 0px 0 0 60px;
	height: 34px;
}
</style>
</head>
<body>
	<div
		style="width: 900px; height: 50px; position: absolute; text-align: left; left: 50%; top: 50%; margin-left: -450px;; margin-top: -280px;">
		<img src="${pageContext.request.contextPath }/static/images/logo.png" style="border-width: 0; margin-left: 0;" />
		<span style="float: right; margin-top: 35px; color: #488ED5;">小小营养师后台管理系统</span>
	</div>
	<div class="main-inner" id="mainCnt"
		style="width: 900px; height: 440px; overflow: hidden; position: absolute; left: 50%; top: 50%; margin-left: -450px; margin-top: -220px; background-image: url('${pageContext.request.contextPath }/static/images/bg_login.jpg')">
		<div id="loginBlock" class="login"
			style="margin-top: 80px; height: 255px;">
			<div class="loginFunc">
				<div id="lbNormal" class="loginFuncMobile">员工登录</div>
			</div>
			<div class="loginForm">
				<form id="loginform" name="loginform"  cssClass="niceform"  >
					<div>
						<p id="loginError" style="color: red;"></p>
					</div>
					<div id="idInputLine" class="loginFormIpt showPlaceholder"
						style="margin-top: 5px;">
						<input type="text" id="loginformidInput" name="name" cssClass="loginFormTdIpt" maxlength="50" tabindex="1" title="请输入帐号"  />
						<label for="idInput" class="placeholder" id="idPlaceholder">帐号：</label>
					</div>
					<div class="forgetPwdLine">
					</div>
					<div id="pwdInputLine" class="loginFormIpt showPlaceholder">
						<input type="password" id="loginformpwdInput" cssClass="loginFormTdIpt" name="password" tabindex="2" title="请输入密码" />
						<label for="pwdInput" class="placeholder" id="pwdPlaceholder">密码：</label>
					</div>
					<div class="loginFormIpt loginFormIptWiotTh"
						style="margin-top:58px;">
						<div id="codeInputLine" class="loginFormIpt showPlaceholder"
							style="margin-left:0px;margin-top:-40px;width:50px;">
							<input id="loginformcodeInput" class="loginFormTdIpt" type="text"
								name="checkcode" title="请输入验证码" />
							<img id="loginformvCode" src="${pageContext.request.contextPath }/validatecode.jsp"
								onclick="javascript:document.getElementById('loginformvCode').src='${pageContext.request.contextPath }/validatecode.jsp?'+Math.random();" />
						</div>
						<a href="javascript:void(0)" id="loginform:j_id19" name="loginform:j_id19">
						<span
							id="loginBtn" class="btn btn-login"
							style="margin-top:-36px;">登录</span>
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div
		style="width: 900px; height: 50px; position: absolute; text-align: left; left: 50%; top: 50%; margin-left: -450px;; margin-top: 220px;">
		<span style="color: #488ED5;">Powered By www.dietitian.com</span><span
			style="color: #488ED5;margin-left:10px;">推荐浏览器（右键链接-目标另存为）：<a
			href="${pageContext.request.contextPath }/Firefox.rar">Firefox</a>
		</span><span style="float: right; color: #488ED5;">小小营养师后台管理系统</span>
	</div>
</body>
</html>
<%-- <form action="${pageContext.request.contextPath}/user/login" method="post">
	<table>
		 <tr>
                        <td>用户名：</td>
                        <td><input  type="text"  name="name" /></td>
                    </tr>
                    <tr>
                        <td>密码：</td>
                        <td><input  type="password" name="password" /></td>
                    </tr>
					<tr>
						<td>
							<input  type="submit" value="登录" />
						</td>
					</tr>
	</table>
</form> --%>
