package Model;

public class Contador extends Pessoa{

	private String cnpj;
	
	public Contador() {
		
	}
	
	public Contador(int id) {
		super(id);
		
	}
	public Contador(int id, String nome, String cnpj, String telefone, String email, Boolean ativo, String modificado) {
		super(id, nome, telefone, email, ativo, modificado);
		this.cnpj = cnpj;
	}

	public String getCnpj() { return cnpj; }
	public void setCnpj(String cnpj) { this.cnpj = cnpj; }

	
	
}
