package br.com.hotel;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SenderMailService {

    @Autowired
    private JavaMailSender mailSender;
    void enviar(String endereco) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(endereco);
        email.setSubject("Recuperação de Senha");
        endereco = endereco.replace(".", "").replace("@","");
        int tamanho = endereco.length()-1;
        Random gerador = new Random();
        String codigo = "";
        for (int i = 0; i < 6; i++){
            if(i%2 == 0){
                int a = gerador.nextInt(tamanho);
                codigo += endereco.substring(a,a+1);
            }else{
                int b = gerador.nextInt(10);
                String[] caracteres = {"0","1","2","3","4","5","6","7","8","9"};
                codigo += caracteres[b];
            }
        }
        codigo = codigo.toUpperCase();
        email.setText("Insira esse código para alterar sua senha: "+codigo);
        mailSender.send(email);
    }
}
