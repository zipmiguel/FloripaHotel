function verificarDisponibilidade(){
    const dataEntradaV = $("#dataEntrada").val()
    const dataSaidaV = $("#dataSaida").val()
    const nAdultos = $("#adultos").val()

    if(dataEntradaV=="" || dataSaidaV=="" ||  nAdultos == 0){
        alert("Preencha todos os campos corretamente!")
    }else{
        $.post("http://localhost:8089/pesquisarReserva",{
        dataEntrada:dataEntradaV,dataSaida:dataSaidaV
    }, function(hospede){
        
    })
    }
}