package ta.uniderp.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ta.uniderp.pojo.Compromisso;

public class Conexao {

	private String driver;
	private String url;
	private String user;
	private String password;
	private Connection conn;
	private Statement st;
	private PreparedStatement ps;

	public Conexao() throws ClassNotFoundException, SQLException {

		this.driver = "org.postgresql.Driver";
		this.url = "jdbc:postgresql:compromisse";
		this.user = "postgres";
		this.password = "postgres";
		Class.forName(driver);
		this.conn = DriverManager.getConnection(url, user, password);
	}

	public void insert(Compromisso compromisso) throws SQLException, ParseException {
		String sql = "INSERT INTO compromisso VALUES ( '?','?','?',? )";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setDate(0, (Date) compromisso.getData());
		ps.setString(1, diaSemana((Date) compromisso.getData()));
		ps.setString(2, compromisso.getDescricao());
		ps.setInt(3, quantidadaDias((Date) compromisso.getData()));
		ps.executeQuery();
		ps.close();
		conn.close();
	}

	public void delete(Compromisso compromisso) throws SQLException {
		// DELETE FROM table_name wHERE some_column=some_value

		String sql = "DELETE FROM compromisso WHERE data = ?";
		ps = conn.prepareStatement(sql);
		ps.setDate(1, (Date) compromisso.getData());
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public void update(Date pk, Compromisso compromisso) throws SQLException, ParseException {

		// UPDATE table_name SET column1=value, column2=value2,... WHERE
		// some_column=some_value
		String sql = "UPDATE compromisso SET data=?, diaSemana=?, descricao=?, quantidadeDias=? WHERE data=?";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setDate(0, (Date) compromisso.getData());
		ps.setString(1, diaSemana((Date) compromisso.getData()));
		ps.setString(2, compromisso.getDescricao());
		ps.setInt(3, quantidadaDias((Date) compromisso.getData()));
		ps.setDate(4, (Date) pk);
		ps.execute(sql);
		ps.close();
		conn.close();

	}

	public List<Compromisso> findAll() throws SQLException, ParseException {
		this.st = conn.createStatement();
		String sql = "select * from compromisso";
		ResultSet rs = st.executeQuery(sql);

		List<Compromisso> compromissos = new ArrayList<Compromisso>();
		while (rs.next()) {
			Compromisso c = new Compromisso();

			c.setData(rs.getDate("data"));
			c.setDescricao(rs.getString("descricao"));
			c.setDiaSemana(diaSemana(rs.getDate("data")));
			c.setQuantidadeDias(quantidadaDias(rs.getDate("data")));
			compromissos.add(c);
		}

		return compromissos;
	}

	// Se eu não tiver mais nda pra fazer eu arrumo isso
	public List<Compromisso> findByRa(String data) throws SQLException {
		this.st = conn.createStatement();
		// String data = converteDataForString(data);

		String sql = "select * from compromisso WHERE data=" + data;
		ResultSet rs = st.executeQuery(sql);
		@SuppressWarnings("unchecked")
		List<Compromisso> retorno = (List<Compromisso>) rs;
		return retorno;
	}

	public int quantidadaDias(Date data) throws ParseException {
		GregorianCalendar ini = new GregorianCalendar();
		GregorianCalendar fim = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data1 = sdf.format(data);
		String data2 = sdf.format(new java.util.Date());
		ini.setTime(sdf.parse(data1));
		fim.setTime(sdf.parse(data2));
		long dt1 = ini.getTimeInMillis();
		long dt2 = fim.getTimeInMillis();
		return (int) (((dt1 - dt2) / 86400000) + 1);
	}

	// Saber o Dia da Semana
	public String diaSemana(Date data) {

		Calendar c = Calendar.getInstance();
		c.setTime(data);
		int d = c.get(Calendar.DAY_OF_WEEK);
		String dia = "semDia";
		System.out.println("VALOR DE D: " + d);
		switch (d) {
		case 0:
			dia = "Domingo";
			break;
		case 1:
			dia = "Segunda-feira";
			break;
		case 2:
			dia = "Terça-feira";
			break;
		case 3:
			dia = "Quarta-feira";
			break;
		case 4:
			dia = "Quinta-feira";
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
