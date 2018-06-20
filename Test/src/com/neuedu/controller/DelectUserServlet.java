package com.neuedu.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.service.UserService;

/**
 * Servlet implementation class DelectUserServlet
 */
public class DelectUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelectUserServlet() {
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
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String[] ids=request.getParameterValues("chk");
		int[] idss=new int[ids.length];
		for(int i=0;i<ids.length;i++){
			idss[i]=Integer.parseInt(ids[i]);
		}
		try {
			UserService.getInstance().delectUsers(idss);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int pageNum = (Integer) request.getSession().getAttribute("pageNum");
		response.sendRedirect(request.getContextPath()+"/selectUser.jsp?pageNum="+pageNum);
		
	}

}
