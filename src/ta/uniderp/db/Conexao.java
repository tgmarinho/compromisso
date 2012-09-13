package ta.uniderp.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		ps.setString(1, diaSemana(compromisso.getData()));
		ps.setString(2, compromisso.getDescricao());
		ps.setInt(3, quantidadaDias(compromisso.getData()));
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
 		ps.setString(1, diaSemana(compromisso.getData());
 		ps.setString(2, compromisso.getDescricao();
 		ps.setInt(3, quantidadaDias(compromisso.getData()));
 		ps.setDate(4, (Date) pk);
 		ps.execute(sql);
 		ps.close();
 		conn.close();
        
    }

    public List<Aluno> findAll() throws SQLException {
        this.st = conn.createStatement();
        String sql = "select * from aluno";
        ResultSet rs = st.executeQuery(sql);
        List<Aluno> retorno = (List<Aluno>) rs;
        return retorno;
    }
        
    public List<Aluno> findByRa(String ra) throws SQLException {
        this.st = conn.createStatement();
        String sql = "select * from aluno WHERE ra="+ra;
        ResultSet rs = st.executeQuery(sql);
        List<Aluno> retorno = (List<Aluno>) rs;
        return retorno;
    }
	
}
