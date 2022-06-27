/*const ButtonAtualizar = document.createElement('input')
ButtonAtualizar.type = "button"
ButtonAtualizar.value = "atualizar"
ButtonAtualizar.classList.add('negrito botaoAtualizarCadastro pointer')
*/
function carregarCargos(){
    $.ajax({
        type: "GET",
        url: "/listaCargos",
        dataType: "json",
        success: function (response) {
            var opcoesCargo = ""
            response.forEach(element => {
                let tipoCargo;
                if (element.permissao==true) {
                    tipoCargo = "Admin"
                }else{tipoCargo= "Comum"}
                opcoesCargo += `<option value=${element.idCargo}>${element.tipoCargo}:${tipoCargo}</option>`
            });
            $("#idCargo").html(opcoesCargo)
        }
    });
}
$(document).ready(function () {
    carregarCargos();
})
function pesquisaCpf(){
$.ajax({
    type: "get",
    url: `/funcionario?cpf=${$('#campoPesquisaCPF').val()}`,
    dataType: "json",
    success: function (response) {
        $("#cadastro").attr('idFuncionario',response.idFuncionario)
        const cargo = document.getElementById('idCargo')
        const nome = $("#nome");
        const telefone = $("#telefone");
        const cpf = $("#cpf");
        const endereco = $('#endereco');
        const email = $('#email');
        const senha = $('#senha');
        const confirmarSenha = $('#confirmarSenha')
        //Insert
        nome.val(response.nome);
        telefone.val(response.telefone);
        cpf.val(response.cpf);
        endereco.val(response.endereco);
        email.val(response.email);
        senha.val(response.senha);
        confirmarSenha.val(response.senha);
        cargo.value = response.cargo.idCargo;

    },
    error:function(response){
        alert('Funcionario não encontrado');
    }
    })
}
function deletarFuncionario(){
   const idFuncionario = document.getElementById('cadastro').idFuncionario;
    if (idFuncionario!=undefined||idFuncionario!=null) {
        $.ajax({
            type: "delete",
            url: `/funcionario?idFuncionario=${idFuncionario}`,
            dataType: "dataType",
            success: function (response) {
              delete document.getElementById('cadastro').idFuncionario;
              alert('funcionario excluido com sucesso')
            },
            error: function(){
                alert('Erro ao deletar o funcionario')
            }
        });
    }else{
        alert('pesquise o funcionario');
    }
}
