public class ContaBancaria {
    double saldoInicial = 2500.00;
    public void dadosCliente() {
        String nomeCompleto = "Oséias Dias de Farias";
        String tipoConta = "Corrente";
        System.out.println("\n\n****************** Dados Iniciais do Cliente ******************\n");
        System.out.println("Nome do Cliente: " + nomeCompleto);
        System.out.println("Tipo de conta:   " + tipoConta);
        System.out.println("Saldo Inicial:   " + saldoInicial);
        System.out.println("\n***************************************************************");
    }

    public void transferirValor (double valor_transferencia){
        if (valor_transferencia <= saldoInicial) {
            saldoInicial = saldoInicial - valor_transferencia;
            System.out.println("Valor Transferido: " + valor_transferencia);
        } else {
            System.out.println("Saldo insuficiente!");
            consultarSaldo();
        }
    }

    public void reveberValor (double valorReceber) {
        saldoInicial += valorReceber;
        System.out.println("Valor Recebido:          " + valorReceber);
    }

    public void consultarSaldo () {
        System.out.println("Valor Total em caixa:    " + saldoInicial);
    }
}
