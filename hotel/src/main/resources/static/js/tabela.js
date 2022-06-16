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
       mostrarPopup()
       const popupEditar = document.getElementById('editarPopup');
       popupEditar.removeAttribute('idquarto');
       popupEditar.setAttribute('idquarto',idquarto);
       popupEditar.style.visibility = 'visible';
       popupEditar.style.display = 'flex'
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
    const popupEditar = document.getElementById('editarPopup');
    popupEditar.style.visibility = 'hidden';
    popupEditar.style.display = 'none';
    ocultarPopup();
}
function gerarQNTpessoas(id){
    const NumeroPessoas = document.querySelector(`${id} input[disabled]`)
    NumeroPessoas.value = "";
    const listaCamas = document.querySelectorAll(`${id} select`)
    const camaSolteiro = parseInt($(listaCamas[0]).val());
    const camaCasal = parseInt($(listaCamas[1]).val());
    NumeroPessoas.value = camaSolteiro+(camaCasal*2);
}
function OpenDeletePopup(idquarto){
        mostrarPopup();
        const deletePopup = document.getElementById('deletePopup');
        deletePopup.removeAttribute('idquarto');
        deletePopup.setAttribute('idquarto',idquarto);
        deletePopup.style.visibility = 'visible';
        deletePopup.style.display = 'flex';
}
function CloseDeletePopup(){
    const deletePopup = document.getElementById('deletePopup');
    deletePopup.style.visibility = 'hidden';
    deletePopup.style.display = 'none';
    ocultarPopup();
}


