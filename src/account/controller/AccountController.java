package account.controller;

import java.util.ArrayList;
import java.util.UUID;

import account.model.Account;
import account.repository.RepositoryAccount;

public class AccountController implements RepositoryAccount {

	private ArrayList<Account> accountList = new ArrayList<Account>();

	@Override
	public void searchByNumber(String id) {
		var account = searchInCollection(id);

		if (account != null)
			account.showAccount();
		else
			System.out.println("\nThe Account ID : " + id + " was not found!");
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
			System.out.println("\nThe Account Number: " + account.getId() + " was successfully updated!");
		} else
			System.out.println("\nThe Account Number: " + account.getId() + " was not found!");

	}

	@Override
	public void delete(String id) {
		var account = searchInCollection(id);

		if (account != null) {
			if (accountList.remove(account) == true)
				System.out.println("\nThe Account Number: " + id + " was successfully deleted!");
		} else
			System.out.println("\nThe Account Number: " + id + " was not found!");

	}

	@Override
	public void withdraw(String id, float value) {
		var account = searchInCollection(id);

		if (account != null) {

			if (account.withdraw(value) == true)
				System.out.println("\nThe Withdraw on Account's Number: " + id + " was successfull!");
		} else
			System.out.println("\nThe Account Number: " + id + " was not found!");

	}

	@Override
	public void deposit(String id, float value) {
		var account = searchInCollection(id);

		if (account != null) {
			account.deposit(value);
			System.out.println("\nThe Deposit on Account's Number: " + id + " was succesfull!");
		} else
			System.out.println("\nThe Account Number: " + id + " was not found!");

	}

	@Override
	public void transfer(String sourceID, String destinyID, float value) {
		var sourceAccount = searchInCollection(sourceID);
		var destinyAccount = searchInCollection(destinyID);

		if (sourceAccount != null && destinyAccount != null) {

			if (sourceAccount.withdraw(value) == true) {
				destinyAccount.deposit(value);
				System.out.println("\nThe Transfer was successfull!");
			}
		} else
			System.out.println("\nThe Account was not Found!");
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
