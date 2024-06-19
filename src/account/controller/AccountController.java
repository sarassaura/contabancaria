package account.controller;

import java.util.ArrayList;

import account.model.Account;
import account.repository.RepositoryAccount;

public class AccountController implements RepositoryAccount {

	private ArrayList<Account> accountList = new ArrayList<Account>();
	int number = 0;

	@Override
	public void searchByNumber(int number) {
		var account = searchInCollection(number);

		if (account != null)
			account.showAccount();
		else
			System.out.println("\nThe Account Number : " + number + " was not found!");
	}

	@Override
	public void showAll() {
		for (var account : accountList) {
			account.showAccount();
		}
	}

	@Override
	public void signup(Account account) {
		accountList.add(account);
		System.out.println("\nThe Account Number: " + account.getNumber() + " was successfully created!");
	}

	@Override
	public void update(Account account) {
		var searchAccount = searchInCollection(account.getNumber());

		if (searchAccount != null) {
			accountList.set(accountList.indexOf(searchAccount), account);
			System.out.println("\nThe Account Number: " + account.getNumber() + " was successfully updated!");
		} else
			System.out.println("\nThe Account Number: " + account.getNumber() + " was not found!");

	}

	@Override
	public void delete(int number) {
		var account = searchInCollection(number);

		if (account != null) {
			if (accountList.remove(account) == true)
				System.out.println("\nThe Account Number: " + number + " was successfully deleted!");
		} else
			System.out.println("\nThe Account Number: " + number + " was not found!");

	}

	@Override
	public void withdraw(int number, float value) {
		var account = searchInCollection(number);

		if (account != null) {

			if (account.withdraw(value) == true)
				System.out.println("\nThe Withdraw on Account's Number: " + number + " was successfull!");
		} else
			System.out.println("\nThe Account Number: " + number + " was not found!");

	}

	@Override
	public void deposit(int number, float value) {
		var account = searchInCollection(number);

		if (account != null) {
			account.deposit(value);
			System.out.println("\nThe Deposit on Account's Number: " + number + " was succesfull!");
		} else
			System.out.println("\nThe Account Number: " + number + " was not found!");

	}

	@Override
	public void transfer(int sourceNumber, int destinyNumber, float value) {
		var sourceAccount = searchInCollection(sourceNumber);
		var destinyAccount = searchInCollection(destinyNumber);

		if (sourceAccount != null && destinyAccount != null) {

			if (sourceAccount.withdraw(value) == true) {
				destinyAccount.deposit(value);
				System.out.println("\nThe Transfer was successfull!");
			}
		} else
			System.out.println("\nThe Account was not Found!");
	}

	public int generateNumber() {
		return ++number;
	}

	public Account searchInCollection(int number) {
		for (var account : accountList) {
			if (account.getNumber() == number) {
				return account;
			}
		}

		return null;
	}

}
