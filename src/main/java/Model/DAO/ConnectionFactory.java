package Model.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.configuration2.ex.ConfigurationException;

import Util.Config;

public class ConnectionFactory {

	public static Connection getConnection() {
		String db_host = null;
		String db_local = null;
		String db_porta = null;
		try {
			db_host = Config.getConfiguracao().getString("db_host");
			db_local = Config.getConfiguracao().getString("db_local");
			db_porta = Config.getConfiguracao().getString("db_porta");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		try {
			// "jdbc:firebirdsql:localhost/3050:C:/Users/Ghost/eclipse-workspace/projeto-fiscal-loja/BANCO.FDB?lc_ctype=WIN1252",
			// "sysdba", "masterkey"
			Class.forName("org.firebirdsql.jdbc.FBDriver");
			return DriverManager.getConnection(
					"jdbc:firebirdsql:" + db_host + "/" + db_porta + ":" + db_local + "?lc_ctype=WIN1252", "sysdba",
					"masterkey");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}
}
/*
 * 
 * 
 * 
 * try {
 * 
 * Class.forName("com.mysql.jdbc.Driver"); Connection conn = DriverManager
 * .getConnection( "jdbc:mysql://localhost:3306/nomebancodedados", "usuario",
 * "senha"); } catch (SQLException e){ e.printStackTrace(); } catch (Exception
 * e) { e.printStackTrace(); }
 */
