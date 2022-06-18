function Logout(){
    document.getElementById('logout').addEventListener('click', function(){
        localStorage.clear();
        window.location.replace('http://localhost:8089/')
    })
}
function checkUser(){
    const divLoginLogout = document.querySelector('.nomeSair');
    try {
    if (localStorage.getItem('user') != null || localStorage.getItem('user') != undefined) {
        const user = JSON.parse(localStorage.getItem("user"))
        divLoginLogout.innerHTML = `<h3 class="negrito branco">${user.nome}/h3> <button class="pointer" onclick="Logout()" id="logout" >Logout</button>`
    }else{
        divLoginLogout.innerHTML = `<div class="nav-right"> <a href="./index.html#reserva" class="primary-btn">Faça sua Reserva</a> <a href="./loginUsuario.html" id="button-login">Login</a></div>`
    }
    } catch (error) {
        divLoginLogout.innerHTML = `<div class="nav-right"> <a href="./index.html#reserva" class="primary-btn">Faça sua Reserva</a> <a href="./loginUsuario.html" id="button-login">Login</a></div>`
    }  
}
