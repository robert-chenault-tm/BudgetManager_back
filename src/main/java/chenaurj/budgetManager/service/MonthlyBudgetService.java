package chenaurj.budgetManager.service;

import java.util.List;

import chenaurj.budgetManager.model.MonthlyBudget;

public interface MonthlyBudgetService {

	List<MonthlyBudget> getMonths();

	MonthlyBudget getMonth(String userId, int year, int month);

	MonthlyBudget createMonth(MonthlyBudget month);

	void deleteMonth(String username, int year, int month);

	MonthlyBudget updateMonth(MonthlyBudget month);

	List<MonthlyBudget> getMonthsByUser(String username);

}
