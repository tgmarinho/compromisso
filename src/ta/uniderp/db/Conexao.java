package ta.uniderp.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        this.url = "jdbc:postgresql:aluno";
        this.user = "postgres";
        this.password = "postgres";
        Class.forName(driver);
        this.conn = DriverManager.getConnection(url, user, password);
    }

    public void insert(Compromisso compromisso) throws SQLException {
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
    
    private void delete(Compromisso compromisso) throws SQLException{
       //DELETE FROM table_name wHERE some_column=some_value
        
         String sql = "DELETE FROM compromisso WHERE ra = ?";
         ps = conn.prepareStatement(sql);
         ps.setDate(1, (Date) compromisso.getData());
         ps.executeUpdate();
         ps.close(); 
         conn.close();
    }
    
    public void update(Date pk, Compromisso compromisso) throws SQLException {
        
        // UPDATE table_name SET column1=value, column2=value2,... WHERE some_column=some_value
        String sql = "UPDATE compromisso SET data=?, diaSemana=?, descricao=?, diasPassados=? WHERE data=?";
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

    public List<Compromisso> findAll() throws SQLException {
        this.st = conn.createStatement();
        String sql = "select * from compromisso";
        ResultSet rs = st.executeQuery(sql);
        List<Compromisso> retorno = (List<Compromisso>) rs;
        return retorno;
    }
        
    // Se eu não tiver mais nda pra fazer eu arrumo isso
    public List<Compromisso> findByRa(String data) throws SQLException {
        this.st = conn.createStatement();
//        String data = converteDataForString(data);
        
        String sql = "select * from compromisso WHERE data="+data;
        ResultSet rs = st.executeQuery(sql);
        List<Compromisso> retorno = (List<Compromisso>) rs;
        return retorno;
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
