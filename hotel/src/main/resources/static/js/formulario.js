$(document).ready(function() {
    $("#inputTeste").click(function(e){
        e.preventDefault()
        const nome = $("input[name=nome]").val()
        const telefone = $("input[name=telefone]").val()
        const cpf = $("input[name=cpf]").val()
        const endereco = $("input[name=endereco]").val()
        const email = $("input[name=email]").val()
        const nascimento = $("input[name=nascimento]").val()
        const senha = $("input[name=senha]").val()
        const confirmarSenha = $("input[name=confirmarSenha]").val()
        // const dadosJson = `{"nome":${nome},"telefone":${telefone},cpf":${cpf},"endereco":${endereco},email":${email},"nascimento":${nascimento},"senha":${senha}}`
        const dados = `?nome=${nome}&telefone=${telefone}&cpf=${cpf}&endereco=${endereco}&email=${email}&nascimento=${nascimento}&senha=${senha}`
        if(vazio(nome) || telefone.length != 15 || cpf.length != 14 || vazio(endereco) || vazio(email) || vazio(nascimento)){
            alert("Preencha corretamente os campos");
        }else if(senha != confirmarSenha){
            alert("Senha e Confirmar senha não são iguais!")
        }else if(!email.includes("@") || !email.includes(".") || email.length <= 5){
            alert("Email incorreto")
        }else if(!validaSenha(senha)){
            alert("Senha deve conter no mínimo:\n\n" + "8 caracteres\n" + "1 Letra maiúscula\n" + "1 Letra minúscula\n" + "1 Número\n" + "1 Caractere especial")
        }else if(JSON.parse(localStorage.getItem('hospede')) != null){
            atualizarSessao(dados)
        }else{
            alert("Foi enviado uma etapa adicional de verificação ao seu email. Prossiga os passos para finalizar o cadastro")
            $("#cadastrarHospede").trigger('submit', [true]);
        }
        
    });
});

function atualizarSessao(dados){
    $.ajax({
        type: "POST",
        dataType: "json",
        contentType: 'application/json',
        url: "/editarHospede"+dados,
        async: false,
        success: function(hospede){
            alert("Dados alterados com sucesso "+hospede.nome)
            localStorage.setItem('hospede', JSON.stringify(hospede))
            window.location.replace("http://localhost:8089/cadastroUsuario")
        },
        error: function (){
            alert("Ocorreu um erro inesperado, por favor, tente novamente!")
        }
    })
}

function validaSenha (senha){
    result = false;
    if (/^((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\W]).{8,})$/.test(senha)) {
        result = true;
    }
    return result;
}

function vazio(input){
    if(input.replaceAll(" ","") == ""){
        return true
    }
    return false
}

function validarLogin() {
    login = $("input[name=login]").val();
    senha = $("input[name=senha]").val();
    if(vazio(login) || vazio(senha)){
        alert("Preencha corretamente todos os campos")
        return false
    }
    logar = false
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "http://localhost:8089/verificarLogin/hospede?login="+login+"&senha="+senha,
        async: false,
        success: function(hospede){
            if(hospede.nome == "sayudasd@#aidshuauisg86aSDBH"){
                alert("Por favor, conclua seu cadastro via e-mail!")
            }else{
                alert("Ola "+hospede.nome)
                localStorage.setItem('hospede', JSON.stringify(hospede));
                logar = true
            }
        },
        error: function (){
            alert("Email e/ou senha incorretos!")
        }
    })
    if(logar){
        window.location.replace('http://localhost:8089/')
    }
    return false
}