package br.com.principal;
import br.com.modelos.ConversorDeMoedas;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        ConversorDeMoedas conversor = new ConversorDeMoedas();

        System.out.println("=== Conversor de Moedas (REAL → Estrangeiras) ===");

        while (true) {
            System.out.println("\n=== Conversor de Moedas ===");
            System.out.println("Escolha a moeda de destino:");
            System.out.println("1 - USD (Dólar Americano)");
            System.out.println("2 - ARS (Peso Argentino)");
            System.out.println("3 - PYG (Guarani Paraguaio)");
            System.out.println("4 - CLP (Peso Chileno)");
            System.out.println("0 - SAIR");
            System.out.print("Digite a opção desejada: ");

            String opcao = scanner.nextLine().trim();

            String moeda = null;

            switch (opcao) {
                case "1":
                    moeda = "USD";
                    break;
                case "2":
                    moeda = "ARS";
                    break;
                case "3":
                    moeda = "PYG";
                    break;
                case "4":
                    moeda = "CLP";
                    break;
                case "0":
                    System.out.println("Encerrando o conversor... Até logo!");
                    return; // sai do programa
                default:
                    System.out.println("Opção inválida! Escolha um número de 0 a 4.");
                    continue;
            }

            // aqui você continua o código para pedir o valor e fazer a conversão
            System.out.print("Digite o valor em Reais (BRL): ");
            double valorBRL;

            try {
                valorBRL = Double.parseDouble(scanner.nextLine().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Tente novamente.");
                continue;
            }


            // chamada à sua classe ConversorDeMoedas
            try {
                double taxa = conversor.buscarTaxa(moeda);
                double convertido = conversor.converter(valorBRL, moeda);
                System.out.printf("Cotação atual: 1 BRL = %.4f %s%n", taxa, moeda);
                System.out.printf("Valor convertido: %.2f BRL = %.2f %s%n", valorBRL, convertido, moeda);
            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

    }
}