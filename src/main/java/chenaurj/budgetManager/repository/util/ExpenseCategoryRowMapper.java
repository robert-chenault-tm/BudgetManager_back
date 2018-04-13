package chenaurj.budgetManager.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import chenaurj.budgetManager.model.ExpenseCategory;

public class ExpenseCategoryRowMapper implements RowMapper<ExpenseCategory> {

	@Override
	public ExpenseCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		ExpenseCategory cat = new ExpenseCategory();
		cat.setId(rs.getString("id"));
		cat.setUsername(rs.getString("username"));
		cat.setTitle(rs.getString("title"));
		cat.setExpectedAmount(rs.getInt("expected_amount"));
		cat.setIsIncome(rs.getBoolean("income"));
		
		return cat;
	}
	
}
