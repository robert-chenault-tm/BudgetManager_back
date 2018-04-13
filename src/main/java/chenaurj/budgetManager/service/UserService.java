package chenaurj.budgetManager.service;

import java.util.List;

import chenaurj.budgetManager.model.User;

public interface UserService {

public User createUser(User user);
	
	public void deleteUser(String id);

	public User getUser(String id);
	
	public User getUser(String username, String password);

	public List<User> getUsers();
	
	public User updateUser(User user);
	
}
