package br.com.ifpe.oficina.apresentation;

import java.util.Scanner;

public class GUIMenu {
	public static void runGUI() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println();

		while (true) {
			System.out.println("[1]-Client\n[2]-Car\n");
			String choice = scanner.nextLine();

			switch (choice) {
			case "1":
				GUIClient guiClient = new GUIClient();
				guiClient.clientGUI();
				break;

			case "2":
				GUICar guiCar = new GUICar();
				guiCar.CarGUI();
				break;

			default:
				System.err.println("Opção " + choice + " é invalida");
				break;
			}
			System.out.println("--------------------------------------------------");
		}
	}
}
