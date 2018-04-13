package chenaurj.budgetManager.model;

public class User {

	private String Id;
	private String username;
	private String password;
	private boolean enabled;
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getId() {
		return Id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setId(String id) {
		Id = id;
	}
	public void setUsername(String name) {
		this.username = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Id: " + Id + ", name: " + username;
	}

}
