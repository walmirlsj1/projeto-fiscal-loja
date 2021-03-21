package Model.DAO_OLD;

import java.util.ArrayList;

import Model.Cliente;
import Model.Contador;


public class ContadorDAO {
    
    
    /**
     * Insere um contador dentro do banco de dados
     * @param contador exige que seja passado um objeto do tipo contador
     */
    public void insert(Contador contador){
        Banco.contador.add(contador);
    }
    
    /**
     * Atualiza um Objeto no banco de dados
     * @param contador
     * @return 
     */
    public boolean update(Contador contador){
        
        for (int i = 0; i < Banco.contador.size(); i++) {
            if(idSaoIguais(Banco.contador.get(i),contador)){
                Banco.contador.set(i, contador);
                return true;
            }
        }
        return false;      

    }
    
    /**
     * Deleta um objeto do banco de dados pelo id do contador passado
     * @param contador
     * @return 
     */
    public boolean delete(Contador contador){
        for (Contador contadorLista : Banco.contador) {
            if(idSaoIguais(contadorLista,contador)){
                Banco.contador.remove(contadorLista);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Retorna um arraylist com todos os contadors do banco de dados
     * @return uma lista com todos os registros do banco
     */
    public ArrayList<Contador> selectAll(){
        return Banco.contador;
    }
    public ArrayList<Contador> selectFilter(String value, String filter){
    	ArrayList<Contador> lstContador = new ArrayList<Contador>();
    	System.out.println("Value: " + value + " Filter: " + filter);
    	for (Contador contador : Banco.contador) {
            if(contador.getNome().contains(value)){
            	lstContador.add(contador);
            }
        }
    	
    	return lstContador;
    }
    
    public Contador selectPerID(int id){
    	for (Contador contador1 : Banco.contador) {
            if(id == contador1.getId()){
                return contador1;
            }
        }
    	return null;
    }
    
    /**
     * Compara se dois objetos tem a propriedade id igual
     * @param contador
     * @param contadorAComparar
     * @return verdadeiro caso os id forem iguais e falso se nao forem
     */
    private boolean idSaoIguais(Contador contador, Contador contadorAComparar) {
        return contador.getId() ==  contadorAComparar.getId();
    }
    
    
    
}