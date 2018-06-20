package com.neuedu.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.model.po.User;
import com.neuedu.model.service.UserService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Servlet implementation class ExportUserServlet
 */
public class ExportUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportUserServlet() {
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
		//设定响应类型
		response.setHeader("Content-disposition", "attachment;filename=userinfo.xls");
		response.setHeader("pragma", "no-cache");
		//设定输出类型为Excel
		response.setContentType("application/mxexcel");
		//获取输出流
		ServletOutputStream os= response.getOutputStream();
		
		//创建Excel文档
		//创建工作本
		WritableWorkbook workbook=Workbook.createWorkbook(os);
		//创建sheet
		WritableSheet sheet=workbook.createSheet("userdata", 0);
		
		//生成标题单元格
		//设定样式
		WritableFont font1=new WritableFont(WritableFont.TIMES,16,WritableFont.BOLD);
		//样式嵌入到单元格样式中
		WritableCellFormat format1=new WritableCellFormat(font1);
		//创建单元格
		Label cell = new Label(0,0,"用户信息",format1);
		try {
			sheet.addCell(cell);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		
		//设定内容样式
		WritableFont font2=new WritableFont(WritableFont.TIMES,12,WritableFont.NO_BOLD);
		//样式嵌入到单元格样式中
		WritableCellFormat format2=new WritableCellFormat(font2);
		try {
			sheet.addCell(new Label(0,1,"用户名",format2));
			sheet.addCell(new Label(1,1,"年龄",format2));
			sheet.addCell(new Label(2,1,"邮箱",format2));
			sheet.addCell(new Label(3,1,"电话号码",format2));
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		//获取查询条件，查询满足条件的所有数据
		String username = (String)request.getSession().getAttribute("username");
		String age = (String)request.getSession().getAttribute("age");
		int ageNew=0;
		if(age !=null&&!"".equals(age)){
			ageNew = Integer.parseInt(age);
		}
		try {
			List<User> list=UserService.getInstance().selectUser(username, ageNew);
			int index=2;
			if(list!=null&&list.size()>0){
				for(User u:list){
					sheet.addCell(new Label(0,index,u.getUsername(),format2));
					sheet.addCell(new Label(1,index,String.valueOf(u.getAge()),format2));
					sheet.addCell(new Label(2,index,u.getEmail(),format2));
					sheet.addCell(new Label(3,index,u.getTelephone(),format2));
					index++;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		workbook.write();
		try {
			workbook.close();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		
	}

}
