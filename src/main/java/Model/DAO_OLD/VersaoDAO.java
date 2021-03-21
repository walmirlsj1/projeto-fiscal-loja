package Model.DAO_OLD;


import java.util.ArrayList;

import Model.Versao;


public class VersaoDAO {
    
    
    /**
     * Insere um versao dentro do banco de dados
     * @param versao exige que seja passado um objeto do tipo versao
     */
    public void insert(Versao versao){
        Banco.versao.add(versao);
    }
    
    /**
     * Atualiza um Objeto no banco de dados
     * @param versao
     * @return 
     */
    public boolean update(Versao versao){
        
        for (int i = 0; i < Banco.versao.size(); i++) {
            if(idSaoIguais(Banco.versao.get(i),versao)){
                Banco.versao.set(i, versao);
                return true;
            }
        }
        return false;      

    }
    
    /**
     * Deleta um objeto do banco de dados pelo id do versao passado
     * @param versao
     * @return 
     */
    public boolean delete(Versao versao){
        for (Versao versaoLista : Banco.versao) {
            if(idSaoIguais(versaoLista,versao)){
                Banco.versao.remove(versaoLista);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Retorna um arraylist com todos os versaos do banco de dados
     * @return uma lista com todos os registros do banco
     */
    public ArrayList<Versao> selectAll(){
        return Banco.versao;
    }
    
    
    public Versao selectPerID(int id){
    	for (Versao versaoLista : Banco.versao) {
            if(id == versaoLista.getId()){
                return versaoLista;
            }
        }
    	return null;
    }
    
    
    /**
     * Compara se dois objetos tem a propriedade id igual
     * @param versao
     * @param versaoAComparar
     * @return verdadeiro caso os id forem iguais e falso se nao forem
     */
    private boolean idSaoIguais(Versao versao, Versao versaoAComparar) {
        return versao.getId() ==  versaoAComparar.getId();
    }
    
    
    
}