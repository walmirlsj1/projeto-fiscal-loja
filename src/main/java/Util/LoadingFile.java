package Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.configuration2.ex.ConfigurationException;

import Model.Cliente;

import Model.DAO.ClienteDAO;

public class LoadingFile {

	private String path = "C:\\GDOOR\\Backups\\";
	private ArrayList<Cliente> lstCliente;

	private Calendar calendario;

	public LoadingFile() {
		path = null;
		try {
			path = Config.getConfiguracao().getString("path_clientes");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		calendario = Calendar.getInstance();
	}
	
	public LoadingFile(ArrayList<Cliente> lstCliente) {
		calendario = Calendar.getInstance();
		this.lstCliente = lstCliente;
		int i = 0;
		for (Cliente cliente : lstCliente) {
			System.out.println("Teste " + (++i) + " " + cliente.getSerial() + " -- " + cliente.getNome()
					+ " Ok: " + findFolder(cliente.getSerial()));
//			findFolder(cliente);
		}
	}

	public boolean findFolder(String serial) {

		String path_folder = getPathFolder(serial);

//		File folder = new File(path_folder);
		System.out.println(path_folder);
		try (Stream<Path> walk = Files.walk(Paths.get(path_folder))) {
			List<String> result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());

			// result.forEach(System.out::println);
			if (!result.isEmpty()) {
				return true;
			}

		} catch (IOException e) {
			
			// e.printStackTrace();
		}
		return false;
	}
	
	public boolean findFolder(String serial, int mes, int ano) {

		String path_folder = getPathFolder(serial, mes, ano);

//		File folder = new File(path_folder);

		try (Stream<Path> walk = Files.walk(Paths.get(path_folder))) {
			List<String> result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());

			// result.forEach(System.out::println);
			if (!result.isEmpty()) {
				return true;
			}

		} catch (IOException e) {
			
			// e.printStackTrace();
		}
		return false;
	}
	
	public List<String> getFilesFolder(String serial) {

		String path_folder = getPathFolder(serial);

		try (Stream<Path> walk = Files.walk(Paths.get(path_folder))) {
			List<String> result = walk.filter(Files::isRegularFile).map(x -> x.getFileName().toString()).collect(Collectors.toList());

			// result.forEach(System.out::println);
			if (!result.isEmpty()) {
				return result;
			}

		} catch (IOException e) {
			
			// e.printStackTrace();
		}
		return null;
	}
	
	public List<Path> getFilesFolderPath(String serial) {

		String path_folder = getPathFolder(serial);
//		System.out.println("::: Pastas -- >> " + path_folder);
		try (Stream<Path> walk = Files.walk(Paths.get(path_folder))) {
			List<Path> result = walk.filter(Files::isRegularFile).map(x -> x).collect(Collectors.toList());

			// result.forEach(System.out::println);
			if (!result.isEmpty()) {
				return result;
			}

		} catch (IOException e) {
			
//			 e.printStackTrace();
		}
		return null;
	}
	
	public boolean criarPastaMensal(Cliente cliente) {
		String path_folder = getPathFolder(cliente.getSerial());
		File folder = new File(path_folder);
		boolean bool = folder.mkdirs();
		if (bool) {
//			Logger.getLogger("Directory created successfully");
			return true;
		} else {
			Logger.getLogger("Sorry couldn’t create specified directory");
		}
		return false;
	}
	
	public String getPathFolder(String serial) {
		return path + serial + "\\Fiscal\\mes_" + getMesAtual() + "_"	+ getAnoAtual() + "\\";
	}

	public String getPathFolder(String serial, int mes, int ano) {
		return path + serial + "\\Fiscal\\mes_" + mes + "_"	+ ano + "\\";
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

		new LoadingFile(lstClientes);
	}

}
