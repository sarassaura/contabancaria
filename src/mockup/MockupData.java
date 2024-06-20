package mockup;

import account.controller.AccountController;
import account.model.CheckingAccount;
import account.model.SavingsAccount;

public class MockupData {
	AccountController accounts;
	
	public MockupData(AccountController accounts) {
		this.accounts = accounts;
	}
	
	public void initialize() {
		CheckingAccount cc1 = new CheckingAccount(accounts.generateID(), 123, 1, "João Silva", 1000f, 100.0f);
		accounts.signup(cc1);

		CheckingAccount cc2 = new CheckingAccount(accounts.generateID(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		accounts.signup(cc2);

		SavingsAccount cp1 = new SavingsAccount(accounts.generateID(), 125, 2, "Mariana dos Santos", 4000f, 12);
		accounts.signup(cp1);

		SavingsAccount cp2 = new SavingsAccount(accounts.generateID(), 125, 2, "Juliana Ramos", 8000f, 15);
		accounts.signup(cp2);
	}

}
