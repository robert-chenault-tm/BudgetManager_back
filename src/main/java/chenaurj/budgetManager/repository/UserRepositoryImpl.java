package chenaurj.budgetManager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import chenaurj.budgetManager.model.User;
import chenaurj.budgetManager.repository.util.UserRowMapper;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public User createUser(User user) {		
		jdbcTemplate.update("insert into users (id, username, password, enabled) values (?, ?, ?, ?) ", user.getId(), user.getUsername(), user.getPassword(), user.isEnabled());
		jdbcTemplate.update("insert into authorities (userid, username, authority) values (?, ?, ?) ", user.getId(), user.getUsername(), "ROLE_USER");
		
		return getUser(user.getId());
	}

	@Override
	public void deleteUser(String id) {
		jdbcTemplate.update("delete from authorities where userid = ?", id);
		jdbcTemplate.update("delete from users where id = ?", id);
	}

	@Override
	public User getUser(String id) {
		User user = jdbcTemplate.queryForObject("select * from users where id = ?", new UserRowMapper(), id);
		
		return user;
	}

	@Override
	public List<User> getUsers() {
		List<User> users = jdbcTemplate.query("select * from users", new UserRowMapper());
		
		return users;
	}

	@Override
	public User updateUser(User user) {
		jdbcTemplate.update("update users set username = ?, password = ?, enabled = ? where id = ?", user.getUsername(), user.getPassword(), user.isEnabled(), user.getId());
		jdbcTemplate.update("update authorities set authority = ? where userid = ?", user.getUsername(), user.getId());
		return user;
	}

	@Override
	public User getUser(String username, String password) {
		User user;
		//User user = jdbcTemplate.queryForObject("select * from users where username = ? and password = ?", new UserRowMapper(), username, password);
		try {
			user = jdbcTemplate.queryForObject("select * from users where username = ?", new UserRowMapper(), username);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			user = null;
		}
		
		return user;
	}

}
