package chenaurj.budgetManager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chenaurj.budgetManager.model.RecurringExpense;
import chenaurj.budgetManager.repository.RecurringExpenseRepository;

@Service("recurringExpenseService")
public class RecurringExpenseServiceImpl implements RecurringExpenseService {

	@Autowired
	private RecurringExpenseRepository recurringExpenseRepository;
	
	@Override
	public RecurringExpense createRecurringExpense(RecurringExpense expense) {
		expense.setId(UUID.randomUUID().toString());
		return recurringExpenseRepository.createRecurringExpense(expense);
	}

	@Override
	public void deleteRecurringExpense(String id) {
		recurringExpenseRepository.deleteRecurringExpense(id);
	}

	@Override
	public RecurringExpense getRecurringExpense(String id) {
		return recurringExpenseRepository.getRecurringExpense(id);
	}

	@Override
	public List<RecurringExpense> getRecurringExpenses(String username) {
		return recurringExpenseRepository.getRecurringExpenses(username);
	}

	@Override
	public List<RecurringExpense> getRecurringExpensesByCategory(String categoryId) {
		return recurringExpenseRepository.getRecurringExpensesByCategory(categoryId);
	}

	@Override
	public RecurringExpense updateRecurringExpense(RecurringExpense expense) {
		return recurringExpenseRepository.updateRecurringExpense(expense);
	}

}
