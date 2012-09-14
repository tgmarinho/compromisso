package ta.uniderp.pojo;

import java.util.Date;

public class Compromisso {

	private String descricao;
	private Date data;
	private String diaSemana;
	private int quantidadeDias;
	
	
	public Compromisso() {}

	
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

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setQuantidadeDias(int quantidadeDias) {
		this.quantidadeDias = quantidadeDias;
	}

	public int getQuantidadeDias() {
		return quantidadeDias;
	}
	
}
