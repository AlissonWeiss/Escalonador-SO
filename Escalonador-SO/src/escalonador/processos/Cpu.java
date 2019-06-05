package escalonador.processos;

import java.util.ArrayList;
import telaprincipal.TelaPrincipal;

/**
 *
 * @author aliss
 */
public class Cpu {
    
    private Processos processoAtual;
    private int tempoExecutando;
    private boolean fimProcessamento;
    private int fila_atual;
    
    // CONSTRUTORES
    public Cpu() {
    
        this.tempoExecutando = 0;
        this.fimProcessamento = false;
    }
    
    public Cpu(Processos processo) {
        this.processoAtual = processo;
        this.tempoExecutando = 0;
        this.fimProcessamento = false;
    }
    
    // GETTERS E SETTERS
    
    public void setFilaAtual(int fila_atual){
        this.fila_atual = fila_atual;        
    }
    public int getFilaAtual(){
        return fila_atual;
    }
    public Processos getProcessoAtual() {
        return processoAtual;
    }
    
    public void setProcessoAtual(Processos processo) {
        this.processoAtual = processo;
    }
    
    public int getTempoExecutando(){
        return tempoExecutando;
    }
    
    public void setTempoExecutando(int tempoExecutando){
        this.tempoExecutando = tempoExecutando;
    }
    
    public void setFimProcessamento(boolean fimProcessamento){
        this.fimProcessamento = fimProcessamento;
    }
    
    public boolean getFimProcessamento(){
        
        return fimProcessamento;
        
    }
    
    // MÉTODOS
    public void executaProcessoAtual() {
        
        if (this.getProcessoAtual() != null){
            
            int escalonador_control = processoAtual.getPriority();
        
            //ESCALONADOR FCFS //NÃO PREEMPTIVO
            if (escalonador_control == 0){
                               
                if (processoAtual.getService_time_restante() > 0){
                    
                    processoAtual.setService_time_restante(processoAtual.getService_time_restante() - 1);
                }
                else {
                    
                    setFimProcessamento(true);
                    TelaPrincipal.setLblLog("● Processo com ID " + processoAtual.getID() + " foi finalizado." );
                    this.liberaCPU();
                
                }

            }
            //FEEDBACK
            else{

                if ((processoAtual.getService_time_restante() > 0) && (getTempoExecutando() < Escalonadores.getQuantum())){
                    
                    processoAtual.setService_time_restante((processoAtual.getService_time_restante() - 1));
                    
                    setTempoExecutando(getTempoExecutando() + 1);
                    
                }
                
                if (processoAtual.getService_time_restante() == 0){
                    
                    TelaPrincipal.setLblLog("● Processo com ID " + processoAtual.getID() + " foi finalizado." );
                    
                    this.liberaCPU();
                    
                }
                
                else if ((processoAtual.getService_time_restante() > 0) && ( getTempoExecutando() == Escalonadores.getQuantum() )){
                    setTempoExecutando(0);
                    ArrayList<Processos> fila1_aux = Escalonadores.getFila1();
                    ArrayList<Processos> fila2_aux = Escalonadores.getFila2();
                    ArrayList<Processos> fila3_aux = Escalonadores.getFila3();

                    switch (getFilaAtual()) {
                        
                        case 1:
                            fila2_aux.add(processoAtual);
                            Escalonadores.setFila2(fila2_aux);
                            TelaPrincipal.setLblLog("• Processo com ID " + processoAtual.getID() + " removido do processador e adicionado ao final da fila 2." );
                            break;
                        case 2:
                            fila3_aux.add(processoAtual);
                            Escalonadores.setFila3(fila3_aux);
                            TelaPrincipal.setLblLog("• Processo com ID " + processoAtual.getID() + " removido do processador e adicionado ao final da fila 3." );

                            break;
                        case 3:
                            fila1_aux.add(processoAtual);
                            Escalonadores.setFila1(fila1_aux);
                            TelaPrincipal.setLblLog("• Processo com ID " + processoAtual.getID() + " removido do processador e adicionado ao final da fila 1." );

                            break;
                        default:
                            break;
                        
                            
                    }      
                    this.liberaCPU();
                }
            }
        }
               
    }

    // Libera a CPU para ser usada por outro processo    
    public void liberaCPU() {
        this.processoAtual = null;
    }
    
// Retorna true se não há um processo na CPU    
    public boolean estaLivre() {
        return this.processoAtual == null;
    }
       
    
}
