package modulo_calculadoras;

import interfaces.ConversaoFinanceira;

public class ConversorMoeda implements ConversaoFinanceira {
    @Override
    public void converterDolarParaReal(double valorDolar) {
        System.out.println("Converção de Dolar em Real: " + valorDolar * 5.12);
    }
}
