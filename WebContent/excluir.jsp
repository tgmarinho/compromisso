<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Excluir Compromisso</title>
</head>

<body bgcolor="#3366CC">

<h3 align="center">Exclusão de Compromisso</h3>
	<form action="calendarioServlet" method="post">
		<table align="center" border="1" bgcolor="#C9C9C9"cellspacing="0" cellpadding="0">
			<tr>
				<td align="center">Data</td>
				<td align="center">Descrição</td>
			</tr>
			<tr>
				<td><input type="text" readonly="readonly" name="data" value="<%=request.getParameter("data") %>" /></td>
				<td><input type="text" readonly="readonly" name="descricao" value="<%=request.getParameter("descricao")%>" /></td>
				<td><input type="submit" value="Excluir" name="btnAction" onclick="alert('Confirma a exclusão?')" /></td>
			</tr>
		</table>
	</form>

	<p align="center"><a href="index.jsp">Voltar ao Início</a></p>

 <% request.getSession().setAttribute("acao", "excluir");  %>	

</body>
</html>