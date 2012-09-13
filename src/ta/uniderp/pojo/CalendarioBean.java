package ta.uniderp.pojo;

import java.util.Date;

public class CalendarioBean {

	private String descricao;
	private Date data;
	
	
	public CalendarioBean() {}

	
	// -- getters and setters 
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
}
