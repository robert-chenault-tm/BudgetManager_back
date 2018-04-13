package chenaurj.budgetManager.repository;

import java.util.List;

import chenaurj.budgetManager.model.ExpenseCategory;

public interface ExpenseCategoryRepository {

	ExpenseCategory createExpenseCategory(ExpenseCategory cat);

	void deleteExpenseCategory(String id);

	ExpenseCategory getExpenseCategory(String id);

	List<ExpenseCategory> getExpenseCategories();

	List<ExpenseCategory> getExpenseCategoriesByUser(String username);

	ExpenseCategory updateExpenseCategory(ExpenseCategory cat);

}
