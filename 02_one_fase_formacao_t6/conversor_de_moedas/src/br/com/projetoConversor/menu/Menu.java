package br.com.projetoConversor.menu;

import br.com.projetoConversor.cunsultaApi.ConsultaApi;
import br.com.projetoConversor.cunsultaApi.DadosApi;

import java.util.Scanner;

public class Menu {

    public void menu(){
        System.out.println("1) Dólar => Real");
        System.out.println("2) Real => Dólar");
        System.out.println("3) Yuan chinês => Real");
        System.out.println("4) Real => Yuan chinês");
        System.out.println("5) Dólar => Yuan chinês");
        System.out.println("6) Yuan chinês => Dólar");
        System.out.println("7) Sair");
    }

    private String converter(double valor, String conv1, String conv2) {
        ConsultaApi consultApi = new ConsultaApi();
        DadosApi novoEndereco = consultApi.buscarConversoes(conv1, conv2);
        String string1 = conv1 + ": " + valor + " >> " + conv2 + ": ";

        String string2 = String.format("%.2f", valor * Double.valueOf(novoEndereco.conversion_rate()));
        String sf2 = string1 + string2;
        return sf2;
    }

    public void selecinarConversao() {
        System.out.println("Digite a opção para realiza a conversão: ");
        Scanner leitura = new Scanner(System.in);
        try {
            int opcao = leitura.nextInt();
            if (opcao == 7)
                // Caso a opção selecionada seja o número 7, o programa finaliza.
                System.exit(0);
            System.out.println("Digite o valor para conversão: ");
            double valor = leitura.nextDouble();

            switch(opcao) {
                case 1:
                    String sf1 = converter(valor, "USD", "BRL");
                    System.out.println(sf1);
                    break;
                case 2:
                    String sf2 = converter(valor, "BRL", "USD");
                    System.out.println(sf2);
                    break;
                case 3:
                    String sf3 = converter(valor, "CNY", "BRL");
                    System.out.println(sf3);
                    break;
                case 4:
                    String sf4 = converter(valor, "BRL", "CNY");
                    System.out.println(sf4);
                    break;
                case 5:
                    String sf5 = converter(valor, "USD", "CNY");
                    System.out.println(sf5);
                    break;
                case 6:
                    String sf6 = converter(valor, "CNY", "USD");
                    System.out.println(sf6);
                    break;
                default:
                    // code block
                    System.out.println("Opção inválida, tente uma válida!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Conversão não pode ser realizada, adicione valores corretos!");
        }
    }
}
