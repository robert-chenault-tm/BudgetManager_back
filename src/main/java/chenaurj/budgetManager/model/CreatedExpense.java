package chenaurj.budgetManager.model;

public class CreatedExpense extends Expense {

	private int year;
	private int month;
	
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
}
