package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DATABASE {
	private Connection con;
	private static String url = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String dburl = "jdbc:sqlserver://localhost:1433;databaseName=management";
	private static String username = "sa";
	private static String password = "123";

	public Connection Connect() {
		try {
			Class.forName(url);
			try {
				con = DriverManager.getConnection(dburl, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void CloseConnect() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
