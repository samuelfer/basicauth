package com.marhasoft.basicauth.controller;


import com.marhasoft.basicauth.dto.UsuarioDTO;
import com.marhasoft.basicauth.model.Usuario;
import com.marhasoft.basicauth.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(usuarioService.cadastrar(usuarioDTO));
    }

    @DeleteMapping
    public String deleteUsuario(@RequestParam Long id) {
        usuarioService.deleteUsuario(id);
        return "Usuário excluído com sucesso";
    }
}
