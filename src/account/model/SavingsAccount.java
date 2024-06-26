package account.model;

import account.util.Format;

public class SavingsAccount extends Account {

	private int birthday;

	public SavingsAccount(String id, int agency, int type, String owner, float balance, int birthday) {
		super(id, agency, type, owner, balance);
		this.birthday = birthday;
	}

	public int getBirthday() {
		return birthday;
	}

	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}

	@Override
	public void showAccount() {
		super.showAccount();
		Format.text("Account's Birthday: " + this.birthday,0,2,false);
	}

}
