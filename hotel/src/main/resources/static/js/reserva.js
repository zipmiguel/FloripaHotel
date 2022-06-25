$(document).ready(function(){
    if(sessionStorage.getItem('dataEntrada') != null){
        $("#dataEntrada").val(sessionStorage.getItem('dataEntrada'))
        $("#dataSaida").val(sessionStorage.getItem('dataSaida'))
        $("#adultos").val(sessionStorage.getItem('adultos'))
        $("#criancas").val(sessionStorage.getItem('criancas'))
    }
});
function verificarDisponibilidade(){
    
    const dataEntradaV = $("#dataEntrada").val()
    const dataSaidaV = $("#dataSaida").val()
    const nAdultos = $("#adultos").val()
    const nCriancas = $("#criancas").val()
    const totalPessoas = parseInt(nAdultos) + parseInt(nCriancas)
    
    sessionStorage.setItem('dataEntrada',dataEntradaV)
    sessionStorage.setItem('dataSaida',dataSaidaV)
    sessionStorage.setItem('adultos',nAdultos)
    sessionStorage.setItem('criancas',nCriancas)

    if(dataEntradaV=="" || dataSaidaV=="" ||  nAdultos == 0){
        alert("Preencha todos os campos corretamente!")
    }else{
        $.post("http://localhost:8089/pesquisarReserva",{
            dataEntrada:dataEntradaV,dataSaida:dataSaidaV,total:totalPessoas
        }, function(listaDispo){
            let conteudo = paginaDisponibilidadeReserva(listaDispo)
            document.querySelector("#divReservaDisponivel").innerHTML = conteudo
        })
    }
}
function paginaDisponibilidadeReserva(listaDisponibilidade){
    let divs = ""
    for (const tipoQuarto of listaDisponibilidade) {
        divs += `<div class="row">`+
        `<div class="col-lg-6">`+
        `<div class="ri-slider-item">`+
        `<div class="ri-sliders owl-carousel">`+
        `<div class="single-img set-bg" data-setbg="../js/img/rooms/room-standard-1.jpg"></div>`+
        `<div class="single-img set-bg" data-setbg="../js/img/rooms/room-standard-2.jpg"></div>`+
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
        `<select id="quantidadeQuartos" name="selectQuantidadeQuartos" class="pointer">`+
        `<option value="0" selected>Quantidade de Quartos</option>`+
        `<option value="1">1</option>`+
        `<option value="2">2</option>`+
        `<option value="3">3</option>`+
        `<option value="4">4</option>`+
        `<option value="5">5</option>`+
        `<option value="6">6</option>`+
        `<option value="7">7</option>`+
        `<option value="8">8</option>`+
        `<option value="9">9</option>`+
        `<option value="10">10</option>`+
        `</select>`+
        `<a href="#" class="primary-btn">Reservar Agora</a>`+
        `</div>`+
        `</div>`+
        `</div>`+
        `</div>`+
        `<hr/>`
    }
    return divs
}