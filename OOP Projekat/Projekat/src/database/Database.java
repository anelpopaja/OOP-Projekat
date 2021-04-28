package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private String ip = "localhost", db = "seminarski_ors1", user = "root", password = "hello123";
	
	private Connection conn = null;
	
	public Database() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init () {
		try {
			String url = "jdbc:mysql://" + ip + ":3306/" + db + "?serverTimezone=UTC";
			setConn(DriverManager.getConnection(url, user, password));
			System.out.println("Connection established.");

		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e);
		}
	}

	public Connection getConn() {
		return conn;
	}
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	
}
