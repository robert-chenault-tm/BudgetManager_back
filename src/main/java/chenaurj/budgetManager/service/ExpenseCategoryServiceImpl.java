package chenaurj.budgetManager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chenaurj.budgetManager.model.ExpenseCategory;
import chenaurj.budgetManager.repository.ExpenseCategoryRepository;

@Service("expenseCategoryService")
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

	@Autowired
	private ExpenseCategoryRepository expenseCategoryRepository;
	
	@Override
	public ExpenseCategory createExpenseCategory(ExpenseCategory cat) {
		cat.setId(UUID.randomUUID().toString());
		return expenseCategoryRepository.createExpenseCategory(cat);
	}

	@Override
	@Transactional
	public void deleteExpenseCategory(String id) {
		expenseCategoryRepository.deleteExpenseCategory(id);
		
	}

	@Override
	public ExpenseCategory getExpenseCategory(String id) {
		return expenseCategoryRepository.getExpenseCategory(id);
	}

	@Override
	public List<ExpenseCategory> getExpenseCategories() {
		return expenseCategoryRepository.getExpenseCategories();
	}

	@Override
	public List<ExpenseCategory> getExpenseCategoriesByUser(String username) {
		return expenseCategoryRepository.getExpenseCategoriesByUser(username);
	}

	@Override
	public ExpenseCategory updateExpenseCategory(ExpenseCategory cat) {
		return expenseCategoryRepository.updateExpenseCategory(cat);
	}

}
