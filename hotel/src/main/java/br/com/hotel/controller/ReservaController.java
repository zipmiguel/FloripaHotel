package br.com.hotel.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.model.Reserva;
import br.com.hotel.model.TipoQuarto;
import br.com.hotel.repositorio.QuartoRepositorio;
import br.com.hotel.repositorio.ReservaRepositorio;
import br.com.hotel.repositorio.TipoQuartoRepositorio;

@RestController
public class ReservaController {

    @Autowired
    private ReservaRepositorio reservaRepositorio;
    @Autowired
    private QuartoRepositorio quartoRepositorio;
    @Autowired
    private TipoQuartoRepositorio tipoQuartoRepositorio;

    @PostMapping("/pesquisarReserva")
    public List<TipoQuarto> pesquisarReserva(HttpServletResponse response, @RequestParam String dataEntrada, @RequestParam String dataSaida, @RequestParam int total)  throws IOException {
        LocalDate entrada = LocalDate.parse(dataEntrada);
        LocalDate saida = LocalDate.parse(dataSaida);
        List<TipoQuarto> listaTipoQuarto = tipoQuartoRepositorio.BuscarTiposQuartoTrue();
        List<TipoQuarto> disponibilidade = new ArrayList<>();
        for (TipoQuarto tipoQuarto : listaTipoQuarto) {
            if(tipoQuarto.getNumeroPessoas() >= total){
                if(disponibilidadeTipoQuarto(tipoQuarto, entrada, saida)){
                    disponibilidade.add(tipoQuarto);
                }
            }
        }
        // response.sendRedirect("http://localhost:8089/reservaUsuario");
        return disponibilidade;
    }

    public Boolean disponibilidadeTipoQuarto(TipoQuarto tipoQuarto, LocalDate entrada, LocalDate saida){
        
        List<Reserva> listaReservas = reservaRepositorio.findAll();
        int quantidadeReservasInterferem = 0;
        for (Reserva reserva : listaReservas) {
            if(reserva.getTipoQuarto() == tipoQuarto){
                if(!(reserva.getDataSaida().isBefore(entrada) || reserva.getDataEntrada().isAfter(saida))){
                    quantidadeReservasInterferem++;
                }
            }
        }
        if(quantidadeReservasInterferem < quartoRepositorio.contarPeloTipoQuarto(tipoQuarto.getIdTipoQuarto())){
            return true;
        }else{
            return false;
        }
    }

    @PostMapping("/finalizarReserva")
    public void cadastrarReserva(Reserva reserva) {
        reservaRepositorio.save(reserva);
    }

    //terminar esse método para fazer a busca da reserva efetivada p/ depois checkin nos quartos
    // @GetMapping("/pesquisarReservaEfetivada/{numero}")
    // public Quarto buscarQuarto(@PathVariable("numero") String numero) {
    //     System.out.println(numero);
    //     Quarto quarto = quartoRepositorio.findBynumero(numero);
    //     return quarto;
    // }

}