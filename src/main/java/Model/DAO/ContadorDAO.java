package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Contador;

public class ContadorDAO {
	public boolean insert(Contador contador) throws SQLException {

		Connection conn = ConnectionFactory.getConnection();
		
		java.sql.Date modificado = new java.sql.Date(contador.getModificado().getTime());
		
		try {

			conn.setAutoCommit(false);
			
			
			String sql = "INSERT INTO CONTADOR "
					+ " ( NOME, TELEFONE, EMAIL, CNPJ, MODIFICADO) "
					+ " values (?,?,?,?,?)";

//			PreparedStatement stmt = conn.prepareStatement(sql);
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// preenche os valores
//			stmt.setInt(1, 0);
			stmt.setString(1, contador.getNome());
			stmt.setString(2, contador.getTelefone());
			stmt.setString(3, contador.getEmail());
			stmt.setString(4, contador.getCnpj());
			stmt.setDate(5, modificado);
			
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

	public boolean update(Contador contador) throws SQLException {

		Connection conn = ConnectionFactory.getConnection();
		
		try {

			// faz um monte de operações.
			// que podem lançar exceptions runtime e SQLException
			java.sql.Date modificado = new java.sql.Date(contador.getModificado().getTime());

			String sql = "UPDATE CONTADOR SET NOME=?, TELEFONE=?, EMAIL=?, CNPJ=?, MODIFICADO=? WHERE ID=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			// preenche os valores
			stmt.setString(1, contador.getNome());
			stmt.setString(2, contador.getTelefone());
			stmt.setString(3, contador.getEmail());
			stmt.setString(4, contador.getCnpj());
			stmt.setDate(5, modificado);
			stmt.setInt(6, contador.getId());

			// contato.setDataNascimento(Calendar.getInstance());
			stmt.execute();
			stmt.close();

			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			conn.close();
		}
		
		return false;

	}

	public boolean delete(Contador contador) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		try {

			String sql = "DELETE FROM CONTADOR WHERE ID=?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setLong(1, contador.getId());

			stmt.execute();
			stmt.close();

			System.out.println("Deletado id = " + contador.getId() + " com sucesso.");

			conn.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			conn.close();
		}
		return false;
	}

	public ArrayList<Contador> selectFilter(String value, String filter) throws SQLException {

		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM CONTADOR";
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);

		ResultSet rs = cmd.executeQuery();
		ArrayList<Contador> lstContador = new ArrayList<Contador>();
		Contador contador = null;
		
		while (rs.next()) {
			contador = new Contador();
			contador.setId(rs.getInt("ID"));
			contador.setNome(rs.getString("NOME"));
			contador.setTelefone(rs.getString("TELEFONE"));
			contador.setEmail(rs.getString("EMAIL"));
			contador.setCnpj(rs.getString("CNPJ"));
			contador.setModificado(rs.getDate("MODIFICADO"));

			lstContador.add(contador);
		}
		rs.close();
		conn.close();

		return lstContador;
	}

	public Contador selectPerID(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM CONTADOR WHERE ID=?";
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);
		cmd.setInt(1, id);

		ResultSet rs = cmd.executeQuery();
		Contador contador = null;
		while (rs.next()) {
			contador = new Contador();
			contador.setId(rs.getInt("ID"));
			contador.setNome(rs.getString("NOME"));
			contador.setTelefone(rs.getString("TELEFONE"));
			contador.setEmail(rs.getString("EMAIL"));
			contador.setCnpj(rs.getString("CNPJ"));
			contador.setModificado(rs.getDate("MODIFICADO"));
			break;
		}
		rs.close();
		conn.close();

		return contador;
	}

	public ArrayList<Contador> selectAll() throws SQLException {

		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM CONTADOR";
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);

		ResultSet rs = cmd.executeQuery();
		ArrayList<Contador> lstContador = new ArrayList<Contador>();
		Contador contador = null;
		while (rs.next()) {
			contador = new Contador();
			contador.setId(rs.getInt("ID"));
			contador.setNome(rs.getString("NOME"));
			contador.setTelefone(rs.getString("TELEFONE"));
			contador.setEmail(rs.getString("EMAIL"));
			contador.setCnpj(rs.getString("CNPJ"));
			contador.setModificado(rs.getDate("MODIFICADO"));

			lstContador.add(contador);
		}
		rs.close();
		conn.close();

		return lstContador;
	}

}