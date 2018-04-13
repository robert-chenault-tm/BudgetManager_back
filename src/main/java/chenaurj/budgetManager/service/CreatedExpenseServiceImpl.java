package chenaurj.budgetManager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chenaurj.budgetManager.model.CreatedExpense;
import chenaurj.budgetManager.model.RecurringExpense;
import chenaurj.budgetManager.repository.CreatedExpenseRepository;
import chenaurj.budgetManager.repository.RecurringExpenseRepository;

@Service("createdExpenseService")
public class CreatedExpenseServiceImpl implements CreatedExpenseService {

	@Autowired
	private CreatedExpenseRepository createdExpenseRepository;
	
	@Autowired
	private RecurringExpenseRepository recurringExpenseRepository;
	
	@Override
	public CreatedExpense createCreatedExpense(CreatedExpense expense) {
		expense.setId(UUID.randomUUID().toString());
		return createdExpenseRepository.createCreatedExpense(expense);
	}

	@Override
	public CreatedExpense createCreatedExpenseFromRecurringExpense(RecurringExpense expense, int year, int month) {
		CreatedExpense cExpense = new CreatedExpense();
		
		cExpense.setCategoryId(expense.getCategoryId());
		cExpense.setName(expense.getName());
		cExpense.setAmount(expense.getAmount());
		cExpense.setYear(year);
		cExpense.setMonth(month);
		cExpense.setId(UUID.randomUUID().toString());
		
		return createdExpenseRepository.createCreatedExpense(cExpense);
	}
	
	@Override
	public List<CreatedExpense> createCreatedExpensesFromRecurringExpenses(String username, int year, int month) {
		
		List<RecurringExpense> expenses = recurringExpenseRepository.getRecurringExpenses(username);
		List<CreatedExpense> createdExpenses = new ArrayList<CreatedExpense>();
		
		for(RecurringExpense rExpense : expenses) {
			CreatedExpense cExpense = new CreatedExpense();
			
			cExpense.setCategoryId(rExpense.getCategoryId());
			cExpense.setName(rExpense.getName());
			cExpense.setAmount(rExpense.getAmount());
			cExpense.setYear(year);
			cExpense.setMonth(month);
			cExpense.setId(UUID.randomUUID().toString());
			
			cExpense = createdExpenseRepository.createCreatedExpense(cExpense);
			
			createdExpenses.add(cExpense);
		}
		
		return createdExpenses;
	}

	@Override
	public void deleteCreatedExpense(String id) {
		createdExpenseRepository.deleteCreatedExpense(id);
	}

	@Override
	public CreatedExpense getCreatedExpense(String id) {
		return createdExpenseRepository.getCreatedExpense(id);
	}

	@Override
	public List<CreatedExpense> getCreatedExpenses() {
		return createdExpenseRepository.getCreatedExpenses();
	}

	@Override
	public List<CreatedExpense> getCreatedExpensesByCategory(String categoryId) {
		return createdExpenseRepository.getCreatedExpensesByCategory(categoryId);
	}

	@Override
	public CreatedExpense updateCreatedExpense(CreatedExpense expense) {
		return createdExpenseRepository.updateCreatedExpense(expense);
	}

	@Override
	public List<CreatedExpense> getMonthlyCreatedExpensesByCategory(String categoryId, int year, int month) {
		return createdExpenseRepository.getMonthlyCreatedExpensesByCategory(categoryId, year, month);
	}

}
