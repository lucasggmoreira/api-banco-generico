package me.lucasggmoreira.banco.config;

import me.lucasggmoreira.banco.domain.contabancaria.ContaBancaria;
import me.lucasggmoreira.banco.domain.contabancaria.ContaBancariaRepository;
import me.lucasggmoreira.banco.domain.usuario.UsuarioRepository;
import me.lucasggmoreira.banco.domain.usuario.dto.DadosCadastroConta;
import me.lucasggmoreira.banco.domain.usuario.enums.TipoConta;
import me.lucasggmoreira.banco.domain.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class VerificarAdmin implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var adminExiste = usuarioRepository.existsByTipoConta(TipoConta.ADMIN);
        if (!adminExiste){
            var dados = new DadosCadastroConta("admin", "admin@email.com", "12345678900", "senhaadmin");
            var usuario = new Usuario(dados.email(), passwordEncoder.encode(dados.senha()), TipoConta.ADMIN);
            var conta = new ContaBancaria(dados, "1");
            contaBancariaRepository.save(conta);
            usuario.setContaBancaria(conta);
            usuarioRepository.save(usuario);
            conta.setUsuario(usuario);
            contaBancariaRepository.save(conta);
            System.out.println("Conta de Adminstrador não encontrada, criando conta padrão.");
        }
    }
}
