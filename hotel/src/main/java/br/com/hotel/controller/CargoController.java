package br.com.hotel.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.model.Cargo;
import br.com.hotel.repositorio.CargoRepositorio;

@RestController
public class CargoController {

    @Autowired
    private CargoRepositorio cargoRepositorio;

    @PostMapping("/cadastrarCargo")
    public String cadastrarCargo(Cargo cargo) throws IOException {
        Cargo cargoExistente = cargoRepositorio.findBytipoCargo(cargo.getTipoCargo());
        if (cargoExistente == null) {
            cargoRepositorio.save(cargo);
            return "adicionar";
        } else {
            return "";
        }
    }
    @GetMapping("/listaCargos")
    public List<Cargo> listaCargos(){
    	return cargoRepositorio.findAll();
    }

}