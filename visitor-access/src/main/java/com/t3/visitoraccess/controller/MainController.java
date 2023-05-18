package com.t3.visitoraccess.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.t3.visitoraccess.entity.SecurityUser;

@RestController
public class MainController {
    
    @GetMapping("/")
    public String index(){
        return "<h1>Bem vindo</h1>";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String user(){
        return "<h1>Tela Somente Para Usuarios</h1>";

    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "<h1> Tela Somente Para Administradores </h1>";
    }

    @GetMapping("info")
    SecurityUser info(@AuthenticationPrincipal SecurityUser principal){
        return principal;
    } 
}
