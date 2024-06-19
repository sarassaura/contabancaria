package account.repository;

import account.model.Account;

public interface RepositoryAccount {

	// CRUD da Conta
	public void searchByNumber(int number);

	public void showAll();

	public void signup(Account account);

	public void update(Account account);

	public void delete(int number);

	// Métodos Bancários
	public void withdraw(int number, float value);

	public void deposit(int number, float value);

	public void transfer(int originNumber, int destinyNumber, float value);
}