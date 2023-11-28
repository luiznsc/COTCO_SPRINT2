package br.com.cotco.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cotco")
public class Controller {

    @GetMapping
    public String teste(){
        return "testando url";
    }
}
