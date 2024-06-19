package account;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import account.controller.AccountController;
import account.model.CheckingAccount;
import account.model.SavingsAccount;
import account.util.Colors;

public class Menu {

	public static void main(String[] args) {

		AccountController contas = new AccountController();

		Scanner leia = new Scanner(System.in);

		int option = 0, number = 0, agency, type, birthday, destinyNumber;
		String owner;
		float balance, limit, value;

		CheckingAccount cc1 = new CheckingAccount(contas.generateNumber(), 123, 1, "João Silva", 1000f, 100.0f);
		contas.signup(cc1);

		CheckingAccount cc2 = new CheckingAccount(contas.generateNumber(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.signup(cc2);

		SavingsAccount cp1 = new SavingsAccount(contas.generateNumber(), 125, 2, "Mariana dos Santos", 4000f, 12);
		contas.signup(cp1);

		SavingsAccount cp2 = new SavingsAccount(contas.generateNumber(), 125, 2, "Juliana Ramos", 8000f, 15);
		contas.signup(cp2);

		

		// contas.listarTodas();

		do {
			System.out.println(Colors.TEXT_BLACK_BOLD + Colors.ANSI_CYAN_BACKGROUND
					+ "\n*******************************************************");
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
					contas.signup(new CheckingAccount(contas.generateNumber(), agency, type, owner, balance, limit));
				}
				case 2 -> {
					System.out.print("Enter the Account's Birthday: ");
					birthday = leia.nextInt();
					contas.signup(new SavingsAccount(contas.generateNumber(), agency, type, owner, balance, birthday));
				}
				}

				keyPress();
				break;
			case 2:
				System.out.println("Show all Accounts\n");
				contas.showAll();
				keyPress();
				break;
			case 3:
				System.out.println("Search Account Data - by ID\n\n");

				System.out.print("Enter the Account Number: ");
				number = leia.nextInt();

				System.out.println();

				contas.searchByNumber(number);

				keyPress();
				break;
			case 4:
				System.out.println("Update Account data                                    \n\n");

				System.out.print("Enter Account Number: ");
				number = leia.nextInt();

				var searchAccount = contas.searchInCollection(number);

				if (searchAccount != null) {

					type = searchAccount.getType();

					System.out.print("Enter the Agency Number: ");
					agency = leia.nextInt();
					System.out.print("Enter the Owner's Number: ");
					leia.skip("\\R?");
					owner = leia.nextLine();

					System.out.print("Enter the Account Balance (R$): ");
					balance = leia.nextFloat();

					switch (type) {
					case 1 -> {
						System.out.print("Enter the Credit Limit (R$): ");
						limit = leia.nextFloat();

						contas.update(new CheckingAccount(number, agency, type, owner, balance, limit));
					}
					case 2 -> {
						System.out.print("Enter the Account's Birthday: ");
						birthday = leia.nextInt();

						contas.update(new SavingsAccount(number, agency, type, owner, balance, birthday));

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

				System.out.print("Enter the Account number: ");
				number = leia.nextInt();

				contas.delete(number);

				keyPress();
				break;
			case 6:
				System.out.println("Withdraw                                               \n\n");

				System.out.print("Enter the Account Number: ");
				number = leia.nextInt();

				do {
					System.out.print("Enter the Withdraw Amount (R$): ");
					value = leia.nextFloat();
				} while (value <= 0);

				contas.withdraw(number, value);

				keyPress();
				break;
			case 7:
				System.out.println("Deposit                                                \n\n");

				System.out.print("Enter the Account Number: ");
				number = leia.nextShort();

				do {
					System.out.print("Enter the Deposit Amount (R$): ");
					value = leia.nextFloat();
				} while (value <= 0);

				contas.deposit(number, value);

				keyPress();
				break;
			case 8:
				System.out.println("Transfer Money Between Accounts                        \n\n");

				System.out.print("Enter the number of the source account: ");
				number = leia.nextInt();
				System.out.print("Enter the number of the destination account: ");
				destinyNumber = leia.nextInt();

				do {
					System.out.print("Enter the transfer amount (R$): ");
					value = leia.nextFloat();
				} while (value <= 0);

				contas.transfer(number, destinyNumber, value);

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
