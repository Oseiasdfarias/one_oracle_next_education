import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ConsultaCep consiltacep = new ConsultaCep();

        System.out.println("Digite um número de CEP para consulta: ");
        var cep = leitura.nextLine();

        try {
            Endereco novoEndereco = consiltacep.buscaEndereco(cep);
            System.out.println(novoEndereco);
            GeradorDeArquivo gerador = new GeradorDeArquivo();
            gerador.salvaJson(novoEndereco);
        } catch (RuntimeException | IOException e){
            System.out.println(e.getMessage());
            System.out.println("Finalizando a aplicação!");
        }
    }
}
