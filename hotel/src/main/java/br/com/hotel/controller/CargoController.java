package br.com.hotel.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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

}