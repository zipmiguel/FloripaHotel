$(document).ready(function(){
    if(localStorage.getItem('hospede') != null){
        var hospede = JSON.parse(localStorage.getItem('hospede'))
        var nomeSeparado = hospede.nome.split(" ");
        document.querySelector(".nav-right").innerHTML = '<a href="/#reserva" class="primary-btn">Faça sua Reserva</a>'+
        '<a onclick="logout()" href="/" id="button-login">Logout</a>'+
        ' <h6 class="text-white">Bem-Vindo, '+nomeSeparado[0]+' | <a href="./cadastroUsuario" id="button-cadastro">Meu Perfil</a></h6>'
        $(".nav-right .text-white").css({"display": "flex", "justify-content": "flex-end", "padding" : "12px 2px 0 0"});
    }
});

function logout(){
    localStorage.clear();
}