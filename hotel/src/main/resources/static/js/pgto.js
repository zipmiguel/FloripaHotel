function carregarDadosReserva(){
    const tipoQuarto = JSON.parse(sessionStorage.getItem('reserva'))
    const dataEntrada = new Date(sessionStorage.getItem('dataEntrada')+' 12:00:00')
    const dataSaida = new Date(sessionStorage.getItem('dataSaida')+' 12:00:00')
    const nAdultos = sessionStorage.getItem('adultos')
    const nCriancas = sessionStorage.getItem('criancas')
    const nQuartos = sessionStorage.getItem('qntdQuartos')
    var diasSemana = ["domingo", "segunda", "terça", "quarta", "quinta", "sexta", "sábado"]

    const diaSemanaEntrada = diasSemana[dataEntrada.getDay()]
    const diaSemanaSaida = diasSemana[dataSaida.getDay()]
    var diferenca = dataSaida.getTime() - dataEntrada.getTime()
    var diferencaEmDias = diferenca / (1000 * 3600 * 24);

    var precoTotal = 0
    document.querySelector("h2.info1").innerHTML = `${diaSemanaEntrada}, ${formatDate(dataEntrada)} - ${diaSemanaSaida}, ${formatDate(dataSaida)}`
    document.querySelector("h2.info2").innerHTML = `${nAdultos} Adulto(s), ${nCriancas} Criança(s)`
    document.querySelector("h2.info3").innerHTML = `${nQuartos} Quarto(s) ${tipoQuarto.tipoQuarto}`

    $.post("http://localhost:8089/diaria/"+tipoQuarto.idTipoQuarto,{
        }, function(Diaria){
            for(let i = 0; i < diferencaEmDias; i++){
                let diariaAtual = new Date(dataEntrada)
                diariaAtual.setDate(dataEntrada.getDate() + i)
                if(diariaAtual.getDay() == 0 || diariaAtual.getDay() == 6){
                    alert("entrou no fds ")
                    precoTotal += Diaria.fimDeSemana
                }else{
                    precoTotal += Diaria.diaUtil
                }
            }
            precoTotal *= nQuartos
            
            document.querySelector("h2.info4").innerHTML = `Total: R$ ${precoTotal}`      
    })
}

function padTo2Digits(num) {
    return num.toString().padStart(2, '0');
}
function formatDate(date) {
    return [
      padTo2Digits(date.getDate()),
      padTo2Digits(date.getMonth() + 1),
      date.getFullYear(),
    ].join('/');
}