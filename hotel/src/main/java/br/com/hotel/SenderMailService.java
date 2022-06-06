package br.com.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SenderMailService {

    @Autowired
    private JavaMailSender mailSender;

    void enviar() {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("elderluciane@uol.com.br");
        email.setSubject("Recuperação de Senha");
        email.setText("Insira esse código para alterar sua senha:");
        mailSender.send(email);
    }
}
