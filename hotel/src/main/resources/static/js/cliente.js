function esquecerSenha(){
    //Script para enviar email a fim de recuperar / alterar a senha atual
}
$(document).ready(function(){ 
    $(".cpf").mask("000.000.000-00")
    $(".telefone").mask("(00) 00000-0000")
    $(".numeroCartao").mask("0000 0000 0000 0000")
    $(".cvv").mask("000")
    $(".codigoRecebido").mask("AAAAAA")
});
// function emailMask(email) {
// 	var maskedEmail = email.replace(/([^@\.])/g, "*").split('');
// 	var previous	= "";
// 	for(i=0;i<maskedEmail.length;i++){
// 		if (i<=1 || previous == "." || previous == "@"){
// 			maskedEmail[i] = email[i];
// 		}
// 		previous = email[i];
// 	}
// 	return maskedEmail.join('');
// }