package com.marhasoft.basicauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRolesController {

    @GetMapping("/accessAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accessAdmin() {
        return "Ola admin";
    }

    @GetMapping("/accessUsuario")
    @PreAuthorize("hasRole('USUARIO')")
    public String accessUsuario() {
        return "Ola usuário";
    }

    @GetMapping("/accessConvidado")
    @PreAuthorize("hasRole('CONVIDADO')")
    public String accessConvidado() {
        return "Ola convidado";
    }
}
