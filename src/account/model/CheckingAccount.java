package account.model;

import account.util.Format;

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
			Format.text("Insufficient Funds!", 1, 1, true);
			return false;
		}

		this.setBalance(this.getBalance() - value);
		return true;

	}

	@Override
	public void showAccount() {
		super.showAccount();
		Format.text("Credit's Limit: " + this.limit, 0, 2, false);
	}

}
