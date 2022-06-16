package br.com.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class redirect {
    @RequestMapping("/about-us")
    public String aboutus(){
        return "about-us.html";
    }
    @RequestMapping("/blog")
        public String blog(){
            return "blog.html";
    }
    @RequestMapping("/boleto")
        public String boleto(){
            return "boleto.html";
    }
    @RequestMapping("/cadastroCargo")
        public String cadastroCargo(){
            return "cadastroCargo.html";
    }
    @RequestMapping("/cadastroFuncionario")
        public String cadastroFuncionario(){
            return "cadastroFuncionario.html";
    }
    @RequestMapping("/cadastroQuarto")
        public String cadastroQuarto(){
            return "cadastroQuarto.html";
    }
    @RequestMapping("/cadastroUsuario")
        public String cadastroUsuario(){
            return "cadastroUsuario.html";
    }
    @RequestMapping("/compraEfetivadaCartao")
        public String compraEfetivadaCartao(){
            return "compraEfetivadaCartao.html";
    }
    @RequestMapping("/contact")
        public String contact(){
            return "contact.html";
    }
    @RequestMapping("/loginUsuario")
        public String loginUsuario(){
            return "loginUsuario.html";
    }
    @RequestMapping("/pagamentoFuncionario")
        public String pagamentoFuncionario(){
            return "pagamentoFuncionario.html";
    }
    @RequestMapping("/pagamentoUsuario")
        public String pagamentoUsuario(){
            return "pagamentoUsuario.html";
    }
    @RequestMapping("/pix")
        public String pix(){
            return "pix.html";
    }
    @RequestMapping("/precoDiariaTipoQuarto")
        public String precoDiariaTipoQuarto(){
            return "precoDiariaTipoQuarto.html";
    }
    @RequestMapping("/recuperarSenhaUsuario")
        public String recuperarSenhaUsuario(){
            return "recuperarSenhaUsuario.html";
    }
    @RequestMapping("/reservaFuncionario")
        public String reservaFuncionario(){
            return "reservaFuncionario.html";
    }
    @RequestMapping("/reservaUsuario")
        public String reservaUsuario(){
            return "reservaUsuario.html";
    }
    @RequestMapping("/rooms")
        public String rooms(){
            return "rooms.html";
    }
    @RequestMapping("/services")
        public String services(){
            return "services.html";
    }
    @RequestMapping("/cadastroTipoQuarto")
        public String cadastroTipoQuarto(){
            return "cadastroTipoQuarto.html";
    }
    @RequestMapping("/")
    public String index(){
        return "index.html";
    }
}