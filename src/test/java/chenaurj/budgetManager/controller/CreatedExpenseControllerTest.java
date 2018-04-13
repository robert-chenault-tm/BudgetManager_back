package chenaurj.budgetManager.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import chenaurj.budgetManager.model.CreatedExpense;
import chenaurj.budgetManager.model.ExpenseCategory;
import chenaurj.budgetManager.model.RecurringExpense;
import chenaurj.budgetManager.model.User;

public class CreatedExpenseControllerTest {
	
	private String userId;
	private List<String> expenseIds = new ArrayList<>();
	private ExpenseCategory cat;
	private String enablerId;
	
	
	
	@Before
	public void setUp() {
		User enabler = JUnitUtil.createUserHelper("LoginEnabler");
		enablerId = enabler.getId();
		
		List<CreatedExpense> expenses = JUnitUtil.getCreatedExpensesHelper();
		for(CreatedExpense expense : expenses) {
			JUnitUtil.deleteCreatedExpenseHelper(expense.getId());
		}
		
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
		
		userId = null;
		cat = null;
		expenseIds.clear();
		
		User user = JUnitUtil.createUserHelper("Johnnytest");
		userId = user.getId();
		
		cat = JUnitUtil.createExpenseCategoryHelper(userId, "Test", 50);
		
		CreatedExpense expense;
		expense = JUnitUtil.createCreatedExpenseHelper(cat.getId(), "exp1", 10, 1990, 5);
		expenseIds.add(expense.getId());
		expense = JUnitUtil.createCreatedExpenseHelper(cat.getId(), "exp2", 20, 1990, 5);
		expenseIds.add(expense.getId());
		expense = JUnitUtil.createCreatedExpenseHelper(cat.getId(), "exp3", 30, 1990, 5);
		expenseIds.add(expense.getId());
		expense = JUnitUtil.createCreatedExpenseHelper(cat.getId(), "exp4", 40, 1990, 5);
		expenseIds.add(expense.getId());
	}
	
	@After
	public void tearDown() {
		List<CreatedExpense> expenses = JUnitUtil.getCreatedExpensesHelper();
		for(CreatedExpense expense : expenses) {
			JUnitUtil.deleteCreatedExpenseHelper(expense.getId());
		}
		
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
		
		userId = null;
		cat = null;
		enablerId = null;
		expenseIds.clear();
		
	}
	
	@Test(timeout = 3000)
	public void createCreatedExpenseTest() {
		JUnitUtil.createCreatedExpenseHelper(cat.getId(), "exp5", 50, 1990, 5);
		
		List<CreatedExpense> expenses = JUnitUtil.getCreatedExpensesHelper();
		
		assertEquals(expenses.size(), 5);
	}
	
	@Test(timeout = 3000)
	public void createCreatedExpenseFromRecurringExpenseTest() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		String categoryId = cat.getId();
		String name = "exp5";
		int amount = 50;
		int year = 1995;
		int month = 8;
		
		RecurringExpense expense = new RecurringExpense();
		expense.setCategoryId(categoryId);
		expense.setName(name);
		expense.setAmount(amount);
		
		HttpEntity<RecurringExpense> request = new HttpEntity<RecurringExpense>(expense, headers);
		
		CreatedExpense cExpense = restTemplate.postForObject("http://localhost:8080/budget_manager/created_expense/" + year + "/" + month, request, CreatedExpense.class);
		
		List<CreatedExpense> expenses = JUnitUtil.getCreatedExpensesHelper();
		
		assertEquals(expenses.size(), 5);
		assertEquals(cExpense.getYear(), year);
		assertEquals(cExpense.getMonth(), month);
		assertEquals(cExpense.getName(), name);
		assertEquals(cExpense.getAmount(), amount);
	}
	
	@Test(timeout = 3000)
	public void deleteCreatedExpenseTest() {
		JUnitUtil.deleteCreatedExpenseHelper(expenseIds.get(0));
		
		List<CreatedExpense> expenses = JUnitUtil.getCreatedExpensesHelper();
		
		assertEquals(expenses.size(), 3);
	}

	@Test(timeout = 3000)
	public void getCreatedExpensesTest() {
		List<CreatedExpense> expenses = JUnitUtil.getCreatedExpensesHelper();
		
		assertEquals(expenses.size(), 4);
	}
	
	@Test(timeout = 3000)
	public void getCreatedExpensesByCategoryTest() {
		List<CreatedExpense> expenses = JUnitUtil.getCreatedExpensesByCategoryHelper(cat.getId());
		
		assertEquals(expenses.size(), 4);
	}
	
	@Test(timeout = 3000)
	public void getCreatedExpenseTest() {
		String name = "exp1";
		int amount = 10;
		
		CreatedExpense expense = JUnitUtil.getCreatedExpenseHelper(expenseIds.get(0));
		
		assertEquals(expense.getName(), name);
		assertEquals(expense.getAmount(), amount);
	}
	
	@Test(timeout = 3000)
	public void updateCreatedExpenseTest() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		CreatedExpense expense = JUnitUtil.getCreatedExpenseHelper(expenseIds.get(0));
		String name = "Misc";
		float amount = expense.getAmount() * 2;
		int year = expense.getYear() + 1;
		int month = expense.getMonth();
		
		expense.setName(name);
		expense.setAmount(amount);
		expense.setYear(year);
		expense.setMonth(month);
		
		HttpEntity<CreatedExpense> request = new HttpEntity<CreatedExpense>(expense, headers);
		
		restTemplate.put("http://localhost:8080/budget_manager/created_expense", request);
		
		expense = JUnitUtil.getCreatedExpenseHelper(expenseIds.get(0));
		
		assertEquals(expense.getName(), name);
		assertEquals(expense.getAmount(), amount);
		assertEquals(expense.getYear(), year);
		assertEquals(expense.getMonth(), month);
	}
	
}
