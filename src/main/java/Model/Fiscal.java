package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Fiscal {
	private int id;
	private Cliente cliente;
	private int ano;
	private int mes;
	private Status status;
	private Date dataEnvio;
	private Date modificado;
	
	public Fiscal() {
		
	}
	public Fiscal(int id, Cliente cliente, int ano, int mes, Status status, String dataEnvio, String modificado) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.ano = ano;
		this.mes = mes;
		this.status = status;
		try {
			this.modificado = new SimpleDateFormat("dd/MM/yyyy").parse(modificado);
			this.dataEnvio = new SimpleDateFormat("dd/MM/yyyy").parse(dataEnvio);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(Fiscal.class.getName()).log(Level.SEVERE, null, e);
//			e.printStackTrace();
		}
	}
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public Cliente getCliente() { return cliente; }
	public void setCliente(Cliente cliente) { this.cliente = cliente; }
	
	public int getAno() { return ano; }
	public void setAno(int ano) { this.ano = ano; }
	
	public int getMes() { return mes; }
	public void setMes(int mes) { this.mes = mes; }
	
	public Status getStatus() { return status; }
	public void setStatus(Status status) { this.status = status; }
	
	public Date getDataEnvio() { return dataEnvio; }
	public void setDataEnvio(Date dataEnvio) { this.dataEnvio = dataEnvio; }
	
	public Date getModificado() { return modificado; }
	public void setModificado(Date modificado) { this.modificado = modificado; }
	
}
