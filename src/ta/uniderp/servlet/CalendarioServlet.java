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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String acao = (String) request.getAttribute("btnAction");
		
		
		// chamada bd
		if(acao == "excluir") {
			
			//cal.removeCompromisso(data);
			
		} else if (acao == "alterar") {
			
//			cal.atualizaCompromisso(pk, data, descricao);
			
		} else if(acao == "Cadastrar") {
			
			String data = (String) request.getAttribute("data");
			String descricao = (String) request.getAttribute("descricao");
			Compromisso c = new Compromisso();
			c.setData(formataData(data));
			c.setDescricao(descricao);
			
			cadastrar(c);
			
			
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.format(data);
		@SuppressWarnings("deprecation")
		Date dataFormatada = new Date(data);
		return dataFormatada;
	}

}
