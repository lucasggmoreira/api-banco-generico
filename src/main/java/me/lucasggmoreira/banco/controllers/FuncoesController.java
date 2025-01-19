package me.lucasggmoreira.banco.controllers;

import me.lucasggmoreira.banco.domain.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcoes")
public class FuncoesController {

    @PostMapping("/depositar")
    public ResponseEntity depositar(@AuthenticationPrincipal Usuario usuario, @ResponseBody ){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sacar")
    public ResponseEntity sacar(@AuthenticationPrincipal Usuario usuario, @ResponseBody ){
    }


}
