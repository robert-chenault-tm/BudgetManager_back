package chenaurj.budgetManager.repository;

import java.util.List;

import chenaurj.budgetManager.model.MonthlyBudget;

public interface MonthlyBudgetRepository {

	List<MonthlyBudget> getMonths();

	MonthlyBudget getMonth(String userId, int year, int month);

	MonthlyBudget createMonth(MonthlyBudget month);

	void deleteMonth(String username, int year, int month);

	MonthlyBudget updateMonth(MonthlyBudget month);

	List<MonthlyBudget> getMonthsByUser(String username);

}
