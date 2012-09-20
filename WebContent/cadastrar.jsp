<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastrar Compromisso</title>
<link href="style.css" rel="stylesheet" type="text/css" charset="utf-8" />
</head>
<script> 
 function validaDat(campo,valor) {
	var date=valor;
	var ardt=new Array;
	var ExpReg=new RegExp("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}");
	ardt=date.split("/");
	erro=false;
	if ( date.search(ExpReg)==-1){
		erro = true;
		}
	else if (((ardt[1]==4)||(ardt[1]==6)||(ardt[1]==9)||(ardt[1]==11))&&(ardt[0]>30))
		erro = true;
	else if ( ardt[1]==2) {
		if ((ardt[0]>28)&&((ardt[2]%4)!=0))
			erro = true;
		if ((ardt[0]>29)&&((ardt[2]%4)==0))
			erro = true;
	}
	if (erro) {
		alert("\"" + valor + "\" não é uma data válida!!!");
		campo.focus();
		campo.value = "";
		return false;
	}
	return true;
}

 </script>
 <script language="javascript" type="text/javascript">
function validar() {
	var nome = frmCadastrar.descricao.value;
	
	if (nome == "") {
		alert('Preencha o campo para prosseguir com o cadastro!');
		frmCadastrar.descricao.focus();
		return false;
		}

}
</script>

<body bgcolor="#3366CC">

<h3 align="center">Cadastro de Compromisso</h3>
	<form action="calendarioServlet" method="post" name="frmCadastrar">
		<table onmousedown="bgcolor:#C9C9C9" align="center" border="1" bgcolor="#C9C9C9" cellspacing="0" cellpadding="0"  >
			<tr>
				<td align="center">Data</td>
				<td align="center">Descrição</td>
			</tr>
			<tr>
				<td><input type="text" name="data" onblur="validaDat(this,this.value)"/></td>
				<td><input type="text" name="descricao"/></td>
				<td><input type="submit" value="Cadastrar" name="btnAction" onclick="return validar()"/></td>
			</tr>
		</table>
	</form>

	<br/>
	<table align="center" border="1" bgcolor="#C9C9C9" cellspacing="0" cellpadding="0">
	<tr>
	<td align="center"><a href="index.jsp">Voltar ao Início</a></td>
	</tr>
	</table>

 <% request.getSession().setAttribute("acao", "cadastrar");  %>	

</body>
</html>