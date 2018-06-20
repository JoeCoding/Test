package com.neuedu.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.neuedu.model.po.User;
import com.neuedu.utils.DButil;

public class UserDAOImp implements UserDAO {

	Connection conn;
	
	public UserDAOImp(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void register(User user) throws SQLException{
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("insert into userinfo values(seq_userinfo.nextval,?,?,?,?,?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getAge());
			ps.setString(4, user.getTelephone());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getPhotourl());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DButil.closePS(ps);
		}
	}

	@Override
	public List<User> selectUser(String username, int age) throws SQLException  {
		PreparedStatement ps=null;
		List<User> list=new ArrayList<User>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from userinfo where 1=1 ");
		if(username !=null && !"".equals(username)){
			sbf.append("and username like?");
		}
		if(age !=0){
		sbf.append("and age=?");
		}
		ps = conn.prepareStatement(sbf.toString());
		int index=1;
		if(username !=null && !"".equals(username)){
			ps.setString(index, "%"+username+"%");
			index++;
		}
		if(age !=0){
			ps.setInt(index, age);
		}
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			User u=new User();
				
			u.setUsername(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setAge(rs.getInt("age"));
			u.setTelephone(rs.getString("phonenumber"));
			u.setEmail(rs.getString("email"));
			list.add(u);
		}
		DButil.closePS(ps);
		return list;
	}

	@Override
	public List<User> selectPageUser(String username, int age, int pageSize,
			int pageNum) throws SQLException {
		PreparedStatement ps=null;
		List<User> list=new ArrayList<User>();
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select * from userinfo where 1=1 ");
		if(username !=null && !"".equals(username)){
			sbf.append("and username like?");
		}
		if(age !=0){
		sbf.append("and age=?");
		}
		ps = conn.prepareStatement(" select b.* from "
				+ " ( select a.*,rownum rw from ( "
				+sbf.toString()+") a "
				+ " where rownum<= "+( pageSize*pageNum) +")b"
				+ " where rw> "+pageSize*(pageNum-1));
		int index=1;
		if(username != null && !"".equals(username)){
			ps.setString(index, username);
			index++;
		}
		if(age != 0){
			ps.setInt(index, age);
		}
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			User u=new User();
			u.setId(rs.getInt("id"));
			u.setUsername(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setAge(rs.getInt("age"));
			u.setTelephone(rs.getString("phonenumber"));
			u.setEmail(rs.getString("email"));
			list.add(u);
		}
		DButil.closePS(ps);
		return list;
	}
	
	public int selectPageCount(String username,int age,int pageSize) throws SQLException{
		int count=0;
		PreparedStatement ps=null;
		StringBuffer sbf = new StringBuffer("");
		sbf.append("select count(*) cc from userinfo where 1=1 ");
		if(username !=null && !"".equals(username)){
			sbf.append("and username like?");
		}
		if(age !=0){
		sbf.append("and age=?");
		}
		ps = conn.prepareStatement(sbf.toString());
		int index=1;
		if(username !=null && !"".equals(username)){
			ps.setString(index, "%"+username+"%");
			   index++;
		}
		if(age !=0){
			ps.setInt(index, age);
		}
		   ResultSet rs = ps.executeQuery();
		   if(rs.next()){
		   count=rs.getInt("cc");
		}

		int pageCount = 0;
		if(count%pageSize==0){
			pageCount = count/pageSize;
		}else{
			pageCount = count/pageSize+1;
		}
		DButil.closePS(ps);
		return pageCount;	
	}

	@Override
	public void delectUsers(int[] ids) throws SQLException {
		PreparedStatement ps=null;
		String id=Arrays.toString(ids).replace('[','(').replace(']', ')');
		ps =conn.prepareStatement(" delete from userinfo "
				+" where id in  "+id);
		ps.executeUpdate();
		DButil.closePS(ps);
	}

	@Override
	public User getUserById(int userId) throws SQLException {
		// TODO Auto-generated method stub
		User u=new User();
		PreparedStatement ps;
		ps = conn.prepareStatement(" select * from userinfo where id=? ");
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			u.setId(rs.getInt("id"));
			u.setUsername(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setAge(rs.getInt("age"));
			u.setTelephone(rs.getString("phonenumber"));
			u.setEmail(rs.getString("email"));
		}
		DButil.closePS(ps);
		return u;
	}
	
	public void updateUser(User u) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(" update userinfo set username=?,age=?,telephone=?"
					+ " where id=?  ");
			ps.setString(1, u.getUsername());
			ps.setInt(2, u.getAge());
			ps.setString(3, u.getTelephone());
			ps.setInt(4, u.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DButil.closePS(ps);
		}
	}

	public User validateUsername(String username) throws SQLException {
		User u=null;
		PreparedStatement ps;
		ps = conn.prepareStatement(" select * from userinfo where username=? ");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			u.setId(rs.getInt("id"));
			u.setUsername(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			u.setAge(rs.getInt("age"));
			u.setTelephone(rs.getString("phonenumber"));
			u.setEmail(rs.getString("email"));
		}
		DButil.closePS(ps);
		return u;
	}
	

}
