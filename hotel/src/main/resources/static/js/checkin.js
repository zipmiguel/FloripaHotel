

function openCheckin(idTipoQuarto,puxarPeloCodigo ){
	mostrarPopup()
    const popupCheckin = document.getElementById('check-in-Popup');
	   if(!puxarPeloCodigo){
		   carregarQuartos();
	   }
       popupCheckin.removeAttribute('idTipoQuarto');
       popupCheckin.setAttribute('idTipoQuarto',idTipoQuarto);
       popupCheckin.style.visibility = 'visible';
       popupCheckin.style.display = 'block';
       let input = $(`input[name="tipoQuarto${idTipoQuarto}"]`)
       $('#tituloCheckin').html(input.val());
}
function closeCheckin(){
    const popupCheckin = document.getElementById('check-in-Popup');
    ocultarPopup()
    popupCheckin.style.visibility = 'hidden';
    popupCheckin.style.display = 'none';
}

function carregarQuartos(){
	const popupCheckin = document.getElementById('check-in-Popup');
    $.ajax({
        type: "put",
        dataType: "json",
        data:{idTipoQuarto:popupCheckin.getAttribute('idTipoQuarto')},
        url: "/listaQuartos",
        success: function (data) {
            var ColecaoQuartosTipo = []
            const corte = 5;
            let conteudoTotal = "";
            let conteudoColuna = "";
            for (let i = 0; i < data.length; i = i + corte) {
            ColecaoQuartosTipo.push(data.slice(i, i + corte));
            }
            ColecaoQuartosTipo.forEach(fileiraQuarto=>{
                conteudoColuna = ""
                conteudoColuna+=`<div class="lineCheckin">`
                fileiraQuarto.forEach(quarto=>{
                    var checkQuarto1 = quarto.status==true ? 'disponivel' : 'indisponivel'
                    var checkQuarto2 = quarto.status==true ? 'buttonPopupCheckinSuccess' : 'buttonPopupCheckinFailed'
                    conteudoColuna+=`
                    <div class="quartoCheckin">
                    <div class="quartoCheckinTitulo">${quarto.numero}</div>
                    <button class="buttonPopupCheckin ${checkQuarto2}">${checkQuarto1}</button>
                    </div>
                    `
                })
                conteudoColuna+='</div>'
                conteudoTotal+=conteudoColuna;
            })
        console.log(conteudoTotal)
         $('.lineCheckin').remove();
         $('#contentCheckin').append(conteudoTotal);
    },
        error: function() {
            alert('Tipos de quarto indisponiveis')
        }
    })
}
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
             <img src="img/buscar_lupa_30_30px.svg" class="botaoPopup pointer" onclick="openCheckin(${value.idTipoQuarto,false})">
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
function carregarQuartosCodigo(){
	const codigoReserva = $('#codigoReserva').val();
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/pesquisarReservaEfetivada/"+parseInt(codigoReserva),
        success: function (data) {
            var ColecaoQuartosTipo = []
            const corte = 5;
            let conteudoTotal = "";
            let conteudoColuna = "";
            for (let i = 0; i < data.length; i = i + corte) {
            ColecaoQuartosTipo.push(data.slice(i, i + corte));
            }
            ColecaoQuartosTipo.forEach(fileiraQuarto=>{
                conteudoColuna = ""
                conteudoColuna+=`<div class="lineCheckin">`
                fileiraQuarto.forEach(quarto=>{
                    var checkQuarto1 = quarto.status==true ? 'disponivel' : 'indisponivel'
                    var checkQuarto2 = quarto.status==true ? 'buttonPopupCheckinSuccess' : 'buttonPopupCheckinFailed'
                    var checkQuarto3 = quarto.status==true ? true : false
                    
                    conteudoColuna+=`

                    <div class="quartoCheckin">
                    <div class="quartoCheckinTitulo">${quarto.numero}</div>
                    <button class="buttonPopupCheckin ${checkQuarto2}" onclick="OpenPopupCheckin(${checkQuarto3},${quarto.idQuarto})">${checkQuarto1}</button>
                    </div>`
                })
                conteudoColuna+='</div>'
                conteudoTotal+=conteudoColuna;
            })
         console.log(conteudoTotal)
         let idtitulo = data[0].tipoQuarto.idTipoQuarto;
         openCheckin(idtitulo,true)
         $('.TipoQuarto').remove();
         $('#contentCheckin').append(conteudoTotal);
    },
        error: function() {
            alert('codigo não encontrado');
        }
    })
}
function OpenPopupCheckin(tipoPopup,idquarto){
        const popupCheckinSalvar = document.getElementById('popupSalvarCheckin');
        popupCheckinSalvar.removeAttribute('idquarto');
        popupCheckinSalvar.setAttribute('idquarto',idquarto);
        popupCheckinSalvar.setAttribute('status',tipoPopup);
        popupCheckinSalvar.style.visibility = 'visible';
        popupCheckinSalvar.style.display = 'flex';
}
function checkinQuarto(tipoPopup){
        const popupCheckinSalvar = document.getElementById('popupSalvarCheckin');
        $.ajax({
            type: "post",
            url: "/checkin",
            data: {tipoQuarto:tipoPopup,idQuarto:popupCheckinSalvar.getAttribute('idquarto')},
            dataType: "json",
            success: function () {
                closePopupCheckin();
            },
            error: function(){
                alert('erro ao liberar quarto')
            }
        });
    }
function closePopupCheckin(){
    const popupCheckinCancelar = document.getElementById('popupCancelarCheckin');
    const popupCheckinSalvar = document.getElementById('popupSalvarCheckin');

    popupCheckinSalvar.removeAttribute('idquarto');
    popupCheckinSalvar.style.visibility = 'hidden';
    popupCheckinSalvar.style.display = 'none';
    popupCheckinCancelar.removeAttribute('idquarto');
    popupCheckinCancelar.style.visibility = 'hidden';
    popupCheckinCancelar.style.display = 'none';
}