package Model.DAO_OLD;

import java.util.ArrayList;

import Model.Cliente;
import Model.Contador;
import Model.Status;
import Model.Versao;
import Model.Usuario;

public class Banco {
    
    public static ArrayList<Usuario> usuario;
    public static ArrayList<Cliente> cliente;
    public static ArrayList<Contador> contador;
    public static ArrayList<Versao> versao;
    public static ArrayList<Status> status;
//    public static ArrayList<Agendamento> agendamento;
    
    
    public static void inicia(){
    
        //Instancia os Objetos
        usuario = new ArrayList<Usuario>();
        cliente = new ArrayList<Cliente>();
        contador = new ArrayList<Contador>();
        versao = new ArrayList<Versao>();
        status = new ArrayList<Status>();
//        agendamento = new ArrayList<Agendamento>();
        
        //criando elementos
        Usuario usuario1 = new Usuario(1, "NOME 1", "teste", "123", "3291111", "walmir_004@hotmail.com", true, "10/04/1995", "admin");
        Usuario usuario2 = new Usuario(2, "NOME 2", "teste2", "123", "3291111", "walmir_004@hotmail2.com", true, "10/04/1995", "admin");
        
        Versao versao1 = new Versao(1, "GDOOR PRO", "20/01/2020", usuario1);
        Versao versao2 = new Versao(2, "GDOOR SLIM", "20/01/2020", usuario1);
        Versao versao3 = new Versao(3, "GDOOR MEI", "20/01/2020", usuario1);
        
        Contador contador1 = new Contador(1, "Contador 1", "1234523452", "32912664", "teste@teste.com", true, "22/01/2020");
        Contador contador2 = new Contador(2, "Contador 2", "2353462642", "23423423", "teste2@teste.com", true, "23/01/2020");
        Contador contador3 = new Contador(3, "Contador 3", "6575675675", "57567565", "teste3@teste.com", true, "24/01/2020");
        Contador contador4 = new Contador(4, "Contador 4", "8383634534", "75675643", "teste4@teste.com", true, "25/01/2020");
        
        Cliente cliente1 = new Cliente(1, "Cliente 1", "01230123", "45243", "teste@teste.com",  versao1, "GTR123123", contador2, "123123123", true, true, "26/01/2020");
        Cliente cliente2 = new Cliente(2, "Cliente 2", "01230123", "45243", "teste@teste.com",  versao2, "GTR123123", contador1, "123123123", false, true, "26/01/2020");
        Cliente cliente3 = new Cliente(3, "Cliente 3", "01230123", "45243", "teste@teste.com",  versao1, "GTR123123", contador2, "123123123", true, true, "26/01/2020");
        Cliente cliente4 = new Cliente(4, "Cliente 4", "01230123", "45243", "teste@teste.com",  versao2, "GTR123123", contador1, "123123123", true, true, "26/01/2020");
        Cliente cliente5 = new Cliente(5, "Cliente 5", "01230123", "45243", "teste@teste.com",  versao2, "GTR123123", contador4, "123123123", true, true, "26/01/2020");
        Cliente cliente6 = new Cliente(6, "Cliente 6", "01230123", "45243", "teste@teste.com",  versao3, "GTR123123", contador3, "123123123", false, true, "26/01/2020");
        
        
        Status status1 = new Status(1, "Pendente", usuario1, "22/01/2020");
        Status status2 = new Status(2, "Aguardando Envio", usuario1, "22/01/2020");
        Status status3 = new Status(3, "Pronto", usuario1, "22/01/2020");
        Status status4 = new Status(4, "Falha ao enviar", usuario1, "22/01/2020");
        
//        Servico servico1 = new Servico(1, "Corte Simples", 18);
//        Servico servico2 = new Servico(2, "Corte Degrade", 30);
//
//        Agendamento agendamento1 = new Agendamento(1, cliente1, servico2, 30, "14/07/2018 09:30");
//        Agendamento agendamento3 = new Agendamento(3, cliente4, servico1, 18, "14/07/2018 10:30");
        
        //Adiciona Elementos na lista
        usuario.add(usuario1);
        usuario.add(usuario2);
        
        versao.add(versao1);
        versao.add(versao2);
        versao.add(versao3);
        
        contador.add(contador1);
        contador.add(contador2);
        contador.add(contador3);
        contador.add(contador4);
        
        cliente.add(cliente1);
        cliente.add(cliente2);
        cliente.add(cliente3);
        cliente.add(cliente4);
        cliente.add(cliente5);
        cliente.add(cliente6);
        
        
        status.add(status1);
        status.add(status2);
        status.add(status3);
        status.add(status4);
//        servico.add(servico1);
//        servico.add(servico2);
//        
//        agendamento.add(agendamento1);
//        agendamento.add(agendamento2);
        
        
    }
    
    
}
