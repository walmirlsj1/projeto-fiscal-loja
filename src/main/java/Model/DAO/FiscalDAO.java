package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Cliente;
import Model.Fiscal;
import Model.Status;

public class FiscalDAO {
	public boolean insert(Fiscal fiscal) throws SQLException {
		
		Connection conn = ConnectionFactory.getConnection();
		
		try {

			conn.setAutoCommit(false);
			
			
			String sql = "INSERT INTO FISCAL "
					+ " ( CLIENTE_ID, MES, ANO, STATUS_ID, DATA_ENVIO, MODIFICADO) "
					+ " values (?,?,?,?,?,?)";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setInt(1, fiscal.getCliente().getId());
			stmt.setInt(2, fiscal.getMes());
			stmt.setInt(3, fiscal.getAno());
			stmt.setInt(4, fiscal.getStatus().getId());
			
			java.sql.Date data_envio = null;
			java.sql.Date modificado = null;
			if(fiscal.getDataEnvio() != null) {
				data_envio = new java.sql.Date(fiscal.getDataEnvio().getTime());
			
			}
			
			if(fiscal.getModificado() != null) {
				modificado = new java.sql.Date(fiscal.getModificado().getTime());
			
			}
			stmt.setDate(5, data_envio);
			stmt.setDate(6, modificado);
			
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

	public boolean update(Fiscal fiscal) throws SQLException {

		Connection conn = ConnectionFactory.getConnection();
		
		try {
			
			String sql = "UPDATE FISCAL SET CLIENTE_ID=?, MES=?, ANO=?, STATUS_ID=?, DATA_ENVIO=?, MODIFICADO=? WHERE ID=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, fiscal.getCliente().getId());
			stmt.setInt(2, fiscal.getMes());
			stmt.setInt(3, fiscal.getAno());
			stmt.setInt(4, fiscal.getStatus().getId());
			
			java.sql.Date data_envio = null;
			java.sql.Date modificado = null;
			if(fiscal.getDataEnvio() != null) {
				data_envio = new java.sql.Date(fiscal.getDataEnvio().getTime());
			
			}
			
			if(fiscal.getModificado() != null) {
				modificado = new java.sql.Date(fiscal.getModificado().getTime());
			
			}
			stmt.setDate(5, data_envio);
			stmt.setDate(6, modificado);
			
			stmt.setInt(7, fiscal.getId());

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

	public boolean delete(Fiscal fiscal) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		try {

			String sql = "DELETE FROM FISCAL WHERE ID=?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setLong(1, fiscal.getId());

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

	public ArrayList<Fiscal> selectFilterClienteMesAno(Cliente cliente, int mes, int ano) throws SQLException {

		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM FISCAL WHERE CLIENTE_ID=? AND MES=? AND ANO=?";
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);
		
		cmd.setInt(1, cliente.getId());
		cmd.setInt(2, mes);
		cmd.setInt(3, ano);
		
		ResultSet rs = cmd.executeQuery();
		ArrayList<Fiscal> lstFiscal = new ArrayList<Fiscal>();
		Fiscal fiscal = null;
		Status status = null;
		ClienteDAO clienteDAO = new ClienteDAO();
		StatusDAO statusDAO = new StatusDAO();
		
		Cliente cliente2;
		while (rs.next()) {
			fiscal = new Fiscal();
			
			fiscal.setId(rs.getInt("ID"));
			fiscal.setMes(rs.getInt("MES"));
			fiscal.setAno(rs.getInt("ANO"));
			fiscal.setDataEnvio(rs.getDate("DATA_ENVIO"));
			fiscal.setModificado(rs.getDate("MODIFICADO"));
			
			cliente2 = clienteDAO.selectPerID(rs.getInt("CLIENTE_ID"));

			
			status = statusDAO.selectPerID(rs.getInt("STATUS_ID"));

			fiscal.setCliente(cliente2);
			fiscal.setStatus(status);

			lstFiscal.add(fiscal);
		}
		rs.close();
		conn.close();

		return lstFiscal;
	}
	
	public ArrayList<Fiscal> selectFilterMesAno(int mes, int ano) throws SQLException {

		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM FISCAL WHERE MES=? AND ANO=?";
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);
		
		cmd.setInt(1, mes);
		cmd.setInt(2, ano);
		
		ResultSet rs = cmd.executeQuery();
		ArrayList<Fiscal> lstFiscal = new ArrayList<Fiscal>();
		Fiscal fiscal = null;
		Status status = null;
		ClienteDAO clienteDAO = new ClienteDAO();
		StatusDAO statusDAO = new StatusDAO();
		
		Cliente cliente2;
		while (rs.next()) {
			fiscal = new Fiscal();
			
			fiscal.setId(rs.getInt("ID"));
			fiscal.setMes(rs.getInt("MES"));
			fiscal.setAno(rs.getInt("ANO"));
			fiscal.setDataEnvio(rs.getDate("DATA_ENVIO"));
			fiscal.setModificado(rs.getDate("MODIFICADO"));
			
			cliente2 = clienteDAO.selectPerID(rs.getInt("CLIENTE_ID"));

			
			status = statusDAO.selectPerID(rs.getInt("STATUS_ID"));

			fiscal.setCliente(cliente2);
			fiscal.setStatus(status);

			lstFiscal.add(fiscal);
		}
		rs.close();
		conn.close();

		return lstFiscal;
	}
	
	public ArrayList<Fiscal> selectFilter(String value, String filter) throws SQLException {

		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM FISCAL";
		
		System.out.println("IMPLEMENTAR PESQUISA!!!! SELECTFILTER FISCALDAO");
		
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);
		
		ResultSet rs = cmd.executeQuery();
		ArrayList<Fiscal> lstFiscal = new ArrayList<Fiscal>();
		Fiscal fiscal = null;
		
		while (rs.next()) {
			fiscal = new Fiscal();
			
			fiscal.setId(rs.getInt("ID"));
			fiscal.setMes(rs.getInt("MES"));
			fiscal.setAno(rs.getInt("ANO"));
			fiscal.setDataEnvio(rs.getDate("DATA_ENVIO"));
			fiscal.setModificado(rs.getDate("MODIFICADO"));
			
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = clienteDAO.selectPerID(rs.getInt("CLIENTE_ID"));

			StatusDAO statusDAO = new StatusDAO();
			Status status = statusDAO.selectPerID(rs.getInt("STATUS_ID"));

			fiscal.setCliente(cliente);
			fiscal.setStatus(status);

			lstFiscal.add(fiscal);
		}
		rs.close();
		conn.close();

		return lstFiscal;
	}

	public Fiscal selectPerID(int id) throws SQLException {
		
		Connection conn = ConnectionFactory.getConnection();
		

		String query = "SELECT * FROM FISCAL WHERE ID=?";
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);
		cmd.setInt(1, id);

		ResultSet rs = cmd.executeQuery();
		Fiscal fiscal = null;
		while (rs.next()) {
			fiscal = new Fiscal();
			
			fiscal.setId(rs.getInt("ID"));
			
			fiscal.setMes(rs.getInt("MES"));
			fiscal.setAno(rs.getInt("ANO"));
			fiscal.setDataEnvio(rs.getDate("DATA_ENVIO"));
			fiscal.setModificado(rs.getDate("MODIFICADO"));
			
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = clienteDAO.selectPerID(rs.getInt("CLIENTE_ID"));

			StatusDAO statusDAO = new StatusDAO();
			Status status = statusDAO.selectPerID(rs.getInt("STATUS_ID"));

			fiscal.setCliente(cliente);
			fiscal.setStatus(status);
		}
		rs.close();
		conn.close();
		
		return fiscal;
	}

	public ArrayList<Fiscal> selectAll() throws SQLException {

		Connection conn = ConnectionFactory.getConnection();

		String query = "SELECT * FROM FISCAL";
		PreparedStatement cmd;
		cmd = conn.prepareStatement(query);
		
		ResultSet rs = cmd.executeQuery();
		ArrayList<Fiscal> lstFiscal = new ArrayList<Fiscal>();
		Fiscal fiscal = null;
		
		while (rs.next()) {
			fiscal = new Fiscal();
			
			fiscal.setId(rs.getInt("ID"));
			
			fiscal.setMes(rs.getInt("MES"));
			fiscal.setAno(rs.getInt("ANO"));
			fiscal.setDataEnvio(rs.getDate("DATA_ENVIO"));
			fiscal.setModificado(rs.getDate("MODIFICADO"));
			
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = clienteDAO.selectPerID(rs.getInt("CLIENTE_ID"));

			StatusDAO statusDAO = new StatusDAO();
			Status status = statusDAO.selectPerID(rs.getInt("STATUS_ID"));

			fiscal.setCliente(cliente);
			fiscal.setStatus(status);

			lstFiscal.add(fiscal);
		}
		rs.close();
		conn.close();

		return lstFiscal;
	}
	
}
