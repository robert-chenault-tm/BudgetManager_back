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

import chenaurj.budgetManager.model.ExpenseCategory;
import chenaurj.budgetManager.model.RecurringExpense;
import chenaurj.budgetManager.model.User;

public class RecurringExpenseControllerTest {

	private String userId;
	private List<String> expenseIds = new ArrayList<>();
	private ExpenseCategory cat;
	private String enablerId;
	
	
	
	@Before
	public void setUp() {
		User enabler = JUnitUtil.createUserHelper("LoginEnabler");
		enablerId = enabler.getId();
		
		List<RecurringExpense> expenses = JUnitUtil.getRecurringExpensesHelper();
		for(RecurringExpense expense : expenses) {
			JUnitUtil.deleteRecurringExpenseHelper(expense.getId());
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
		
		RecurringExpense expense;
		expense = JUnitUtil.createRecurringExpenseHelper(cat.getId(), "exp1", 10);
		expenseIds.add(expense.getId());
		expense = JUnitUtil.createRecurringExpenseHelper(cat.getId(), "exp2", 20);
		expenseIds.add(expense.getId());
		expense = JUnitUtil.createRecurringExpenseHelper(cat.getId(), "exp3", 30);
		expenseIds.add(expense.getId());
		expense = JUnitUtil.createRecurringExpenseHelper(cat.getId(), "exp4", 40);
		expenseIds.add(expense.getId());
	}
	
	@After
	public void tearDown() {
		List<RecurringExpense> expenses = JUnitUtil.getRecurringExpensesHelper();
		for(RecurringExpense expense : expenses) {
			JUnitUtil.deleteRecurringExpenseHelper(expense.getId());
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
		expenseIds.clear();
		enablerId = null;
		
	}
	
	@Test(timeout = 3000)
	public void createRecurringExpenseTest() {
		JUnitUtil.createRecurringExpenseHelper(cat.getId(), "exp5", 50);
		
		List<RecurringExpense> expenses = JUnitUtil.getRecurringExpensesHelper();
		
		assertEquals(expenses.size(), 5);
	}
	
	@Test(timeout = 3000)
	public void deleteRecurringExpenseTest() {
		JUnitUtil.deleteRecurringExpenseHelper(expenseIds.get(0));
		
		List<RecurringExpense> expenses = JUnitUtil.getRecurringExpensesHelper();
		
		assertEquals(expenses.size(), 3);
	}

	@Test(timeout = 3000)
	public void getRecurringExpensesTest() {
		List<RecurringExpense> expenses = JUnitUtil.getRecurringExpensesHelper();
		
		assertEquals(expenses.size(), 4);
	}
	
	@Test(timeout = 3000)
	public void getRecurringExpensesByCategoryTest() {
		List<RecurringExpense> expenses = JUnitUtil.getRecurringExpensesByCategoryHelper(cat.getId());
		
		assertEquals(expenses.size(), 4);
	}
	
	@Test(timeout = 3000)
	public void getRecurringExpenseTest() {
		String name = "exp1";
		int amount = 10;
		
		RecurringExpense expense = JUnitUtil.getRecurringExpenseHelper(expenseIds.get(0));
		
		assertEquals(expense.getName(), name);
		assertEquals(expense.getAmount(), amount);
	}
	
	@Test(timeout = 3000)
	public void updateRecurringExpenseTest() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		RecurringExpense expense = JUnitUtil.getRecurringExpenseHelper(expenseIds.get(0));
		String name = "Misc";
		float amount = expense.getAmount() * 2;
		
		expense.setName(name);
		expense.setAmount(amount);
		
		HttpEntity<RecurringExpense> request = new HttpEntity<RecurringExpense>(expense, headers);
		
		restTemplate.put("http://localhost:8080/budget_manager/recurring_expense", request);
		
		expense = JUnitUtil.getRecurringExpenseHelper(expenseIds.get(0));
		
		assertEquals(expense.getName(), name);
		assertEquals(expense.getAmount(), amount);		
	}
}
