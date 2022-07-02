package br.com.hotel.controller;

import java.io.Console;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.apis.SenderMailService;
import br.com.hotel.model.Hospede;
import br.com.hotel.model.Reserva;
import br.com.hotel.model.TipoQuarto;
import br.com.hotel.model.Quarto;
import br.com.hotel.repositorio.HospedeRepositorio;
import br.com.hotel.repositorio.QuartoRepositorio;
import br.com.hotel.repositorio.ReservaRepositorio;
import br.com.hotel.repositorio.TipoQuartoRepositorio;

@RestController
public class ReservaController {
    
    @Autowired
    SenderMailService senderMailService;
    @Autowired
    HospedeRepositorio hospedeRepositorio;
    @Autowired
    private ReservaRepositorio reservaRepositorio;
    @Autowired
    private QuartoRepositorio quartoRepositorio;
    @Autowired
    private TipoQuartoRepositorio tipoQuartoRepositorio;

    @PostMapping("/pesquisarReserva")
    public List<List<? extends Object>> pesquisarReserva(HttpServletResponse response, @RequestParam String dataEntrada, @RequestParam String dataSaida, @RequestParam int qntdPessoas, @RequestParam int qntdAdultos)  throws IOException {
        LocalDate entrada = LocalDate.parse(dataEntrada);
        LocalDate saida = LocalDate.parse(dataSaida);
        List<TipoQuarto> listaTipoQuarto = tipoQuartoRepositorio.BuscarTiposQuartoTrue();
        List<TipoQuarto> disponibilidade = new ArrayList<>();
        List<Integer> maximo = new ArrayList<>();
        for (TipoQuarto tipoQuarto : listaTipoQuarto) {
            if(disponibilidadeTipoQuarto(tipoQuarto, entrada, saida) * tipoQuarto.getNumeroPessoas() >= qntdPessoas){
                double qntdPessoasD = qntdPessoas;
                Double minQuartos = Math.ceil(qntdPessoasD/tipoQuarto.getNumeroPessoas());
                System.out.println(qntdAdultos);
                System.out.println(qntdPessoas);
                System.out.println(tipoQuarto.getNumeroPessoas());
                System.out.println(minQuartos);
                System.out.println(tipoQuarto.getTipoQuarto());
                if(qntdAdultos >= minQuartos){
                    disponibilidade.add(tipoQuarto);
                    maximo.add(disponibilidadeTipoQuarto(tipoQuarto,entrada,saida));
                }
            }
        }
        return Arrays.asList(disponibilidade,maximo);
    }

    public int disponibilidadeTipoQuarto(TipoQuarto tipoQuarto, LocalDate entrada, LocalDate saida){
        
        List<Reserva> listaReservas = reservaRepositorio.findAll();
        int quantidadeReservasInterferem = 0;
        for (Reserva reserva : listaReservas) {
            if(reserva.getTipoQuarto() == tipoQuarto){
                if(reserva.getDataEntrada().isBefore(saida) && reserva.getDataSaida().isAfter(entrada)){
                    quantidadeReservasInterferem++;
                }
            }
        }
        // if(quantidadeReservasInterferem < quartoRepositorio.contarPeloTipoQuarto(tipoQuarto.getIdTipoQuarto())){
        //     return true;
        // }else{
        //     return false;
        // }
        return quartoRepositorio.contarPeloTipoQuarto(tipoQuarto.getIdTipoQuarto()) - quantidadeReservasInterferem;
    }


    @PostMapping("/finalizarReserva")
    public void cadastrarReserva(HttpServletResponse response, @RequestParam String metodoPagamento,
    		@RequestParam Long idHospede,@RequestParam String dataEntrada, 
    		@RequestParam String dataSaida, @RequestParam Double valorTotal, @RequestParam Long idTipoQuarto,@RequestParam Integer qntQuartos) throws MessagingException{
        Optional<TipoQuarto> tipoQuarto = tipoQuartoRepositorio.findById(idTipoQuarto);
        Optional<Hospede> hospede = hospedeRepositorio.findById(idHospede);
        System.out.println(qntQuartos);
        List<Reserva> listaReservas = new ArrayList<>();
        for (int i = 0; i < qntQuartos; i++) {
        Reserva reserva = new Reserva();
        reserva.setHospede(hospede.get());
        System.out.println(dataSaida);
        System.out.println(dataEntrada);
        reserva.setDataEntrada(LocalDate.parse(dataEntrada));
        reserva.setDataSaida(LocalDate.parse(dataSaida));
        reserva.setValorPago(valorTotal);
        reserva.setTipoQuarto(tipoQuarto.get());
        reserva.setStatus("Não chegou");
        reserva.setMetodoPagamento(metodoPagamento);
        Long codigo = System.nanoTime();
        senderMailService.codigoReserva(hospede.get().getEmail(),hospede.get().getNome(),codigo);
        reserva.setCodigoReserva(codigo);
        listaReservas.add(reserva);
        }
        reservaRepositorio.saveAll(listaReservas);
    }

     //terminar esse método para fazer a busca da reserva efetivada p/ depois checkin nos quartos
     @GetMapping("/pesquisarReservaEfetivada/{numero}")
     public List<Object> buscarQuartosDisponiveis(@PathVariable("numero") String numero) {
         System.out.println(numero);
         System.out.println(numero);
         Reserva reservaBusca = reservaRepositorio.searchCodeReserva(numero);
         List<Quarto> listaQuartoEspecifico = quartoRepositorio.findByTipoQuarto(reservaBusca.getTipoQuarto());
         return Arrays.asList(listaQuartoEspecifico,reservaBusca);
     }
    @PutMapping("/listaQuartos")
    public List<Quarto> listaQuartos(@RequestParam String idTipoQuarto){
    		Optional<TipoQuarto> quartoTipo =  tipoQuartoRepositorio.findById((Long.parseLong(idTipoQuarto)));
    		if (quartoTipo.isPresent()) {
				List<Quarto> listaQuartoEspecifico = quartoRepositorio.findByTipoQuarto(quartoTipo.get());
				return listaQuartoEspecifico;
			}
    	return null;
    }
    @PostMapping("/checkin")
    public void fazerCheckin(@RequestParam Boolean tipoQuarto, @RequestParam long idQuarto) {
    	if (tipoQuarto){
            Optional<Quarto> quartoCancelado = quartoRepositorio.findById(idQuarto);
            quartoCancelado.get().setStatus(true);
            quartoRepositorio.save(quartoCancelado.get());
        } else {
            Optional<Quarto> quartoLiberado = quartoRepositorio.findById(idQuarto);
            quartoLiberado.get().setStatus(false);
            quartoRepositorio.save(quartoLiberado.get());
        }
    }
    @PostMapping("/reservaPeloQuarto")
    public Boolean reservasQuartos(@RequestParam Long idQuarto, @RequestParam String data){
        LocalDate dataFormatada = LocalDate.parse(data);
        Optional<Quarto> quarto = quartoRepositorio.findById(idQuarto);
        List<Reserva> lista = reservaRepositorio.findByQuarto(quarto.get());
        Boolean reservaExistente = false;
        for (Reserva reserva : lista) {
            if(reserva.getDataEntrada().isBefore(dataFormatada) && reserva.getDataSaida().isAfter(dataFormatada)){
                reservaExistente = true;
                break;
            }
        }
        return reservaExistente;
    }
}
