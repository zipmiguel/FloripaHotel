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

        
        if(vazio(nome) || telefone.length != 15 || cpf.length != 14 || vazio(endereco) || vazio(nascimento)){
            alert("Preencha corretamente os campos");
        }else if(senha != confirmarSenha){
            alert("Senha e Confirmar senha não são iguais!")
        }else if(!email.includes("@") || !email.includes(".") || email.length <= 5){
            alert("Email incorreto")
        }else if(!validaSenha(senha)){
            alert("Senha deve conter no mínimo:\n\n" + "8 caracteres\n" + "1 Letra maiúscula\n" + "1 Letra minúscula\n" + "1 Número\n" + "1 Caractere especial")
        }else if(JSON.parse(localStorage.getItem('hospede')) != null){
            atualizarSessao(nome,telefone,cpf,endereco,email,nascimento,senha)
        }else{
            alert("Foi enviado uma etapa adicional de verificação ao seu email. Prossiga os passos para finalizar o cadastro")
            $("#cadastrarHospede").trigger('submit', [true]);
        }
        
    });
});

function atualizarSessao(nomeV,telefoneV,cpfV,enderecoV,emailV,nascimentoV,senhaV){

    $.post("http://localhost:8089/editarHospede",{
        nome:nomeV,telefone:telefoneV,cpf:cpfV,endereco:enderecoV,email:emailV,nascimento:nascimentoV,senha:senhaV
    }, function(hospede){
        if(hospede){
            localStorage.setItem('hospede', JSON.stringify(hospede))
            window.location.replace("http://localhost:8089/cadastroUsuario")
        }else{
            alert("Ocorreu um erro inesperado, por favor tente novamente!")
        }
    })

}

function esqueciMinhaSenha(emailV){
    if(!emailV.includes("@") || !emailV.includes(".") || emailV.length <= 5){
        alert("Para recuperar sua senha, por favor, insira seu email no campo acima e clique novamente!")
    }else{
        $.post("http://localhost:8089/recuperarSenha",{
        email: emailV
    }, function(hospede){
        if(!hospede){
            alert("Ocorreu um erro inesperado, por favor tente novamente!")
        }else{
            alert("Verifique em instantes sua caixa de email para recuperar sua senha e siga as instruções!")
            window.location.replace("http://localhost:8089/recuperarSenhaUsuario")
        }
    })
    }
}

function recuperarSenha(senha){
    if(!validaSenha(senha)){
        alert("Senha deve conter no mínimo:\n\n" + "8 caracteres\n" + "1 Letra maiúscula\n" + "1 Letra minúscula\n" + "1 Número\n" + "1 Caractere especial")
        return false
    }
    
    const codigoV = $("input[name=codigo]").val()
    const novaSenhaV = $("input[name=novaSenha]").val()
    $.post("http://localhost:8089/trocarSenha",{
        novaSenha: novaSenhaV, codigo: codigoV
    }, function(msg){
        if(!msg){
            alert("Ocorreu um erro inesperado, por favor tente novamente!")
        }else{
            window.location.replace("http://localhost:8089/loginUsuario")
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
    loginV = $("input[name=login]").val();
    senhaV = $("input[name=senha]").val();
    if(vazio(loginV) || senhaV.length < 8){
        alert("Email e/ou senha incorretos!")
    }else{
        $.post("http://localhost:8089/verificarLogin/hospede",{
            login: loginV, senha: senhaV
        }, function(hospede){
            if(!hospede){
                alert("Email e/ou senha incorretos!")
            }else if(hospede.nome == "sayudasd@#aidshuauisg86aSDBH"){
                alert("Por favor, conclua seu cadastro via e-mail!")
            }else{
                alert("Ola "+hospede.nome)
                localStorage.setItem('hospede', JSON.stringify(hospede));
                window.location.replace('http://localhost:8089/')
            }
        })
    }
}