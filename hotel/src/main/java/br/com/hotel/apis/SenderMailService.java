package br.com.hotel.apis;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SenderMailService {

    @Autowired
    private JavaMailSender mailSender;
    
    public String codigoRecuperarSenha(String endereco){
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
        return codigo;
    }

    @Async
    public void enviarRecuperarSenha(String endereco, String nome, String codigo) throws MessagingException {
        MimeMessage mimeMessage= mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setTo(endereco);
        helper.setSubject("Recuperação de Senha");
        helper.setText("Insira esse código para alterar sua senha: "+codigo);
        helper.setText("<html><head></head><body><h2 style=\"color: black;\">Floripa Hotel</h2> <br/>"+
        "<h3 style=\"color: black;\">Olá "+nome+" !<br/><br/>"+
        "<h2>"+codigo+"</h2><br/>"+
        "<h3> <a href=\"http://localhost:8089/recuperarSenhaUsuario\">Clique aqui para ir para a página de alteração de senha e insira o código acima !</a></h3></body></html>", true);
        mailSender.send(mimeMessage);

    }

    public int codigoFinalizarCadastro(){
        return (int)System.currentTimeMillis();
    }

    @Async
    public void enviarConfirmarCadastro(String endereco, String nome, int codigo) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setTo(endereco);
        helper.setSubject("Confirmar Cadastro no Floripa Hotel");
        helper.setText("<html><head></head><body><h2 style=\"color: black;\">Floripa Hotel</h2> <br/>"+
        "<h3 style=\"color: black;\">Olá "+nome+", seu cadastro está quase concluído, por favor clique no link abaixo para finalizá-lo !<br/><br/>"+
        "<a href=\"http://localhost:8089/finalizarCadastro/"+codigo+"\">Clique aqui!</a></h3></body></html>", true);
        mailSender.send(mimeMessage);
    }
    @Async
    public void codigoReserva(String endereco, String nome,Long codigo) throws MessagingException {
        String codigoS = codigo.toString();
        String codigoFormatado = codigoS.substring(0, 4)+"-"+codigoS.substring(4, 8)+"-"+codigoS.substring(8, 12)+"-"+codigoS.substring(12);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setTo(endereco);
        helper.setSubject("Reserva Concluída com sucesso!");
        helper.setText("<html><head></head><body><h2 style=\"color: black;\">Floripa Hotel</h2> <br/>"+
        "<h3 style=\"color: black;\">Olá "+nome+", sua reserva está concluída, por favor apresente esse código para realizar o check-in ao chegar no hotel!<br/><br/>"+
        "<h2>"+codigoFormatado+"</h2></body></html>", true);
        mailSender.send(mimeMessage);
    }
}
