package chenaurj.budgetManager.repository;

import java.util.List;

import chenaurj.budgetManager.model.RecurringExpense;

public interface RecurringExpenseRepository {

	RecurringExpense createRecurringExpense(RecurringExpense expense);

	void deleteRecurringExpense(String id);

	RecurringExpense getRecurringExpense(String id);

	List<RecurringExpense> getRecurringExpenses(String username);

	List<RecurringExpense> getRecurringExpensesByCategory(String categoryId);

	RecurringExpense updateRecurringExpense(RecurringExpense expense);

}
