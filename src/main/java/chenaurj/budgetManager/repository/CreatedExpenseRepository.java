package chenaurj.budgetManager.repository;

import java.util.List;

import chenaurj.budgetManager.model.CreatedExpense;

public interface CreatedExpenseRepository {

	CreatedExpense createCreatedExpense(CreatedExpense expense);

	void deleteCreatedExpense(String Id);

	CreatedExpense getCreatedExpense(String Id);

	List<CreatedExpense> getCreatedExpenses();

	List<CreatedExpense> getCreatedExpensesByCategory(String categoryId);
	
	List<CreatedExpense> getMonthlyCreatedExpensesByCategory(String categoryId, int year, int month);

	CreatedExpense updateCreatedExpense(CreatedExpense expense);

}
