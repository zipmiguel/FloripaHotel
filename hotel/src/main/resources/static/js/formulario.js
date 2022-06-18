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
    if(input == ""){
        return true
    }
    return false
}