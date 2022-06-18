package br.com.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class redirect {
    @RequestMapping("/about-us")
    public static String aboutus(){
        return "about-us.html";
    }
    @RequestMapping("/blog")
    public static String blog(){
            return "blog.html";
    }
    @RequestMapping("/boleto")
    public static String boleto(){
            return "boleto.html";
    }
    @RequestMapping("/cadastroCargo")
    public static String cadastroCargo(){
            return "cadastroCargo.html";
    }
    @RequestMapping("/cadastroFuncionario")
    public static String cadastroFuncionario(){
            return "cadastroFuncionario.html";
    }
    @RequestMapping("/cadastroQuarto")
    public static String cadastroQuarto(){
            return "cadastroQuarto.html";
    }
    @RequestMapping("/cadastroUsuario")
    public static String cadastroUsuario(){
            return "cadastroUsuario.html";
    }
    @RequestMapping("/compraEfetivadaCartao")
    public static String compraEfetivadaCartao(){
            return "compraEfetivadaCartao.html";
    }
    @RequestMapping("/contact")
    public static String contact(){
            return "contact.html";
    }
    @RequestMapping("/loginUsuario")
    public static String loginUsuario(){
            return "loginUsuario.html";
    }
    @RequestMapping("/pagamentoFuncionario")
    public static String pagamentoFuncionario(){
            return "pagamentoFuncionario.html";
    }
    @RequestMapping("/pagamentoUsuario")
    public static String pagamentoUsuario(){
            return "pagamentoUsuario.html";
    }
    @RequestMapping("/pix")
    public static String pix(){
            return "pix.html";
    }
    @RequestMapping("/precoDiariaTipoQuarto")
    public static String precoDiariaTipoQuarto(){
            return "precoDiariaTipoQuarto.html";
    }
    @RequestMapping("/recuperarSenhaUsuario")
    public static String recuperarSenhaUsuario(){
            return "recuperarSenhaUsuario.html";
    }
    @RequestMapping("/reservaFuncionario")
    public static String reservaFuncionario(){
            return "reservaFuncionario.html";
    }
    @RequestMapping("/reservaUsuario")
    public static String reservaUsuario(){
            return "reservaUsuario.html";
    }
    @RequestMapping("/rooms")
    public static String rooms(){
            return "rooms.html";
    }
    @RequestMapping("/services")
    public static String services(){
            return "services.html";
    }
    @RequestMapping("/cadastroTipoQuarto")
    public static String cadastroTipoQuarto(){
            return "cadastroTipoQuarto.html";
    }
    @RequestMapping("/editarReservaUsuario")
    public static String editarReservaUsuario(){
            return "editarReservaUsuario.html";
    }
    @RequestMapping("/editarReservaFuncionario")
    public static String editarReservaFuncionario(){
            return "editarReservaFuncionario.html";
    }
    @RequestMapping("/")
    public static String index(){
        return "index.html";
    }
}