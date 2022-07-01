function pegarValores(){
    var dataEntradaV = $("#dataEntrada").val()
    var dataSaidaV = $("#dataSaida").val()
    var nAdultos = $("#adultos").val()
    var nCriancas = $("#criancas").val()
    var totalPessoas = parseInt(nAdultos) + parseInt(nCriancas)

    return [dataEntradaV,dataSaidaV,nAdultos,nCriancas,totalPessoas]
}

function carregarDadosReserva(){
    $("#dataEntrada").val(sessionStorage.getItem('dataEntrada'))
    $("#dataSaida").val(sessionStorage.getItem('dataSaida'))
    $("#adultos").val(sessionStorage.getItem('adultos'))
    $("#criancas").val(sessionStorage.getItem('criancas'))
    verificarDisponibilidade()
}

function verificarReservaIndex(){
    atualizarSession()
    window.location.href = "http://localhost:8089/reservaUsuario"
}

function atualizarSession(){
    [dataEntradaV,dataSaidaV,nAdultos,nCriancas,totalPessoas] = pegarValores()
    sessionStorage.setItem('dataEntrada',dataEntradaV)
    sessionStorage.setItem('dataSaida',dataSaidaV)
    sessionStorage.setItem('adultos',nAdultos)
    sessionStorage.setItem('criancas',nCriancas)
}

function verificarDisponibilidade(){

    [dataEntradaV,dataSaidaV,nAdultos,nCriancas,totalPessoas] = pegarValores()
    atualizarSession()
    if(dataEntradaV=="" || dataSaidaV=="" ||  nAdultos == 0){
        alert("Preencha todos os campos corretamente!")
    }else{
        $.post("http://localhost:8089/pesquisarReserva",{
            dataEntrada:dataEntradaV,dataSaida:dataSaidaV,qntdPessoas:parseInt(totalPessoas),qntdAdultos:nAdultos
        }, function(listaDispo){
            let conteudo = paginaDisponibilidadeReserva(listaDispo[0],totalPessoas,nAdultos,listaDispo[1])
            document.querySelector("#divReservaDisponivel").innerHTML = conteudo
        })
    }
}
function paginaDisponibilidadeReserva(listaDisponibilidade,totalPessoas,nAdultos,maximoQuartos){
    let divs = ""
    let i = 0
    for (const tipoQuarto of listaDisponibilidade) {
        
        minQuartos = Math.ceil(totalPessoas/tipoQuarto.numeroPessoas)
        let optionText = ""
        let final = maximoQuartos[i]
        if(maximoQuartos[i] > nAdultos){
            final = nAdultos
        }
        for (let i = minQuartos; i<=final; i++) {
            optionText+=`<option value="${i}">${i}</option>`
        }

        divs += `<div class="row">`+
        `<div class="col-lg-6">`+
        `<div class="ri-slider-item">`+
        `<div class="">`+
        `<div class="single-img set-bg"><img style="width: 850px; height: 750px;" src="http://localhost:8089/tipoQuarto/imagem/`+tipoQuarto.idTipoQuarto+`"></img></div>`+
        // `<div class="single-img set-bg"><img src="http://localhost:8089/tipoQuarto/imagem/`+tipoQuarto.idTipoQuarto+`"></img></div>`+
        `</div>`+
        `</div>`+
        `</div>`+
        `<div class="col-lg-6">`+
        `<div class="ri-text">`+
        `<div class="section-title">`+
        `<div class="section-title">`+
        `<span>acomodação confortável que cabe no seu bolso</span>`+
        `<h2>`+tipoQuarto.tipoQuarto+`</h2>`+
        `</div>`+
        `<p>Para você que quer economizar e desfrutar de toda a infraestrutura do hotel, essa é a escolha perfeita! Possui tudo que você precisa por um preço que cabe no seu bolso. Não deixa nada a desejar e ainda não pesa nos custos de sua viagem!!!<br/><br/>`+
        `As suítes <a class="negrito" style="font-weight: 700;">`+tipoQuarto.tipoQuarto+`</a> possuem `+tipoQuarto.quantidadeCamaSolteiro+` camas de solteiro, `+tipoQuarto.quantidadeCamaCasal+` camas de casal e recebem até `+tipoQuarto.numeroPessoas+` pessoas!</p>`+
        `<div class="ri-features">`+
        `<div class="ri-info">`+
        `<i class="flaticon-019-television"></i>`+
        `<p>Smart TV</p>`+
        `</div>`+
        `<div class="ri-info">`+
        `<i class="flaticon-029-wifi"></i>`+
        `<p>Wi-fi</p>`+
        `</div>`+
        `<div class="ri-info">`+
        `<i class="flaticon-003-air-conditioner"></i>`+
        `<p>AC</p>`+
        `</div>`+
        `</div>`+
        `<select id="quantidadeQuartos" class="select`+tipoQuarto.idTipoQuarto+`" name="selectQuantidadeQuartos" class="pointer">`+
        `<option value="0" selected>Quantidade de Quartos</option>`+
        optionText+
        `</select>`+
        `<button onclick="atualizarSessaoPgto(`+tipoQuarto.idTipoQuarto+`)" class="primary-btn" style="border:none;">Reservar Agora</button>`+
        `</div>`+
        `</div>`+
        `</div>`+
        `</div>`+
        `<hr/>`
        i++
    }
    return divs
}
function atualizarSessaoPgto(id){
    sessionStorage.setItem('dataEntrada',dataEntradaV)
    if($(".select"+id).val() == 0){
        alert("Insira uma quantidade de quartos válida!")
    }else{
        $.post("http://localhost:8089/tipoQuarto/"+id,{
            }, function(tipoQuarto){
                sessionStorage.setItem('tipoQuarto',JSON.stringify(tipoQuarto))
                sessionStorage.setItem('qntdQuartos',$(".select"+id).val())
                window.location.href = "http://localhost:8089/pagamentoUsuario"
        })
    }
}