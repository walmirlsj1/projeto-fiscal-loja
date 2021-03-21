package Model.DAO_OLD;

import java.util.ArrayList;

import Model.Usuario;


public class UsuarioDAO {
    
    
    /**
     * Insere um user dentro do banco de dados
     * @param user exige que seja passado um objeto do tipo user
     */
    public void insert(Usuario user){
        Banco.usuario.add(user);
    }
    
    /**
     * Atualiza um Objeto no banco de dados
     * @param user
     * @return 
     */
    public boolean update(Usuario user){
        
        for (int i = 0; i < Banco.usuario.size(); i++) {
            if(idSaoIguais(Banco.usuario.get(i),user)){
                Banco.usuario.set(i, user);
                return true;
            }
        }
        return false;      

    }
    
    /**
     * Deleta um objeto do banco de dados pelo id do user passado
     * @param user
     * @return 
     */
    public boolean delete(Usuario user){
        for (Usuario userLista : Banco.usuario) {
            if(idSaoIguais(userLista,user)){
                Banco.usuario.remove(userLista);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Retorna um arraylist com todos os users do banco de dados
     * @return uma lista com todos os registros do banco
     */
    public ArrayList<Usuario> selectAll(){
        return Banco.usuario;
    }
    /**
     * Retorna um Objeto do tipo usuario se a funcao encontrar o usuario passado como parâmetro no banco, para considerar sao usado nome e senha
     * @param usuario
     * @return Usuario encontrado no banco de dados
     */
    public Usuario selectPorNomeESenha(Usuario usuario){
        for (Usuario usuarioLista : Banco.usuario) {
            if(nomeESenhaSaoIguais(usuarioLista,usuario)){
                return usuarioLista;
            }
        }
        return null;
    }

    /**
     * Recebe dois objetos e verifica se são iguais verificando o nome e senha
     * @param usuario
     * @param usuarioAPesquisar
     * @return verdadeiro caso sejam iguais e falso caso nao forem iguais
     */
    private boolean nomeESenhaSaoIguais(Usuario usuario, Usuario usuarioAPesquisar) {
        return usuario.getUsername().equals(usuarioAPesquisar.getUsername()) && usuario.getPasswd().equals(usuarioAPesquisar.getPasswd());
    }
    /**
     * Compara se dois objetos tem a propriedade id igual
     * @param user
     * @param userAComparar
     * @return verdadeiro caso os id forem iguais e falso se nao forem
     */
    private boolean idSaoIguais(Usuario user, Usuario userAComparar) {
        return user.getId() ==  userAComparar.getId();
    }
    
    
    
}