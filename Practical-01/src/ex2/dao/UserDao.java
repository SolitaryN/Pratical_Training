package ex2.dao;

import java.util.List;


import ex2.entity.User;


public interface UserDao {
	public void insert(User user); 
	
	public User delete(String name);
	
	public void update(User user);

	public User selectByEmail(String email) ;
	
	public List<User> selectAll();
}
