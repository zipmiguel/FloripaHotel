$(document).ready(function(){
    if(localStorage.getItem('hospede') != null){
        var hospede = JSON.parse(localStorage.getItem('hospede'))
        var nomeSeparado = hospede.nome.split(" ");
        document.querySelector(".nav-right").innerHTML = '<a href="/#reserva" class="primary-btn">Faça sua Reserva</a>'+
        '<a onclick="localStorage.clear()" href="/" id="button-login">Logout</a>'+
        ' <h6 class="text-white">Bem-Vindo, '+nomeSeparado[0]+' | <a href="./cadastroUsuario" id="button-cadastro">Meu Perfil</a></h6>'
        $(".nav-right .text-white").css({"display": "flex", "justify-content": "flex-end", "padding" : "12px 2px 0 0"});
        
        if(window.location.href == "http://localhost:8089/cadastroUsuario"){
            nome = document.querySelector("input[name=nome]");
            telefone = document.querySelector("input[name=telefone]");
            cpf = document.querySelector("input[name=cpf]");
            endereco = document.querySelector("input[name=endereco]");
            email = document.querySelector("input[name=email]");
            nascimento = document.querySelector("input[name=nascimento]");
            senha = document.querySelector("input[name=senha]");
            confirmarSenha = document.querySelector("input[name=confirmarSenha]");
            
            nome.value = hospede.nome
            telefone.value = hospede.telefone
            cpf.value = hospede.cpf
            desabilitar(cpf)
            endereco.value = hospede.endereco
            email.value = hospede.email
            desabilitar(email)
            nascimento.value = hospede.nascimento
            desabilitar(nascimento)
            senha.value = hospede.senha
            confirmarSenha.value = hospede.senha
            
        }
    }

});

function desabilitar(campo){
    campo.setAttribute("readonly", "");
    campo.style.backgroundColor = "#EEEEEE"
    campo.style.cursor = "auto"
}