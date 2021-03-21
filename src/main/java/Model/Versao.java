package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Versao {
	private int id;
	private String versao;
	private Date modificado;
	private Model.Usuario usuario;
	
	public Versao() {
		
	}
	
	public Versao(int id, String versao, Model.Usuario usuario) {
		super();
		this.id = id;
		this.versao = versao;
		this.modificado = new Date();
		this.usuario = usuario;
	}
	
	public Versao(int id, String versao, String modificado, Model.Usuario usuario) {
		super();
		this.id = id;
		this.versao = versao;
		this.usuario = usuario;
		
		try {
			this.modificado = new SimpleDateFormat("dd/MM/yyyy").parse(modificado);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(Fiscal.class.getName()).log(Level.SEVERE, null, e);
//			e.printStackTrace();
		}
	}
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getVersao() { return versao; }
	public void setVersao(String versao) { this.versao = versao; }
	
	public Date getModificado() { return modificado; }
	public void setModificado(Date modificado) { this.modificado = modificado; }
	
	public Usuario getUsuario() { return usuario; }
	public void setUsuario(Usuario usuario) { this.usuario = usuario; }
	
	public String toString() {
		return this.getVersao();
	}
	
}
