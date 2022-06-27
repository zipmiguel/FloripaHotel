package br.com.hotel.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.model.Diaria;
import br.com.hotel.model.TipoQuarto;
import br.com.hotel.repositorio.DiariaRepositorio;
import br.com.hotel.repositorio.tipoQuartoRepositorio;

@RestController
@RequestMapping("/diaria")
public class DiariaController {
    
    @Autowired
    DiariaRepositorio diariaRepositorio;
    @Autowired
    tipoQuartoRepositorio tipoQuartoRepositorio;

    @PostMapping("/{id}")
    public Diaria findDiaria(@PathVariable("id") Long id){
        Diaria diaria =  diariaRepositorio.findBytipoQuarto(tipoQuartoRepositorio.findById(id));
        return diaria;
    }
    @GetMapping
    public List<Diaria> listaDiaria(){
    	return diariaRepositorio.findAll();
    }
    @PostMapping("/edit")
    public void editarDiaria(HttpServletResponse response,Diaria diaria) throws IOException{
    	Optional<Diaria> tipoQuartoDiaria = diariaRepositorio.findById(diaria.getIdDiara());
    	if (tipoQuartoDiaria.isPresent()) {
    		diaria.setTipoQuarto(tipoQuartoDiaria.get().getTipoQuarto());
        	diariaRepositorio.save(diaria);
		}
    	response.sendRedirect("/precoDiariaTipoQuarto");
    }

}
