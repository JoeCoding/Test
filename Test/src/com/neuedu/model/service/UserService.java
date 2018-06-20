package com.neuedu.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.neuedu.model.dao.UserDAO;
import com.neuedu.model.dao.UserDAOImp;
import com.neuedu.model.po.User;
import com.neuedu.utils.DButil;

public class UserService {
	
	private static UserService service = new UserService();
	
	private UserService(){	
	}
	
	public static UserService getInstance(){
		return service;
	}
	/*
	 * Service:
	 * 复杂的业务逻辑处理
	 * 事务处理（与数据库交互过程要保证数据一致性）
	 * 
	 */
	//注册
	public void register(User user) throws ClassNotFoundException, SQLException {
		DButil dbutil = new DButil();
		Connection conn = dbutil.getconn();
		//开启事务
		dbutil.beginTransaction(conn);
		try {
			UserDAO dao = new UserDAOImp(conn);
			dao.register(user);
			//提交
			dbutil.commit(conn);
		} catch (Exception e) {
			//回滚
			dbutil.rollback(conn);
		}finally{
			dbutil.closeConn(conn);
		}		
	}
	
	//组合查询
	public List<User> selectUser(String username,int age) throws ClassNotFoundException, SQLException{
		DButil dbutil = new DButil();
		Connection conn = dbutil.getconn();
		UserDAOImp dao = new UserDAOImp(conn);
		List<User> list = dao.selectUser(username, age);
		dbutil.closeConn(conn);
		return list;	
	}
	
	//分页查询
	public List<User> selectPageUser(String username,int age,int pageSize,int pageNum) throws ClassNotFoundException, SQLException{
		DButil dbutil = new DButil();
		Connection conn = dbutil.getconn();
		UserDAOImp dao = new UserDAOImp(conn);
		List<User> list=dao.selectPageUser(username, age,pageSize,pageNum);
		dbutil.closeConn(conn);
		return list;	
	}

	public int selectPageCount(String username,int ageNew,int pageSize) throws SQLException, Exception {
		DButil dbutil = new DButil();
		Connection conn = dbutil.getconn();
		UserDAOImp dao = new UserDAOImp(conn);
		int pageCount=dao.selectPageCount(username, ageNew,pageSize);
		dbutil.closeConn(conn);
		return pageCount;
	}
	
	//批量删除
	public void delectUsers(int[] ids) throws SQLException, ClassNotFoundException{
		DButil dbutil = new DButil();
		Connection conn = dbutil.getconn();
		//开启事务
		dbutil.beginTransaction(conn);
		try {
			UserDAO dao = new UserDAOImp(conn);
			dao.delectUsers(ids);
			//提交
			dbutil.commit(conn);
		} catch (Exception e) {
			//回滚
			dbutil.rollback(conn);
		}finally{
			dbutil.closeConn(conn);
		}		
		
		
	}

	public User getUserById(int userId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		DButil dbutil = new DButil();
		Connection conn = dbutil.getconn();
		UserDAOImp dao = new UserDAOImp(conn);
		User user=dao.getUserById(userId);
		dbutil.closeConn(conn);
		return user;
		
		
	}

	public void updateUser(User u) throws ClassNotFoundException, SQLException {
		DButil dbutil = new DButil();
		Connection conn = dbutil.getconn();
		//开启事务
		dbutil.beginTransaction(conn);
		try {
			UserDAO dao = new UserDAOImp(conn);
			dao.updateUser(u);
			//提交
			dbutil.commit(conn);
		} catch (Exception e) {
			//回滚
			dbutil.rollback(conn);
		}finally{
			dbutil.closeConn(conn);
		}		
	}
	
	//判断用户名是否重复
	public boolean validateUsername(String username) throws Exception, SQLException{
		DButil dbutil = new DButil();
		Connection conn = dbutil.getconn();
		UserDAOImp dao = new UserDAOImp(conn);
		User u = dao.validateUsername(username);
		dbutil.closeConn(conn);
		if(u==null){
			return false;
		}else{
			return true;
		}	
	}

}
