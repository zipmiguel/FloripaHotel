function carregarCheck(){
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/tipoQuarto",
        success: function (data) {
            const tabela = document.querySelector('#listerCheckin')
            let tr = "";
            $.each(data,function(i,value){
             tr+= `<tr class="TipoQuarto" idTipoQuarto=${value.idTipoQuarto}">
             <td><input type="text" class="centralText" name="tipoQuarto${value.idTipoQuarto}" value="${value.tipoQuarto}" style="font-family: 'Montserrat Alternates';font-weight: bold;" disabled></td>
             <td>
             <img src="img/buscar_lupa_30_30px.svg" class="botaoPopup pointer" onclick="openCheckin(${value.idTipoQuarto})">
             </td>
             </tr>
             `;
            })
        $('.TipoQuarto').remove();
        $('#listerCheckin').append(tr);
    },
        error: function() {
            alert('Tipos de quarto indisponiveis')
        }
    })
}
function openCheckin(idTipoQuarto){
	mostrarPopup()
    const popupCheckin = document.getElementById('check-in-Popup');
       popupCheckin.removeAttribute('idTipoQuarto');
       popupCheckin.setAttribute('idTipoQuarto',idTipoQuarto);
       popupCheckin.style.visibility = 'visible';
       popupCheckin.style.display = 'block';
       const input = $(`input[name="tipoQuarto${idTipoQuarto}"]`)
       $('#tituloCheckin').html(input.val());
       console.log(input.val())
}