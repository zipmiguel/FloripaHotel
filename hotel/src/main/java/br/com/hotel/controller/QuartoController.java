package br.com.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.model.Quarto;
import br.com.hotel.model.Reserva;
import br.com.hotel.repositorio.QuartoRepositorio;

@RestController
@RequestMapping("/cadastrarQuarto")
public class QuartoController {
    @Autowired
    private QuartoRepositorio quartoRepositorio;

    @PostMapping
    public Quarto adicionar(Quarto quarto){
        String texto = "";
        List<Reserva> lista = new ArrayList<>();
        for (int i = 0; i < 366; i++) {
            lista.add(null);
        }
        for (int i = 0; i < 2; i++) {
            Reserva novaReserva = new Reserva();
            novaReserva.setDataEntrada("2022-06-14");
            novaReserva.setDataSaida("2022-06-14");
            novaReserva.setDiaria(null);
            novaReserva.setFuncionario(null);
            novaReserva.setHospede(null);
            novaReserva.setIdReserva(null);
            novaReserva.setMetodoPagamento("dinheiro");
            novaReserva.setStatus("chegou");
            novaReserva.setTipoQuarto(null);
            novaReserva.setValorPago(1500.50);
            lista.set(i, novaReserva);
        }

        Gson gson = new Gson();
        for(Reserva reservas : lista) {
            texto += gson.toJson(reservas);
        }
        quarto.setListaReservas(texto);
        return quartoRepositorio.save(quarto);
    }
}