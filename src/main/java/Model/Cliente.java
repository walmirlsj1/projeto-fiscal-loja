package Model;

//import javax.validation.constraints.*;


public class Cliente extends Pessoa {
	private String cnpj;
	private Versao versao;
	private String serial;
	private String idRemoto;
	private Boolean enviarDadosContador;
	private Contador contador;
	
	public Cliente() {
		super();
	}
	
	public Cliente(int id) {
		super(id);
	}
	
	public Cliente(int id, String nome, String cnpj, String telefone, String email, Versao versao, String serial, Contador contador, String idRemoto, Boolean enviarDadosContador, Boolean ativo, String modificado) {
		super(id, nome, telefone, email, ativo, modificado);
		this.cnpj = cnpj;
		this.versao = versao;
		this.serial = serial;
		this.contador = contador;
		this.idRemoto = idRemoto;
		this.enviarDadosContador = enviarDadosContador;
	}
	
	public String getCnpj() { return cnpj; }
	public void setCnpj(String cnpj) { this.cnpj = cnpj; }
	
	public Versao getVersao() { return versao; }
	public void setVersao(Versao versao) { this.versao = versao; }
	
	public String getSerial() { return serial; }
	public void setSerial(String serial) { this.serial = serial; }
	
	public Contador getContador() { return contador; }
	public void setContador(Contador contador) { this.contador = contador; }
	
	public String getIdRemoto() { return idRemoto; }

	public void setIdRemoto(String idRemoto) { this.idRemoto = idRemoto; }

	public Boolean getEnviarDadosContador() { return enviarDadosContador; }

	public void setEnviarDadosContador(Boolean enviarDadosContador) { this.enviarDadosContador = enviarDadosContador; }

	@Override
	public String toString() {
		return "Codigo: " + this.getId() + " Nome: " + this.getId() 
				+ " Sistema: " + versao.getVersao() + " Serial: " + this.getSerial()
				+ " Contador: " + this.getContador().getNome()
				+ " RemotoID: " + this.getIdRemoto()
				+ " EnviarDadosContador: " + this.getEnviarDadosContador();
	}
}
