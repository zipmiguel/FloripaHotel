package br.com.hotel.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.hotel.apis.SenderMailService;

@Controller
public class EmailController {
    
    @Autowired
    SenderMailService senderMailService;
    
    @RequestMapping("/recuperar")
    public void testEnvioEmail(HttpServletResponse response, @RequestParam(name = "emailCliente") String email) throws IOException{
        senderMailService.enviarRecuperarSenha(email);
        response.sendRedirect("/recuperarSenhaUsuario");
    }
}
