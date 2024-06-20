package account.repository;

import account.model.Account;

public interface RepositoryAccount {

	// CRUD da Conta
	public void searchByNumber(String id);

	public void showAll();

	public String signup(Account account);

	public void update(Account account);

	public void delete(String id);

	// Métodos Bancários
	public void withdraw(String id, float value);

	public void deposit(String id, float value);

	public void transfer(String originNumber, String destinyNumber, float value);
}