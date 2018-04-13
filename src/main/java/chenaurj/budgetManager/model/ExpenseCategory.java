package chenaurj.budgetManager.model;

public class ExpenseCategory {
	
	private String Id;
	private String username;
	private String title;
	private int expectedAmount;
	private boolean isIncome;
	
	public int getExpectedAmount() {
		return expectedAmount;
	}
	public String getId() {
		return Id;
	}
	public String getTitle() {
		return title;
	}
	public String getUsername() {
		return username;
	}
	public void setExpectedAmount(int expectedAmount) {
		this.expectedAmount = expectedAmount;
	}
	public void setId(String id) {
		Id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isIncome() {
		return isIncome;
	}
	public void setIsIncome(boolean isIncome) {
		this.isIncome = isIncome;
	}
}
