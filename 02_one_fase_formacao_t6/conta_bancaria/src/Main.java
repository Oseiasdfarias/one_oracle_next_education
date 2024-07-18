import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContaBancaria contaOseias = new ContaBancaria(2000);
        Scanner leitura = new Scanner(System.in);

        int acao = 0;

        while (acao != 4) {
            System.out.println("------------- Operações -------------\n");
            System.out.println("1 - Consultar saldo");
            System.out.println("2 - Receber valor");
            System.out.println("3 - Transferir valor");
            System.out.println("4 - Sair");

            System.out.println("Digiter a opção desejada: ");
            acao = leitura.nextInt();

            if (acao == 1) {
                contaOseias.consultarSaldo();
            } else if (acao == 2) {
                System.out.println("Digite o valor para enviar");
                double valorReceber = leitura.nextDouble();
                contaOseias.reveberValor(valorReceber);
            } else if (acao == 3) {
                System.out.println("Digite o valor para transferir");
                double valorTransferir = leitura.nextDouble();
                contaOseias.transferirValor(valorTransferir);
            } else if (acao == 4) {
                break;
            } else {
                System.out.println("Digite um valor entre 1 e 4!!!");
            }
        }
    }
}