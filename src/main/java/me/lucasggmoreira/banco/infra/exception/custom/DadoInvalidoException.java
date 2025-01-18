package me.lucasggmoreira.banco.infra.exception.custom;

public class DadoInvalidoException extends RuntimeException{
    public DadoInvalidoException(String message) {
        super(message);
    }
}
