package Model;

public class Usuario extends Pessoa{

	private String username;
	private String passwd;
	private String nivelAcesso;
	
	public Usuario() {
		super();
	}
	
	public Usuario(int id, String username, String passwd) {
		super(id);
		this.username = username;
		this.passwd = passwd;
	}
	
	public Usuario(int id, String nome, String username, String passwd, String telefone, String email, Boolean ativo, String modificado, String nivelAcesso) {
		super(id, nome, telefone, email, ativo, modificado);	
		this.username = username;
		this.passwd = passwd;
//		this.modificado = modificado;
		this.nivelAcesso = nivelAcesso;
		
	}
	
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	
	public String getPasswd() { return passwd; }
	public void setPasswd(String passwd) { this.passwd = passwd; }
	
	public String getNivelAcesso() { return nivelAcesso; }
	public void setNivelAcesso(String nivelAcesso) { this.nivelAcesso = nivelAcesso; }
	
}
