package chenaurj.budgetManager.controller;

import static org.junit.Assert.assertEquals;

import java.util.Base64;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import chenaurj.budgetManager.model.User;

public class UserControllerTest {
	
	private HashMap<String, String> ids = new HashMap<>();
	private String[] testUsers = {"test", "Johnnytest", "Goku", "Alucard"};
	private String enablerId;
	
	//@Before
	public void setUp() {
		User enabler = JUnitUtil.createUserHelper("LoginEnabler");
		enablerId = enabler.getId();
		List<User> users = JUnitUtil.getUsersHelper();
		for(User user : users) {
			if(!user.getId().equals(enablerId)){
				JUnitUtil.deleteUserHelper(user.getId());
			}
		}
		
		ids.clear();
		
		User user;
		for(int i = 0; i < testUsers.length; i++) {
			user = JUnitUtil.createUserHelper(testUsers[i]);
			ids.put(user.getUsername(), user.getId());
		}
	}
	
	//@After
	public void tearDown() {
		List<User> users = JUnitUtil.getUsersHelper();
		for(User user : users) {
			if(!user.getId().equals(enablerId)){
				JUnitUtil.deleteUserHelper(user.getId());
			}
		}
		JUnitUtil.deleteUserHelper(enablerId);
		
		ids.clear();
		enablerId = null;
	}

	@Test(timeout = 3000)
	public void createUserTest() {
		User user = JUnitUtil.createUserHelper("Johnny");
		ids.put(user.getUsername(), user.getId());
		List<User> users = JUnitUtil.getUsersHelper();
		user = JUnitUtil.getUserHelper(ids.get("Johnny"));
		
		assertEquals(user.getUsername(), "Johnny");
		assertEquals(users.size(), testUsers.length + 1 + 1); //returned = dummies + johnny + loginenabler
	}
	
	@Test(timeout = 3000)
	public void getUsersTest() {
		List<User> users = JUnitUtil.getUsersHelper();
		
		assertEquals(users.size(), testUsers.length + 1); //returned == dummies + loginenabler
	}
	
	@Test(timeout = 3000)
	public void getUserByIdTest() {
		User user = JUnitUtil.getUserHelper(ids.get("Goku"));
		
		assertEquals(user.getUsername(), "Goku");
	}
	
	@Test(timeout = 3000)
	public void AuthenticateTest() {
		User user = JUnitUtil.authenticateHelper("Goku", "password");
		
		assertEquals(user.getUsername(), "Goku");
	}
	
	@Test(timeout = 3000)
	public void updateUserTest() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		User user = JUnitUtil.getUserHelper(ids.get("Alucard"));
		user.setUsername("Dracula");
		
		HttpEntity<User> request = new HttpEntity<User>(user, headers);
		
		restTemplate.put("http://localhost:8080/budget_manager/user", request);

		user = JUnitUtil.getUserHelper(ids.get("Alucard"));
		
		assertEquals(user.getUsername(), "Dracula");
		
	}
	
	@Test(timeout = 3000)
	public void deleteUserTest() {
		JUnitUtil.deleteUserHelper(ids.get("Johnnytest"));
		List<User> users = JUnitUtil.getUsersHelper();
		assertEquals(users.size(), testUsers.length - 1 + 1); //assertEquals(returned, dummyUsers - JohnnyTest + LoginEnabler)
	}
	

	
}
