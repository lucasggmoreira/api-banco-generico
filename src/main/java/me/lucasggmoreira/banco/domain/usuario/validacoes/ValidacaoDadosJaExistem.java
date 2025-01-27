package me.lucasggmoreira.banco.domain.usuario.validacoes;

import me.lucasggmoreira.banco.domain.contabancaria.ContaBancariaRepository;
import me.lucasggmoreira.banco.domain.usuario.dto.DadosCadastroConta;
import me.lucasggmoreira.banco.domain.usuario.UsuarioRepository;
import me.lucasggmoreira.banco.infra.exception.custom.DadoExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoDadosJaExistem implements ValidacaoContaBancaria{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;


    @Override
    public void validar(DadosCadastroConta dados) {
        var validarConta = contaBancariaRepository.findByCpf(dados.cpf());
        if (validarConta != null){
            throw new DadoExistenteException("CPF já cadastrado.");
        }
        var validarUsuario = usuarioRepository.findByLogin(dados.email());
        if (validarUsuario != null){
            throw new DadoExistenteException("Email já cadastrado.");
        }
    }

    @Override
    public void validar(String dados) {
    }
}
