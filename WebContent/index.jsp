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
       
        <h1>Listagem de alunos</h1>
        <table>
            <tr>
                <td width="200" bgcolor="silver">RA</td>
                <td width="600" bgcolor="silver">Nome</td>
                <td width="600" bgcolor="silver">N1</td>
                <td width="600" bgcolor="silver">N2</td>
                
            </tr>
            <%
            Conexao conn = new Conexao();
        	List<Compromisso> compromissos = conn.findAll();
        	
           
            for(Compromisso compromisso : compromissos) {
               %>
               <tr>
                <td><%=compromisso.getRa()%></td>
                <td><%=compromisso.getNome()%></td>
                <td><%=compromisso.getN1()%></td>
                <td><%=compromisso.getN2()%></td>
                
            </tr>
            <%
            }
           %>
            
        </table>
    </body>

</html>