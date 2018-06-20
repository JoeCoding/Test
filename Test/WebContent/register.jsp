<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<link href="css/register/css/register.css" type="text/css" rel="stylesheet">  
<script type="text/javascript" src="js/register/register.js" ></script>
<title>注册界面</title>
</head>
<body>
	<form action="registerServlet" method="post" enctype="multipart/form-data">
       <div>
           <p>用户名：</p>
           <input type="text" id="username" name="username" onblur="validate()">
       	   <span id="res" style="color:red" ></span>
       </div>
       <div>
           <p>密码：</p>
           <input type="password" name="password">
       </div>
       <div>
           <p>年龄：</p>
           <input type="number" name="age">
       </div>
       <div>
           <p>联系电话：</p>
           <input type="number" name="telephone">
       </div>
       <div>
           <p>邮箱：</p>
           <input type="email" name="email">
       </div>
       <div>
           <p>头像：</p>
           <input type="file" name="photo">
       </div>
       <div>
           <br>
           <button type="submit">注册</button>
       </div>
       </form>
</body>
</html>