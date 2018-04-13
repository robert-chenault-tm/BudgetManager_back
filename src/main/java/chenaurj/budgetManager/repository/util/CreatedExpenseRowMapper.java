package chenaurj.budgetManager.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import chenaurj.budgetManager.model.CreatedExpense;

public class CreatedExpenseRowMapper implements RowMapper<CreatedExpense> {

	@Override
	public CreatedExpense mapRow(ResultSet rs, int rowNum) throws SQLException {
		CreatedExpense expense = new CreatedExpense();
		
		expense.setId(rs.getString("id"));
		expense.setCategoryId(rs.getString("category_id"));
		expense.setName(rs.getString("name"));
		expense.setAmount(rs.getFloat("amount"));
		expense.setYear(rs.getInt("year"));
		expense.setMonth(rs.getInt("month"));
		
		return expense;
	}

}
