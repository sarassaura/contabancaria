package account;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import account.controller.AccountController;
import account.model.CheckingAccount;
import account.model.SavingsAccount;
import account.util.Colors;
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
			System.out.println(Colors.TEXT_BLACK_BOLD + Colors.ANSI_CYAN_BACKGROUND
					+ "*******************************************************");
			System.out.println("*                                                     *");
			System.out.println("*                      PETBANK                        *");
			System.out.println("*                                                     *");
			System.out.println("*******************************************************");
			System.out.println("*                                                     *");
			System.out.println("*              1 - Create Account                     *");
			System.out.println("*              2 - Show all Accounts                  *");
			System.out.println("*              3 - Search Account by ID               *");
			System.out.println("*              4 - Update Account                     *");
			System.out.println("*              5 - Delete Account                     *");
			System.out.println("*              6 - Withdraw Money                     *");
			System.out.println("*              7 - Deposit Money                      *");
			System.out.println("*              8 - Transfer Money                     *");
			System.out.println("*              9 - Exit                               *");
			System.out.println("*                                                     *");
			System.out.println("*******************************************************");
			System.out.println("             Choose an Operation by Number:            ");
			System.out.println("                                                       ");

			try {
				option = leia.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(Colors.TEXT_YELLOW + Colors.ANSI_BLACK_BACKGROUND
						+ "\n                 Enter Integer Values!                 ");
				leia.nextLine();
				option = 0;
			}

			switch (option) {
			case 1:
				System.out.print("                   Create Account                      \n");

				System.out.print(Colors.TEXT_RESET + "Enter the Agency Number: ");
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
				System.out.println("Show all Accounts\n");
				accounts.showAll();
				keyPress();
				break;
			case 3:
				System.out.println("Search Account Data - by ID\n\n");

				System.out.println("Enter the Account's ID: ");
				leia.nextLine();
				id = leia.nextLine();

				System.out.println();

				accounts.searchByNumber(id);

				keyPress();
				break;
			case 4:
				System.out.println("Update Account data                                    \n\n");

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
				System.out.println("Delete Account                                         \n\n");

				System.out.println("Enter the Account's ID: ");
				leia.nextLine();
				id = leia.nextLine();

				accounts.delete(id);

				keyPress();
				break;
			case 6:
				System.out.println("Withdraw                                               \n\n");

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
				System.out.println("Deposit                                                \n\n");

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
				System.out.println("Transfer Money Between Accounts                        \n\n");

				System.out.println("Enter the number of the source account:");
				leia.nextLine();
				id = leia.nextLine();
				System.out.println("Enter the number of the destination account:");
				destinyID = leia.nextLine();

				do {
					System.out.print("Enter the transfer amount (R$): ");
					value = leia.nextFloat();
				} while (value <= 0);

				accounts.transfer(id, destinyID, value);

				keyPress();
				break;
			case 9:
				System.out.println(Colors.TEXT_BLACK + "\n    PETBANK Where every pet's savings earns a treat!   ");
				credits();
				leia.close();
				break;
			default:
				System.out.println(Colors.TEXT_RED_BOLD + "\n*******************************************************");
				System.out.println("*                   Invalid Option!                   *");
				System.out.println("*******************************************************" + Colors.TEXT_RESET);
				break;
			}

		} while (option != 9);
	}

	public static void credits() {
		System.out.println("*******************************************************");
		System.out.println("*  Project Develop By:                                *");
		System.out.println("*  Generation Brasil - generation@generation.org      *");
		System.out.println("*  github.com/conteudoGeneration                      *");
		System.out.println("*  Coded by " + Colors.ANSI_PURPLE_BACKGROUND + Colors.TEXT_WHITE + "Sarah Yukino Nakada"
				+ Colors.ANSI_CYAN_BACKGROUND + Colors.TEXT_BLACK + "                       *");
		System.out.println("*  github.com/sarassaura                              *");
		System.out.println("*******************************************************");
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
