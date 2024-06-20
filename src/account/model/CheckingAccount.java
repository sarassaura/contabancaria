package account.model;

public class CheckingAccount extends Account {

	private float limit;

	public CheckingAccount(String id, int agency, int type, String owner, float balance, float limit) {
		super(id, agency, type, owner, balance);
		this.limit = limit;
	}

	public float getLimit() {
		return limit;
	}

	public void setLimit(float limit) {
		this.limit = limit;
	}

	@Override
	public boolean withdraw(float value) {

		if (this.getBalance() + this.getLimit() < value) {
			System.out.println("\nInsufficient Funds!");
			return false;
		}

		this.setBalance(this.getBalance() - value);
		return true;

	}

	@Override
	public void showAccount() {
		super.showAccount();
		System.out.println("Credit's Limit: " + this.limit + "\n");
	}

}
