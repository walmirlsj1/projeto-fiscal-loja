package Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import Model.Cliente;
import Model.Fiscal;
import Model.Status;
import Model.DAO.ClienteDAO;
import Model.DAO.FiscalDAO;
import Model.DAO.StatusDAO;

public class GeradorFiscal {
	private ArrayList<Cliente> lstCliente;
	private Calendar calendario;
	
	public GeradorFiscal() {
		calendario = Calendar.getInstance();
		
	}
	
	public GeradorFiscal(ArrayList<Cliente> lstCliente) {
		calendario = Calendar.getInstance();
		this.lstCliente = lstCliente;
		
	}
	
	public int gerarFiscal() {
		FiscalDAO fiscalDAO = new FiscalDAO();
		StatusDAO statusDAO = new StatusDAO();
		
		Status status_pendente = null;
		Status status_arquivos_na_pasta = null;
		
		LoadingFile folder_verification = new LoadingFile();
		ArrayList<Fiscal> lstFiscal = null;
		
		Fiscal fiscal;
		
		int contador = 0;
		
		try {
			status_pendente = statusDAO.selectPerID(1);
			status_arquivos_na_pasta = statusDAO.selectPerID(2);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (Cliente cliente : lstCliente) {
			try {
				lstFiscal = fiscalDAO.selectFilterClienteMesAno(cliente, getMesAtual(), getAnoAtual());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Ocorreu um erro no BD. GERADOR_FISCAL");
			}
			
			if(lstFiscal.isEmpty()) {
				contador++;
				fiscal = new Fiscal();
				
				fiscal.setCliente(cliente);
				
				if(folder_verification.findFolder(cliente.getSerial())) {
					fiscal.setStatus(status_arquivos_na_pasta);
				} else {
					fiscal.setStatus(status_pendente);
				}
				
				fiscal.setDataEnvio(null);
				fiscal.setMes(getMesAtual());
				fiscal.setAno(getAnoAtual());
				fiscal.setModificado(calendario.getTime());
				
				try {
					fiscalDAO.insert(fiscal);
					System.out.println(" Cliente " + cliente.getSerial() + " cadastrado com sucesso");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				fiscal = lstFiscal.get(0);
				boolean encontrei_arquivos = folder_verification.findFolder(cliente.getSerial());
				if (fiscal.getStatus().getId() == 1 && encontrei_arquivos) {
					fiscal.setStatus(status_arquivos_na_pasta);
					try {
						fiscalDAO.update(fiscal);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if(fiscal.getStatus().getId() != 1 && !encontrei_arquivos) {
					fiscal.setStatus(status_pendente);
					try {
						fiscalDAO.update(fiscal);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
//		if(contador == 0) {
//			System.out.println("Não à registros a serem gravados na tabela fiscal");
//		} else {
//			System.out.println("registros gravados na tabela fiscal com sucesso");	
//		}
		return contador;
	}
	
	public int gerarFiscal(ArrayList<Cliente> lstCliente, int mes, int ano) {
		FiscalDAO fiscalDAO = new FiscalDAO();
		StatusDAO statusDAO = new StatusDAO();

		Status status_pendente = null;
		Status status_arquivos_na_pasta = null;

		LoadingFile folder_verification = new LoadingFile();
		ArrayList<Fiscal> lstFiscal = null;

		Fiscal fiscal;

		int contador = 0;

		try {
			status_pendente = statusDAO.selectPerID(1);
			status_arquivos_na_pasta = statusDAO.selectPerID(2);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (Cliente cliente : lstCliente) {
			try {
				lstFiscal = fiscalDAO.selectFilterClienteMesAno(cliente, mes, ano);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Ocorreu um erro no BD. GERADOR_FISCAL");
			}

			if (lstFiscal.isEmpty()) {
				contador++;
				fiscal = new Fiscal();

				fiscal.setCliente(cliente);

				if (folder_verification.findFolder(cliente.getSerial(), mes, ano)) {
					fiscal.setStatus(status_arquivos_na_pasta);
				} else {
					fiscal.setStatus(status_pendente);
				}

				fiscal.setDataEnvio(null);
				fiscal.setMes(mes);
				fiscal.setAno(ano);
				fiscal.setModificado(calendario.getTime());

				try {
					fiscalDAO.insert(fiscal);
					System.out.println(" Cliente " + cliente.getSerial() + " cadastrado com sucesso");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				fiscal = lstFiscal.get(0);
				boolean encontrei_arquivos = folder_verification.findFolder(cliente.getSerial(), mes, ano);
				if (fiscal.getStatus().getId() == 1 && encontrei_arquivos) {
					fiscal.setStatus(status_arquivos_na_pasta);
					try {
						fiscalDAO.update(fiscal);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (fiscal.getStatus().getId() != 1 && !encontrei_arquivos) {
					fiscal.setStatus(status_pendente);
					try {
						fiscalDAO.update(fiscal);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return contador;
	}
	public String obterMesString(int mes) {
		String[] meses = { "1JAN", "2FEV", "3MAR", "4ABR", "5MAI", "6JUN", "7JUL", "8AGO", "9SET", "10OUT", "11NOV",
				"12DEZ" };
		return meses[mes];
	}
	
	public int getAnoAtual() {
		return calendario.get(Calendar.YEAR);
	}

	public int getMesAtual() {
		return calendario.get(Calendar.MONTH);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ClienteDAO clienteDAO = new ClienteDAO();

		ArrayList<Cliente> lstClientes = null;

		try {
			lstClientes = clienteDAO.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GeradorFiscal gerFiscal = new GeradorFiscal(lstClientes);
		System.out.println("Fiscal registrados: " + gerFiscal.gerarFiscal());
		
	}

}
