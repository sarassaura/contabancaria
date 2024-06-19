package account.model;

import account.util.Colors;

public abstract class Account {

	private int number;
	private int agency;
	private int type;
	private String owner;
	private float balance;

	public Account(int number, int agency, int type, String owner, float balance) {
		this.number = number;
		this.agency = agency;
		this.type = type;
		this.owner = owner;
		this.balance = balance;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getAgency() {
		return agency;
	}

	public void setAgency(int agency) {
		this.agency = agency;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public boolean withdraw(float value) {

		if (this.getBalance() < value) {
			System.out.println("\nInsufficient Funds\n!");
			return false;
		}

		this.setBalance(this.getBalance() - value);
		return true;
	}

	public void deposit(float value) {

		this.setBalance((this.getBalance() + value));

	}

	public void showAccount() {

		String type = "";

		switch (this.type) {
		case 1:
			type = "Checking Account";
			break;
		case 2:
			type = "Savings Account";
			break;
		}

		System.out.println(Colors.TEXT_RED + Colors.ANSI_BLACK_BACKGROUND
				+ "*******************************************************");
		System.out.println("*" + Colors.TEXT_WHITE + "                   Account's Data:                   "
				+ Colors.TEXT_RED + "*" + Colors.TEXT_WHITE);
		System.out.println(
				Colors.TEXT_RED + "*******************************************************" + Colors.TEXT_WHITE);
		System.out.println("Account's Number: " + this.number);
		System.out.println("Agency: " + this.agency);
		System.out.println("Account's Type: " + type);
		System.out.println("Owner: " + this.owner);
		System.out.println("Balance: " + this.balance);
	}

}
