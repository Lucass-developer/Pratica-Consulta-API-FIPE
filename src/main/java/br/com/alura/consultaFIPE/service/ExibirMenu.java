package br.com.alura.consultaFIPE.service;

import java.util.Scanner;

public class ExibirMenu {

    public  String exbirMenu() {

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("====== MENU ======");
            System.out.println("1. Carros\n2. Motos\n3. Caminhões");
            System.out.println("Escolha uma opção:");

            var numModelo = scanner.nextInt();
            scanner.nextLine();

            switch (numModelo) {
                case 1: {
                    return "carros";
                }
                case 2: {
                    return "motos";
                }
                case 3: {
                    return "caminhoes";
                }
                default: {
                    System.out.println("Opção Invalida!");
                    break;
                }
            }
        }
    }
}
