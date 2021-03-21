package Util;

public class ExConexaoFirebird {
	private static final String URL_DEFAULT = "jdbc:firebirdsql:192.168.0.200:banco_teste";
	private static final String USER_DEFAULT = "SYSDBA";
	private static final String PASSWORD_DEFAULT = "masterkey";

	private static final int REGISTER_CLASS_FOR_NAME = 1;
	private static final int REGISTER_PROPERTIES = 2;
	private static final int REGISTER_JDBC4 = 3;

	private static final int CONNECT_DRIVERMANAGER = 1;
	private static final int CONNECT_DRIVER = 2;

	public void testMe() {
		String databaseURL = URL_DEFAULT;
		String user = USER_DEFAULT;
		String password = PASSWORD_DEFAULT;

		java.sql.Driver driver = null;
		java.sql.Connection con = null;
		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null;

		int registrationAlternative = REGISTER_CLASS_FOR_NAME;
		switch (registrationAlternative) {

		case REGISTER_CLASS_FOR_NAME:
			try {
				Class.forName("org.firebirdsql.jdbc.FBDriver");
			} catch (java.lang.ClassNotFoundException e) {
				System.out.println("Firebird JCA-JDBC driver not found in class path");
				System.out.println(e.getMessage());
				return;
			}
			break;

		case REGISTER_PROPERTIES:
			java.util.Properties sysProps = System.getProperties();
			StringBuffer drivers = new StringBuffer("org.firebirdsql.jdbc.FBDriver");
			String oldDrivers = sysProps.getProperty("jdbc.drivers");
			if (oldDrivers != null)
				drivers.append(":" + oldDrivers);
			sysProps.put("jdbc.drivers", drivers.toString());
			System.setProperties(sysProps);
			break;

		case REGISTER_JDBC4:
			break;
		}

		try {
			driver = java.sql.DriverManager.getDriver(databaseURL);
			System.out.println("Firebird JCA-JDBC driver version " + driver.getMajorVersion() + "."
					+ driver.getMinorVersion() + " registered with driver manager.");
		} catch (java.sql.SQLException e) {
			System.out.println("Unable to find Firebird JCA-JDBC driver among the registered drivers.");
			System.out.println(e);
			return;
		}

		int connectionAlternative = CONNECT_DRIVER;
		switch (connectionAlternative) {

		case CONNECT_DRIVERMANAGER:
			try {
				con = java.sql.DriverManager.getConnection(databaseURL, user, password);
				System.out.println("Connection established.");
			} catch (java.sql.SQLException e) {
				e.printStackTrace();
				System.out.println("Unable to establish a connection through the driver manager.");
				System.out.println(e);
				return;
			}
			break;

		case CONNECT_DRIVER:
			try {
				java.util.Properties connectionProperties = new java.util.Properties();
				connectionProperties.put("user", user);
				connectionProperties.put("password", password);
				connectionProperties.put("lc_ctype", "WIN1252");
				con = driver.connect(databaseURL, connectionProperties);
				System.out.println("Connection established.");
			} catch (java.sql.SQLException e) {
				e.printStackTrace();
				System.out.println("Unable to establish a connection through the driver.");
				System.out.println(e);
				return;
			}
			break;
		}

		// Disable the default autocommit so we can undo our changes later
		try {
			con.setAutoCommit(false);
			System.out.println("Auto-commit is disabled.");
		} catch (java.sql.SQLException e) {
			System.out.println("Unable to disable autocommit.");
			System.out.println(e);
			return;
		}

	}
}