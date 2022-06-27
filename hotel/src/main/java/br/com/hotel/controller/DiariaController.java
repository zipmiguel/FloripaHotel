package br.com.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.model.Diaria;
import br.com.hotel.repositorio.DiariaRepositorio;
import br.com.hotel.repositorio.TipoQuartoRepositorio;

@RestController
@RequestMapping("/diaria")
public class DiariaController {
    
    @Autowired
    DiariaRepositorio diariaRepositorio;
    @Autowired
    TipoQuartoRepositorio tipoQuartoRepositorio;

    @PostMapping("/{id}")
    public Diaria findDiaria(@PathVariable("id") Long id){
        Diaria diaria =  diariaRepositorio.findBytipoQuarto(tipoQuartoRepositorio.findById(id));
        return diaria;
    }

}
