package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Status {
	private int id;
	private String descricao;
	private Usuario usuario;
	private Date modificado;
	
	
	public Status() {
		
	}
	
	public Status(int id, String descricao, Usuario usuario) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.modificado = new Date();
		this.usuario = usuario;
	}
	
	public Status(int id, String descricao, Usuario usuario, String modificado) {
		super();
		this.id = id;
		this.descricao = descricao;
//		this.modificado = modificado;
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
	
	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
	
	public Date getModificado() { return modificado; }
	public void setModificado(Date modificado) { this.modificado = modificado; }
	
	public Usuario getUsuario() { return usuario; }
	public void setUuario(Usuario usuario) { this.usuario = usuario; }

}
