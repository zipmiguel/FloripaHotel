package br.com.hotel.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotel.apis.SenderMailService;
import br.com.hotel.model.Hospede;
import br.com.hotel.repositorio.HospedeRepositorio;

@RestController
public class HospedeController {

    @Autowired
    SenderMailService senderMailService;

    @Autowired
    private HospedeRepositorio hospedeRepositorio;

    @PostMapping("/cadastrarHospede")
    public void adicionar(Hospede hospede, HttpServletResponse response) throws IOException, MessagingException {
        int codigo = senderMailService.enviarConfirmarCadastro(hospede.getEmail(), hospede.getNome());
        hospede.setVerificarConfirmacao(codigo);
        hospedeRepositorio.save(hospede);
        response.sendRedirect("/loginUsuario");
    }

    @GetMapping("/finalizarCadastro/{codigo}")
    public void finalizarCadastro(@PathVariable("codigo") int codigo) {
        Hospede hospede = hospedeRepositorio.findByverificarConfirmacao(codigo);
        hospede.setVerificarConfirmacao(-1);
        hospedeRepositorio.save(hospede);
    }

    @PostMapping("/editarHospede")
    public Hospede editarHospede(Hospede hospede, HttpServletResponse response) throws CloneNotSupportedException, IOException {
        Hospede hospedeExistente = hospedeRepositorio.findBycpf(hospede.getCpf());
        Long id = hospedeExistente.getIdHospede();
        hospedeExistente = hospede.clone();
        hospedeExistente.setIdHospede(id);
        hospedeExistente.setVerificarConfirmacao(-1);
        hospedeRepositorio.save(hospedeExistente);
        return hospedeExistente;
    }

    @GetMapping("/verificarLogin/hospede")
    public Hospede verificarLoginHospede(HttpServletResponse response, @RequestParam String login, @RequestParam String senha){
        Hospede hospede = hospedeRepositorio.findByemail(login);
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

    @GetMapping("/recuperarSenha")
    public void enviarRecuperacaoSenha(HttpServletResponse response, @RequestParam(name = "email") String email)
            throws MessagingException, IOException {
        Hospede hospede = hospedeRepositorio.findByemail(email);
        String codigo = senderMailService.enviarRecuperarSenha(email,hospede.getEmail());
        hospede.setSenha(codigo);
        hospedeRepositorio.save(hospede);
        response.sendRedirect("/recuperarSenhaUsuario");
    }

    @PostMapping("/trocarSenha")
    public void recuperarSenha(HttpServletResponse response, @RequestParam(name = "novaSenha") String novaSenha, @RequestParam(name = "codigo") String codigo){
        Hospede hospede = hospedeRepositorio.findBysenha(codigo);
        hospede.setSenha(novaSenha);
        hospedeRepositorio.save(hospede);
    }

}
