
<%@page import="java.io.Console"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="ta.uniderp.db.Conexao"%>
<%@page import="ta.uniderp.pojo.Compromisso"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Compromissos</title>
<link href="style.css" rel="stylesheet" type="text/css" charset="utf-8" />
</head>
<body bgcolor="#3366CC">
       
       <% 
       	  Date dataHoje = new Date();
          SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
       %>
       
        <h1 align="center">Agenda de Compromissos - Hoje: <%=sdformat.format(dataHoje)%></h1>
        <center>
        <table width="300" border="1" cellspacing="0" cellpadding="0"  bgcolor="#C9C9C9">
  			<tr>
   			 <td width="150" align="center"><a href="index.jsp">Início</a></td>
   			 <td width="150" align="center"><a href="cadastrar.jsp">Cadastrar</a></td>
  			</tr>
            </table>
            <br/>
        <table width="950" border="1"  cellspacing="0" cellpadding="0" onmouseup="bgcolor:#C9C9C9" >
            <tr>
                <td width="100"  align="center" bgcolor="silver">Data</td>
                <td width="300"  align="center" bgcolor="silver">Descrição</td>
                <td width="300"  align="center" bgcolor="silver">Dia da Semana</td>
                <td width="200"  align="center" bgcolor="silver">Dias faltantes</td>
                <td width="150"  align="center" bgcolor="silver">Opções</td>
                
            </tr>
            <%
            Conexao conn = new Conexao();
        	List<Compromisso> compromissos = conn.findAll();
        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
           
            for(Compromisso compromisso : compromissos) {
       		   String dataFormatada = sdf.format(compromisso.getData());
       		   
               %>
               <tr style="cursor:default" onMouseOver="javascript:this.style.backgroundColor='#C0B085'" onMouseOut="javascript:this.style.backgroundColor=''">
                <td align="center"><%=dataFormatada%></td>
                <td align="center"><%=compromisso.getDescricao()%></td>
                <td align="center"><%=compromisso.getDiaSemana()%></td>
                <td align="center"><%=compromisso.getQuantidadeDias()%></td>
                 <td bgcolor="#C9C9C9">
	                <a href="alterar.jsp?data=<%=dataFormatada%>&descricao=<%=compromisso.getDescricao()%>" name="alterar" id="alt">Alterar/</a>
	                <a href="excluir.jsp?data=<%=dataFormatada%>&descricao=<%=compromisso.getDescricao()%>" name="excluir" id="exc">Excluir</a>
	            </td>
            </tr>
            <%
            }
           %>
                   
            
            
        </table>
        </center>
    </body>

</html>