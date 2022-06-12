// feito por luiz eduardo sagaz carvalho


function mostrarPopup(){
    const listaOpen = Array.from(document.querySelectorAll('.botaoPopup'))
    listaOpen.forEach(open => {
        document.querySelector('.popup').classList.add('show');
    }) 
}
function ocultarPopup(){
   const listaClose = Array.from(document.querySelectorAll('.close'));
    listaClose.forEach(close => {
        document.querySelector('.popup').classList.remove('show');
    }) 
}
function OpenEditPopup(idquarto){
       const popupEditar = document.getElementById('editarPopup');
       popupEditar.removeAttribute('idquarto');
       popupEditar.setAttribute('idquarto',idquarto);
       mostrarPopup()
}
function CloseEditPopup(){
    const listaDiv = Array.from(document.querySelectorAll('#editarPopup div'));
    for (let i = 0; i < listaDiv.length; i++) {
        const divAtual = listaDiv[i].children[1];
        console.log(divAtual.tagName);
        switch (divAtual.tagName) {
            case 'INPUT':
                divAtual.value = ""; 
            break;
                
            case 'SELECT':
                const tipoSelect = divAtual.id;
                document.getElementById(tipoSelect).value = divAtual.children[0].value;
                break;
            }
    }
    ocultarPopup();
}
function gerarQNTpessoas(){
    const camaSolteiro = parseInt($("#quantidadeCamaSolteiro").val());
    const camaCasal = parseInt($("#quantidadeCamaCasal").val());
    document.getElementById('numeroPessoas').value = camaSolteiro+camaCasal;
}


