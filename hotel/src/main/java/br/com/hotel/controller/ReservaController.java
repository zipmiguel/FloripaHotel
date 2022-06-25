package br.com.hotel.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.model.Quarto;
import br.com.hotel.model.Reserva;
import br.com.hotel.repositorio.QuartoRepositorio;
import br.com.hotel.repositorio.ReservaRepositorio;
import ch.qos.logback.core.util.Duration;

@RestController
public class ReservaController {

    @Autowired
    private ReservaRepositorio reservaRepositorio;
    @Autowired
    private QuartoRepositorio quartoRepositorio;

    //método para redirecionar com data e quantidade pessoas
    @PostMapping("/pesquisarReserva")
    public void pesquisarReserva(HttpServletResponse response, @RequestParam String dataEntrada, @RequestParam String dataSaida)  throws IOException {
        
        LocalDate entrada = LocalDate.parse(dataEntrada);
        LocalDate saida = LocalDate.parse(dataSaida);
        int diasDiferenca = (int)ChronoUnit.DAYS.between(entrada, saida);
        List<Reserva> listaReservas = reservaRepositorio.findAll();
        int quantidadeQuartosAtivos = quartoRepositorio.countBystatus(true);

        // for (Reserva reserva : listaReservas) {
        //     if(reserva.getTipoQuarto())
        // }


        // for (Quarto quarto : listaQuartosAtivos){
        //     for (int i = 0; i <= diasDiferenca; i++) {
        //         LocalDate dataVerificada = entrada.plusDays(i);
        //         for (Reserva reserva : reservas) {
        //             if(reserva.getDataEntrada().isBefore(dataVerificada) || reserva.getDataEntrada().isEqual(dataVerificada)){
        //                 if(reserva.getDataSaida().isEqual(dataVerificada) || reserva.getDataSaida().isEqual(dataVerificada)){
        //                 }
        //             }
        //         }
        //     }
        // }
        response.sendRedirect("/reservaUsuario");
    }

    //método para redirecionar com data, quantidade pessoas e quantidade de quartos
    // @PostMapping("/setarReserva")
    // // public void pesquisarReserva(HttpServletResponse response, Reserva reserva) throws IOException {
    // //     reservaRepositorio.save(reserva);
    // //     response.sendRedirect("/reservaUsuario");
    // // }



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