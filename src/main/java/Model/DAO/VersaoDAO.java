package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Usuario;
import Model.Versao;


public class VersaoDAO {
	public boolean insert(Versao versao) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();

		java.sql.Date modificado = new java.sql.Date(versao.getModificado().getTime());

		try {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO VERSAO "
					+ " ( VERSAO, USUARIO_ID, MODIFICADO) "
					+ " values (?,?,?)";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// preenche os valores
			stmt.setString(1, versao.getVersao());
			stmt.setInt(2, versao.getUsuario().getId());
			stmt.setDate(3, modificado);
			
			stmt.execute();

			long key = -1L;

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				key = rs.getLong(1);
			}

			stmt.close();
			
			conn.commit();
			conn.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			conn.close();
		}

		return false;
	}

	public boolean update(Versao versao) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();

		try {
			java.sql.Date modificado = new java.sql.Date(versao.getModificado().getTime());

			String sql = "UPDATE VERSAO SET VERSAO=?, USUARIO_ID=?, MODIFICADO=? WHERE ID=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, versao.getVersao());
			stmt.setInt(2, versao.getUsuario().getId());
			stmt.setDate(3, modificado);
			stmt.setInt(4, versao.getId());
			
			
			stmt.execute();
			stmt.close();

			conn.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			conn.close();
		}

		return false;

	}

	public boolean delete(Versao versao) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		try {

			String sql = "DELETE FROM VERSAO WHERE ID=?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setLong(1, versao.getId());

			stmt.execute();
			stmt.close();

			conn.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			conn.close();
		}
		return false;
	}

	public ArrayList<Versao> selectFilter(String value, String filter) throws SQLException {

		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM VERSAO";
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);
		
		ResultSet rs = cmd.executeQuery();
		ArrayList<Versao> lstVersao = new ArrayList<Versao>();
		Versao versao = null;
		while (rs.next()) {
			versao = new Versao();

			versao.setId(rs.getInt("ID"));
			versao.setVersao(rs.getString("VERSAO"));
			versao.setModificado(rs.getDate("MODIFICADO"));

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.selectPerID(rs.getInt("USUARIO_ID"));

			versao.setUsuario(usuario);

			lstVersao.add(versao);
		}
		rs.close();
		conn.close();

		return lstVersao;
	}

	public Versao selectPerID(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM VERSAO WHERE ID=?";
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);
		cmd.setInt(1, id);

		ResultSet rs = cmd.executeQuery();
		Versao versao = null;
		while (rs.next()) {
			versao = new Versao();

			versao.setId(rs.getInt("ID"));
			versao.setVersao(rs.getString("VERSAO"));
			versao.setModificado(rs.getDate("MODIFICADO"));

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.selectPerID(rs.getInt("USUARIO_ID"));

			versao.setUsuario(usuario);

		}
		rs.close();
		conn.close();

		return versao;
	}

	public ArrayList<Versao> selectAll() throws SQLException {

		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM VERSAO";
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);
		
		ResultSet rs = cmd.executeQuery();
		ArrayList<Versao> lstVersao = new ArrayList<Versao>();
		Versao versao = null;
		while (rs.next()) {
			versao = new Versao();

			versao.setId(rs.getInt("ID"));
			versao.setVersao(rs.getString("VERSAO"));
			versao.setModificado(rs.getDate("MODIFICADO"));

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.selectPerID(rs.getInt("USUARIO_ID"));

			versao.setUsuario(usuario);

			lstVersao.add(versao);
		}
		rs.close();
		conn.close();

		return lstVersao;
	}

}