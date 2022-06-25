function cadastrarCargo(){
    const tipoCargoV = $("input[name=tipoCargo]").val()
    const permissaoV = $("select[name=permissao]").val()
    
    if(tipoCargoV == ""){
        alert("Preencha todos os campos corretamente!")
    }else{
        $.post("http://localhost:8089/cadastrarCargo",{
            tipoCargo:tipoCargoV, permissao:permissaoV
        }, function(funcao){
            if(funcao == "adicionar"){
                alert("Cargo adicionado com sucesso!")
                window.location.replace("http://localhost:8089/cadastroCargo")
            }else{
                alert("Cargo já existente, insira um novo cargo!")
            }
        })
    }

    
}