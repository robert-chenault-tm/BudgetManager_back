package chenaurj.budgetManager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import chenaurj.budgetManager.model.RecurringExpense;
import chenaurj.budgetManager.repository.util.RecurringExpenseRowMapper;

@Repository("recurringExpenseRepository")
public class RecurringExpenseRepositoryImpl implements RecurringExpenseRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public RecurringExpense createRecurringExpense(RecurringExpense expense) {
		jdbcTemplate.update("insert into recurring_expense (id, category_id, name, amount) values (?, ?, ?, ?)", expense.getId(), expense.getCategoryId(), expense.getName(), expense.getAmount());
		
		return getRecurringExpense(expense.getId());
	}
	
	@Override
	public void deleteRecurringExpense(String id) {
		jdbcTemplate.update("delete from recurring_expense where id = ?", id);
	}
	
	@Override
	public RecurringExpense getRecurringExpense(String id) {
		RecurringExpense expense = jdbcTemplate.queryForObject("select * from recurring_expense where id = ?", new RecurringExpenseRowMapper(), id);
		
		return expense;
	}
	
	@Override
	public List<RecurringExpense> getRecurringExpenses(String username) {
		List<RecurringExpense> expenses = jdbcTemplate.query("select ex.id, ex.category_id, ex.name, ex.amount from recurring_expense ex inner join expense_category cat on ex.category_id = cat.id where cat.username = ?", new RecurringExpenseRowMapper(), username);
		
		return expenses;
	}
	
	@Override
	public List<RecurringExpense> getRecurringExpensesByCategory(String categoryId) {
		List<RecurringExpense> expenses = jdbcTemplate.query("select * from recurring_expense where category_id = ?", new RecurringExpenseRowMapper(), categoryId);
		
		return expenses;
	}
	
	@Override
	public RecurringExpense updateRecurringExpense(RecurringExpense expense) {
		jdbcTemplate.update("update recurring_expense set category_id = ?, name = ?, amount = ? where id = ?", expense.getCategoryId(), expense.getName(), expense.getAmount(), expense.getId());
		return expense;
	}
	
}
