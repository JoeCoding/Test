package com.neuedu.controller;

import java.io.IOException;
import java.sql.SQLException;





import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import com.neuedu.model.po.User;
import com.neuedu.model.service.UserService;

/**
 * Servlet implementation class EditUserServlet
 */
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		
		if("edit".equals(action)){
			doEditUser(request,response);
		}else if("update".equals(action)){
			try {
				doUpdateUser(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	
	private void doUpdateUser(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, ServletException{
		
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String age = request.getParameter("age");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		User u = new User();
		u.setId(Integer.parseInt(id));
		u.setUsername(username);
		u.setAge(Integer.parseInt(age));
		u.setTelephone(telephone);
		u.setEmail(email);
		try {
			UserService.getInstance().updateUser(u);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int pageNum = (Integer) request.getSession().getAttribute("pageNum");
		try {
			response.sendRedirect(request.getContextPath()+"/userManagerServlet?pageNum="+pageNum);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void doEditUser(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String userId = request.getParameter("userId");
			User u = UserService.getInstance().getUserById(Integer.parseInt(userId));
			request.setAttribute("editUser", u);
			request.getRequestDispatcher("/editUser.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
