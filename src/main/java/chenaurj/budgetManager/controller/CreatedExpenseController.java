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

import chenaurj.budgetManager.model.CreatedExpense;
import chenaurj.budgetManager.model.RecurringExpense;
import chenaurj.budgetManager.service.CreatedExpenseService;

@CrossOrigin(origins = "http://localhost:8000")
@Controller
public class CreatedExpenseController {
	
	@Autowired
	private CreatedExpenseService createdExpenseService;
	
	@RequestMapping(value = "/created_expense", method = RequestMethod.POST)
	public @ResponseBody CreatedExpense createCreatedExpense(@RequestBody CreatedExpense expense) {
		return createdExpenseService.createCreatedExpense(expense);
	}
	
	@RequestMapping(value = "/delete/created_expense/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteCreatedExpense(@PathVariable(value = "id") String id) {
		createdExpenseService.deleteCreatedExpense(id);
		
		return null;
	}
	
	@RequestMapping(value = "/delete/created_expenses", method = RequestMethod.POST)
	public @ResponseBody Object deleteCreatedExpenses(@RequestBody String[] ids) {
		for(String id : ids) {
			createdExpenseService.deleteCreatedExpense(id);
		}
		
		return null;
	}
	
	@RequestMapping(value = "/created_expenses/{categoryId}/{year}/{month}", method = RequestMethod.GET)
	public @ResponseBody List<CreatedExpense> getMonthlyCreatedExpensesByCategory(@PathVariable(value = "categoryId") String categoryId, @PathVariable(value = "year") int year, @PathVariable(value = "month") int month) {
		return createdExpenseService.getMonthlyCreatedExpensesByCategory(categoryId, year, month);
	}
	
	@RequestMapping(value = "/created_expense", method = RequestMethod.PUT)
	public @ResponseBody CreatedExpense updateCreatedExpense(@RequestBody CreatedExpense expense) {
		return createdExpenseService.updateCreatedExpense(expense);
	}
	
	/* Didnt end up using these ones, perhaps in the future
	@RequestMapping(value = "/created_expense/{id}", method = RequestMethod.GET)
	public @ResponseBody CreatedExpense getCreatedExpense(@PathVariable(value = "id") String id) {
		return createdExpenseService.getCreatedExpense(id);
	}
	
	@RequestMapping(value = "/created_expenses", method = RequestMethod.GET)
	public @ResponseBody List<CreatedExpense> getCreatedExpenses() {
		return createdExpenseService.getCreatedExpenses();
	}
	
	@RequestMapping(value = "/created_expenses/{categoryId}", method = RequestMethod.GET)
	public @ResponseBody List<CreatedExpense> getCreatedExpensesByCategory(@PathVariable(value = "categoryId") String categoryId) {
		return createdExpenseService.getCreatedExpensesByCategory(categoryId);
	}
	
	@RequestMapping(value = "/created_expense/{year}/{month}", method = RequestMethod.POST)
	public @ResponseBody CreatedExpense createCreatedExpenseFromRecurringExpense(@RequestBody RecurringExpense expense, @PathVariable(value = "year") int year, @PathVariable(value = "month") int month) {
		return createdExpenseService.createCreatedExpenseFromRecurringExpense(expense, year, month);
	}
	*/
}
