package me.lucasggmoreira.banco.infra.exception.custom;

public class DadoExistenteException extends RuntimeException {
    public DadoExistenteException(String mensagem) {
        super(mensagem);
    }
}
