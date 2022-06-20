package br.com.hotel.apis;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SenderMailService {

    @Autowired
    private JavaMailSender mailSender;
    public void enviarRecuperarSenha(String endereco) {
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
    
    public int enviarConfirmarCadastro(String endereco, String nome) {
        int codigo = (int)System.currentTimeMillis();
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setTo(endereco);
            helper.setSubject("Confirmar Cadastro no Floripa Hotel");
            helper.setText("<html><head></head><body><h2 style=\"color: black;\">Floripa Hotel</h2> <br/>"+
            "<h3 style=\"color: black;\">Olá "+nome+", seu cadastro está quase concluído, por favor clique no link abaixo para finalizá-lo !<br/><br/>"+
            "<a href=\"http://localhost:8089/finalizarCadastro/"+codigo+"\">Clique aqui!</a></h3></body></html>", true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println(e);
        }
        return codigo;
    }
}
