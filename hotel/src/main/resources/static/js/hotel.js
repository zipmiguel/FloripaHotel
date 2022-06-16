function pad(valor) { // completa com zeros à esquerda, caso necessário
    return valor.toString().padStart(2, '0');
}

function formataData(data) {
    return `${data.getFullYear()}-${pad(data.getMonth() + 1)}-${pad(data.getDate())}`;
}

window.onload = function() {
const listaInputDate = document.querySelectorAll('.dataReserva');
for (let index = 0; index < listaInputDate.length; index++) {
    let data = new Date(); // data de hoje
    listaInputDate[index].min = formataData(data)
    data.setFullYear(data.getFullYear()+2)
    listaInputDate[index].max = formataData(data)
    }
}