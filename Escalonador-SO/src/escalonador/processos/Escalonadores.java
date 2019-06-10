/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escalonador.processos;

import java.util.ArrayList;
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
    private static ArrayList<Processos> fila_pronto_suspenso;
    private Cpu cpu0;
    private Cpu cpu1;
    private Cpu cpu2;
    private Cpu cpu3;
    
    private static int quantum;
    
    public Escalonadores(){
    
        quantum = 2;
        
    }
    
    public Escalonadores(ArrayList<Processos> fila0, ArrayList<Processos> fila1, ArrayList<Processos> fila2, ArrayList<Processos> fila3, ArrayList<Processos> filaProntoSuspenso, Cpu cpu0, Cpu cpu1, Cpu cpu2, Cpu cpu3){
        
        Escalonadores.fila0 = fila0;
        Escalonadores.fila1 = fila1;
        Escalonadores.fila2 = fila2;
        Escalonadores.fila3 = fila3;
        Escalonadores.fila_pronto_suspenso = filaProntoSuspenso;
        
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
    public static void setFila2(ArrayList<Processos> fila2){
        Escalonadores.fila2 = fila2;
    }
    public static void setFila3(ArrayList<Processos> fila3){
        Escalonadores.fila3 = fila3;
    }
    public static void setFilaProntoSuspenso(ArrayList<Processos> fila_pronto_suspenso){
        Escalonadores.fila_pronto_suspenso = fila_pronto_suspenso;
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
    public static ArrayList<Processos> getFilaProntoSuspenso(){
        return fila_pronto_suspenso;
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
            
            //SE FILA PRIORITÁRIA POSSUIR PROCESSO, ELA É CHAMADA PRIMEIRO EXECUTANDO POR POLITICA FCFS
            if (Escalonadores.fila0.size() > 0){
                
                FCFS(encontraCpuVazia());

            }
            //CASO CONTRÁRIO É CHAMADO ESCALONADOR DE POLITICA FEEDBACK
            else if ((Escalonadores.fila1.size() > 0) || (Escalonadores.fila2.size() > 0) || (Escalonadores.fila3.size() > 0)){

                FeedBack(encontraCpuVazia());
                
            }
        }
        
        //VERIFICA SE TEM CPU VAZIA E SE TEM PROCESSO EM ALGUMA LISTA, SE TIVER, CHAMA A FUNCAO ESCALONAR RECURSIVAMENTE ATE A VERIFICACAO SER FALSA
        if (encontraCpuVazia() != null && (!fila0.isEmpty() || !fila1.isEmpty() || !fila2.isEmpty() || !fila3.isEmpty())){
            escalonar();
        }
        //SE TODOS CPUS ESTÃO OCUPADOS E/OU NÃO TEM PROCESSOS EM FILAS
        else{
            
            //EXECUTA PARALELAMENTE (CASO OUVER) OS PROCESSOS EM CADA CPU

            atualizaLabel();
            
            cpu0.executaProcessoAtual();
            cpu1.executaProcessoAtual();
            cpu2.executaProcessoAtual();
            cpu3.executaProcessoAtual();

            atualizaLabel();
            
            /*
            if (encontraCpuVazia() != null && (!fila0.isEmpty() || !fila1.isEmpty() || !fila2.isEmpty() || !fila3.isEmpty())){
                escalonar();
            }
            */
        }
        
    }
    
    //RETORNA QUAL CPU ESTA SENDO USADO
    public int qualCpu(Cpu cpu){
        
        if (cpu == cpu0) return 0;
        
        if (cpu == cpu1) return 1;
        
        if (cpu == cpu2) return 2;
        
        if (cpu == cpu3) return 3;
        
        
        //RETORNA COM ERRO
        return -1;
        
    }
    
    //ATUALIZA LABEL DOS PROCESSADORES
    public void atualizaLabel(){
        
        if (cpu0.estaLivre()){
            TelaPrincipal.setLblCpus("vazio" , qualCpu(cpu0));
        }
        else{
            if (cpu0.getProcessoAtual() != null)
                TelaPrincipal.setLblCpus(Integer.toString(cpu0.getProcessoAtual().getID()), qualCpu(cpu0));
        }
        
        if (cpu1.estaLivre()){
            TelaPrincipal.setLblCpus("vazio" , qualCpu(cpu1));
        }
        else{
            if (cpu1.getProcessoAtual() != null)
                TelaPrincipal.setLblCpus(Integer.toString(cpu1.getProcessoAtual().getID()), qualCpu(cpu1));
        }
        
        if (cpu2.estaLivre()){
            TelaPrincipal.setLblCpus("vazio" , qualCpu(cpu2));
        }
        else{
            if (cpu2.getProcessoAtual() != null)
                TelaPrincipal.setLblCpus(Integer.toString(cpu2.getProcessoAtual().getID()), qualCpu(cpu2));
        }
        
        if (cpu3.estaLivre()){
            TelaPrincipal.setLblCpus("vazio" , qualCpu(cpu3));
        }
        else{
            if (cpu3.getProcessoAtual() != null)
                TelaPrincipal.setLblCpus(Integer.toString(cpu3.getProcessoAtual().getID()), qualCpu(cpu3));
        }

    }
    
    //ESCALONADOR DE POLITICA FCFS
    public void FCFS(Cpu cpu){
        
        cpu.setFimProcessamento(false);
        cpu.setProcessoAtual(fila0.get(0));
        fila0.remove(fila0.get(0));
        TelaPrincipal.setLblLog("• Processo com ID " + cpu.getProcessoAtual().getID() + " sendo executado no processador " + qualCpu(cpu) + " com política FCFS.");
                
    }
    
    //ESCALONADOR DE POLITICA FEEDBACK
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
            
            TelaPrincipal.setLblLog("• Processo com ID " + cpu.getProcessoAtual().getID() + " sendo executado no processador " + qualCpu(cpu)+ " com política Feedback.");

            cpu.setTempoExecutando(0);
        }
    }
    
}
