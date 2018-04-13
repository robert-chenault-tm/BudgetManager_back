package chenaurj.budgetManager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import chenaurj.budgetManager.model.CreatedExpense;
import chenaurj.budgetManager.repository.util.CreatedExpenseRowMapper;

@Repository("createdExpenseRepository")
public class CreatedExpenseRepositoryImpl implements CreatedExpenseRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public CreatedExpense createCreatedExpense(CreatedExpense expense) {
		jdbcTemplate.update("insert into created_expense (id, category_id, name, amount, year, month) values (?, ?, ?, ?, ?, ?)", expense.getId(), expense.getCategoryId(), expense.getName(), expense.getAmount(), expense.getYear(), expense.getMonth());
		
		return getCreatedExpense(expense.getId());
	}

	@Override
	public void deleteCreatedExpense(String id) {
		jdbcTemplate.update("delete from created_expense where id = ?", id);		
	}

	@Override
	public CreatedExpense getCreatedExpense(String id) {
		CreatedExpense expense = jdbcTemplate.queryForObject("select * from created_expense where id = ?", new CreatedExpenseRowMapper(), id);
		
		return expense;
	}

	@Override
	public List<CreatedExpense> getCreatedExpenses() {
		List<CreatedExpense> expenses = jdbcTemplate.query("select * from created_expense", new CreatedExpenseRowMapper());
		
		return expenses;
	}

	@Override
	public List<CreatedExpense> getCreatedExpensesByCategory(String categoryId) {
		List<CreatedExpense> expenses = jdbcTemplate.query("select * from created_expense where category_id = ?", new CreatedExpenseRowMapper(), categoryId);
		
		return expenses;
	}

	@Override
	public CreatedExpense updateCreatedExpense(CreatedExpense expense) {
		jdbcTemplate.update("update created_expense set category_id = ?, name = ?, amount = ?, year = ?, month = ? where id = ?", expense.getCategoryId(), expense.getName(), expense.getAmount(), expense.getYear(), expense.getMonth(), expense.getId());
		return expense;
	}

	@Override
	public List<CreatedExpense> getMonthlyCreatedExpensesByCategory(String categoryId, int year, int month) {
		List<CreatedExpense> expenses = jdbcTemplate.query("select * from created_expense where category_id = ? and year = ? and month = ?", new CreatedExpenseRowMapper(), categoryId, year, month);
		
		return expenses;
	}
}
