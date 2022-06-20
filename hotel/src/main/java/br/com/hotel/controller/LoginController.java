package br.com.hotel.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.model.Hospede;
import br.com.hotel.repositorio.LoginRepositorio;


@RestController
@RequestMapping("/verificarLogin")
public class LoginController {
    
    @Autowired
    private LoginRepositorio loginRepositorio;

    @GetMapping("/hospede")
    public Hospede verificarLoginHospede(HttpServletResponse response, @RequestParam String login, @RequestParam String senha){
        Hospede hospede = loginRepositorio.findByemail(login);
        if(hospede.getSenha().equals(senha) && hospede.getVerificarConfirmacao() == -1){
            return hospede;
        }else if(hospede.getSenha().equals(senha)){
            Hospede hospede2 = new Hospede();
            hospede2.setNome("sayudasd@#aidshuauisg86aSDBH");
            return hospede2; 
        }
        else{
            return null;
        }
    }
}
