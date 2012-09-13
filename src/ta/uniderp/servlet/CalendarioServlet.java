package ta.uniderp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ta.uniderp.service.CalendarioService;

@WebServlet("/calendarioServlet")
public class CalendarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	enum acao {
		EXCLUIR, ALTERAR; 
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getAttribute("btnAction");
		CalendarioService cal = new CalendarioService();
		
		
		
		// chamada bd
		if(acao == "excluir") {
			
			//cal.removeCompromisso(data);
			
		} else if (acao == "alterar") {
			
//			cal.atualizaCompromisso(pk, data, descricao);
			
		} else if(acao == "cadastrar") {
			
//			cal.insereCompromisso(data, descricao);
		
		}
		
		
	}

}
