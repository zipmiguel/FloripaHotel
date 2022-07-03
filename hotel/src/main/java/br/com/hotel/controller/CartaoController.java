package br.com.hotel.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.model.Cartao;
import br.com.hotel.model.Hospede;
import br.com.hotel.repositorio.CartaoRepositorio;
import br.com.hotel.repositorio.HospedeRepositorio;

@RestController
public class CartaoController {

    @Autowired
    CartaoRepositorio cartaoRepositorio;
    @Autowired
    HospedeRepositorio hospedeRepositorio;

    @PostMapping("/cadastrarCartao")
    public void cadastrarCartao(Cartao cartao, @RequestParam Long idHospede){
        cartaoRepositorio.save(cartao);
        Optional<Hospede> hospede = hospedeRepositorio.findById(idHospede);
        hospede.get().setCartao(cartao);
        hospedeRepositorio.save(hospede.get());
    }
}