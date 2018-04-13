package chenaurj.budgetManager.repository;

import java.util.List;

import chenaurj.budgetManager.model.User;

public interface UserRepository {

	public User createUser(User user);
	
	public void deleteUser(String id);

	public User getUser(String id);
	
	public User getUser(String username, String password);

	public List<User> getUsers();
	
	public User updateUser(User user);
	
}
