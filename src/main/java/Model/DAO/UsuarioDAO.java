package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Usuario;

public class UsuarioDAO {
	public boolean insert(Usuario usuario) throws SQLException {

		Connection conn = ConnectionFactory.getConnection();
		
		try {

			conn.setAutoCommit(false);
			
			
			String sql = "INSERT INTO USUARIO "
					+ " ( NOME, USERNAME, PASSWD, TELEFONE, EMAIL, ATIVO, MODIFICADO) "
					+ " values (?,?,?,?,?,?,?)";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getUsername());
			stmt.setString(3, usuario.getPasswd());
			stmt.setString(4, usuario.getTelefone());
			stmt.setString(5, usuario.getEmail());
			stmt.setInt(6, usuario.getAtivo()?1:0);
			
			java.sql.Date modificado = new java.sql.Date(usuario.getModificado().getTime());
			stmt.setDate(7, modificado);
			
			
			stmt.execute();
			
			long key = -1L;
			
			
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs != null && rs.next()){
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

	public boolean update(Usuario usuario) throws SQLException {

		Connection conn = ConnectionFactory.getConnection();
		
		try {
			
			String sql = "UPDATE USUARIO SET NOME=?, USERNAME=?, PASSWD=?, TELEFONE=?, EMAIL=?, ATIVO=?, MODIFICADO=? WHERE ID=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getUsername());
			stmt.setString(3, usuario.getPasswd());
			stmt.setString(4, usuario.getTelefone());
			stmt.setString(5, usuario.getEmail());
			stmt.setInt(6, usuario.getAtivo()?1:0);
			
			java.sql.Date modificado = new java.sql.Date(usuario.getModificado().getTime());
			stmt.setDate(7, modificado);
			
			stmt.setInt(8, usuario.getId());

			
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

	public boolean delete(Usuario usuario) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		try {

			String sql = "DELETE FROM USUARIO WHERE ID=?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setLong(1, usuario.getId());

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

	public ArrayList<Usuario> selectFilter(String value, String filter) throws SQLException {

		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM USUARIO";
		
		System.out.println("NAO IMPLEMENTADO -- USUARIO SELECTFILTER *");
		
		PreparedStatement cmd = conn.prepareStatement(query);
		
		ResultSet rs = cmd.executeQuery();
		
		ArrayList<Usuario> lstUsuario = new ArrayList<Usuario>();
		Usuario usuario = null;
		
		while (rs.next()) {
			usuario = new Usuario();
			
			usuario.setId(rs.getInt("ID"));
			usuario.setNome(rs.getString("NOME"));
			usuario.setUsername(rs.getString("USERNAME"));
			usuario.setPasswd(rs.getString("PASSWD"));
			usuario.setTelefone(rs.getString("TELEFONE"));
			usuario.setEmail(rs.getString("EMAIL"));
			usuario.setAtivo(rs.getBoolean("ATIVO"));
			
			usuario.setModificado(rs.getDate("MODIFICADO"));

			lstUsuario.add(usuario);
		}
		rs.close();
		conn.close();

		return lstUsuario;
	}

	public Usuario selectPerID(int id) throws SQLException {
		
		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM USUARIO WHERE ID=?";
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);
		cmd.setInt(1, id);

		ResultSet rs = cmd.executeQuery();
		Usuario usuario = null;
		while (rs.next()) {
			usuario = new Usuario();
			
			usuario.setId(rs.getInt("ID"));
			usuario.setNome(rs.getString("NOME"));
			usuario.setUsername(rs.getString("USERNAME"));
			usuario.setPasswd(rs.getString("PASSWD"));
			usuario.setTelefone(rs.getString("TELEFONE"));
			usuario.setEmail(rs.getString("EMAIL"));
			usuario.setAtivo(rs.getBoolean("ATIVO"));
			
			usuario.setModificado(rs.getDate("MODIFICADO"));
			
		}
		rs.close();
		conn.close();

		return usuario;
	}

	public ArrayList<Usuario> selectAll() throws SQLException {

		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM USUARIO";
		
		PreparedStatement cmd = conn.prepareStatement(query);
		
		ResultSet rs = cmd.executeQuery();
		
		ArrayList<Usuario> lstUsuario = new ArrayList<Usuario>();
		Usuario usuario = null;
		
		while (rs.next()) {
			usuario = new Usuario();
			
			usuario.setId(rs.getInt("ID"));
			usuario.setNome(rs.getString("NOME"));
			usuario.setUsername(rs.getString("USERNAME"));
			usuario.setPasswd(rs.getString("PASSWD"));
			usuario.setTelefone(rs.getString("TELEFONE"));
			usuario.setEmail(rs.getString("EMAIL"));
			usuario.setAtivo(rs.getBoolean("ATIVO"));
			
			usuario.setModificado(rs.getDate("MODIFICADO"));

			lstUsuario.add(usuario);
		}
		rs.close();
		conn.close();

		return lstUsuario;
	}

	public Usuario selectPorNomeESenha(Usuario usuario1) throws SQLException {

		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM USUARIO WHERE USERNAME=? and PASSWD=?";
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);
		cmd.setString(1, usuario1.getUsername());
		cmd.setString(2, usuario1.getPasswd());

		ResultSet rs = cmd.executeQuery();
		Usuario usuario = null;
		while (rs.next()) {
			usuario = new Usuario();
			
			usuario.setId(rs.getInt("ID"));
			usuario.setNome(rs.getString("NOME"));
			usuario.setUsername(rs.getString("USERNAME"));
			usuario.setPasswd(rs.getString("PASSWD"));
			usuario.setTelefone(rs.getString("TELEFONE"));
			usuario.setEmail(rs.getString("EMAIL"));
			usuario.setAtivo(rs.getBoolean("ATIVO"));
			
			usuario.setModificado(rs.getDate("MODIFICADO"));
			
		}
		rs.close();
		conn.close();

		return usuario;
	}
}