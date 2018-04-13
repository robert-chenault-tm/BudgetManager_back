package chenaurj.budgetManager.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import chenaurj.budgetManager.model.CreatedExpense;
import chenaurj.budgetManager.model.ExpenseCategory;
import chenaurj.budgetManager.model.MonthlyBudget;
import chenaurj.budgetManager.model.RecurringExpense;
import chenaurj.budgetManager.model.User;

public class JUnitUtil {
	
	public static User createUserHelper(String name) {
		RestTemplate restTemplate = new RestTemplate();
		
		User user = new User();
		user.setUsername(name);
		user.setPassword("password");
		user.setEnabled(true);
		
		user = restTemplate.postForObject("http://localhost:8080/budget_manager/user/generate", user, User.class);
		
		return user;
	}
	
	public static User getUserHelper(String id) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();		
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity request = new HttpEntity(headers);
		
		ResponseEntity<User> usersResponse = restTemplate.exchange("http://localhost:8080/budget_manager/user/" + id, HttpMethod.GET, request, User.class);
		User user = usersResponse.getBody();
		
		return user;
	}
	
	public static User authenticateHelper(String username, String password) {
		RestTemplate restTemplate = new RestTemplate();
		
		return(restTemplate.postForObject("http://localhost:8080/budget_manager/authenticate/" + username + "/" + password, new HttpEntity(new HttpHeaders()), User.class));
	}
	
	public static List<User> getUsersHelper() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();		
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);

		HttpEntity<String> request = new HttpEntity<String>("", headers);
		
		ResponseEntity<List<User>> usersResponse = restTemplate.exchange("http://localhost:8080/budget_manager/users", HttpMethod.GET, request, new ParameterizedTypeReference<List<User>>() {});
		return usersResponse.getBody();
	}
	
	public static void deleteUserHelper(String id) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();		
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);
		restTemplate.exchange("http://localhost:8080/budget_manager/delete/user/" + id, HttpMethod.DELETE, entity, Object.class);
	}
	
	public static MonthlyBudget createMonthlyBudgetHelper(String username, int year, int month) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		MonthlyBudget monthlyBudget = new MonthlyBudget();
		monthlyBudget.setUsername(username);
		monthlyBudget.setYear(year);
		monthlyBudget.setMonth(month);
		
		HttpEntity<MonthlyBudget> request = new HttpEntity<MonthlyBudget>(monthlyBudget, headers);
		
		monthlyBudget = restTemplate.postForObject("http://localhost:8080/budget_manager/month", request, MonthlyBudget.class);
		
		return monthlyBudget;
	}
	
	public static MonthlyBudget getMonthlyBudgetHelper(String userId, int year, int month) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<MonthlyBudget> response = restTemplate.exchange("http://localhost:8080/budget_manager/month/" + userId + "/" + year + "/" + month, HttpMethod.GET, entity, MonthlyBudget.class);
		
		return response.getBody();
	}
	
	public static List<MonthlyBudget> getMonthlyBudgetsHelper() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<List<MonthlyBudget>> monthlyBudgetsResponse = restTemplate.exchange("http://localhost:8080/budget_manager/months", HttpMethod.GET, entity, new ParameterizedTypeReference<List<MonthlyBudget>>() {});
		return monthlyBudgetsResponse.getBody();
	}
	
	public static List<MonthlyBudget> getMonthlyBudgetsByUserHelper(String userId) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<List<MonthlyBudget>> monthlyBudgetsResponse = restTemplate.exchange("http://localhost:8080/budget_manager/months/" + userId, HttpMethod.GET, entity, new ParameterizedTypeReference<List<MonthlyBudget>>() {});
		return monthlyBudgetsResponse.getBody();
	}
	
	public static void deleteMonthlyBudgetHelper(String userId, int year, int month) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);
		
		restTemplate.exchange("http://localhost:8080/budget_manager/delete/month/" + userId + "/" + year + "/" + month, HttpMethod.DELETE, entity, Object.class);
	}
	
	public static ExpenseCategory createExpenseCategoryHelper(String username, String title, int expectedAmount) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		ExpenseCategory expenseCategory = new ExpenseCategory();
		expenseCategory.setUsername(username);
		expenseCategory.setTitle(title);
		expenseCategory.setExpectedAmount(expectedAmount);
		
		HttpEntity<ExpenseCategory> request = new HttpEntity<ExpenseCategory>(expenseCategory, headers);
		
		expenseCategory = restTemplate.postForObject("http://localhost:8080/budget_manager/expense_category", request, ExpenseCategory.class);
		
		return expenseCategory;
	}
	
	public static ExpenseCategory getExpenseCategoryHelper(String id) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<ExpenseCategory> response = restTemplate.exchange("http://localhost:8080/budget_manager/expense_category/" + id, HttpMethod.GET, entity, ExpenseCategory.class);
		
		return response.getBody();
	}
	
	public static List<ExpenseCategory> getExpenseCategoriesHelper() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<List<ExpenseCategory>> expenseCategoriesResponse = restTemplate.exchange("http://localhost:8080/budget_manager/expense_categories", HttpMethod.GET, entity, new ParameterizedTypeReference<List<ExpenseCategory>>() {});
		return expenseCategoriesResponse.getBody();
	}
	
	public static List<ExpenseCategory> getExpenseCategoriesByUserHelper(String username) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<List<ExpenseCategory>> expenseCategoriesResponse = restTemplate.exchange("http://localhost:8080/budget_manager/expense_categories/" + username, HttpMethod.GET, entity, new ParameterizedTypeReference<List<ExpenseCategory>>() {});
		return expenseCategoriesResponse.getBody();
	}
	
	public static void deleteExpenseCategoryHelper(String id) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);
		
		restTemplate.exchange("http://localhost:8080/budget_manager/delete/expense_category/" + id, HttpMethod.DELETE, entity, Object.class);
	}
	
	public static CreatedExpense createCreatedExpenseHelper(String categoryId, String name, int amount, int year, int month) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		CreatedExpense expense = new CreatedExpense();
		expense.setCategoryId(categoryId);
		expense.setName(name);
		expense.setAmount(amount);
		expense.setYear(year);
		expense.setMonth(month);
		
		HttpEntity<CreatedExpense> request = new HttpEntity<CreatedExpense>(expense, headers);
		
		expense = restTemplate.postForObject("http://localhost:8080/budget_manager/created_expense", request, CreatedExpense.class);
		
		return expense;
	}
	
	public static CreatedExpense getCreatedExpenseHelper(String id) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<CreatedExpense> response = restTemplate.exchange("http://localhost:8080/budget_manager/created_expense/" + id, HttpMethod.GET, entity, CreatedExpense.class);
		
		return response.getBody();
	}
	
	public static List<CreatedExpense> getCreatedExpensesHelper() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<List<CreatedExpense>> createdExpensesResponse = restTemplate.exchange("http://localhost:8080/budget_manager/created_expenses", HttpMethod.GET, entity, new ParameterizedTypeReference<List<CreatedExpense>>() {});
		return createdExpensesResponse.getBody();
	}
	
	public static List<CreatedExpense> getCreatedExpensesByCategoryHelper(String categoryId) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<List<CreatedExpense>> expenseCategoriesResponse = restTemplate.exchange("http://localhost:8080/budget_manager/created_expenses/" + categoryId, HttpMethod.GET, entity, new ParameterizedTypeReference<List<CreatedExpense>>() {});
		return expenseCategoriesResponse.getBody();
	}
	
	public static void deleteCreatedExpenseHelper(String id) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);
		
		restTemplate.exchange("http://localhost:8080/budget_manager/delete/created_expense/" + id, HttpMethod.DELETE, entity, Object.class);
	}

	
	public static RecurringExpense createRecurringExpenseHelper(String categoryId, String name, int amount) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		RecurringExpense expense = new RecurringExpense();
		expense.setCategoryId(categoryId);
		expense.setName(name);
		expense.setAmount(amount);
		
		HttpEntity<RecurringExpense> request = new HttpEntity<RecurringExpense>(expense, headers);
		
		expense = restTemplate.postForObject("http://localhost:8080/budget_manager/recurring_expense", request, RecurringExpense.class);
		
		return expense;
	}
	
	public static RecurringExpense getRecurringExpenseHelper(String id) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<RecurringExpense> response = restTemplate.exchange("http://localhost:8080/budget_manager/recurring_expense/" + id, HttpMethod.GET, entity, RecurringExpense.class);
		
		return response.getBody();
	}
	
	public static List<RecurringExpense> getRecurringExpensesHelper() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<List<RecurringExpense>> recurringExpensesResponse = restTemplate.exchange("http://localhost:8080/budget_manager/recurring_expenses", HttpMethod.GET, entity, new ParameterizedTypeReference<List<RecurringExpense>>() {});
		return recurringExpensesResponse.getBody();
	}
	
	public static List<RecurringExpense> getRecurringExpensesByCategoryHelper(String categoryId) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);
		
		ResponseEntity<List<RecurringExpense>> recurringExpensesResponse = restTemplate.exchange("http://localhost:8080/budget_manager/recurring_expenses/" + categoryId, HttpMethod.GET, entity, new ParameterizedTypeReference<List<RecurringExpense>>() {});
		return recurringExpensesResponse.getBody();
	}
	
	public static void deleteRecurringExpenseHelper(String id) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String authdata = "Basic " + Base64.getEncoder().encodeToString(("LoginEnabler:password").getBytes());
		headers.add("Authorization", authdata);
		
		HttpEntity entity = new HttpEntity(headers);

		restTemplate.exchange("http://localhost:8080/budget_manager/delete/recurring_expense/" + id, HttpMethod.DELETE, entity, Object.class);
	}

}
