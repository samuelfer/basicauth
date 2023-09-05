package com.marhasoft.basicauth.service;

import com.marhasoft.basicauth.dto.UsuarioDTO;
import com.marhasoft.basicauth.enums.RoleEnum;
import com.marhasoft.basicauth.model.Role;
import com.marhasoft.basicauth.model.Usuario;
import com.marhasoft.basicauth.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario cadastrar(UsuarioDTO usuarioDTO) {
        Set<Role> roles = usuarioDTO.getRoles().stream()
                .map(role -> Role.builder()
                        .descricao(RoleEnum.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        Usuario usuario = Usuario.builder()
                .login(usuarioDTO.getLogin())
                .email(usuarioDTO.getEmail())
                .senha(passwordEncoder.encode(usuarioDTO.getSenha()))
                .status(true)
                .roles(roles)
                .dataCriacao(LocalDateTime.now())
                .build();
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
