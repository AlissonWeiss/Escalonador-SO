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
import javax.swing.JOptionPane;

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
    private ArrayList<Processos> fila_pronto_suspenso;
                
    
    private final int memoriaPrincipal = 16384; //EM MB
    private int memoriaUtilizada = 0; //EM MB
    private int memoriaLivre = memoriaPrincipal; //EM MB
    
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
        TelaPrincipal.setLblLog("Escalonador Inicializado!");

        
    }
    
    //GETTERS & SETTERS
    
    public Escalonadores getEscalonador(){
        
        return escalonador;
        
    }
    
    public int getMemoriaLivre(){
        return memoriaLivre;
    }
    
    public void setMemoriaLivre(int memoriaLivre){
        this.memoriaLivre = memoriaLivre;
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
       
    
    //METODOS
    public void colocarNaFilaDePrioridade(Processos processo){
        
        
        
        switch(processo.getPriority()){
            
                case 0:
                    fila0.add(processo);
                    TelaPrincipal.setLblLog("-> Processo com ID " + processo.getID() + " adicionado a fila 0.");
                    break;
                case 1:
                    fila1.add(processo);
                    TelaPrincipal.setLblLog("-> Processo com ID " + processo.getID() + " adicionado a fila 1.");
                    break;
                case 2:
                    fila1.add(processo);                    
                    TelaPrincipal.setLblLog("-> Processo com ID " + processo.getID() + " adicionado a fila 1.");
                    break;
                case 3:
                    fila1.add(processo);
                    TelaPrincipal.setLblLog("-> Processo com ID " + processo.getID() + " adicionado a fila 1.");
                    break;
                default:
                    break;                       
        }
                
    }
    
    void calcularMemoriaLivre(){
        
        setMemoriaLivre(getMemoriaPrincipal() - getMemoriaUtilizada());
        TelaPrincipal.setLblMemoriaLivre(Integer.toString(getMemoriaLivre()));
        
    }
    
    public void calcularMemoriaUtilizada(){
        
        int aux_memoria = 0;
        
        if (!cpu0.estaLivre()) aux_memoria += cpu0.getProcessoAtual().getTamanho();

        if (!cpu1.estaLivre()) aux_memoria += cpu1.getProcessoAtual().getTamanho();

        if (!cpu2.estaLivre()) aux_memoria += cpu2.getProcessoAtual().getTamanho();

        if (!cpu3.estaLivre()) aux_memoria += cpu3.getProcessoAtual().getTamanho();

        if (!fila0.isEmpty()){
            for (Processos processo : fila0){
                aux_memoria += processo.getTamanho();
            }
        }
        
        
        if (!fila1.isEmpty()){
            for (Processos processo : fila1){
                aux_memoria += processo.getTamanho();
            }
        }
        
        if (!fila2.isEmpty()){
            for (Processos processo : fila2){
                aux_memoria += processo.getTamanho();
            }
        }
        
        
        if (!fila3.isEmpty()){
            for (Processos processo : fila3){
                aux_memoria += processo.getTamanho();
            }
        }
        
        setMemoriaUtilizada(aux_memoria);
        
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
    
    public void atualizarListasLBL(){
        TelaPrincipal.setFila0(concatenar_fila(fila0));
        TelaPrincipal.setFila1(concatenar_fila(fila1));
        TelaPrincipal.setFila2(concatenar_fila(fila2));
        TelaPrincipal.setFila3(concatenar_fila(fila3));
    }
    
    public void setLblDiscosEimpressoras(){
        
        TelaPrincipal.setLblDisco(Integer.toString(TelaPrincipal.getContDisco()));
        TelaPrincipal.setLblImpressora(Integer.toString(TelaPrincipal.getContImpressora()));
        
    }
    
    
    public void atualizaListaDeChegada(ArrayList<Processos> lista, int tempoAtual){
        
        ArrayList<Processos> aux = new ArrayList<>();
        
        for (Processos i: lista){
            
            if (i.getArrival_time() <= tempoAtual){
                
                if (i.getImpressora() == 0 && i.getDisco() == 0){
                    
                    colocarNaFilaDePrioridade(i);
                    aux.add(i);
                    
                }
                
                else if (i.getImpressora() != 0 && i.getDisco() != 0){
                    
                    //CASO TESTE COM 2 IMPRESSORAS
                    if (TelaPrincipal.getContImpressora() == 0 && i.getImpressora() == 2){
                        if (TelaPrincipal.getContDisco() == 0 && i.getDisco() == 2){
                            TelaPrincipal.setContImpressora(TelaPrincipal.getContImpressora() + i.getImpressora());
                            TelaPrincipal.setContDisco(TelaPrincipal.getContDisco() + i.getDisco());
                            colocarNaFilaDePrioridade(i);
                            aux.add(i);
                        }
                        else if (TelaPrincipal.getContDisco() < 2 && i.getDisco() == 1){
                            TelaPrincipal.setContImpressora(TelaPrincipal.getContImpressora() + i.getImpressora());
                            TelaPrincipal.setContDisco(TelaPrincipal.getContDisco() + i.getDisco());
                            colocarNaFilaDePrioridade(i);
                            aux.add(i);
                        }
                        
                    }
                    if (TelaPrincipal.getContImpressora() < 2 && i.getImpressora() == 1){
                        if (TelaPrincipal.getContDisco() == 0 && i.getDisco() == 2){
                            TelaPrincipal.setContImpressora(TelaPrincipal.getContImpressora() + i.getImpressora());
                            TelaPrincipal.setContDisco(TelaPrincipal.getContDisco() + i.getDisco());
                            colocarNaFilaDePrioridade(i);
                            aux.add(i);
                        }
                        else if (TelaPrincipal.getContDisco() < 2 && i.getDisco() == 1){
                            TelaPrincipal.setContImpressora(TelaPrincipal.getContImpressora() + i.getImpressora());
                            TelaPrincipal.setContDisco(TelaPrincipal.getContDisco() + i.getDisco());
                            colocarNaFilaDePrioridade(i);
                            aux.add(i);                  
                        }
                    }
                }
                
                else if (i.getImpressora() != 0){
                    
                    if (TelaPrincipal.getContImpressora() == 0 && i.getImpressora() == 2){
                        TelaPrincipal.setContImpressora(TelaPrincipal.getContImpressora() + i.getImpressora());
                        colocarNaFilaDePrioridade(i);
                        aux.add(i);
                    }
                    else if (TelaPrincipal.getContImpressora() < 2 && i.getImpressora() == 1){
                        TelaPrincipal.setContImpressora(TelaPrincipal.getContImpressora() + i.getImpressora());
                        colocarNaFilaDePrioridade(i);
                        aux.add(i);
                    }
                      
                    
                }
                else if (i.getDisco() != 0){
                    
                    if (TelaPrincipal.getContDisco() == 0 && i.getDisco() == 2){
                        TelaPrincipal.setContDisco(TelaPrincipal.getContDisco() + i.getDisco());
                        colocarNaFilaDePrioridade(i);
                        aux.add(i);
                    }
                    else if (TelaPrincipal.getContDisco() < 2 && i.getDisco() == 1){
                        TelaPrincipal.setContDisco(TelaPrincipal.getContDisco() + i.getDisco());
                        colocarNaFilaDePrioridade(i);
                        aux.add(i);
                    }
                    
                }
                
                
                
                
//                if (TelaPrincipal.getContImpressora() < 2 && i.getImpressora() == 1){
//                    TelaPrincipal.setContImpressora(TelaPrincipal.getContImpressora() + i.getImpressora());
//                    JOptionPane.showMessageDialog(null, TelaPrincipal.getContImpressora());
//                    colocarNaFilaDePrioridade(i);
//                    aux.add(i);
//                }
//                else if (TelaPrincipal.getContImpressora() == 0 && i.getImpressora() == 2){
//                    TelaPrincipal.setContImpressora(TelaPrincipal.getContImpressora() + i.getImpressora());
//                    colocarNaFilaDePrioridade(i);
//                    aux.add(i);
//                }
//                else if (i.getImpressora() == 0){
//                    
//                    colocarNaFilaDePrioridade(i);
//                    aux.add(i);
//                }
//                
                
                
                
                
//                if (TelaPrincipal.getContDisco()< 2 && i.getDisco()== 1){
//                    TelaPrincipal.setContDisco(TelaPrincipal.getContDisco()+ i.getDisco());
//                    colocarNaFilaDePrioridade(i);
//                    aux.add(i);
//                }
//                else if (TelaPrincipal.getContDisco()== 0 && i.getDisco() == 2){
//                    TelaPrincipal.setContDisco(TelaPrincipal.getContDisco() + i.getDisco());
//                    colocarNaFilaDePrioridade(i);
//                    aux.add(i);
//                }
//                else if (i.getDisco() == 0){
//                    colocarNaFilaDePrioridade(i);
//                    aux.add(i);
//                }
//                
            }  
        }
        
        for (Processos i: aux){
            
            lista.remove(i);
            
        }
        
        atualizarListasLBL();
     
    }

}
