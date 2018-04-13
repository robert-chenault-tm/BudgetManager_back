package chenaurj.budgetManager.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import chenaurj.budgetManager.model.RecurringExpense;

public class RecurringExpenseRowMapper implements RowMapper<RecurringExpense>{

	@Override
	public RecurringExpense mapRow(ResultSet rs, int rowNum) throws SQLException {
		RecurringExpense expense = new RecurringExpense();
		
		expense.setId(rs.getString("id"));
		expense.setCategoryId(rs.getString("category_id"));
		expense.setName(rs.getString("name"));
		expense.setAmount(rs.getFloat("amount"));
		
		return expense;
	}

}
