function validarForm(){
    nome = $("input[name=nome]").val();
    telefone = $("input[name=telefone]").val();
    cpf = $("input[name=cpf]").val();
    endereco = $("input[name=endereco]").val();
    email = $("input[name=email]").val();
    nascimento = $("input[name=nascimento]").val();
    senha = $("input[name=senha]").val();
    confirmarSenha = $("input[name=confirmarSenha]").val();

    if(vazio(nome) || vazio(telefone) || vazio(cpf) || vazio(endereco) || 
    vazio(email) || vazio(nascimento) || vazio(senha) || vazio(confirmarSenha)){
        alert("Preencha corretamente os campos");
        return false
    }else if(senha != confirmarSenha){
        alert("Senha e Confirmar senha não são iguais!")
        return false
    }else if(!email.includes("@") || !email.includes(".") || email.length() <= 5){
        alert("Email incorreto")
        return false
    }
    alert("Foi enviado uma etapa adicional de verificação ao seu email. Prossiga os passos para finalizar o cadastro")
    return true
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
                logar = true
            }
        },
        error: function (){
            alert("Email e/ou senha incorretos!")
        }
        // },
        // beforeSend: function (){
    
        // }
    })
    if(logar){
        //Logar na sessao
        window.location.replace('http://localhost:8089/')
    }
    return false
}