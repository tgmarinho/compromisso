package ta.uniderp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ta.uniderp.db.Conexao;
import ta.uniderp.pojo.Compromisso;

@WebServlet("/calendarioServlet")
public class CalendarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Conexao conn;
	
	public CalendarioServlet() throws ClassNotFoundException, SQLException {
		this.conn = new Conexao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getSession().getAttribute("acao");
		
		if(acao == "excluir") {
			System.out.println("opa entrei aqui");
			String data = (String) request.getParameter("data");
			String descricao = (String) request.getParameter("descricao");
			if(data != null || descricao != null) {
				Compromisso c = new Compromisso();
				c.setData(formataData(data));
				excluir(c);
				// Terminou de excluir redireciono o usuário para tela principal
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			
		} else if (acao == "alterar") {
			System.out.println("opa entrei aqui");
			String data = (String) request.getParameter("data");
			String descricao = (String) request.getParameter("descricao");
			if(data != null || descricao != null) {
				Compromisso c = new Compromisso();
				c.setData(formataData(data));
				c.setDescricao(descricao);
				alterar(c);
				// Terminou de alterar redireciono o usuário para tela principal
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			
		} else if(acao == "cadastrar") {
			System.out.println("opa entrei aqui");
			String data = (String) request.getParameter("data");
			String descricao = (String) request.getParameter("descricao");
			if(data != null || descricao != null) {
				Compromisso c = new Compromisso();
				c.setData(formataData(data));
				c.setDescricao(descricao);
				cadastrar(c);
				// Terminou de cadastrar redireciono o usuário para tela principal
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			} else {
				System.err.println("data " + data 
						+ "descricao "  + descricao);
			}
			 
		} else if(acao  == null) {
			System.out.println("affffff acao não pode ser nula programador");
		}
	}
	
	private void excluir(Compromisso c) {
		try {
			this.conn.delete(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void alterar(Compromisso c) {
		try {
			this.conn.update(c);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void cadastrar(Compromisso c) {
		try {
			this.conn.insert(c);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// Formato a data para deixar padrão para o SGBD PostgreSql
	private Date formataData(String data) {
		String strDia = data.substring(0, 2);
		String strMes = data.substring(3, 5);
		String strAno = data.substring(6, 10);
		int dia = Integer.parseInt(strDia);
		int mes = Integer.parseInt(strMes) - 1;
		int ano = Integer.parseInt(strAno);
		Calendar c = Calendar.getInstance();
		c.set(ano, mes, dia);
		return c.getTime();
	}

}
