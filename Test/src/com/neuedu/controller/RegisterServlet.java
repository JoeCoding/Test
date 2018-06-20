package com.neuedu.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.neuedu.model.po.User;
import com.neuedu.model.service.UserService;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("进入Servlet");
		//设定编码格式
		request.setCharacterEncoding("utf-8");
		
		String action = request.getParameter("action");
		if("validate".equals(action)){
			String username = request.getParameter("username");
			try {
				boolean flag = UserService.getInstance().validateUsername(username);
				response.setContentType("text/html");
				PrintWriter pw=response.getWriter();
				pw.print(flag);
				pw.close();	
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			//获取表单参数
			String username="";
			String password="";
			String age="";
			String telephone="";
			String email="";
			String newName="";
			
			boolean isMultipart=ServletFileUpload.isMultipartContent(request);
			if(isMultipart){
				//设置工厂类
				DiskFileItemFactory factory= new DiskFileItemFactory();
				//构建upload
				ServletFileUpload upload=new ServletFileUpload(factory);
				upload.setHeaderEncoding("utf-8");
				upload.setSizeMax(1024*1024*2);
				
				try {
					List<FileItem> fileItems=upload.parseRequest(request);
					Iterator<FileItem> iter= fileItems.iterator();
					while(iter.hasNext()){
						FileItem item = iter.next();
						//判断元素类型
						if(item.isFormField()){
							//普通的表单元素
							//表单元素名称
							String name=item.getFieldName();
							//表单元素值
							String value =new String(item.getString().getBytes("iso8859-1"),"utf-8");
							if("username".equals(name)){
								username=value;
							}
							if("password".equals(name)){
								password=value;
							}
							if("age".equals(name)){
								age=value;
							}
							if("telephone".equals(name)){
								telephone=value;
							}
							if("email".equals(name)){
								email=value;
							}
						}else{
							//要上传的文件
							String filename=item.getName();
							//重命名
							newName=new Date().getTime()+filename.substring(filename.indexOf("."));
							
							File file=new File("d:/photo/",newName);
							item.write(file);
						
						}
					}
				
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
					
			//校验
			
			//封装数据
			User user=new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setAge(Integer.parseInt(age));
			user.setTelephone(telephone);
			user.setEmail(email);
			user.setPhotourl(newName);
			//调用模块层代码进行保存
			try {
				UserService.getInstance().register(user);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*
			 * 响应
			 * 页面跳转的方式
			 * 请求转发：
			 *     URL不发生变化；
			 *     可以以'/'开头，也可以不带'/'，都是相对于当前路径跳转
			 *     不支持站外跳转
			 *     过程中只有一个request对象
			 * 重定向：
			 *     跳转到指定路径，URL发生变化；
			 *     不能以'/'开头，否则相对于Tomcat服务器进行跳转 建议写成绝对路径
			 *     支持站外跳转
			 *     产生两个request对象
			 * 数据共享的方式决定页面跳转的方式，如果数据存放在request内则只能请求转发，否则都可以
			 */
			//请求转发
			//request.getRequestDispatcher("selectUser.jsp").forward(request, response);
			//重定向
			//response.sendRedirect("http://www.baidu.com");
			response.sendRedirect(request.getContextPath()+"/selectUser.jsp");
		}
		
		

	}
}
