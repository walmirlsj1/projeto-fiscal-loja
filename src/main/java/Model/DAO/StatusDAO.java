package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Status;
import Model.Usuario;

public class StatusDAO {
	public boolean insert(Status status) throws SQLException {

		Connection conn = ConnectionFactory.getConnection();

		try {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO STATUS " + " ( DESCRICAO, USUARIO_ID, MODIFICADO) " + " values (?,?,?)";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, status.getDescricao());
			stmt.setInt(2, status.getUsuario().getId());

			java.sql.Date modificado = new java.sql.Date(status.getModificado().getTime());
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

	public boolean update(Status status) throws SQLException {

		Connection conn = ConnectionFactory.getConnection();

		try {

			String sql = "UPDATE STATUS SET DESCRICAO=?, USUARIO_ID=?, MODIFICADO=? WHERE ID=?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, status.getDescricao());
			stmt.setInt(2, status.getUsuario().getId());

			java.sql.Date modificado = new java.sql.Date(status.getModificado().getTime());
			stmt.setDate(3, modificado);

			stmt.setInt(4, status.getId());

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

	public boolean delete(Status status) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		try {

			String sql = "DELETE FROM STATUS WHERE ID=?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setLong(1, status.getId());

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

	public ArrayList<Status> selectFilter(String value, String filter) throws SQLException {

		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM STATUS";

		System.out.println("IMPLEMENTAR PESQUISA!!!! SELECTFILTER FISCALDAO");

		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);

		ResultSet rs = cmd.executeQuery();
		ArrayList<Status> lstStatus = new ArrayList<Status>();
		Status status = null;

		while (rs.next()) {
			status = new Status();

			status.setId(rs.getInt("ID"));
			status.setDescricao(rs.getString("DESCRICAO"));

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.selectPerID(rs.getInt("USUARIO_ID"));

			status.setUuario(usuario);

			lstStatus.add(status);
		}
		rs.close();
		conn.close();

		return lstStatus;
	}

	public Status selectPerID(int id) throws SQLException {

		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM STATUS WHERE ID=?";
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);
		cmd.setInt(1, id);

		ResultSet rs = cmd.executeQuery();
		Status status = null;
		while (rs.next()) {
			status = new Status();

			status.setId(rs.getInt("ID"));
			status.setDescricao(rs.getString("DESCRICAO"));

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.selectPerID(rs.getInt("USUARIO_ID"));

			status.setUuario(usuario);
		}
		rs.close();
		conn.close();

		return status;
	}

	public ArrayList<Status> selectAll() throws SQLException {

		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM STATUS";

		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);

		ResultSet rs = cmd.executeQuery();
		ArrayList<Status> lstStatus = new ArrayList<Status>();
		Status status = null;

		while (rs.next()) {
			status = new Status();

			status.setId(rs.getInt("ID"));
			status.setDescricao(rs.getString("DESCRICAO"));

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.selectPerID(rs.getInt("USUARIO_ID"));

			status.setUuario(usuario);

			lstStatus.add(status);
		}
		rs.close();
		conn.close();

		return lstStatus;
	}

}
