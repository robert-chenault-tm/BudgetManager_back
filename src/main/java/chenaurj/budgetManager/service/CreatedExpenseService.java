package chenaurj.budgetManager.service;

import java.util.List;

import chenaurj.budgetManager.model.CreatedExpense;
import chenaurj.budgetManager.model.RecurringExpense;

public interface CreatedExpenseService {

	CreatedExpense createCreatedExpense(CreatedExpense expense);

	void deleteCreatedExpense(String Id);

	CreatedExpense getCreatedExpense(String Id);

	List<CreatedExpense> getCreatedExpenses();

	List<CreatedExpense> getCreatedExpensesByCategory(String categoryId);
	
	List<CreatedExpense> getMonthlyCreatedExpensesByCategory(String categoryId, int year, int month);

	CreatedExpense updateCreatedExpense(CreatedExpense expense);
	
	CreatedExpense createCreatedExpenseFromRecurringExpense(RecurringExpense expense, int year, int month);

	List<CreatedExpense> createCreatedExpensesFromRecurringExpenses(String username, int year, int month);
	
}
