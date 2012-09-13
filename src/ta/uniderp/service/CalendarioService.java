package ta.uniderp.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ta.uniderp.db.Conexao;

import com.mysql.jdbc.PreparedStatement;

public class CalendarioService {

	private Conexao bdSQL;
	 
	public CalendarioService() {
		this.bdSQL = new Conexao();
	}

	public ResultSet listaCompromissos(Date data) throws SQLException{
		
		bdSQL.iniciar();
		String sql = "SELECT * FROM calendario WHERE data = " + formataData(data);
		Statement st = bdSQL.getConn().createStatement();
		ResultSet rs = st.executeQuery(sql);
		rs.close();
		st.close();
		bdSQL.fechar();
		return rs;
	}
	
	public void insereCompromisso(Date data, String descricao) throws SQLException {
	
		bdSQL.iniciar();
		Connection conn = bdSQL.getConn();
		String sql = "INSERT INTO calendario VALUES ( '?','?','?',? )";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setDate(0, (java.sql.Date) data);
		ps.setString(1, diaSemana(data));
		ps.setString(2, descricao);
		ps.setInt(3, quantidadaDias(data));
		ps.execute(sql);
		ps.close();
		conn.close();
		bdSQL.fechar();
		
	}
	
	public void atualizaCompromisso(Date pk, Date data, String descricao) throws SQLException {
		
		bdSQL.iniciar();
		Connection conn = bdSQL.getConn();
		String sql = "UPDATE calendario SET data=?, diaSemana=?, descricao=?, diasPassados=? WHERE data=?";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setDate(0, (java.sql.Date) data);
		ps.setString(1, diaSemana(data));
		ps.setString(2, descricao);
		ps.setInt(3, quantidadaDias(data));
		ps.setDate(4, (java.sql.Date) pk);
		ps.execute(sql);
		ps.close();
		conn.close();
		bdSQL.fechar();
		
	}
	
	public void removeCompromisso(Date data) throws SQLException {
		
		//DELETE FROM table_name. WHERE some_column = some_value.
		bdSQL.iniciar();
		Connection conn = bdSQL.getConn();
		String sql = "DELETE FROM compromisse WHERE data = " + formataData(data);
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.execute(sql);
		ps.close();
		conn.close();
		bdSQL.fechar();
	}
	
	public String formataData(Date dt) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
		return sdf.format(dt);
	}
	
	public int quantidadaDias(Date data) {
		Calendar diaHoje = Calendar.getInstance();
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		
		//TODO: remover esse comentario
		System.out.println(data);
		System.err.println(c.getTime());
		
		int diaAtual = diaHoje.get(Calendar.DATE); 
		c.add(diaAtual, c.get(Calendar.DATE));
		
		return c.get(Calendar.DATE);
		
	}
	
	// Saber o Dia da Semana
	public String diaSemana(Date data) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		int d = c.get(Calendar.DAY_OF_WEEK);
		String dia = "semDia";
		switch (d) {
			case 0:
				dia = "Dom";
				break;
			case 1:
				dia = "Seg";
				break;
			case 2:
				dia = "Ter";
				break;
			case 3:
				dia = "Qua";
				break;
			case 4:
				dia = "Qui";
				break;
			case 5:
				dia = "Sex";
				break;
			case 6:
				dia = "Sab";
				break;
		}
		
		return dia;

		
	}

	
	
}
