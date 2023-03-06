package br.com.controlefinanceiro.exception;

public class NegocioException extends RuntimeException {

    public NegocioException(String msg) {
        super(msg);
    }
}
