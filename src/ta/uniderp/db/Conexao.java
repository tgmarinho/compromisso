package ta.uniderp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	
	private Connection conn;
	private Statement stm;
	private String driver;
	private String url;
	private String usuario;
	private String senha;
	private String server;
	private String bd;

	public Conexao() { }
	
	public void iniciar() {
		setDriver("org.gjt.mm.mysql.Driver");
	 	setURL("jdbc:mysql://");  
        setUsuario("root");  
        setSenha("mysql");  
        setServer("localhost");  
        setBd("compromisse");  
        try {
			Class.forName(driver);
			url = url +""+ server + "/"+ bd;  
			setConn(DriverManager.getConnection(url, usuario, senha));
        
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}

	public void fechar() {
		
		try {
			getConn().commit();
			getConn().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUsuario() {
		return usuario;
	}
	
	public String getURL() {
		return url;
	}

	public void setURL(String uRL) {
		url = uRL;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDriver() {
		return driver;
	}

	public void setStm(Statement stm) {
		this.stm = stm;
	}

	public Statement getStm() {
		return stm;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Connection getConn() {
		return conn;
	}
	
}
