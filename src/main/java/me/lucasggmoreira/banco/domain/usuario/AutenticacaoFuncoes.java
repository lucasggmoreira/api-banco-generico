package me.lucasggmoreira.banco.domain.usuario;

import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;
import me.lucasggmoreira.banco.domain.contabancaria.ContaBancariaRepository;
import me.lucasggmoreira.banco.domain.contabancaria.DadosDetalheConta;
import me.lucasggmoreira.banco.domain.usuario.validacoes.ValidacaoContaBancaria;
import me.lucasggmoreira.banco.infra.security.DadosTokenJWT;
import me.lucasggmoreira.banco.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutenticacaoFuncoes {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private List<ValidacaoContaBancaria> validacoes;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public DadosDetalheConta cadastro(DadosCadastroConta dados){
        validacoes.forEach(v -> v.validar(dados));
        return salvarContaBancoDados(dados);
    }

    private DadosDetalheConta salvarContaBancoDados(DadosCadastroConta dados){
        var usuario = new Usuario(dados.email(), passwordEncoder.encode(dados.senha()));
        var conta = new ContaBancaria(dados, usuario);
        usuarioRepository.save(usuario);
        contaBancariaRepository.save(conta);
        return new DadosDetalheConta(conta);
    }

    public DadosTokenJWT login(DadosAutenticacao dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return new DadosTokenJWT(tokenJWT);
    }




}
