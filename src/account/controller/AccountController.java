package account.controller;

import java.util.ArrayList;
import java.util.UUID;

import account.model.Account;
import account.repository.RepositoryAccount;
import account.util.Format;

public class AccountController implements RepositoryAccount {

	private ArrayList<Account> accountList = new ArrayList<Account>();

	@Override
	public void searchByNumber(String id) {
		var account = searchInCollection(id);

		if (account != null)
			account.showAccount();
		else
			Format.text("The Account ID : " + id + " was not found!", 1, 1, false);
	}

	@Override
	public void showAll() {
		for (var account : accountList) {
			account.showAccount();
		}
	}

	@Override
	public String signup(Account account) {
		accountList.add(account);
		return account.getId();
	}

	@Override
	public void update(Account account) {
		var searchAccount = searchInCollection(account.getId());

		if (searchAccount != null) {
			accountList.set(accountList.indexOf(searchAccount), account);
			Format.text("The Account Number: " + account.getId() + " was successfully updated!", 1, 1, false);
		} else
			Format.text("The Account Number: " + account.getId() + " was not found!", 1, 1, false);

	}

	@Override
	public void delete(String id) {
		var account = searchInCollection(id);

		if (account != null) {
			if (accountList.remove(account) == true)
				Format.text("The Account Number: " + id + " was successfully deleted!", 1, 1, false);
		} else
			Format.text("The Account Number: " + id + " was not found!", 1, 1, false);

	}

	@Override
	public void withdraw(String id, float value) {
		var account = searchInCollection(id);

		if (account != null) {

			if (account.withdraw(value) == true)
				Format.text("The Withdraw on Account's Number: " + id + " was successfull!",1,1,false);
		} else
			Format.text("The Account Number: " + id + " was not found!",1,1,false);

	}

	@Override
	public void deposit(String id, float value) {
		var account = searchInCollection(id);

		if (account != null) {
			account.deposit(value);
			Format.text("The Deposit on Account's Number: " + id + " was succesfull!",1,1,false);
		} else
			Format.text("The Account Number: " + id + " was not found!",1,1,false);

	}

	@Override
	public void transfer(String sourceID, String destinyID, float value) {
		var sourceAccount = searchInCollection(sourceID);
		var destinyAccount = searchInCollection(destinyID);

		if (sourceAccount != null && destinyAccount != null) {

			if (sourceAccount.withdraw(value) == true) {
				destinyAccount.deposit(value);
				Format.text("The Transfer was successfull!",1,1,false);
			}
		} else
			Format.text("The Account was not Found!",1,1,false);
	}

	public String generateID() {
		return UUID.randomUUID().toString();
	}

	public Account searchInCollection(String id) {
		for (var account : accountList) {
			if (id.equals(account.getId())) {
				return account;
			}
		}

		return null;
	}

}
