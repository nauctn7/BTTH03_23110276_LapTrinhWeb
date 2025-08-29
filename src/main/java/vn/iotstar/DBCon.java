package vn.iotstar;

import java.sql.DriverManager;
import java.sql.Connection;
//import com.sun.jdi.connect.spi.Connection;

public class DBCon {
	private final String serverName = "THANHNHAN\\MSSQLSERVER01";
	private final String dbName = "LapTrinhWeb";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String userID = "sa";
	private final String password = "123456";

	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + "\\" + instance + ":" + portNumber + ";databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty()) {
		    url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
		}
		url += ";encrypt=true;trustServerCertificate=true";

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
	}

	public static void main(String[] args) {
		try {
			System.out.println(new DBCon().getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
