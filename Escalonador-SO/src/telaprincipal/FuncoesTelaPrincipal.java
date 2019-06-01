/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaprincipal;

import escalonador.processos.Cpu;
import escalonador.processos.Escalonadores;
import escalonador.processos.ProcessarArquivo;
import escalonador.processos.Processos;
import java.util.ArrayList;

/**
 *
 * @author aliss
 */
public class FuncoesTelaPrincipal {

    private ArrayList<Processos> lista;
    private ArrayList<Processos> fila0;
    private ArrayList<Processos> fila1;
    private ArrayList<Processos> fila2;
    private ArrayList<Processos> fila3;
    
    private final int memoriaPrincipal = 16384; //EM MB
    private int memoriaUtilizada = 0; //EM MB
    
    private Cpu cpu0;
    private Cpu cpu1;
    private Cpu cpu2;
    private Cpu cpu3;
    private Escalonadores escalonador;
    
    public FuncoesTelaPrincipal(){
        
        this.lista = ProcessarArquivo.getArrayList();
        
        fila0 = new ArrayList<>();
        fila1 = new ArrayList<>();
        fila2 = new ArrayList<>();
        fila3 = new ArrayList<>();  
        
        cpu0 = new Cpu();
        cpu1 = new Cpu();
        cpu2 = new Cpu();
        cpu3 = new Cpu();
        
        escalonador = new Escalonadores(fila0, fila1, fila2, fila3, cpu0, cpu1, cpu2, cpu3);
        
    }
    
    public Escalonadores getEscalonador(){
        
        return escalonador;
        
    }
    
    public int getMemoriaPrincipal() {
        return memoriaPrincipal;
    }

    public int getMemoriaUtilizada() {
        return memoriaUtilizada;
    }

    public void setMemoriaUtilizada(int memoriaUtilizada) {
        this.memoriaUtilizada = memoriaUtilizada;
    }
     
    
    public ArrayList<Processos> getArrayList(){
        
        return lista;
        
    }
    
    public void incrementarMemoriaUsada(int memoria){
        
        setMemoriaUtilizada(getMemoriaUtilizada() + memoria);
        
    }
    
    
    public void colocarNaFilaDePrioridade(Processos processo){
        
        switch(processo.getPriority()){
            
                case 0:
                    fila0.add(processo);
                    break;
                case 1:
                    fila1.add(processo);
                    break;
                case 2:
                    fila1.add(processo);
                    break;
                case 3:
                    fila1.add(processo);
                    break;
                default:
                    break;                       
        }
                
    }
    
    public String concatenar_fila(ArrayList<Processos> lista){
            
        String texto = "ID: ";
        String aux;
        
        for (int i = 0; i < lista.size(); i++){
            
            
            aux = Integer.toString(lista.get(i).getID());
            
            texto = texto.concat(" - " + aux);
            
        }
        
        return texto;
            
    }
    
    public void atualizaListaDeChegada(ArrayList<Processos> lista, int tempoAtual){
        
        for (Processos i: lista){
            
            if (i.getArrival_time() == tempoAtual){
                
                colocarNaFilaDePrioridade(i);
                incrementarMemoriaUsada(i.getTamanho());

            }  
        }
        
        TelaPrincipal.setFila0(concatenar_fila(fila0));
        TelaPrincipal.setFila1(concatenar_fila(fila1));
        TelaPrincipal.setFila2(concatenar_fila(fila2));
        TelaPrincipal.setFila3(concatenar_fila(fila3));
        
     
    }

}
