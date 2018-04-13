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

import chenaurj.budgetManager.model.MonthlyBudget;
import chenaurj.budgetManager.model.User;

public class MonthlyBudgetControllerTest {
	
	private HashMap<String, String> userIds = new HashMap<>();
	private String[] testUsers = {"Johnnytest", "Alucard"};
	private String enablerId;
	
	@Before
	public void setUp() {
		User enabler = JUnitUtil.createUserHelper("LoginEnabler");
		enablerId = enabler.getId();
		
		List<MonthlyBudget> months = JUnitUtil.getMonthlyBudgetsHelper();
		for(MonthlyBudget month : months) {
			JUnitUtil.deleteMonthlyBudgetHelper(month.getUsername(), month.getYear(), month.getMonth());
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
		JUnitUtil.createMonthlyBudgetHelper(userIds.get("Johnnytest"), 1990, 9);
		JUnitUtil.createMonthlyBudgetHelper(userIds.get("Johnnytest"), 1990, 8);
		JUnitUtil.createMonthlyBudgetHelper(userIds.get("Johnnytest"), 1990, 7);
		JUnitUtil.createMonthlyBudgetHelper(userIds.get("Alucard"), 1990, 7);
	}
	
	@After
	public void tearDown() {
		List<MonthlyBudget> months = JUnitUtil.getMonthlyBudgetsHelper();
		for(MonthlyBudget month : months) {
			JUnitUtil.deleteMonthlyBudgetHelper(month.getUsername(), month.getYear(), month.getMonth());
		}
		
		List<User> users = JUnitUtil.getUsersHelper();
		for(User user : users) {
			if(!user.getId().equals(enablerId)){
				JUnitUtil.deleteUserHelper(user.getId());
			}
		}
		JUnitUtil.deleteUserHelper(enablerId);
		
		enablerId = null;
		userIds.clear();
	}
	
	@Test(timeout = 3000)
	public void createMonthlyBudgetTest() {
		JUnitUtil.createMonthlyBudgetHelper(userIds.get("Alucard"), 1990, 8);
		
		List<MonthlyBudget> months = JUnitUtil.getMonthlyBudgetsHelper();
		
		assertEquals(months.size(), 5);
	}
	
	@Test(timeout = 3000)
	public void deleteMonthlyBudgetTest() {
		JUnitUtil.deleteMonthlyBudgetHelper(userIds.get("Johnnytest"), 1990, 9);
		
		List<MonthlyBudget> months = JUnitUtil.getMonthlyBudgetsHelper();
		
		assertEquals(months.size(), 3);
	}

	@Test(timeout = 3000)
	public void getMonthlyBudgetsTest() {
		List<MonthlyBudget> months = JUnitUtil.getMonthlyBudgetsHelper();
		
		assertEquals(months.size(), 4);
	}
	
	@Test(timeout = 3000)
	public void getMonthlyBudgetsByUserTest() {
		List<MonthlyBudget> months = JUnitUtil.getMonthlyBudgetsByUserHelper(userIds.get("Johnnytest"));
		
		assertEquals(months.size(), 3);
	}
	
	@Test(timeout = 3000)
	public void getMonthlyBudgetTest() {
		
		int year = 1990;
		int month = 9;
		
		MonthlyBudget monthlyBudget = JUnitUtil.getMonthlyBudgetHelper(userIds.get("Johnnytest"), year, month);
		
		assertEquals(monthlyBudget.getYear(), year);
		assertEquals(monthlyBudget.getMonth(), month);
	}
	
	@Test(timeout = 3000)
	public void updateMonthlyBudgetTest() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		String userId = userIds.get("Alucard");
		int year = 1990;
		int month = 7;
		
		MonthlyBudget monthlyBudget = JUnitUtil.getMonthlyBudgetHelper(userId, year, month);
		
		year = 1431;
		month = 5;
		
		monthlyBudget.setYear(year);
		monthlyBudget.setMonth(month);
		
		HttpEntity<MonthlyBudget> request = new HttpEntity<MonthlyBudget>(monthlyBudget, headers);
		
		restTemplate.put("http://localhost:8080/budget_manager/month", request);
		
		monthlyBudget = JUnitUtil.getMonthlyBudgetHelper(userId, year, month);
		
		assertEquals(monthlyBudget.getYear(), year);
		assertEquals(monthlyBudget.getMonth(), month);
	}
}
