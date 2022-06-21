package br.com.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.model.Quarto;
import br.com.hotel.repositorio.QuartoRepositorio;

@RestController
public class QuartoController {

    @Autowired
    private QuartoRepositorio quartoRepositorio;

    @PostMapping("/cadastrarQuarto")
    public void cadastrarQuarto(Quarto quarto) {
        quarto.setListaReservas("a");
        quartoRepositorio.save(quarto);
    }

    @GetMapping("/buscarQuarto/{numero}")
    public Quarto buscarQuarto(@PathVariable("numero") String numero) {
        System.out.println(numero);
        Quarto quarto = quartoRepositorio.findBynumero(numero);
        return quarto;
    }

}