<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastrar Compromisso</title>
</head>
<body>

<h3 align="center">Cadastro de Compromisso</h3>
	<form action="calendarioServlet" method="post">
		<table align="center" border="1">
			<tr>
				<td align="center">Data</td>
				<td align="center">Descrição</td>
			</tr>
			<tr>
				<td><input type="text" name="data" /></td>
				<td><input type="text" name="descricao" /></td>
				<td><input type="submit" value="Cadastrar" name="btnAction" /></td>
			</tr>
		</table>
	</form>

	<p align="center"><a href="index.jsp">Início</a></p>

 <% request.getSession().setAttribute("acao", "cadastrar");  %>	

</body>
</html>