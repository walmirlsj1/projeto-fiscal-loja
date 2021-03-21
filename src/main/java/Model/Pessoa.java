package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pessoa {
	private int id;
	private String nome;
	private String telefone;
	private String email;
	private Boolean ativo;
	private Date modificado;
	
	public Pessoa() {
		
	}
	
	public Pessoa(int id, String nome, String telefone, String email, Boolean ativo, String modificado) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.ativo = ativo;
		if(!modificado.isEmpty()) {
			this.modificado = new Date();
		} else {
			try {
				this.modificado = new SimpleDateFormat("dd/MM/yyyy").parse(modificado);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				Logger.getLogger(Fiscal.class.getName()).log(Level.SEVERE, null, e);
//				e.printStackTrace();
			}
		}
	}
	
	public Pessoa(int id) {
		this.id = id;
		this.modificado = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getModificado() { return modificado; }
	public void setModificado(Date modificado) { this.modificado = modificado; }

	public Boolean getAtivo() {
		return ativo;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
