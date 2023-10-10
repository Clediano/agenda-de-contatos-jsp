<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="model.JavaBeans"%>
<%@page import="java.util.ArrayList"%>

<%
	ArrayList<JavaBeans> contatos = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de contatos</h1>
	<a href="novo.html" class="btn">Novo contato</a>
	<a href="report" class="btn">Imprimir</a>
	
	<table id="tabela">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<%for (int i = 0; i < contatos.size(); i++) { %>
			<tr>
				<td><%=contatos.get(i).getIdcon() %></td>
				<td><%=contatos.get(i).getNome() %></td>
				<td><%=contatos.get(i).getFone() %></td>
				<td><%=contatos.get(i).getEmail() %></td>
				<td>
					<a class="btn" href="select?idcon=<%=contatos.get(i).getIdcon() %>">
						Editar
					</a>
				</td>
				<td>
					<a class="btn btn-danger" href="javascript: confirmar(<%=contatos.get(i).getIdcon() %>)">
						Excluir
					</a>
				</td>
			</tr>
			<%} %>
		</tbody>
	</table>
	<script type="text/javascript" src="scripts/confirmador.js"></script>
</body>
</html>