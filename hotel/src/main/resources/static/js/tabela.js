function mostrarPopup(){
    const listaOpen = Array.from(document.querySelectorAll('.botaoPopup'))
    listaOpen.forEach(open => {
        document.querySelector('.popup').classList.add('show');
    }) 
}
// o parametro id é o ID do <a id="">
function ocultarPopup(){
   const listaClose = Array.from(document.querySelectorAll('.close'));
    listaClose.forEach(close => {
        document.querySelector('.popup').classList.remove('show');
    }) 
}
function editarPopup(){
    mostrarPopup();
    const listaEditar = Array.from(document.querySelectorAll('.editarPopup'));
    listaEditar.forEach(tt => function(){
        document.querySelector('.')
    })
}
function gerarQNTpessoas(){
    const camaSolteiro = parseInt($("#quantidadeCamaSolteiro").val());
    const camaCasal = parseInt($("#quantidadeCamaCasal").val());
    document.getElementById('numeroPessoas').value = camaSolteiro+camaCasal;
}



