package fiap.tds.services;

import fiap.tds.DTOS.UsuarioDTO;
import fiap.tds.models.Usuario;
import fiap.tds.repositories.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class UsuarioService {
    @Inject
    UsuarioRepository userRepository;

    public Usuario login(String login, String senha) {
        return userRepository.login(login, senha);
    }

    public void register(UsuarioDTO dto) {
        var user = new Usuario();
        if (!dto.getSenha().equals(dto.getSenha())) {
            throw new IllegalArgumentException("Senhas diferentes");
        }
        user.setNome(dto.getNome());
        user.setSenha(dto.getSenha());
        user.setEmail(dto.getEmail());
        userRepository.save(user);
    }
}


