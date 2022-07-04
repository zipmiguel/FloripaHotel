package br.com.hotel.controller;

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
         Reserva reservaBusca = reservaRepositorio.searchCodeReserva(numero);
         List<Quarto> listaQuartoEspecifico = quartoRepositorio.findByTipoQuarto(reservaBusca.getTipoQuarto().getIdTipoQuarto());
         List<Boolean> listaQuartosB = new ArrayList<>();
         if (!(LocalDate.now().isAfter(reservaBusca.getDataSaida())) && !(reservaBusca.getQuarto()!=null) && !(LocalDate.now().isBefore(reservaBusca.getDataEntrada()))) {
         for (int i = 0; i < listaQuartoEspecifico.size(); i++) {
            listaQuartosB.add(reservasQuartos(listaQuartoEspecifico.get(i).getIdQuarto()));
         }}
         else{
         for (int i = 0; i < listaQuartoEspecifico.size(); i++) {
            listaQuartosB.add(false);
         }
         }
         return Arrays.asList(listaQuartoEspecifico,reservaBusca,listaQuartosB);
     }
     private Boolean reservasQuartos(Long idQuarto){
        LocalDate dataFormatada = LocalDate.now();
        Optional<Quarto> quarto = quartoRepositorio.findById(idQuarto);
        List<Reserva> listaReserva = reservaRepositorio.searchQuartoNchegou(quarto.get().getIdQuarto());
        for (int i = 0; i < listaReserva.size(); i++) {
            System.out.print(listaReserva.get(i).getDataEntrada()+" e "+listaReserva.get(i).getDataSaida()+"\n\n\n\n ");
        }
        Boolean reservaDisponivel = true;
        for (Reserva reserva : listaReserva) {
                if(!(reserva.getDataEntrada().isAfter(dataFormatada)) && reserva.getDataSaida().isAfter(dataFormatada)){
                reservaDisponivel = false;
                break;
            }
        }
        return reservaDisponivel;
    }


    @PutMapping("/listaQuartos")
    public List<Object> listaQuartos(@RequestParam String idTipoQuarto){
    		Optional<TipoQuarto> Tipoquarto =  tipoQuartoRepositorio.findById((Long.parseLong(idTipoQuarto)));
            List<Boolean> listaQuartosB = new ArrayList<>();
    		if (Tipoquarto.isPresent()) {
				List<Quarto> listaQuartoEspecifico = quartoRepositorio.findByTipoQuarto(Tipoquarto.get().getIdTipoQuarto());
                for (int i = 0; i < listaQuartoEspecifico.size(); i++) {
                    listaQuartosB.add(reservasQuartos(listaQuartoEspecifico.get(i).getIdQuarto()));
                }
                System.err.println(listaQuartosB);
				return Arrays.asList(listaQuartoEspecifico,listaQuartosB);
			}
    	return null;
    }
    @GetMapping("/checkin")
    public void fazerCheckin(@RequestParam String idquarto,@RequestParam String codigoReserva) {
    	Reserva reserva = reservaRepositorio.searchCodeReserva(codigoReserva);
        Optional<Quarto> quartoReserva = quartoRepositorio.findById(Long.parseLong(idquarto));
        reserva.setStatus("Entrou");
        reserva.setQuarto(quartoReserva.get());
        reservaRepositorio.save(reserva);
    }
}
