package ta.uniderp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		
		if(acao == "Excluir") {
			
		} else if (acao == "alterar") {
			System.out.println("opa entrei aqui");
			String data = (String) request.getParameter("data");
			String descricao = (String) request.getParameter("descricao");
			if(data != null || descricao != null) {
				Compromisso c = new Compromisso();
				c.setDescricao(descricao);
				alterar(c);
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
			} else {
				System.err.println("data " + data 
						+ "descricao "  + descricao);
			}
			 
		} else if(acao  == null) {
			System.out.println("affffff acao é null");
		}
		
		
		
	}
	
	
	private void alterar(Compromisso c) {
		try {
			this.conn.update(c);
//			String a;
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

	private Date formataData(String data) {
		try{
		System.err.println("formataData() - 74");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		@SuppressWarnings("deprecation")
		Date dataFormatada = new Date(data);
		sdf.format(dataFormatada);
		System.out.println(dataFormatada);
		return dataFormatada; 
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

}
