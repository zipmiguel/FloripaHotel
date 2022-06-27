$(document).ready(function () {
    carregarDiaria()
});

function carregarDiaria(){
    $.ajax({
        type: "GET",
        url: "/diaria",
        dataType: "json",
        success: function (response) {
            let Diarias = "";
            response.forEach(element => {
                Diarias+=`  <tr>
                            <td idquarto="${element.idDiara}" class="negritoLinhas DiariaAjax">${element.tipoQuarto.tipoQuarto}</td>
                            <td class="inputDiaria${element.idDiara}">R$ ${formataValor2(element.diaUtil)}</td>
                            <td class="inputDiaria${element.idDiara}">R$ ${formataValor2(element.fimDeSemana)}</td>
                            <td class="inputDiaria${element.idDiara}">R$ ${formataValor2(element.feriado)}</td>
                            <td class="inputDiaria${element.idDiara}">R$ ${formataValor2(element.promocional)}</td>
                            <td>
                            <img src="img/editar_lapis_30x30px.svg" class="botaoPopup" onclick="OpenEditPopup(${element.idDiara});">
                            </td>
                        </tr>`
            });
            $('.DiariaAjax').remove();
            $('#tabela_diaria').append(Diarias);
        },
        error: function(){
            alert('erro ao carregar as diárias')
        }
    });
}
function formataValor2(valor){
    const valor2 = valor.toString().replace('.',',');
    return valor2;
}
function EditDiaria(){
    const editPopup = document.getElementById('editarPopup');
    $('#idDiaria').val(parseInt(editPopup.getAttribute("idquarto")))
}

function formataReal(valor) {
    // valor formatado R$numero
   const valor2 = valor.toString().replace('R$',"").trim();
   return valor2;
}
 