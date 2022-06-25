$(document).ready(function(){
    $.ajax({
        type: "GET",
        dataType: "json",
        contentType: 'application/json',
        url: "/tipoQuarto",
        async: false,
        success: function(lista){
            var stringOptions = "<optgroup label='Selecione o Tipo de Quarto'>"
            lista.forEach(tipoQuarto => {
                stringOptions += `<option value="${tipoQuarto.idTipoQuarto}">${tipoQuarto.tipoQuarto}</option>`
            });
            stringOptions+="</optgroup>"
            document.querySelector("#selectTipoQuartoBanco").innerHTML = stringOptions
        },
        error: function (){
            alert("Ocorreu um erro inesperado, por favor, tente novamente!")
        }
    })



    $("#pesquisarQuarto").click(function(){
        var numero = $("#inputNumeroQuarto").val();
        $.get("http://localhost:8089/buscarQuarto/"+numero,{
            }, function(quarto){
                numeroQuarto = document.querySelector("input[name=numero]");
                select = document.querySelector("select[name=tipoQuarto]");
                if(!quarto){
                    alert("Quarto Nº "+numero+" não encontrado!")
                    radio1 = document.querySelector("#campotrue");
                    radio2 = document.querySelector("#campofalse");
                    numeroQuarto.value = ""
                    select.value = 1
                    radio1.checked = false
                    radio2.checked = false
                }else{
                    radio = document.querySelector("#campo"+quarto.status);
                    numeroQuarto.value = quarto.numero
                    select.value = quarto.tipoQuarto.idTipoQuarto
                    radio.checked = true
                }
        })
    });
});

function cadastroEsalvarQuarto(){
    const numeroV = $("input[name=numero]").val()
    const statusV = $("input[name=status]").val()
    const tipoQuartoV = $("select[name=tipoQuarto]").val()
    
    if(numeroV == "" || statusV == "" || tipoQuartoV == ""){
        alert("Preencha todos os campos corretamente!")
    }else{
        $.post("http://localhost:8089/cadastrarQuarto",{
            numero:numeroV, status:statusV, tipoQuarto:tipoQuartoV
        }, function(funcao){
            if(funcao == "editar"){
                alert("Quarto editado com sucesso!")
                window.location.replace("http://localhost:8089/cadastroQuarto")
            }else if(funcao == "adicionar"){
                alert("Quarto adicionado com sucesso!")
                window.location.replace("http://localhost:8089/cadastroQuarto")
            }else{
                alert("Ocorreu um erro inesperado, por favor, tente novamente!")
            }
        })
    }

    
}