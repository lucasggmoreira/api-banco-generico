package me.lucasggmoreira.banco.infra.exception.custom;

public class TokenJWTInvalidoException extends RuntimeException {
    public TokenJWTInvalidoException(String message) {
        super(message);
    }
}
