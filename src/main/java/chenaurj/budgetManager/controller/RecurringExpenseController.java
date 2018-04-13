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

import chenaurj.budgetManager.model.RecurringExpense;
import chenaurj.budgetManager.service.RecurringExpenseService;

@CrossOrigin(origins = "http://localhost:8000")
@Controller
public class RecurringExpenseController {

	@Autowired
	private RecurringExpenseService recurringExpenseService;
	
	@RequestMapping(value = "/recurring_expense", method = RequestMethod.POST)
	public @ResponseBody RecurringExpense createRecurringExpense(@RequestBody RecurringExpense expense) {
		return recurringExpenseService.createRecurringExpense(expense);
	}
	
	@RequestMapping(value = "/delete/recurring_expense/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteRecurringExpense(@PathVariable(value = "id") String id) {
		recurringExpenseService.deleteRecurringExpense(id);
		
		return null;
	}
	
	@RequestMapping(value = "/recurring_expenses/user/{username}", method = RequestMethod.GET)
	public @ResponseBody List<RecurringExpense> getRecurringExpensesByUsername(@PathVariable(value = "username") String username) {
		return recurringExpenseService.getRecurringExpenses(username);
	}
	
	@RequestMapping(value = "/recurring_expense", method = RequestMethod.PUT)
	public @ResponseBody RecurringExpense updateRecurringExpense(@RequestBody RecurringExpense expense) {
		return recurringExpenseService.updateRecurringExpense(expense);
	}
	
	/* Didnt end up using these ones, perhaps in the future
	@RequestMapping(value = "/recurring_expenses/{categoryId}", method = RequestMethod.GET)
	public @ResponseBody List<RecurringExpense> getRecurringExpensesByCategory(@PathVariable(value = "categoryId") String categoryId) {
		return recurringExpenseService.getRecurringExpensesByCategory(categoryId);
	}
	
	@RequestMapping(value = "/recurring_expense/{id}", method = RequestMethod.GET)
	public @ResponseBody RecurringExpense getRecurringExpense(@PathVariable(value = "id") String id) {
		return recurringExpenseService.getRecurringExpense(id);
	}
	*/
}
