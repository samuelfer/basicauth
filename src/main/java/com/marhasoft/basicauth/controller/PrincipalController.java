package com.marhasoft.basicauth.controller;

import com.marhasoft.basicauth.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
