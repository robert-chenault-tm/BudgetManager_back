package chenaurj.budgetManager.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import chenaurj.budgetManager.model.MonthlyBudget;

public class MonthlyBudgetRowMapper implements RowMapper<MonthlyBudget>{

	@Override
	public MonthlyBudget mapRow(ResultSet rs, int rowNum) throws SQLException {
		MonthlyBudget month = new MonthlyBudget();
		month.setId(rs.getString("id"));
		month.setUsername(rs.getString("username"));
		month.setMonth(rs.getInt("month"));
		month.setYear(rs.getInt("year"));
		return month;
	}

}
