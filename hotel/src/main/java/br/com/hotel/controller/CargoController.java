package br.com.hotel.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

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
    public Cargo cadastrarCargo(HttpServletResponse response, Cargo cargo) throws IOException {
        response.sendRedirect("/cadastroCargo");
        return cargoRepositorio.save(cargo);

    }
}