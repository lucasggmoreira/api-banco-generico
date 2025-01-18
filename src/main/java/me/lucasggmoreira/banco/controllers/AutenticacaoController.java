package me.lucasggmoreira.banco.controllers;


import jakarta.validation.Valid;
import me.lucasggmoreira.banco.domain.contabancaria.DadosDetalheConta;
import me.lucasggmoreira.banco.domain.usuario.AutenticacaoFuncoes;
import me.lucasggmoreira.banco.domain.usuario.DadosAutenticacao;
import me.lucasggmoreira.banco.domain.usuario.DadosCadastroConta;
import me.lucasggmoreira.banco.infra.security.DadosTokenJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoFuncoes autenticacao;

    @PostMapping("/login")
    public ResponseEntity<DadosTokenJWT> login(@RequestBody @Valid DadosAutenticacao dados){
        var token = autenticacao.login(dados);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<DadosDetalheConta> cadastro(DadosCadastroConta dados){
        var retorno = autenticacao.cadastro(dados);
        return ResponseEntity.ok(retorno);
    }





}
