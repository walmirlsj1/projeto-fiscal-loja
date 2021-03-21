package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Cliente;
import Model.Versao;
import Model.Contador;

public class ClienteDAO {

	public boolean insert(Cliente cliente) throws SQLException {
		
		Connection conn = ConnectionFactory.getConnection();
		
		java.sql.Date modificado = new java.sql.Date(cliente.getModificado().getTime());
		
		try {

			conn.setAutoCommit(false);
			
			
			String sql = "INSERT INTO CLIENTE "
					+ " ( NOME, TELEFONE, EMAIL, CNPJ, SERIAL, IDREMOTO, VERSAO_ID, ENVIAR_DADOS_CONTADOR, CONTADOR_ID, MODIFICADO) "
					+ " values (?,?,?,?,?,?,?,?,?,?)";

//			PreparedStatement stmt = conn.prepareStatement(sql);
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// preenche os valores
//			stmt.setInt(1, 0);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getTelefone());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getCnpj());
			stmt.setString(5, cliente.getSerial());
			stmt.setString(6, cliente.getIdRemoto());
			stmt.setInt(7, cliente.getVersao().getId());
			stmt.setInt(8, cliente.getEnviarDadosContador()?1:0);
			stmt.setInt(9, cliente.getContador().getId());
			stmt.setDate(10, modificado);
			
			// contato.setDataNascimento(Calendar.getInstance());
//			stmt.;
//			System.out.println("executando a query...");
			stmt.execute();
//			stmt.executeQuery();
			long key = -1L;
			
			
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs != null && rs.next()){
			   key = rs.getLong(1);
			}
			
			
			stmt.close();

//			System.out.println("Gravado!" + key);
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

	public boolean update(Cliente cliente) throws SQLException {
		
		Connection conn = ConnectionFactory.getConnection();
		
		try {

			// faz um monte de operações.
			// que podem lançar exceptions runtime e SQLException
			java.sql.Date modificado = new java.sql.Date(cliente.getModificado().getTime());

			String sql = "UPDATE CLIENTE SET NOME=?, TELEFONE=?, EMAIL=?, CNPJ=?, SERIAL=?, "
					+ "IDREMOTO=?, VERSAO_ID=?, ENVIAR_DADOS_CONTADOR=?, CONTADOR_ID=?, MODIFICADO=? " + " WHERE ID=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			// preenche os valores
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getTelefone());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getCnpj());
			stmt.setString(5, cliente.getSerial());
			stmt.setString(6, cliente.getIdRemoto());
			stmt.setInt(7, cliente.getVersao().getId());
			stmt.setBoolean(8, cliente.getEnviarDadosContador());
			stmt.setInt(9, cliente.getContador().getId());
			stmt.setDate(10, modificado);
			stmt.setInt(11, cliente.getId());

			// contato.setDataNascimento(Calendar.getInstance());
			stmt.execute();
			stmt.close();

			System.out.println("Gravado!");

			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			conn.close();
		}
		
		return false;

	}

	public boolean delete(Cliente cliente) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		try {

			String sql = "DELETE FROM CLIENTE WHERE ID=?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setLong(1, cliente.getId());

			stmt.execute();
			stmt.close();

			System.out.println("Deletado id = " + cliente.getId() + " com sucesso.");

			conn.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			conn.close();
		}
		return false;
	}

	public ArrayList<Cliente> selectFilter(String value, String filter) throws SQLException {
		
		Connection conn = ConnectionFactory.getConnection();
		

		String query = "SELECT * FROM CLIENTE";
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);
		
		ResultSet rs = cmd.executeQuery();
		ArrayList<Cliente> lstCliente = new ArrayList<Cliente>();
		Cliente cliente = null;
		
		while (rs.next()) {
			cliente = new Cliente(Integer.parseInt(rs.getString("ID")));

			cliente.setNome(rs.getString("NOME"));
			cliente.setTelefone(rs.getString("TELEFONE"));
			cliente.setEmail(rs.getString("EMAIL"));
			cliente.setCnpj(rs.getString("CNPJ"));
			cliente.setSerial(rs.getString("SERIAL"));

			cliente.setIdRemoto(rs.getString("IDREMOTO"));
			cliente.setModificado(rs.getDate("MODIFICADO"));
			cliente.setEnviarDadosContador(rs.getBoolean("ENVIAR_DADOS_CONTADOR"));

			VersaoDAO versaoDAO = new VersaoDAO();
			Versao versao = versaoDAO.selectPerID(rs.getInt("VERSAO_ID"));

			ContadorDAO contadorDAO = new ContadorDAO();
			Contador contador = contadorDAO.selectPerID(rs.getInt("CONTADOR_ID"));

			cliente.setVersao(versao);
			cliente.setContador(contador);

			lstCliente.add(cliente);
		}
		rs.close();
		conn.close();
		
		return lstCliente;
	}

	public Cliente selectPerID(int id) throws SQLException {
		
		Connection conn = ConnectionFactory.getConnection();
		
		String query = "SELECT * FROM CLIENTE WHERE ID=?";
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);
		cmd.setInt(1, id);

		ResultSet rs = cmd.executeQuery();
		Cliente cliente = null;
		
		while (rs.next()) {
			cliente = new Cliente(Integer.parseInt(rs.getString("ID")));

			cliente.setNome(rs.getString("NOME"));
			cliente.setTelefone(rs.getString("TELEFONE"));
			cliente.setEmail(rs.getString("EMAIL"));
			cliente.setCnpj(rs.getString("CNPJ"));
			cliente.setSerial(rs.getString("SERIAL"));

			cliente.setIdRemoto(rs.getString("IDREMOTO"));
			cliente.setModificado(rs.getDate("MODIFICADO"));
			cliente.setEnviarDadosContador(rs.getBoolean("ENVIAR_DADOS_CONTADOR"));

			VersaoDAO versaoDAO = new VersaoDAO();
			Versao versao = versaoDAO.selectPerID(rs.getInt("VERSAO_ID"));

			ContadorDAO contadorDAO = new ContadorDAO();
			Contador contador = contadorDAO.selectPerID(rs.getInt("CONTADOR_ID"));

			cliente.setVersao(versao);
			cliente.setContador(contador);
			break;
		}
		rs.close();
		conn.close();
		
		return cliente;
	}

	public ArrayList<Cliente> selectAll() throws SQLException {

		Connection conn = ConnectionFactory.getConnection();
		

		String query = "SELECT * FROM CLIENTE";
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);
		
		ResultSet rs = cmd.executeQuery();
		ArrayList<Cliente> lstCliente = new ArrayList<Cliente>();
		Cliente cliente = null;
		while (rs.next()) {
			cliente = new Cliente(Integer.parseInt(rs.getString("ID")));

			cliente.setNome(rs.getString("NOME"));
			cliente.setTelefone(rs.getString("TELEFONE"));
			cliente.setEmail(rs.getString("EMAIL"));
			cliente.setCnpj(rs.getString("CNPJ"));
			cliente.setSerial(rs.getString("SERIAL"));

			cliente.setIdRemoto(rs.getString("IDREMOTO"));
			cliente.setModificado(rs.getDate("MODIFICADO"));
			cliente.setEnviarDadosContador(rs.getBoolean("ENVIAR_DADOS_CONTADOR"));

			VersaoDAO versaoDAO = new VersaoDAO();
			Versao versao = versaoDAO.selectPerID(rs.getInt("VERSAO_ID"));

			ContadorDAO contadorDAO = new ContadorDAO();
			Contador contador = contadorDAO.selectPerID(rs.getInt("CONTADOR_ID"));

			cliente.setVersao(versao);
			cliente.setContador(contador);

			lstCliente.add(cliente);
		}
		rs.close();
		conn.close();
		
		return lstCliente;
	}

}