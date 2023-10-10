function confirmar(idcon) {
	let resposta = confirm('Tem certeza que deseja excluir este item?')
	
	if(resposta) {
		window.location.href = "delete?idcon=" + idcon
	}
}