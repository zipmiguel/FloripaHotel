package br.com.hotel.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.hotel.repositorio.tipoQuartoRepositorio;

import br.com.hotel.model.TipoQuarto;

//Luiz Eduardo 

@RestController
@RequestMapping("/tipoQuarto")
public class TipoQuartoController {
    @Autowired
    private tipoQuartoRepositorio tipoQuartoRepositorio;

    @GetMapping
    public List<TipoQuarto> listaTipoQuarto(){
        List<TipoQuarto> listaTipos = tipoQuartoRepositorio.BuscarTiposQuartoTrue();
       return listaTipos;
    }

    @PostMapping("/save")
    public void saveQuarto(HttpServletResponse response,@RequestParam String tipoQuarto,@RequestParam int quantidadeCamaSolteiro, @RequestParam int quantidadeCamaCasal) throws IOException{
        TipoQuarto tQuarto = new TipoQuarto(tipoQuarto, quantidadeCamaSolteiro, quantidadeCamaCasal);
        tipoQuartoRepositorio.save(tQuarto);
        response.sendRedirect("/cadastroTipoQuarto.html");
    }
    @PostMapping("/delete")
    public void deleteQuarto(HttpServletResponse response,@RequestParam Long idQuarto) throws IOException{  
        TipoQuarto tipoQuarto = tipoQuartoRepositorio.getReferenceById(idQuarto);
        tipoQuartoRepositorio.delete(tipoQuarto);
        //tipoQuartoRepositorio.CancelaTipoQuarto(idQuarto);
        response.sendRedirect("/cadastroTipoQuarto.html");
    }
    @PostMapping("/edit")
    public void EditQuarto(HttpServletResponse response,@RequestParam Long idQuarto,@RequestParam int quantidadeCamaSolteiro, @RequestParam int quantidadeCamaCasal) throws IOException{
        TipoQuarto tipoQuartoOriginal = tipoQuartoRepositorio.getReferenceById(idQuarto);
        TipoQuarto tipoQuartoNovo = new TipoQuarto(tipoQuartoOriginal.getTipoQuarto(),quantidadeCamaSolteiro,quantidadeCamaCasal);
        tipoQuartoRepositorio.CancelaTipoQuarto(idQuarto);
        tipoQuartoRepositorio.save(tipoQuartoNovo);
        response.sendRedirect("/cadastroTipoQuarto.html");
    }
}
