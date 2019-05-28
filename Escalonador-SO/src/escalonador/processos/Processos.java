/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escalonador.processos;

/**
 *
 * @author aliss
 */
public class Processos {
    
    private int id;
    private int arrival_time;
    private int priority;
    private int service_time;
    private int tamanho;
    private int impressora;
    private int disco;
    
    
    //CONSTRUTOR
    public Processos(){}
    
    
    //CONSTRUTOR
    public Processos(int id, int arrival_time, int priority, int service_time, int tamanho, int impressora, int disco){
        
        this.id = id;
        this.arrival_time = arrival_time;
        this.priority = priority;
        this.service_time = service_time;
        this.tamanho = tamanho;
        this.impressora = impressora;
        this.disco = disco;
                   
    }

    public void imprimirProcesso(){
                
        System.out.println("PROCESSO:");
        System.out.printf("ID: %d ", this.getID());
        System.out.printf("Arrival Time: %d ", this.getArrival_time());
        System.out.printf("Priority: %d ", this.getPriority());
        System.out.printf("Service Time: %d ", this.getService_time());
        System.out.printf("Tamanho: %d ", this.getTamanho());
        System.out.printf("Impressora: %d ", this.getImpressora());
        System.out.printf("Disco: %d ", this.getDisco());
        System.out.println("\n\n");
        
    }
    
    
    //GETTERS & SETTERS
    
    public int getID(){
        return id;
    }
    
    public void setID(int id){
        this.id = id;
    }
    
    public int getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(int arrival_time) {
        this.arrival_time = arrival_time;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getService_time() {
        return service_time;
    }

    public void setService_time(int service_time) {
        this.service_time = service_time;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getImpressora() {
        return impressora;
    }

    public void setImpressora(int impressora) {
        this.impressora = impressora;
    }

    public int getDisco() {
        return disco;
    }

    public void setDisco(int disco) {
        this.disco = disco;
    }
       
}
