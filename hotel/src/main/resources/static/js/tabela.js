function mostrarPopup(){
    const listaOpen = Array.from(document.querySelectorAll('.botaoPopup'))
    listaOpen.forEach(open => {
        document.querySelector('.popup').style.display = "flex";
    }) 
}
// o parametro id é o ID do <a id="">
function ocultarPopup(){
   const listaClose = Array.from(document.querySelectorAll('.close'));
    listaClose.forEach(close => {
        document.querySelector('.popup').style.display = "none";
    }) 
}


