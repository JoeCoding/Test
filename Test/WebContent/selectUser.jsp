<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/selectUser.js" ></script>
<title>Insert title here</title>
</head>
<body>

     <form action="userManagerServlet" method="post">
     <div>
                      姓名:<input type="text" name="username" >
                      年龄:<input type="number" name="age">
                      每页显示
                      <select name="pageSize" style="width:150px">
                      		<option value="5">5</option>
                      		<option value="10">10</option>
                      		<option value="15">15</option>
                      </select>
                      条
       <button type="submit">查询</button>
     </div>
     
     <div id="data">
         <table>
             <tr>
             <th>ID</th>
             <th>用户名</th>
             <th>年龄</th>
     		 <th>联系电话</th>
     		 <th>邮箱</th>
     		 </tr>
     		 <!-- 
     		 	forEach:遍历循环
     		 	items属性：进行循环的数据
     		 	var属性：当前遍历到的元素
     		  -->
     		 <c:forEach items="${resultList}" var="user">
     		 	<tr>
     		 		<td>
     		 			<input type="checkbox" value="${user.id }" name="chk">
     		 		</td>
     		 		<td>${user.username}</td>
     		 		<td>${user.age}</td>
     		 		<td>${user.telephone}</td>
     		 		<td>${user.email}</td>
     		 		<td><a href="editUserServlet?userId=${user.id }&action=edit">编辑</a></td>
     		 	</tr>
     		 </c:forEach>
     	</table>
     </div>
     <div>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     <!--  分页点击页码查询
     &lt;&lt;
     	<c:forEach begin="1" end="${pageCount}" var="p">
     		<c:if test="${p==pageNum }">
     			${p}
     		</c:if>
     		<c:if test="${p!=pageNum }">
     			<a href="userManagerServlet?pageNum=${p }">${p}</a>
     		</c:if>
     		&nbsp;&nbsp;
     	</c:forEach>
     &gt;&gt;
     -->
     <!-- 使用Ajax查询 -->
	<div id="more" data-status="1">  
               	 加载更多  
	</div>  
	<input type="hidden" id="page" value="2">  
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     <button type="button" onclick="delectUser()">删除</button>
     <button type="button" onclick="exportUser()">导出Excel</button>
     </div>
     </form>
</body>
</html>