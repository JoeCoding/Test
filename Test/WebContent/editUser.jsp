<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form action="editUserServlet?action=update" method="post">
        <input type="hidden" name="id" value="${editUser.id }">
		<div>
			<p>用户名：</p>
			<input type="text"  name="username"  value="${editUser.username}"   >
		</div>
		<div>
			<p>年龄：</p>
			<input type="number"  name="age" value="${editUser.age}" >
		</div>
		<div>
			<p>联系电话：</p>
			<input type="text"   name="telephone" value="${editUser.telephone}"  >
		</div>
        <div>
            <br>
            <button type="submit">修改</button>
        </div>
    </form>
</body>
</html>