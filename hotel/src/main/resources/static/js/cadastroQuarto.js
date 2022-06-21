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
        $.ajax({
            type: "GET",
            dataType: "json",
            contentType: 'application/json',
            url: "/buscarQuarto/"+numero,
            async: false,
            success: function(quarto){
                numeroQuarto = document.querySelector("input[name=numero]");
                select = document.querySelector("select[name=tipoQuarto]");
                radio = document.querySelector("#campo"+quarto.status);
                numeroQuarto.value = quarto.numero
                select.value = quarto.tipoQuarto.idTipoQuarto
                radio.checked = true
            },
            error: function (quarto){
                alert("Ocorreu um erro inesperado, por favor, tente novamente!")
            }
        })
    });
});