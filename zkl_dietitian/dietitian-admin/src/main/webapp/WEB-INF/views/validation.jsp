<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <script type="text/javascript">
  $(function(){

    //1. 简单写法：
    $("#form1").validation();
  
    // 要从后台数据确定填的邮箱是不是已注册过了，采用回调方法
    // $("form").validation(function(obj,params){
    //   if (obj.id=='mail'){
    //     $.post("/verifymail",{mail :$(obj).val()},function(data){
    //       params.err = !data.success;
    //       params.msg = data.msg;
    //     });
    //   }}
    //   ,{reqmark:true} //这个参数是设必填不要显示有星号，默认是有星号的
    // );

  //.注册
    $("#submit1").on('click',function(event){
      // 2.最后要调用 valid()方法。
      if ($("#form1").valid()==false){
        $("#error-text").text("填写信息不完整。")
        return false;
      }
    })

    $("#form2").validation();
    //.注册
    $("#submit2").on('click',function(event){
      // 2.最后要调用 valid()方法。
      if ($("#form2").valid()==false){
        alert('填写信息不完整。');
        return false;
      }
    });


    $("#form3").validation();
    //.注册
    $("#submit3").on('click',function(event){
      // 2.最后要调用 valid()方法。
      if ($("#form3").valid()==false){
        alert('填写信息不完整。');
        return false;
      }
    });

  })
</script>
 </head>
   <body>
    <h1><a href="https://github.com/mrlong/bootstrap3-validation.js">bootstrap3 of validation</a></h1>

<!-- 水平排列的表单 -->
     <form class="form-horizontal"  id="form1" action="#" role="form">
      <div class="form-group">
        <label for="mail" class="col-sm-2 control-label">Email</label>
        <div class="col-sm-6">
          <input type="text" class="form-control" id="mail" placeholder="xxxx@qq.com" check-type="mail required">
        </div>
        <div class="col-sm-offset-2 col-sm-10">
          <span class="help-block" style="color: #000000;padding-top: 10px;">请填写真实的邮箱，在注册之前会将验证码发送到你的邮箱内.</span>
        </div>
      </div>

      <div class="form-group">
        <label for="name" class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-6">
          <input type="text" class="form-control" id="name" check-type="required" required-message="请填写你的大名。">
        </div>
      </div>

      <div class="form-group">
        <label for="pw1" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-6">
          <input type="password" class="form-control" id="pw1" check-type="required" minlength="6">
        </div>
      </div>

      <div class="form-group">
        <label for="pw2" class="col-sm-2 control-label">确认密码</label>
        <div class="col-sm-6">
          <input type="password" class="form-control" id="pw2" check-type="required" minlength="6">
        </div>
      </div>  

      <div class="form-group">
        <label for="vercode" class="col-sm-2 control-label">验证码</label>
        <div class="col-sm-6">
          <input type="text" class="form-control" id="vercode" check-type="number" range="2.2,5">
          <button class="btn btn-link" type="button" data-toggle="tooltip" title="验证码会以邮件的方式发送到你的上面填写的邮箱内" data-placement="right" id="getvercode" >获取验证码</button>
        </div>
      </div>  

      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" id="submit1" class="btn btn-primary col-sm-4">注册</button>
        </div>
      </div>

      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <span id="error-text" style="color: #FF0000;"></span>
        </div>
      </div>

    </form>

<!-- 基本表单 -->
<div class="container">
<h1>基本表单</h1>
<form role="form" id="form2">
  <div class="form-group has-error">
    <label for="exampleInputEmail1" >Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Enter email" check-type="required">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1" >Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" check-type="required">
  </div>
  <div class="form-group ">
    <label for="exampleInputFile">File input</label>
    <input type="File" id="exampleInputFile" class="form-control" check-type="required">
    <p class="help-block">Example block-level help text here.</p>
  </div>

  <div class="checkbox ">
    <label>
      <input type="checkbox"> Check me out
    </label>
  </div>
  <button type="submit" id="submit2" class="btn btn-primary col-sm-4">提交</button>
</form>
</div>

<div class="container">
<h1>内联表单</h1>
<form class="form-inline" role="form" id="form3">
  <div class="form-group">
    <label class="sr-only" for="exampleInputEmail2">Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail2" placeholder="Enter email" check-type="required">
  </div>
  <div class="form-group">
    <label class="sr-only" for="exampleInputPassword2">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password" check-type="required">
  </div>
  <div class="checkbox">
    <label>
      <input type="checkbox" > Remember me<span>ssss</span>
    </label>
  </div>
  <button type="submit" class="btn btn-primary" id="submit3">确定</button>
</form>
</div>



  </body>
</html>