package br.com.hotel.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.hotel.apis.SenderMailService;
import br.com.hotel.model.Hospede;
import br.com.hotel.repositorio.HospedeRepositorio;

@Controller
@RequestMapping("/cadastrarHospede")
public class HospedeController {
    
    @Autowired
    SenderMailService senderMailService;

    @Autowired
    private HospedeRepositorio hospedeRepositorio;

    @PostMapping
    public void adicionar(Hospede hospede, HttpServletResponse response) throws IOException{
        int codigo = senderMailService.enviarConfirmarCadastro(hospede.getEmail(), hospede.getNome());
        hospede.setVerificarConfirmacao(codigo);
        hospedeRepositorio.save(hospede);
        response.sendRedirect("/loginUsuario");
    }

    @GetMapping("/finalizarCadastro/{codigo}")
    public void finalizarCadastro(@PathVariable("codigo") int codigo){
        Hospede hospede = hospedeRepositorio.findByverificarConfirmacao(codigo);
        hospede.setVerificarConfirmacao(-1);
        hospedeRepositorio.save(hospede);
    }

}
