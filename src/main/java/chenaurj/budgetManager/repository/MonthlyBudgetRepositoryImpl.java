package chenaurj.budgetManager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import chenaurj.budgetManager.model.MonthlyBudget;
import chenaurj.budgetManager.repository.util.MonthlyBudgetRowMapper;

@Repository("monthRepository")
public class MonthlyBudgetRepositoryImpl implements MonthlyBudgetRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public MonthlyBudget createMonth(MonthlyBudget month) {
		jdbcTemplate.update("insert into monthly_budget (id, username, year, month) values (?, ?, ?, ?)", month.getId(), month.getUsername(), month.getYear(), month.getMonth());
		
		return getMonth(month.getId());
	}

	@Override
	public void deleteMonth(String username, int year, int month) {
		jdbcTemplate.update("delete from monthly_budget where username = ? and year = ? and month = ?", username, year, month);		
	}

	private MonthlyBudget getMonth(String id) {
		MonthlyBudget monthlyBudget = jdbcTemplate.queryForObject("select * from monthly_budget where id = ?", new MonthlyBudgetRowMapper(), id);
		
		return monthlyBudget;
	}

	@Override
	public MonthlyBudget getMonth(String userId, int year, int month) {
		MonthlyBudget monthlyBudget = jdbcTemplate.queryForObject("select * from monthly_budget where userid = ? and year = ? and month = ?", new MonthlyBudgetRowMapper(), userId, year, month);
		
		return monthlyBudget;
	}

	@Override
	public List<MonthlyBudget> getMonths() {
		List<MonthlyBudget> months = jdbcTemplate.query("select * from monthly_budget", new MonthlyBudgetRowMapper());
		
		return months;
	}

	@Override
	public List<MonthlyBudget> getMonthsByUser(String username) {
		List<MonthlyBudget> months = jdbcTemplate.query("select * from monthly_budget where username = ? order by monthly_budget.year desc, monthly_budget.month desc", new MonthlyBudgetRowMapper(), username);
		
		return months;
	}

	@Override
	public MonthlyBudget updateMonth(MonthlyBudget month) {
		jdbcTemplate.update("update monthly_budget set year = ?, month = ? where id = ?", month.getYear(), month.getMonth(), month.getId());
		return month;
	}

	
}
