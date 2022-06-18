function validarForm(){
    nome = $("input[name=nome]").val();
    telefone = $("input[name=telefone]").val();
    cpf = $("input[name=cpf]").val();
    endereco = $("input[name=endereco]").val();
    email = $("input[name=email]").val();
    nascimento = $("input[name=nascimento]").val();
    senha = $("input[name=senha]").val();
    confirmarSenha = $("input[name=confirmarSenha]").val();

    if(vazio(nome) || telefone.length != 11 || cpf.length != 11 || vazio(endereco) || 
    vazio(email) || vazio(nascimento)){
        alert("Preencha corretamente os campos");
        return false
    }else if(senha != confirmarSenha){
        alert("Senha e Confirmar senha não são iguais!")
        return false
    }else if(!email.includes("@") || !email.includes(".") || email.length <= 5){
        alert("Email incorreto")
        return false
    }else if(!validaSenha(senha)){
        alert("Senha deve conter no mínimo:\n\n" + "8 caracteres\n" + "1 Letra maiúscula\n" + "1 Letra minúscula\n" + "1 Número\n" + "1 Caractere especial")
        return false
    }else{
        alert("Foi enviado uma etapa adicional de verificação ao seu email. Prossiga os passos para finalizar o cadastro")
    }
    return true
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