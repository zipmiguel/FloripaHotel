

function openCheckin(idTipoQuarto,puxarPeloCodigo ){
    const popupCheckin = document.getElementById('check-in-Popup');
    popupCheckin.setAttribute('idTipoQuarto',idTipoQuarto);
	   if(!puxarPeloCodigo){
		   carregarQuartos();
	   }
       popupCheckin.style.visibility = 'visible';
       popupCheckin.style.display = 'block';
       mostrarPopup()
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
            const listaQuartos = data[0];
            const listaQuartosBoolean = data[1];
            var ColecaoQuartosTipo = []
            const corte = 5;
            let conteudoTotal = "";
            let conteudoColuna = "";
            let tamanhoListaQuartos = 0;
            for (let i = 0; i < listaQuartos.length; i = i + corte) {
            ColecaoQuartosTipo.push(listaQuartos.slice(i, i + corte));
            }
            ColecaoQuartosTipo.forEach(fileiraQuarto=>{
                conteudoColuna = ""
                conteudoColuna+=`<div class="lineCheckin">`
                fileiraQuarto.forEach(quarto=>{
                    var checkQuarto1 = listaQuartosBoolean[tamanhoListaQuartos]==true ? 'disponivel' : 'indisponivel'
                    var checkQuarto2 = listaQuartosBoolean[tamanhoListaQuartos]==true ? 'buttonPopupCheckinSuccess' : 'buttonPopupCheckinFailed'
                    conteudoColuna+=`
                    <div class="quartoCheckin">
                    <div class="quartoCheckinTitulo">${quarto.numero}</div>
                            <button class="buttonPopupCheckin ${checkQuarto2}">${checkQuarto1}</button>
                    </div>
                    `
                    tamanhoListaQuartos+=1;
                })
                conteudoColuna+='</div>'
                conteudoTotal+=conteudoColuna;
            })
        //console.log(conteudoTotal)
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
            data.forEach(value => {
                
                tr+= `<tr class="TipoQuarto" idTipoQuarto="${value.idTipoQuarto}">
                <td><input type="text" class="centralText" name="tipoQuarto${value.idTipoQuarto}" value="${value.tipoQuarto}" style="font-family: 'Montserrat Alternates';font-weight: bold;" disabled></td>
                <td>
                <img src="img/buscar_lupa_30_30px.svg" class="botaoPopup pointer" onclick="openCheckin(${value.idTipoQuarto},false)">
                </td>
                </tr>
                `;
            });
        $('.TipoQuarto').remove();
        $('#listerCheckin').append(tr);
    },
        error: function() {
            alert('Tipos de quarto indisponiveis')
        }
    })
}
function padTo2Digits(num) {
    return num.toString().padStart(2, '0');
}
function formatDate(date){
    return [
        date.getFullYear(),
        padTo2Digits(date.getMonth() + 1),
        padTo2Digits(date.getDate()),
      ].join('-');
}
function carregarQuartosCodigo(){
    const codigoReserva = document.querySelector('#codigoReserva').value;
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/pesquisarReservaEfetivada/"+parseInt(codigoReserva),
        success: function (data) {
            const listaQuartos = data[0];
            const listaQuartosBoolean = data[2];
            const Reserva = data[1];  
            let tamanhoListaQuartos = 0;
            const idtitulo = listaQuartos[0].tipoQuarto.idTipoQuarto;
            var ColecaoQuartosTipo = []
            const corte = 5;
            let conteudoTotal = "";
            var conteudoColuna = ""
            for (let i = 0; i < listaQuartos.length; i = i + corte) {
            ColecaoQuartosTipo.push(listaQuartos.slice(i, i + corte));
            }
            ColecaoQuartosTipo.forEach(fileiraQuarto=>{
                conteudoColuna = ""
                conteudoColuna+=`<div class="lineCheckin">`
                fileiraQuarto.forEach(function(quartoV,index){
                            var checkQuarto1 = listaQuartosBoolean[tamanhoListaQuartos] ? 'disponivel' : 'indisponivel'
                            var checkQuarto2 = listaQuartosBoolean[tamanhoListaQuartos] ? 'buttonPopupCheckinSuccess' : 'buttonPopupCheckinFailed'
                            conteudoColuna+=` 
                            <div class="quartoCheckin">
                            <div class="quartoCheckinTitulo">${quartoV.numero}</div>
                            <button class="buttonPopupCheckin ${checkQuarto2}" onclick="OpenPopupCheckin(${listaQuartosBoolean[tamanhoListaQuartos]},${quartoV.idQuarto},${Reserva.codigoReserva})">${checkQuarto1}</button>
                            </div>`
                            if((fileiraQuarto.length-1) === index){
                            conteudoColuna+="</div>"
                            }
                            tamanhoListaQuartos+=1;
                })
                conteudoTotal+=conteudoColuna;
                })
            //console.log(conteudoTotal);
            $('#contentCheckin').html(conteudoTotal);
            var quantidadeQuartoFalse;
            function verificaQuantidadeQuartosBoolean(){
                let quantidade = 0;
                listaQuartosBoolean.forEach(e =>{
                    if(!(e===false)){
                    quantidade++;
                    }
                    
                })
                return quantidade;
            }
            if (verificaQuantidadeQuartosBoolean()===0) {
                alert('Todos Quartos ocupados ou seu codigo com data invalida')
            }
            openCheckin(idtitulo,true);
            },
            error: function() {
                alert('codigo não encontrado');
        }
    })
}
function checkinQuarto(idquarto,codigoReserva){
    $.ajax({
        type: "GET",
        url: `/checkin/?codigoReserva=${parseInt(codigoReserva)}&idquarto=${parseInt(idquarto)}`,
        dataType: "json",
        success: function () {
            closePopupCheckin();
        }
    });
    closePopupCheckin();
    // try {  
    // $.post("http://localhost:8089/checkin",{codigoReserva:parseInt(codigoReserva),idquarto:idquarto}, function(){
    //     closePopupCheckin();
    // })
    // } catch (error) {
    //     alert('erro ao liberar quarto')
    // }
}
function OpenPopupCheckin(tipoPopup,idquarto,codigoReserva){
        if(tipoPopup){
        const popupCheckinSalvar = document.getElementById('popupSalvarCheckin');
        const botaoCheckinSalvar = document.getElementById('botaoCheckinSalvar');
        popupCheckinSalvar.removeAttribute('idquarto');
        popupCheckinSalvar.setAttribute('idquarto',idquarto);
        popupCheckinSalvar.setAttribute('status',tipoPopup);
        popupCheckinSalvar.setAttribute('codigoReserva',codigoReserva);
        popupCheckinSalvar.style.visibility = 'visible';
        popupCheckinSalvar.style.display = 'block';
        botaoCheckinSalvar.setAttribute('onclick',`checkinQuarto(${idquarto},${codigoReserva})`);
        }else{
            alert('Quarto Ocupado')
        }
}
function closePopupCheckin(){
    const popupCheckinSalvar = document.getElementById('popupSalvarCheckin');
    const checkinPopup = document.getElementById('check-in-Popup')
    popupCheckinSalvar.removeAttribute('idquarto');
    popupCheckinSalvar.style.visibility = 'hidden';
    popupCheckinSalvar.style.display = 'none';
    popupCheckinSalvar.idquarto = '';
    popupCheckinSalvar.codigoreserva = ''
    checkinPopup.style.display = 'none'
    checkinPopup.style.visibility = 'hidden';
    checkinPopup.idtipoquarto = ''
    ocultarPopup()
    $('#codigoReserva').val('')
    alert('check-in concluido')
}