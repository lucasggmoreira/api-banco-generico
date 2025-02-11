package me.lucasggmoreira.banco.controllers;


import me.lucasggmoreira.banco.domain.contabancaria.ContaBancariaRepository;
import me.lucasggmoreira.banco.domain.contabancaria.DadosDetalheConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @GetMapping("/contas")
    public ResponseEntity<Page<DadosDetalheConta>> retornarContasBancarias(@PageableDefault(size = 10, sort = "id")Pageable paginacao){
        var contas = contaBancariaRepository.findAll(paginacao).map(DadosDetalheConta::new);
        return ResponseEntity.ok(contas);
    }



}
