package chenaurj.budgetManager.controller;

import static org.junit.Assert.assertEquals;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import chenaurj.budgetManager.model.ExpenseCategory;
import chenaurj.budgetManager.model.User;

public class ExpenseCategoryControllerTest {

	private HashMap<String, String> userIds = new HashMap<>();
	private HashMap<String, String> catIds = new HashMap<>();
	
	private String[] testUsers = {"Johnnytest", "Alucard"};
	private String enablerId;
	
	@Before
	public void setUp() {
		User enabler = JUnitUtil.createUserHelper("LoginEnabler");
		enablerId = enabler.getId();
		
		List<ExpenseCategory> cats = JUnitUtil.getExpenseCategoriesHelper();
		for(ExpenseCategory cat : cats) {
			JUnitUtil.deleteExpenseCategoryHelper(cat.getId());
		}

		List<User> users = JUnitUtil.getUsersHelper();
		for(User user : users) {
			if(!user.getId().equals(enablerId)){
				JUnitUtil.deleteUserHelper(user.getId());
			}
		}
		
		userIds.clear();
		
		User user;
		for(int i = 0; i < testUsers.length; i++) {
			user = JUnitUtil.createUserHelper(testUsers[i]);
			userIds.put(user.getUsername(), user.getId());
		}
		ExpenseCategory cat;
		cat = JUnitUtil.createExpenseCategoryHelper("Johnnytest", "Test", 50);
		catIds.put(cat.getTitle(), cat.getId());
		cat = JUnitUtil.createExpenseCategoryHelper("Johnnytest", "SecondTest", 15);
		catIds.put(cat.getTitle(), cat.getId());
		cat = JUnitUtil.createExpenseCategoryHelper("Johnnytest", "ThirdTest", 100);
		catIds.put(cat.getTitle(), cat.getId());
		cat = JUnitUtil.createExpenseCategoryHelper("Alucard", "Entertainment", 50000);
		catIds.put(cat.getTitle(), cat.getId());
	}
	
	@After
	public void tearDown() {
		List<ExpenseCategory> cats = JUnitUtil.getExpenseCategoriesHelper();
		for(ExpenseCategory cat : cats) {
			JUnitUtil.deleteExpenseCategoryHelper(cat.getId());
		}
		
		List<User> users = JUnitUtil.getUsersHelper();
		for(User user : users) {
			if(!user.getId().equals(enablerId)){
				JUnitUtil.deleteUserHelper(user.getId());
			}
		}
		JUnitUtil.deleteUserHelper(enablerId);
		
		userIds.clear();
		enablerId = null;
	}
	
	@Test(timeout = 3000)
	public void createExpenseCategoryTest() {
		JUnitUtil.createExpenseCategoryHelper("Johnnytest", "FinalTest", 1000);
		
		List<ExpenseCategory> cats = JUnitUtil.getExpenseCategoriesHelper();
		
		assertEquals(cats.size(), 5);
	}
	
	@Test(timeout = 3000)
	public void deleteExpenseCategoryTest() {
		JUnitUtil.deleteExpenseCategoryHelper(catIds.get("Test"));
		
		List<ExpenseCategory> cats = JUnitUtil.getExpenseCategoriesHelper();
		
		assertEquals(cats.size(), 3);
	}

	@Test(timeout = 3000)
	public void getExpenseCategoriesTest() {
		List<ExpenseCategory> cats = JUnitUtil.getExpenseCategoriesHelper();
		
		assertEquals(cats.size(), 4);
	}
	
	@Test(timeout = 3000)
	public void getExpenseCategoriesByUserTest() {
		List<ExpenseCategory> cats = JUnitUtil.getExpenseCategoriesByUserHelper("Johnnytest");
		
		assertEquals(cats.size(), 3);
	}
	
	@Test(timeout = 3000)
	public void getExpenseCategoryTest() {
		String title = "ThirdTest";
		int expectedAmount = 100;
		
		ExpenseCategory cat = JUnitUtil.getExpenseCategoryHelper(catIds.get(title));
		
		assertEquals(cat.getTitle(), title);
		assertEquals(cat.getExpectedAmount(), expectedAmount);
	}
	
	@Test(timeout = 3000)
	public void updateExpenseCategoryTest() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		ExpenseCategory cat = JUnitUtil.getExpenseCategoryHelper(catIds.get("Entertainment"));
		String title = "Misc";
		int expectedAmount = cat.getExpectedAmount() * 2;
		
		cat.setTitle(title);
		cat.setExpectedAmount(expectedAmount);
		
		HttpEntity<ExpenseCategory> request = new HttpEntity<ExpenseCategory>(cat, headers);
		
		restTemplate.put("http://localhost:8080/budget_manager/expense_category", request);
		
		cat = JUnitUtil.getExpenseCategoryHelper(catIds.get("Entertainment"));
		
		assertEquals(cat.getTitle(), title);
		assertEquals(cat.getExpectedAmount(), expectedAmount);
	}
}
