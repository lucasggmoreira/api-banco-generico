package me.lucasggmoreira.banco.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import me.lucasggmoreira.banco.domain.transacoes.DadosTransacaoDetalhado;
import me.lucasggmoreira.banco.domain.transacoes.DadosValorTransferencia;
import me.lucasggmoreira.banco.domain.transacoes.TipoTransacao;
import me.lucasggmoreira.banco.domain.transacoes.funcoes.FerramentaTransacoes;
import me.lucasggmoreira.banco.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcoes")
@SecurityRequirement(name = "bearer-key")
public class FuncoesController {

    @Autowired
    private FerramentaTransacoes ferramentaTransacoes;



    @PostMapping("/depositar")
    @Transactional
    public ResponseEntity<DadosTransacaoDetalhado> depositar(@AuthenticationPrincipal Usuario usuario, @RequestBody @Valid DadosValorTransferencia dados){
        var dadosTransacao = ferramentaTransacoes.transferir(usuario.getContaBancaria(), dados, TipoTransacao.ENTRADA);
        return ResponseEntity.ok(dadosTransacao);
    }

    @PostMapping("/sacar")
    @Transactional
    public ResponseEntity<DadosTransacaoDetalhado> sacar(@AuthenticationPrincipal Usuario usuario, @RequestBody @Valid DadosValorTransferencia dados){
        var dadosTransacao = ferramentaTransacoes.transferir(usuario.getContaBancaria(), dados, TipoTransacao.SAIDA);
        return ResponseEntity.ok(dadosTransacao);
    }


}
