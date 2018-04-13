package chenaurj.budgetManager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import chenaurj.budgetManager.model.ExpenseCategory;
import chenaurj.budgetManager.repository.util.ExpenseCategoryRowMapper;

@Repository("expenseCategoryRepository")
public class ExpenseCategoryRepositoryImpl implements ExpenseCategoryRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ExpenseCategory createExpenseCategory(ExpenseCategory cat) {
		jdbcTemplate.update("insert into expense_category (id, username, title, expected_amount, income) values (?, ?, ?, ?, ?)", cat.getId(), cat.getUsername(), cat.getTitle(), cat.getExpectedAmount(), cat.isIncome());
		
		return getExpenseCategory(cat.getId());
	}

	@Override
	public void deleteExpenseCategory(String id) {
		jdbcTemplate.update("delete from created_expense where category_id = ?", id);
		jdbcTemplate.update("delete from recurring_expense where category_id = ?", id);
		jdbcTemplate.update("delete from expense_category where id = ?", id);
	}

	@Override
	public ExpenseCategory getExpenseCategory(String id) {
		ExpenseCategory cat = jdbcTemplate.queryForObject("select * from expense_category where id = ?", new ExpenseCategoryRowMapper(), id);
		
		return cat;
	}

	@Override
	public List<ExpenseCategory> getExpenseCategories() {
		List<ExpenseCategory> cats = jdbcTemplate.query("select * from expense_category", new ExpenseCategoryRowMapper());
		
		return cats;
	}

	@Override
	public List<ExpenseCategory> getExpenseCategoriesByUser(String username) {
		List<ExpenseCategory> cats = jdbcTemplate.query("select * from expense_category where username = ? order by expense_category.income desc, expense_category.title asc", new ExpenseCategoryRowMapper(), username);
		
		return cats;
	}

	@Override
	public ExpenseCategory updateExpenseCategory(ExpenseCategory cat) {
		jdbcTemplate.update("update expense_category set title = ?, expected_amount = ?, income = ? where id = ?", cat.getTitle(), cat.getExpectedAmount(), cat.isIncome(), cat.getId());
		return cat;
	}
}
