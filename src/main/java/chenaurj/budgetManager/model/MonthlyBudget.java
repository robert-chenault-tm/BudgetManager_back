package chenaurj.budgetManager.model;

public class MonthlyBudget {

	private String Id;
	private String username;
	private int month;
	private int year;
	
	public String getId() {
		return Id;
	}
	public int getMonth() {
		return month;
	}
	public String getUsername() {
		return username;
	}
	public int getYear() {
		return year;
	}
	public void setId(String Id) {
		this.Id = Id;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		return "Id: " + Id + ", userName: " + username + ", year: " + year + ", month: " + month;
	}
	
}
