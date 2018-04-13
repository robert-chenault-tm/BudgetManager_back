package chenaurj.budgetManager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chenaurj.budgetManager.model.User;
import chenaurj.budgetManager.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public User createUser(User user) {
		user.setEnabled(true);
		user.setId(UUID.randomUUID().toString());
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRepository.createUser(user);
	}

	@Override
	public User getUser(String id) {
		return userRepository.getUser(id);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.getUsers();
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		return userRepository.updateUser(user);
	}

	@Override
	@Transactional
	public void deleteUser(String id) {
		userRepository.deleteUser(id);
	}

	@Override
	public User getUser(String username, String password) {
		User user = userRepository.getUser(username, password);
		if(user != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			if(passwordEncoder.matches(password, user.getPassword())) {
				return user;
			} else {
				return null;
			}
		}
		
		return user;
	}

}
