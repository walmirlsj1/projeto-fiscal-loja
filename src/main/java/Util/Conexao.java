package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conexao {
	public Connection con = null;
	public Statement stm = null;

	public Conexao() {

		try {

			Class.forName("org.firebirdsql.jdbc.FBDriver");
			con = DriverManager.getConnection("jdbc:firebirdsql:localhost/3050:C:/GDOOR Sistemas/GDOOR PRO/datages.fdb",
					"sysdba", "masterkey");
			stm = con.createStatement();
//			System.out.println("Conectar ao banco: OK");
		} catch (Exception e) {
			System.out.println("Não foi possível conectar ao banco: " + e.getMessage());
		}

	}
	public static void main(String[] args) throws Throwable {
		Conexao con = new Conexao();
		con.finalize();
	}
}
