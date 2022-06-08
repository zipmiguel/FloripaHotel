function Logout(){
    document.getElementById('logout').addEventListener('click', function(){
        localStorage.clear();
        window.location.replace('http://localhost:8089/')
    })
}