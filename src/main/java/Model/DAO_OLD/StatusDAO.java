package Model.DAO_OLD;

import java.util.ArrayList;

import Model.Status;

public class StatusDAO {

    
    /**
     * Insere um status dentro do banco de dados
     * @param status exige que seja passado um objeto do tipo status
     */
    public void insert(Status status){
        Banco.status.add(status);
    }
    
    /**
     * Atualiza um Objeto no banco de dados
     * @param status
     * @return 
     */
    public boolean update(Status status){
        
        for (int i = 0; i < Banco.status.size(); i++) {
            if(idSaoIguais(Banco.status.get(i),status)){
                Banco.status.set(i, status);
                return true;
            }
        }
        return false;      

    }
    
    /**
     * Deleta um objeto do banco de dados pelo id do status passado
     * @param status
     * @return 
     */
    public boolean delete(Status status){
        for (Status statusLista : Banco.status) {
            if(idSaoIguais(statusLista,status)){
                Banco.status.remove(statusLista);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Retorna um arraylist com todos os statuss do banco de dados
     * @return uma lista com todos os registros do banco
     */
    public ArrayList<Status> selectAll(){
        return Banco.status;
    }
    
    
    public Status selectPerID(int id){
    	for (Status statusLista : Banco.status) {
            if(id == statusLista.getId()){
                return statusLista;
            }
        }
    	return null;
    }
    
    
    /**
     * Compara se dois objetos tem a propriedade id igual
     * @param status
     * @param statusAComparar
     * @return verdadeiro caso os id forem iguais e falso se nao forem
     */
    private boolean idSaoIguais(Status status, Status statusAComparar) {
        return status.getId() ==  statusAComparar.getId();
    }
}
