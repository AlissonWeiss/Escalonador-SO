package escalonador.processos;

/**
 *
 * @author Fellippe
 */
public class Cpu {
    private Processos processoAtual;
    
    // CONSTRUTORES
    public Cpu() {
        this.processoAtual = null;
    }
    
    public Cpu(Processos processo) {
        this.processoAtual = processo;
    }
    
    // GETTERS E SETTERS
    public Processos getProcessoAtual() {
        return processoAtual;
    }
    
    public void setProcessoAtual(Processos processo) {
        this.processoAtual = processo;
    }
    
    // MÉTODOS
    public void executaProcessoAtual() {
        // Checa se existe um processo na CPU e se ele ainda tem tempo restante para ser executado
        if (this.getProcessoAtual() != null) {
            if (processoAtual.getService_time_restante() > 0) {

                // Decrementa 1 unidade de tempo
                processoAtual.setService_time_restante(processoAtual.getService_time_restante() - 1);
            }
            else {
                System.out.println("Processo c/ ID:" + processoAtual.getID() + " finalizado.");
            }
        }
    }
    
    public void liberaCPU() {
        this.processoAtual = null;
    }
}
