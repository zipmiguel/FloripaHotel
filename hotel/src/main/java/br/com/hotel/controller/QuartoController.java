package br.com.hotel.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

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
    public String cadastrarQuarto(HttpServletResponse response, Quarto quarto) throws IOException {
        System.out.println(quarto.getNumero());
        System.out.println(quarto.getStatus());
        System.out.println(quarto.getTipoQuarto());
        try {
            Quarto quartoExistente = quartoRepositorio.findBynumero(quarto.getNumero());

            if (quartoExistente == null) {
                quartoRepositorio.save(quarto);
                return "adicionar";
            } else {
                quartoExistente.setNumero(quarto.getNumero());
                quartoExistente.setStatus(quarto.getStatus());
                quartoExistente.setTipoQuarto(quarto.getTipoQuarto());
                quartoRepositorio.save(quartoExistente);
                return "editar";
            }
        } catch (Exception e) {
            return "";
        }
    }

    @GetMapping("/buscarQuarto/{numero}")
    public Quarto buscarQuarto(@PathVariable("numero") String numero) {
        System.out.println(numero);
        try {
            System.out.println(numero);
            Quarto quarto = quartoRepositorio.findBynumero(numero);
            return quarto;
        } catch (Exception e) {
            return null;
        }
    }

}