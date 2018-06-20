package com.neuedu.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.User;
import com.neuedu.model.service.UserService;

/**
 * Servlet implementation class UserManager
 */
public class UserManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request,response);
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		//�趨�����ʽ
	    request.setCharacterEncoding("utf-8");
	    String pagenum = request.getParameter("pageNum");
	    int pageNum = 1;
	    String username="";
	    String age="";
	    String pageSize="";
	    if(pagenum != null&&!"".equals(pagenum)){
	    	//���ҳ����в�ѯ
	    	pageNum = Integer.parseInt(pagenum);
	    	username = (String)request.getSession().getAttribute("username");
	    	age = (String)request.getSession().getAttribute("age");
	    	pageSize = (String)request.getSession().getAttribute("pageSize");
	    }else{
	    	//�����ѯ��ť��ѯ
		    username=request.getParameter("username");
			age=request.getParameter("age");
			pageSize=request.getParameter("pageSize");
	    }
		int ageNew=0;
		if(age !=null&&!"".equals(age)){
			ageNew = Integer.parseInt(age);
		}
		//��ѯ
		//List<User> list=UserService.getInstance().selectUser(username, ageNew);
		//��ѯҳ��
		//��ѯָ��ҳ����
		List<User> list;
		try {
			int pageCount = UserService.getInstance().selectPageCount(username, ageNew, Integer.parseInt(pageSize));
			list = UserService.getInstance().selectPageUser(username, ageNew, Integer.parseInt(pageSize), pageNum);
			/*
			 * ���ݹ���
			 * page
			 * request:����������
			 * session����ǰ�����Ӧ�ĻỰ����������������
			 * applicationContext��Ӧ��
			 * ԭ�򣺾�����ѡ��ΧС��������   ���Ⲣ������   �ര��ͬʱ����ʱ�������ݻ��ҵ�����
			 * 
			 */
			request.setAttribute("resultList", list);
			request.setAttribute("pageCount", pageCount);
			request.getSession().setAttribute("pageNum", pageNum);
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("age", age);
			request.getSession().setAttribute("pageSize", pageSize);
			//��ת
			request.getRequestDispatcher("selectUser.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
