import java.sql.Connection;
import java.sql.SQLException;

import View.LoginGUI;

import Model.DAO.ConnectionFactory;

public class main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Connection conn = ConnectionFactory.getConnection();
		
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			
			LoginGUI login = new LoginGUI();
//			login.setModal(true);
			login.setVisible(true);
			//MainWin window = new MainWin();
			//window.main(args);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
