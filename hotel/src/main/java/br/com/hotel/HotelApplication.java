package br.com.hotel;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@Controller
public class HotelApplication{
	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}
	
	@Autowired
    SenderMailService senderMailService;
	@RequestMapping("/recuperar")
	public void testEnvioEmail(HttpServletResponse response, @RequestParam(name = "emailCliente") String email) throws IOException{
		senderMailService.enviar(email);
		response.sendRedirect("/recuperarSenhaUsuario.html");
	}

}
