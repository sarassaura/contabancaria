package account;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import account.controller.AccountController;
import account.model.CheckingAccount;
import account.model.SavingsAccount;
import account.util.Colors;
import account.util.Format;
import mockup.MockupData;

public class Menu {

	public static void main(String[] args) {

		AccountController accounts = new AccountController();
		MockupData mockupData = new MockupData(accounts);
		mockupData.initialize();

		Scanner leia = new Scanner(System.in);

		int option = 0, agency, type, birthday;
		String owner, id = "", destinyID = "";
		float balance, limit, value;

		do {
			Format.stars(Colors.TEXT_BLACK_BOLD + Colors.ANSI_CYAN_BACKGROUND);
			Format.text("", 0, 1, true);
			Format.text("PETBANK", 0, 1, true);
			Format.text("", 0, 1, true);
			Format.stars("");
			Format.text("", 0, 1, true);
			Format.text("1 - Create Account      ", 0, 1, true);
			Format.text("2 - Show all Accounts   ", 0, 1, true);
			Format.text("3 - Search Account by ID", 0, 1, true);
			Format.text("4 - Update Account      ", 0, 1, true);
			Format.text("5 - Delete Account      ", 0, 1, true);
			Format.text("6 - Withdraw Money      ", 0, 1, true);
			Format.text("7 - Deposit Money       ", 0, 1, true);
			Format.text("8 - Transfer Money      ", 0, 1, true);
			Format.text("9 - Exit                ", 0, 1, true);
			Format.text("", 0, 1, true);
			Format.stars("");
			Format.text("Choose an Operation by Number:", 0, 1, true);
			Format.text("", 0, 1, true);

			try {
				System.out.print(Colors.TEXT_RESET);
				option = leia.nextInt();
				System.out.print(Colors.ANSI_CYAN_BACKGROUND + Colors.TEXT_BLACK_BOLD);
			} catch (InputMismatchException e) {
				System.out.print(Colors.TEXT_YELLOW + Colors.ANSI_BLACK_BACKGROUND);
				Format.text("Enter Integer Values!", 1, 1, true);
				leia.nextLine();
				option = 0;
			}

			switch (option) {
			case 1:
				Format.title("Create Account");

				System.out.print(Colors.TEXT_RESET);
				System.out.print("Enter the Agency Number: ");
				agency = leia.nextInt();
				System.out.print("Enter the Owner's Number: ");
				leia.skip("\\R?");
				owner = leia.nextLine();

				do {
					System.out.print("Enter the type of Account (1-CC ou 2-CP): ");
					type = leia.nextInt();
				} while (type < 1 || type > 2);

				System.out.print("Enter the Account Balance (R$): ");
				balance = leia.nextFloat();

				switch (type) {
				case 1 -> {
					System.out.print("Enter the Credit Limit (R$): ");
					limit = leia.nextFloat();
					String accountNumber = accounts
							.signup(new CheckingAccount(accounts.generateID(), agency, type, owner, balance, limit));
					System.out.println("\nThe Account Number: " + accountNumber + " was successfully created!");
				}
				case 2 -> {
					System.out.print("Enter the Account's Birthday: ");
					birthday = leia.nextInt();
					accounts.signup(new SavingsAccount(accounts.generateID(), agency, type, owner, balance, birthday));
				}
				}

				keyPress();
				break;
			case 2:
				Format.title("Show all Accounts");
				accounts.showAll();
				keyPress();
				break;
			case 3:
				Format.title("Search Account Data - by ID");

				System.out.print(Colors.TEXT_RESET);
				System.out.println("Enter the Account's ID: ");
				leia.nextLine();
				id = leia.nextLine();

				System.out.println();

				accounts.searchByNumber(id);

				keyPress();
				break;
			case 4:
				Format.title("Update Account data");

				System.out.print(Colors.TEXT_RESET);
				System.out.println("Enter Account's ID: ");
				leia.nextLine();
				id = leia.nextLine();

				var searchAccount = accounts.searchInCollection(id);

				if (searchAccount != null) {

					type = searchAccount.getType();

					System.out.print("Enter the Agency Number: ");
					agency = leia.nextInt();
					System.out.print("Enter the Owner's Name: ");
					leia.skip("\\R?");
					owner = leia.nextLine();

					System.out.print("Enter the Account Balance (R$): ");
					balance = leia.nextFloat();

					switch (type) {
					case 1 -> {
						System.out.print("Enter the Credit Limit (R$): ");
						limit = leia.nextFloat();

						accounts.update(new CheckingAccount(id, agency, type, owner, balance, limit));
					}
					case 2 -> {
						System.out.print("Enter the Account's Birthday: ");
						birthday = leia.nextInt();

						accounts.update(new SavingsAccount(id, agency, type, owner, balance, birthday));

					}
					default -> {
						System.out.println("Invalid Account Type!                                  ");
					}
					}

				} else {
					System.out.println("Account not Found!                                     ");
				}

				keyPress();
				break;
			case 5:
				Format.title("Delete Account");

				System.out.print(Colors.TEXT_RESET);
				System.out.println("Enter the Account's ID: ");
				leia.nextLine();
				id = leia.nextLine();

				accounts.delete(id);

				keyPress();
				break;
			case 6:
				Format.title("Withdraw");

				System.out.print(Colors.TEXT_RESET);
				System.out.println("Enter the Account's ID: ");
				leia.nextLine();
				id = leia.nextLine();

				do {
					System.out.print("Enter the Withdraw Amount (R$): ");
					value = leia.nextFloat();
				} while (value <= 0);

				accounts.withdraw(id, value);

				keyPress();
				break;
			case 7:
				Format.title("Deposit");

				System.out.print(Colors.TEXT_RESET);
				System.out.println("Enter the Account's ID:");
				leia.nextLine();
				id = leia.nextLine();

				do {
					System.out.print("Enter the Deposit Amount (R$): ");
					value = leia.nextFloat();
				} while (value <= 0);

				accounts.deposit(id, value);

				keyPress();
				break;
			case 8:
				Format.title("Transfer Money Between Accounts");

				System.out.print(Colors.TEXT_RESET);
				Format.text("Enter the number of the source account:",0,1,false);
				leia.nextLine();
				id = leia.nextLine();
				Format.text("Enter the number of the destination account:",0,1,false);
				destinyID = leia.nextLine();

				do {
					System.out.print("Enter the transfer amount (R$): ");
					value = leia.nextFloat();
				} while (value <= 0);

				accounts.transfer(id, destinyID, value);

				keyPress();
				break;
			case 9:
				System.out.print(Colors.TEXT_BLACK);
				Format.text("PETBANK Where every pet's savings earns a treat!", 1, 1, true);
				credits();
				leia.close();
				break;
			default:
				Format.stars("\n" + Colors.TEXT_RED_BOLD);
				Format.text("Invalid Option!", 0, 1, true);
				Format.stars("");
				System.out.print(Colors.TEXT_RESET + "\n");
				break;
			}

		} while (option != 9);
	}

	public static void credits() {
		Format.stars("");
		Format.text("Project Develop By:                          ", 0, 1, true);
		Format.text("Generation Brasil - generation@generation.org", 0, 1, true);
		Format.text("github.com/conteudoGeneration                ", 0, 1, true);
		Format.text("Coded by Sarah Yukino Nakada                 ", 0, 1, true);
		Format.text("github.com/sarassaura                        ", 0, 1, true);
		Format.stars("");
	}

	public static void keyPress() {
		try {
			System.out.println(Colors.TEXT_RESET + "\nPress Enter to Continue...");
			System.in.read();

		} catch (IOException e) {
			System.out.println("You pressed a key other than enter\n!");
		}
	}
}
