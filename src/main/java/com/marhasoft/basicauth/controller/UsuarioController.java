package com.marhasoft.basicauth.controller;


import com.marhasoft.basicauth.dto.UsuarioDTO;
import com.marhasoft.basicauth.enums.RoleEnum;
import com.marhasoft.basicauth.model.Role;
import com.marhasoft.basicauth.model.Usuario;
import com.marhasoft.basicauth.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Usuario> getUsuarios() {
        return new ResponseEntity(usuarioService.getUsuarios(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO){
        Set<Role> roles = usuarioDTO.getRoles().stream()
                .map(role -> Role.builder()
                        .descricao(RoleEnum.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        Usuario usuario = Usuario.builder()
                .login(usuarioDTO.getLogin())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .status(true)
                .roles(roles)
                .dataCriacao(LocalDateTime.now())
                .build();
        usuarioService.cadastrar(usuario);

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping
    public String deleteUsuario(@RequestParam Long id) {
        usuarioService.deleteUsuario(id);
        return "Usuário excluído com sucesso";
    }
}
