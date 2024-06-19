package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {

	public static void main(String[] args) {

		ContaController contas = new ContaController();

		Scanner leia = new Scanner(System.in);

		int option = 0, number = 0, agency, type, birthday, destinyNumber;
		String owner;
		float balance, limit, value;

		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);

		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);

		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana dos Santos", 4000f, 12);
		contas.cadastrar(cp1);

		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);

		contas.listarTodas();

		while (true) {
			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					+ "\n*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     ");

			try {
				option = leia.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				option = 0;
			}

			if (option == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brazil com Z - O seu Futuro começa aqui!");
				sobre();
				leia.close();
				System.exit(0);
			}

			switch (option) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");

				System.out.println("Digite o Numero da Agencia: ");
				agency = leia.nextInt();
				System.out.println("Digite o Nome do Titular: ");
				leia.skip("\\R?");
				owner = leia.nextLine();

				do {
					System.out.println("Digite o Tipo da Conta (1-CC ou 2-CP): ");
					type = leia.nextInt();
				} while (type < 1 || type > 2);

				System.out.println("Digite o Saldo da Conta (R$): ");
				balance = leia.nextFloat();

				switch (type) {
				case 1 -> {
					System.out.println("Digite o Limite de Crédito (R$): ");
					limit = leia.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agency, type, owner, balance, limit));
				}
				case 2 -> {
					System.out.println("Digite o dia do Aniversario da Conta: ");
					birthday = leia.nextInt();
					contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agency, type, owner, balance, birthday));
				}
				}

				keyPress();
				break;
			case 2:
				System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");
				contas.listarTodas();
				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por número\n\n");

				System.out.println("Digite o número da conta: ");
				number = leia.nextInt();

				contas.procurarPorNumero(number);

				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");

				System.out.println("Digite o número da conta: ");
				number = leia.nextInt();

				var searchAccount = contas.buscarNaCollection(number);

				if (searchAccount != null) {

					type = searchAccount.getTipo();

					System.out.println("Digite o Numero da Agencia: ");
					agency = leia.nextInt();
					System.out.println("Digite o Nome do Titular: ");
					leia.skip("\\R?");
					owner = leia.nextLine();

					System.out.println("Digite o Saldo da Conta (R$): ");
					balance = leia.nextFloat();

					switch (type) {
					case 1 -> {
						System.out.println("Digite o Limite de Crédito (R$): ");
						limit = leia.nextFloat();

						contas.atualizar(new ContaCorrente(number, agency, type, owner, balance, limit));
					}
					case 2 -> {
						System.out.println("Digite o dia do Aniversario da Conta: ");
						birthday = leia.nextInt();

						contas.atualizar(new ContaPoupanca(number, agency, type, owner, balance, birthday));

					}
					default -> {
						System.out.println("Tipo de conta inválido!");
					}
					}

				} else {
					System.out.println("A conta não foi encontrada!");
				}

				keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");

				System.out.println("Digite o numero da conta: ");
				number = leia.nextInt();

				contas.deletar(number);

				keyPress();
				break;
			case 6:
				System.out.println(Cores.TEXT_WHITE + "Saque\n\n");

				System.out.println("Digite o Numero da conta: ");
				number = leia.nextInt();

				do {
					System.out.println("Digite o valor do saque (R$): ");
					value = leia.nextFloat();
				} while (value <= 0);

				contas.sacar(number, value);

				keyPress();
				break;
			case 7:
				System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");

				System.out.println("Digite o numero da conta: ");
				number = leia.nextShort();

				do {
					System.out.println("Digite o Valor do Depósito (R$): ");
					value = leia.nextFloat();
				} while (value <= 0);
				
				contas.depositar(number, value);
				
				keyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");
				
				System.out.println("Digite o numero da conta de origem: ");
				number = leia.nextInt();
				System.out.println("Digite o numero da conta de destino: ");
				destinyNumber = leia.nextInt();
				
				do {
					System.out.println("Digite o valor da transferencia (R$): ");
					value = leia.nextFloat();
				} while(value <=0);
				
				contas.transferir(number, destinyNumber, value);
				
				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
				break;
			}

		}
	}

	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: ");
		System.out.println("Generation Brasil - generation@generation.org");
		System.out.println("github.com/conteudoGeneration");
		System.out.println("*********************************************************");
	}

	public static void keyPress() {
		try {
			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");

			char c = (char) System.in.read();
			if (c != 13) {
				throw new IOException();
			}
		} catch (IOException e) {
			System.out.println("Você pressionou uma tecla diferente de enter!");
		}
	}
}
