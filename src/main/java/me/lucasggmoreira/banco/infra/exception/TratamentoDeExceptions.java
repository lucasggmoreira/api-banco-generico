package me.lucasggmoreira.banco.infra.exception;


import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.EntityNotFoundException;
import me.lucasggmoreira.banco.infra.exception.custom.DadoExistenteException;
import me.lucasggmoreira.banco.infra.exception.custom.DadoInvalidoException;
import me.lucasggmoreira.banco.infra.exception.custom.NaoEncontradoException;
import me.lucasggmoreira.banco.infra.exception.custom.TokenJWTInvalidoException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class TratamentoDeExceptions {

    @ExceptionHandler(DadoInvalidoException.class)
    public ResponseEntity tratarErro400(DadoInvalidoException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({EntityNotFoundException.class, NaoEncontradoException.class})
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException e){
        var erros = e.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity tratarErro401(){
        return ResponseEntity.status(401).build();
    }

    @ExceptionHandler(DadoExistenteException.class)
    public ResponseEntity tratarErro409(DadoExistenteException e){
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(TokenJWTInvalidoException.class)
    public ResponseEntity tratarTokenInvalido(TokenJWTInvalidoException e){
        return ResponseEntity.status(401).body(e.getMessage());
    }


    public record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }



}
