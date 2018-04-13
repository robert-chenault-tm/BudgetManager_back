package chenaurj.budgetManager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chenaurj.budgetManager.model.MonthlyBudget;
import chenaurj.budgetManager.repository.MonthlyBudgetRepository;

@Service("monthService")
public class MonthlyBudgetServiceImpl implements MonthlyBudgetService {

	@Autowired
	private MonthlyBudgetRepository monthlyBudgetRepository;
	
	@Autowired
	private CreatedExpenseService createdExpenseService;

	@Override
	public MonthlyBudget createMonth(MonthlyBudget month) {
		month.setId(UUID.randomUUID().toString());
		MonthlyBudget retMonth = monthlyBudgetRepository.createMonth(month);
		createdExpenseService.createCreatedExpensesFromRecurringExpenses(month.getUsername(), month.getYear(), month.getMonth());
		
		return retMonth;
	}

	@Override
	public void deleteMonth(String username, int year, int month) {
		monthlyBudgetRepository.deleteMonth(username, year, month);
	}

	@Override
	public MonthlyBudget getMonth(String userId, int year, int month) {
		return monthlyBudgetRepository.getMonth(userId, year, month);
	}

	@Override
	public List<MonthlyBudget> getMonths() {
		return monthlyBudgetRepository.getMonths();
	}

	@Override
	public List<MonthlyBudget> getMonthsByUser(String username) {
		return monthlyBudgetRepository.getMonthsByUser(username);
	}

	@Override
	public MonthlyBudget updateMonth(MonthlyBudget month) {
		return monthlyBudgetRepository.updateMonth(month);
	}
	
}
