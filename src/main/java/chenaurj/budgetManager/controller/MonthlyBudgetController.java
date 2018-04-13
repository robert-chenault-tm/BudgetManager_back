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

import chenaurj.budgetManager.model.MonthlyBudget;
import chenaurj.budgetManager.service.MonthlyBudgetService;

@CrossOrigin(origins = "http://localhost:8000")
@Controller
public class MonthlyBudgetController {

	@Autowired
	private MonthlyBudgetService monthlyBudgetService;
	
	@RequestMapping(value = "/month", method = RequestMethod.POST)
	public @ResponseBody MonthlyBudget createMonth(@RequestBody MonthlyBudget month) {
		return monthlyBudgetService.createMonth(month);
	}
	
	@RequestMapping(value = "/delete/month/{username}/{year}/{month}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteMonth(@PathVariable(value = "username") String username, @PathVariable(value = "year") int year, @PathVariable(value = "month") int month) {
		monthlyBudgetService.deleteMonth(username, year, month);
		
		return null;
	}
	
	@RequestMapping(value = "/months/{username}", method = RequestMethod.GET)
	public @ResponseBody List<MonthlyBudget> getMonthsByUser(@PathVariable(value = "username") String username) {
		return monthlyBudgetService.getMonthsByUser(username);
	}
	
	/* Didnt end up using these ones, perhaps in the future
	@RequestMapping(value = "/month", method = RequestMethod.PUT)
	public @ResponseBody MonthlyBudget updateMonth(@RequestBody MonthlyBudget month) {
		return monthlyBudgetService.updateMonth(month);
	}
	
	@RequestMapping(value = "/month/{userId}/{year}/{month}", method = RequestMethod.GET)
	public @ResponseBody MonthlyBudget getMonth(@PathVariable(value = "userId") String userId, @PathVariable(value = "year") int year, @PathVariable(value = "month") int month) {
		return monthlyBudgetService.getMonth(userId, year, month);
	}
	
	@RequestMapping(value = "/months", method = RequestMethod.GET)
	public @ResponseBody List<MonthlyBudget> getMonths() {
		return monthlyBudgetService.getMonths();
	}
	*/
}
