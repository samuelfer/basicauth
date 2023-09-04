package com.marhasoft.basicauth.controller;

import com.marhasoft.basicauth.dto.UsuarioDTO;
import com.marhasoft.basicauth.enums.RoleEnum;
import com.marhasoft.basicauth.model.Role;
import com.marhasoft.basicauth.model.Usuario;
import com.marhasoft.basicauth.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/principal")
public class PrincipalController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/aberto")
    public String semSeguranca() {
        return "Enpoint aberto";
    }

    @GetMapping("/seguro")
    public String comSeguranca() {
        return "Enpoint seguro";
    }
}
