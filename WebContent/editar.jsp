<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.JavaBeans"%>

<%
	String idcon = (String) request.getAttribute("idcon");
	String nome = (String) request.getAttribute("nome");
	String fone = (String) request.getAttribute("fone");
	String email = (String) request.getAttribute("email");

%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Contato <%=idcon %></h1>
	
	<form name="frmContato" action="update">
		<label for="idcon">
			<span>ID</span>
		</label>
		<br />
		<input type="text" required="required" value="<%=idcon %>" name="idcon" placeholder="ID" readonly>

		<br />
		
		<label for="name">
			<span>Nome</span>
		</label>
		<br />
		<input type="text" required="required" value="<%=nome %>" name="nome" placeholder="Nome">
		
		<br />
		
		<label for="fone">
			<span>Telefone</span>
		</label>
		<br />
		<input type="text" required="required" value="<%=fone %>" name="fone" placeholder="Telefone">
		
		<br />
		
		<label for="email">
			<span>E-mail</span>
		</label>
		<br />
		<input type="email" name="email" value="<%=email %>" placeholder="E-mail">
		
		<br />
		
		<button class="btn" style="margin-top: 12px;" type="submit">Atualizar contato</button>
	</form>
</body>
</html>