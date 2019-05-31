package escalonador.processos;

import javax.swing.JOptionPane;

/**
 *
 * @author Fellippe
 */
public class Cpu {
    
    private Processos processoAtual;
    private int tempoExecutando;
    private boolean fimProcessamento;
    
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
        
        if (getTempoExecutando() < Escalonadores.getQuantum()){

            
            // Checa se existe um processo na CPU e se ele ainda tem tempo restante para ser executado
            if (this.getProcessoAtual() != null) {
                if (processoAtual.getService_time_restante() > 0) {

                    // Decrementa 1 unidade de tempo
                    processoAtual.setService_time_restante(processoAtual.getService_time_restante() - 1);
                }
                else {
                    setFimProcessamento(true);
                    
                    JOptionPane.showMessageDialog(null, "Processo c/ ID: " + processoAtual.getID() + " finalizado!");
                    System.out.println("Processo c/ ID:" + processoAtual.getID() + " finalizado.");
                    this.liberaCPU();
                }
            }
        }
        else{
            
            fimProcessamento = true;
            
        }
        
    }
    
    public void liberaCPU() {
        // Libera a CPU para ser usada por outro processo
        this.processoAtual = null;
    }
    
    public boolean estaLivre() {
        // Retorna true se não há um processo na CPU
        return this.processoAtual == null;
    }
}
