package br.com.hotel;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class HotelApplication{
	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}

	@Autowired
    SenderMailService senderMailService;
	@GetMapping("/recuperar")
	public void testEnvioEmail(HttpServletResponse response) throws IOException{
		senderMailService.enviar();
    	response.sendRedirect("/recuperarSenhaUsuario.html");
	}

}
