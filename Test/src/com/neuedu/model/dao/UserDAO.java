package com.neuedu.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.neuedu.model.po.User;

public interface UserDAO {
	
	public void register(User user)throws SQLException;
	
	public List<User> selectUser(String username,int age)throws SQLException;
	
	public List<User> selectPageUser(String username,int age,int pageSize,int pageNum)throws SQLException;

	public void delectUsers(int[] ids)throws SQLException;
	
	public User getUserById(int UserId)throws SQLException;
	
	public void updateUser(User u) throws SQLException;

	public User validateUsername(String username) throws SQLException;
}
