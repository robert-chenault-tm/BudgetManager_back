package chenaurj.budgetManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import chenaurj.budgetManager.model.ExpenseCategory;
import chenaurj.budgetManager.service.ExpenseCategoryService;

@CrossOrigin(origins = "http://localhost:8000")
@Controller
public class ExpenseCategoryController {
	
	@Autowired
	private ExpenseCategoryService expenseCategoryService;
	
	@RequestMapping(value = "/expense_category", method = RequestMethod.POST)
	public @ResponseBody ExpenseCategory createExpenseCategory(@RequestBody ExpenseCategory cat) {
		return expenseCategoryService.createExpenseCategory(cat);
	}
	
	@RequestMapping(value = "/delete/expense_category/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteExpenseCategory(@PathVariable(value = "id") String id) {
		expenseCategoryService.deleteExpenseCategory(id);
		
		return null;
	}
	
	@RequestMapping(value = "/expense_categories/{username}", method = RequestMethod.GET)
	public @ResponseBody List<ExpenseCategory> getExpenseCategoriesByUser(@PathVariable(value = "username") String username) {
		return expenseCategoryService.getExpenseCategoriesByUser(username);
	}
	
	@RequestMapping(value = "/expense_category", method = RequestMethod.PUT)
	public @ResponseBody ExpenseCategory updateExpenseCategory(@RequestBody ExpenseCategory cat) {
		return expenseCategoryService.updateExpenseCategory(cat);
	}
	
	/* Didnt end up using these ones, perhaps in the future
	@RequestMapping(value = "/expense_category/{id}", method = RequestMethod.GET)
	public @ResponseBody ExpenseCategory getExpenseCategory(@PathVariable(value = "id") String id) {
		return expenseCategoryService.getExpenseCategory(id);
	}
	
	@RequestMapping(value = "/expense_categories", method = RequestMethod.GET)
	public @ResponseBody List<ExpenseCategory> getExpenseCategories() {
		return expenseCategoryService.getExpenseCategories();
	}
	*/
}
