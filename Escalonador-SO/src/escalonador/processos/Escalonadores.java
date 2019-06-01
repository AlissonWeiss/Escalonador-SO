/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escalonador.processos;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import telaprincipal.TelaPrincipal;

/**
 *
 * @author aliss
 */

public class Escalonadores {

    private static ArrayList<Processos> fila0;
    private static ArrayList<Processos> fila1;
    private static ArrayList<Processos> fila2;
    private static ArrayList<Processos> fila3;
    private Cpu cpu0;
    private Cpu cpu1;
    private Cpu cpu2;
    private Cpu cpu3;
    
    private static int quantum;
    
    public Escalonadores(){
    
        quantum = 2;
        
    }
    
    public Escalonadores(ArrayList<Processos> fila0, ArrayList<Processos> fila1, ArrayList<Processos> fila2, ArrayList<Processos> fila3, Cpu cpu0, Cpu cpu1, Cpu cpu2, Cpu cpu3){
        
        this.fila0 = fila0;
        this.fila1 = fila1;
        this.fila2 = fila2;
        this.fila3 = fila3;
        
        this.cpu0 = cpu0;
        this.cpu1 = cpu1;
        this.cpu2 = cpu2;
        this.cpu3 = cpu3;
        
        quantum = 2;
        
    }
    
    // GETTERS E SETTERS
    
    
    public static int getQuantum() {
        
        return quantum;
        
    }
    public static void setFila1(ArrayList<Processos> fila1){
        Escalonadores.fila1 = fila1;
    }
    public static void setFila2(ArrayList<Processos> fila1){
        Escalonadores.fila2 = fila1;
    }
    public static void setFila3(ArrayList<Processos> fila1){
        Escalonadores.fila3 = fila1;
    }
            
    public static ArrayList<Processos> getFila1(){
        return fila1;
    }    
    public static ArrayList<Processos> getFila2(){
        return fila2;
    }    
    public static ArrayList<Processos> getFila3(){
        return fila3;
    }    
    
    
    public void incrementaQuantum(int quantum) {
        Escalonadores.quantum += quantum;
    }
    
    public Cpu encontraCpuVazia(){
        
        
        if (cpu0.estaLivre()) return cpu0;
        
        else if (cpu1.estaLivre()) return cpu1;
        
        else if (cpu2.estaLivre()) return cpu2;
        
        else if (cpu3.estaLivre()) return cpu3;

        
        //RETORNA NULL CASO NENHUM ESTEJA LIVRE
        return null;
        
    }
    
    
    public void escalonar(){
        
        if (encontraCpuVazia() != null){
            
        
            if (Escalonadores.fila0.size() > 0){
                
                FCFS(encontraCpuVazia());

            }
            if ((Escalonadores.fila1.size() > 0) || (Escalonadores.fila2.size() > 0) || (Escalonadores.fila3.size() > 0)){

                FeedBack(encontraCpuVazia());
                
            }
        }
                    
        if (encontraCpuVazia() != null && (!fila0.isEmpty() || !fila1.isEmpty() || !fila2.isEmpty() || !fila3.isEmpty())){
            escalonar();
        }
        
        cpu0.executaProcessoAtual();
        cpu1.executaProcessoAtual();
        cpu2.executaProcessoAtual();
        cpu3.executaProcessoAtual();
        
        atualizaLabel();
        

        
        }
    
    public int qualCpu(Cpu cpu){
        
        if (cpu == cpu0) return 0;
        
        if (cpu == cpu1) return 1;
        
        if (cpu == cpu2) return 2;
        
        if (cpu == cpu3) return 3;
        
        
        //RETORNA COM ERRO
        return -1;
        
    }
    
    public void atualizaLabel(){
        
        if (cpu0.getFimProcessamento()){
            TelaPrincipal.setLblCpus("vazio" , qualCpu(cpu0));
        }
        else{
            if (cpu0.getProcessoAtual() != null)
                TelaPrincipal.setLblCpus(Integer.toString(cpu0.getProcessoAtual().getID()), qualCpu(cpu0));
        }
        
        if (cpu1.getFimProcessamento()){
            TelaPrincipal.setLblCpus("vazio" , qualCpu(cpu1));
        }
        else{
            if (cpu1.getProcessoAtual() != null)
                TelaPrincipal.setLblCpus(Integer.toString(cpu1.getProcessoAtual().getID()), qualCpu(cpu1));
        }
        
        if (cpu2.getFimProcessamento()){
            TelaPrincipal.setLblCpus("vazio" , qualCpu(cpu2));
        }
        else{
            if (cpu2.getProcessoAtual() != null)
                TelaPrincipal.setLblCpus(Integer.toString(cpu2.getProcessoAtual().getID()), qualCpu(cpu2));
        }
        
        if (cpu3.getFimProcessamento()){
            TelaPrincipal.setLblCpus("vazio" , qualCpu(cpu3));
        }
        else{
            if (cpu3.getProcessoAtual() != null)
                TelaPrincipal.setLblCpus(Integer.toString(cpu3.getProcessoAtual().getID()), qualCpu(cpu3));
        }
        

    }
    
    
    public void FCFS(Cpu cpu){
        
        cpu.setFimProcessamento(false);
        cpu.setProcessoAtual(fila0.get(0));
        
        fila0.remove(fila0.get(0));
                
    }
    
    public void FeedBack(Cpu cpu){
        
        if (cpu != null){
            
            cpu.setFimProcessamento(false);

            if (!fila1.isEmpty()){

                cpu.setProcessoAtual(fila1.get(0));
                fila1.remove(fila1.get(0));
                cpu.setFilaAtual(1);

            }

            else if (!fila2.isEmpty()){

                cpu.setProcessoAtual(fila2.get(0));
                fila2.remove(fila2.get(0));
                cpu.setFilaAtual(2);

            }

            else if (!fila3.isEmpty()){

                cpu.setProcessoAtual(fila3.get(0));
                fila3.remove(fila3.get(0));
                cpu.setFilaAtual(3);

            }
        }
    }
    
}
