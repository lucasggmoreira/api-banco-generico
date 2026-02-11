package me.lucasggmoreira.banco.infra.exception.custom;

public class NaoEncontradoException extends RuntimeException{
    public NaoEncontradoException(String message) {
        super(message);
    }
}
