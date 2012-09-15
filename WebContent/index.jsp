
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
</head>
<body>
       
       <% 
       	  Date dataHoje = new Date();
          SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
       %>
       
        <h1 align="center">Agenda de Compromissos - Hoje: <%=sdformat.format(dataHoje)%></h1>
        <table>
            <tr>
                <td width="200" bgcolor="silver">Data</td>
                <td width="600" bgcolor="silver">Descrição</td>
                <td width="600" bgcolor="silver">Dia da Semana</td>
                <td width="600" bgcolor="silver">Prazo</td>
                <td width="600" bgcolor="silver">Alterar/Excluir</td>
                
            </tr>
            <%
            Conexao conn = new Conexao();
        	List<Compromisso> compromissos = conn.findAll();
        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
           
            for(Compromisso compromisso : compromissos) {
       		   String dataFormatada = sdf.format(compromisso.getData());
       		   
               %>
               <tr>
                <td><%=dataFormatada%></td>
                <td><%=compromisso.getDescricao()%></td>
                <td><%=compromisso.getDiaSemana()%></td>
                <td><%=compromisso.getQuantidadeDias()%></td>
                 <td width="600" bgcolor="silver">
	                <a href="alterar.jsp?data=<%=dataFormatada%>&descricao=<%=compromisso.getDescricao()%>" name="alterar" id="alt">Alterar/</a>
	                <a href="excluir.jsp?data=<%=dataFormatada%>&descricao=<%=compromisso.getDescricao()%>" name="excluir" id="exc">Excluir</a>
	            </td>
            </tr>
            <%
            }
           %>
            
           <br> 
           <p align="center"><a href="index.jsp">Início</a></p>
           <p align="center"><a href="cadastrar.jsp">Cadastrar</a></p>
            
            
            
        </table>
    </body>

</html>