package br.com.hotel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastrarQuarto")
public class QuartoController {
    @GetMapping("/teste/{teste}")
    public String teste(@PathVariable("teste") String teste){
        return teste;
    }

}