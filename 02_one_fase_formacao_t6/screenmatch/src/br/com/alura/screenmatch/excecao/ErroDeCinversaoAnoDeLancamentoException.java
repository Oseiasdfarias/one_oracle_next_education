package br.com.alura.screenmatch.excecao;

public class ErroDeCinversaoAnoDeLancamentoException extends RuntimeException {
    private String mensagem;
    public ErroDeCinversaoAnoDeLancamentoException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
