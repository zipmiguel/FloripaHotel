package br.com.hotel.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.repositorio.CargoRepositorio;
import br.com.hotel.repositorio.DiariaRepositorio;
import br.com.hotel.repositorio.tipoQuartoRepositorio;
import br.com.hotel.model.Diaria;

import br.com.hotel.model.TipoQuarto;

//Luiz Eduardo 

@RestController
@RequestMapping("/tipoQuarto")
public class TipoQuartoController{
    @Autowired
    private tipoQuartoRepositorio tipoQuartoRepositorio;
    
    @Autowired
    private DiariaRepositorio diariaRepositorio;

    @PostMapping("/{id}")
    public TipoQuarto pesquisarId(@PathVariable("id") Long id){
        Optional<TipoQuarto> tipoQuarto = tipoQuartoRepositorio.findById(id);
        if (tipoQuarto.isPresent()) {
			return tipoQuarto.get();
		}
        return null;
    }

    @GetMapping
    public List<TipoQuarto> listaTipoQuarto(){
        List<TipoQuarto> listaTipos = tipoQuartoRepositorio.BuscarTiposQuartoTrue();
        return listaTipos;
    }

    @PostMapping("/save")
    public void saveQuarto(HttpServletResponse response, @RequestParam String tipoQuarto, @RequestParam int quantidadeCamaSolteiro, @RequestParam int quantidadeCamaCasal) throws IOException {
        TipoQuarto tQuarto = new TipoQuarto(tipoQuarto, quantidadeCamaSolteiro, quantidadeCamaCasal);
        Diaria diaria = new Diaria();
        diaria.setDiaUtil(0.0);
        diaria.setFeriado(0.0);
        diaria.setFimDeSemana(0.0);
        diaria.setPromocional(0.0);
        diaria.setTipoQuarto(tQuarto);
        tipoQuartoRepositorio.save(tQuarto);
        diariaRepositorio.save(diaria);
        response.sendRedirect("/cadastroTipoQuarto");
    }

    @PostMapping("/delete")
    public void deleteQuarto(HttpServletResponse response, @RequestParam Long idQuarto) throws IOException {
        tipoQuartoRepositorio.CancelaTipoQuarto(idQuarto);
        response.sendRedirect("/cadastroTipoQuarto");
    }

    @PostMapping("/edit")
    public void EditQuarto(HttpServletResponse response, @RequestParam Long idQuarto, @RequestParam int quantidadeCamaSolteiro, @RequestParam int quantidadeCamaCasal) throws IOException {
        TipoQuarto tipoQuartoOriginal = tipoQuartoRepositorio.getReferenceById(idQuarto);
        tipoQuartoOriginal.setQuantidadeCamaCasal(quantidadeCamaCasal);
        tipoQuartoOriginal.setQuantidadeCamaSolteiro(quantidadeCamaSolteiro);
        tipoQuartoOriginal.setNumeroPessoas((quantidadeCamaCasal*2)+quantidadeCamaSolteiro);
        tipoQuartoRepositorio.save(tipoQuartoOriginal);
        response.sendRedirect("/cadastroTipoQuarto");
    }
}
