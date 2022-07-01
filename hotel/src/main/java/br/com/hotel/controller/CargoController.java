package br.com.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.model.Cargo;
import br.com.hotel.repositorio.CargoRepositorio;

@RestController
@RequestMapping("/cadastrarCargo")
public class CargoController {

    @Autowired
    private CargoRepositorio cargoRepositorio;

    @PostMapping
    public Cargo adicionar(Cargo cargo){
        return cargoRepositorio.save(cargo);
    }
}