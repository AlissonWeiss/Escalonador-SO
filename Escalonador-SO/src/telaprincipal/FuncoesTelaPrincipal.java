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

    //LISTA E FILAS DE PRIORIDADES
    private ArrayList<Processos> lista;
    private ArrayList<Processos> fila0;
    private ArrayList<Processos> fila1;
    private ArrayList<Processos> fila2;
    private ArrayList<Processos> fila3;
    private ArrayList<Processos> fila_pronto_suspenso;
                
    
    //VARIÁVEIS DE CONTROLE
    private final int memoriaPrincipal = 16384; //EM MB
    private int memoriaUtilizada = 0; //EM MB
    private int memoriaLivre = memoriaPrincipal; //EM MB
    private boolean controle_remocao = false;
    
    //CPUS UTILIZADOS NO PROCESSAMENTO
    private Cpu cpu0;
    private Cpu cpu1;
    private Cpu cpu2;
    private Cpu cpu3;
    //ESCALONADOR
    private Escalonadores escalonador;
    
    //CONSTRUTOR
    public FuncoesTelaPrincipal(){
        
        this.lista = ProcessarArquivo.getArrayList();
        
        fila0 = new ArrayList<>();
        fila1 = new ArrayList<>();
        fila2 = new ArrayList<>();
        fila3 = new ArrayList<>();
        fila_pronto_suspenso = new ArrayList<>();
        
        cpu0 = new Cpu();
        cpu1 = new Cpu();
        cpu2 = new Cpu();
        cpu3 = new Cpu();
        
        escalonador = new Escalonadores(fila0, fila1, fila2, fila3,fila_pronto_suspenso, cpu0, cpu1, cpu2, cpu3);
        TelaPrincipal.setLblLog("Escalonador Inicializado!");
        
    }
    
    //GETTERS & SETTERS
    
    public boolean getControleRemocao(){
        return controle_remocao;
    }
    public void setControleRemocao(boolean controle_remocao){
        this.controle_remocao = controle_remocao;
    }
    
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
    
    //TENTA REMOVER ALGUÉM DA FILA DE PRONTOS (APENAS PROCESSOS QUE TEM PRIORIDADE != 0)
    public void removerProcessoPorTamanho(){
        
        int maior = 0;
        Processos processo_aux = null;
        
        //VERIFICA SE HÁ ALGUÉM NA FILA 3 (MENOR PRIORIDADE) PARA REMOVER DELA
        if (!fila3.isEmpty()){
            
            for (Processos processo : fila3){
                if (processo.getTamanho() > maior){
                    maior = processo.getTamanho();
                    processo_aux = processo;
                }
            }
            fila3.remove(processo_aux);
            
            //PROCESSO REMOVIDO É ADICIONADO A FILA DE PRONTO SUSPENSO
            fila_pronto_suspenso.add(processo_aux);
        }
        
        //VERIFICA SE HÁ ALGUÉM NA FILA 2 (MÉDIA PRIORIDADE) PARA REMOVER DELA
        else if (!fila2.isEmpty()){
            
            for (Processos processo : fila2){
                if (processo.getTamanho() > maior){
                    maior = processo.getTamanho();
                    processo_aux = processo;
                }
            }
            fila2.remove(processo_aux);
            
            //PROCESSO REMOVIDO É ADICIONADO A FILA DE PRONTO SUSPENSO
            fila_pronto_suspenso.add(processo_aux);
            
        }
        
        //VERIFICA SE HÁ ALGUÉM NA FILA 1 (MAIOR PRIORIDADE ENTRE FEEDBACK) PARA REMOVER DELA
        else if (!fila1.isEmpty()){
            
            for (Processos processo : fila1){
                if (processo.getTamanho() > maior){
                    maior = processo.getTamanho();
                    processo_aux = processo;
                }
            }
            fila1.remove(processo_aux);
            
            //PROCESSO REMOVIDO É ADICIONADO A FILA DE PRONTO SUSPENSO
            fila_pronto_suspenso.add(processo_aux);

        }
        
        //REMOVEU ALGUEM DA FILA DE PRONTOS
        if (processo_aux != null){
            TelaPrincipal.setLblLog(">> Processo com ID " + processo_aux.getID() + " desalocado e adicionado na fila de Pronto Suspenso.");
            setControleRemocao(true);
        }
        //NÃO REMOVEU NINGUÉM
        else{
            setControleRemocao(false);
        }
                
    }
    
    //METODOS
    
    //APRESENTA O PROCESSO ADICIONADO EM FILA DE PRONTOS
    public void apresentarProcesso(Processos processo){
        
        
        TelaPrincipal.setLblLog("\nId:                                " + processo.getID());
        TelaPrincipal.setLblLog("Prioridade:                 " + processo.getPriority());
        TelaPrincipal.setLblLog("Tempo de serviço:   " + processo.getService_time());
        TelaPrincipal.setLblLog("Tamanho:                  " + processo.getTamanho());
        TelaPrincipal.setLblLog("Impressora(s):          " + processo.getImpressora());
        TelaPrincipal.setLblLog("Disco(s):                    " + processo.getDisco() + "\n");
        
    }
    
    //RECEBE UM PROCESSO E COLOCA ELE EM SUA DEVIDA FILA DE PRIORIDADE
    public void colocarNaFilaDePrioridade(Processos processo){
        
        //VERIFICA SE HÁ ESPAÇO DISPONÍVEL EM MEMÓRIA
        if (processo.getTamanho() <= getMemoriaLivre()){
            //CASO HAJA ESPAÇO DISPONÍVEL, ADICIONA O PROCESSO EM FILA DE PRONTOS
            switch(processo.getPriority()){

                    case 0:
                        fila0.add(processo);
                        TelaPrincipal.setLblLog("-> Processo com ID " + processo.getID() + " alocado a fila 0.");
                        break;
                    case 1:
                        fila1.add(processo);
                        TelaPrincipal.setLblLog("-> Processo com ID " + processo.getID() + " alocado a fila 1.");
                        break;
                    case 2:
                        fila1.add(processo);                    
                        TelaPrincipal.setLblLog("-> Processo com ID " + processo.getID() + " alocado a fila 1.");
                        break;
                    case 3:
                        fila1.add(processo);
                        TelaPrincipal.setLblLog("-> Processo com ID " + processo.getID() + " alocado a fila 1.");
                        break;
                    default:
                        break;             
            }
            apresentarProcesso(processo);
        }
        //CASO CONTRARIO, SE PROCESSO PRIORIDADE = 0, TENTA REMOVER ALGUEM COM PRIORIDADE DE USUÁRIO
        else if (processo.getPriority() == 0){
            removerProcessoPorTamanho();
            calcularMemoriaLivre();
            //SE REMOVEU ALGUÉM, CHAMA A FUNÇÃO NOVAMENTE E TENTA ALOCAR O PROCESSO EM MEMÓRIA
            if (getControleRemocao() == true){
                colocarNaFilaDePrioridade(processo);
                setControleRemocao(false);
            }
        }
                
    }

    
    //CONCATENA OS PROCESSOS DA FILA EM STRING PARA SER EXIBIDO NA TELA PRINCIPAL
    public String concatenar_fila(ArrayList<Processos> lista){
            
        String texto = "ID: ";
        String aux;
        
        if (!lista.isEmpty()){

            for (int i = 0; i < lista.size(); i++){


                aux = Integer.toString(lista.get(i).getID());

                texto = texto.concat(" - " + aux);

            }
        }
        return texto;
            
    }
    
    //ATUALIZA OS LABELS DAS FILAS DE PRONTOS E DA PRONTO SUSPENSO
    public void atualizarListasLBL(){
        TelaPrincipal.setFila0(concatenar_fila(fila0));
        TelaPrincipal.setFila1(concatenar_fila(fila1));
        TelaPrincipal.setFila2(concatenar_fila(fila2));
        TelaPrincipal.setFila3(concatenar_fila(fila3));
        TelaPrincipal.setFilaProntoSuspenso(concatenar_fila(fila_pronto_suspenso));
    }
    
    //ATUALIZA OS LABELS DOS DISCOS E IMPRESSORAS
    public void setLblDiscosEimpressoras(){
        
        TelaPrincipal.setLblDisco(Integer.toString(TelaPrincipal.getContDisco()));
        TelaPrincipal.setLblImpressora(Integer.toString(TelaPrincipal.getContImpressora()));
        
    }
    
    //CALCULA A MEMÓRIA RAM DISPONÍVEL
    public void calcularMemoriaLivre(){
        calcularMemoriaUtilizada();

        setMemoriaLivre(getMemoriaPrincipal() - getMemoriaUtilizada());
        
        TelaPrincipal.setLblMemoriaLivre(Integer.toString(getMemoriaLivre()));
        
    }
    
    //CALCULA TODA A MEMÓRIA UTILIZADA PELOS PROCESSOS (FILAS E CPUS)
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
        
        //SETA A MEMORIA UTILIZADA
        setMemoriaUtilizada(aux_memoria);
        
    }
    
    
    //CHECA SE PROCESSO ESTÁ PRESENTE EM ALGUMA FILA
    public boolean checarSeAdicionou(Processos processo){
        
        for (Processos aux : fila0){
            if (aux == processo)
                return true;
        }  
        for (Processos aux : fila1){
            if (aux == processo)
                return true;
        }
        for (Processos aux : fila2){
            if (aux == processo)
                return true;
        }
        for (Processos aux : fila3){
            if (aux == processo)
                return true;
        }
        
        return false;
        
    }
    
    //RECEBE LISTA DE PROCESSOS E VERIFICA SE ELES JA CHEGARAM
    public void atualizaListaDeChegada(ArrayList<Processos> lista, int tempoAtual){
                
        boolean control = false;
        
        //VERIFICA SE HÁ ALGUÉM NA FILA DE PRONTO SUSPENSO
        if (!fila_pronto_suspenso.isEmpty()){
            
            //PERCORRE A LISTA DE CHEGADA E VERIFICA SE ALGUÉM QUE CHEGOU TEM PRIORIDADE 0 (MAIOR QUE PRONTO SUSPENSO)
            for (Processos i : lista){
            
                if (i.getArrival_time() <= tempoAtual){
             
                    if (i.getPriority() == 0){
                        control = true;
                    }
                }
            }
            //SE ENCONTROU ALGUÉM COM PRIORIDADE = 0, EXECUTA DA LISTA
            if (control){

                controlaListaChegada(lista, tempoAtual);

            }
            //CASO CONTRÁRIO, EXECUTA DA FILA DE PRONTO SUSPENSO
            else{
                controlaListaChegada(fila_pronto_suspenso, tempoAtual);
            }

        }
        //CASO PADRÃO, EXECUTA DA LISTA DE CHEGADA
        else{
            controlaListaChegada(lista, tempoAtual);
        }


        //ATUALIZA OS LABELS DAS FILAS
        atualizarListasLBL();
     
    }
    
    //FUNÇÃO QUE COMPLEMENTA A ATUALIZAR LISTA DE CHEGADA
    public void controlaListaChegada(ArrayList<Processos> lista, int tempoAtual){
        
        //VETOR AUX FUNCIONARÁ COMO LIXEIRA
        ArrayList<Processos> aux = new ArrayList<>();
        
        //PERCORRE A LISTA
        for (Processos i: lista){
            calcularMemoriaLivre();
            //VERIFICA SE PROCESSO JÁ CHEGOU
            if (i.getArrival_time() <= tempoAtual){

                calcularMemoriaLivre();
                //VEIFICA SE OS PROCESSOS NÃO UTILIZAM IMPRESSORA NEM DISCO
                if (i.getImpressora() == 0 && i.getDisco() == 0){
                    
                    //TENTA COLOCAR NA FILA DE PRIORIDADE
                    colocarNaFilaDePrioridade(i);

                    //VERIFICA SE COLOCOU NA FILA DE PRIORIDADE
                    //CASO COLOCOU, ADICIONA ELE NO VETOR AUX
                    if (checarSeAdicionou(i))
                        aux.add(i);

                }

                //VERIFICA SE OS PROCESSOS UTILIZAM DE DISCO E IMPRESSORA
                else if (i.getImpressora() != 0 && i.getDisco() != 0){

                    //CASO TESTE COM 2 IMPRESSORAS E 2 DISCOS
                    if (TelaPrincipal.getContImpressora() == 0 && i.getImpressora() == 2){
                        if (TelaPrincipal.getContDisco() == 0 && i.getDisco() == 2){
                            colocarNaFilaDePrioridade(i);
                            if (checarSeAdicionou(i)){
                                TelaPrincipal.setContImpressora(TelaPrincipal.getContImpressora() + i.getImpressora());
                                TelaPrincipal.setContDisco(TelaPrincipal.getContDisco() + i.getDisco());
                                aux.add(i);

                            }
                        }
                        //CASO TESTE COM 2 IMPRESSORAS E 1 DISCO
                        else if (TelaPrincipal.getContDisco() < 2 && i.getDisco() == 1){
                            colocarNaFilaDePrioridade(i);
                            if (checarSeAdicionou(i)){
                                TelaPrincipal.setContImpressora(TelaPrincipal.getContImpressora() + i.getImpressora());
                                TelaPrincipal.setContDisco(TelaPrincipal.getContDisco() + i.getDisco());
                                aux.add(i);
                            }
                        }

                    }
                    //CASO TESTE COM 2 DISCOS E 1 IMPRESSORA
                    if (TelaPrincipal.getContImpressora() < 2 && i.getImpressora() == 1){
                        if (TelaPrincipal.getContDisco() == 0 && i.getDisco() == 2){
                            colocarNaFilaDePrioridade(i);
                            if (checarSeAdicionou(i)){
                                TelaPrincipal.setContImpressora(TelaPrincipal.getContImpressora() + i.getImpressora());
                                TelaPrincipal.setContDisco(TelaPrincipal.getContDisco() + i.getDisco());
                                aux.add(i);
                            }
                        }
                        //CASO TESTE COM UM DISCO E UMA IMPRESSORA
                        else if (TelaPrincipal.getContDisco() < 2 && i.getDisco() == 1){

                            colocarNaFilaDePrioridade(i);
                            if (checarSeAdicionou(i)){
                                TelaPrincipal.setContImpressora(TelaPrincipal.getContImpressora() + i.getImpressora());
                                TelaPrincipal.setContDisco(TelaPrincipal.getContDisco() + i.getDisco());
                                aux.add(i);           
                            }
                        }
                    }
                }

                //VERIFICA SE O PROCESSO USA IMPRESSORA (APENAS) E SE HÁ IMPRESSORA DISPONÍVEL
                else if (i.getImpressora() != 0){

                    //CASO TESTE COM PROCESSO USANDO DUAS IMPRESSORAS
                    if (TelaPrincipal.getContImpressora() == 0 && i.getImpressora() == 2){
                        colocarNaFilaDePrioridade(i);
                        if (checarSeAdicionou(i)){
                            TelaPrincipal.setContImpressora(TelaPrincipal.getContImpressora() + i.getImpressora());
                            aux.add(i);
                        }
                    }
                    //CASO TESTE COM PROCESSO USANDO UMA IMPRESSORA
                    else if (TelaPrincipal.getContImpressora() < 2 && i.getImpressora() == 1){
                        colocarNaFilaDePrioridade(i);
                        if (checarSeAdicionou(i)){
                            TelaPrincipal.setContImpressora(TelaPrincipal.getContImpressora() + i.getImpressora());
                            aux.add(i);
                        }
                    }

                }
                //VERIFICA SE PROCESSO USA DISCO (APENAS) E SE HÁ DISCO DISPONÍVEL
                else if (i.getDisco() != 0){

                    //CASO TESTE COM DOIS DISCOS
                    if (TelaPrincipal.getContDisco() == 0 && i.getDisco() == 2){
                        colocarNaFilaDePrioridade(i);
                        if (checarSeAdicionou(i)){
                            TelaPrincipal.setContDisco(TelaPrincipal.getContDisco() + i.getDisco());
                            aux.add(i);
                        }
                    }
                    //CASO TESTE COM UM DISCO
                    else if (TelaPrincipal.getContDisco() < 2 && i.getDisco() == 1){
                        colocarNaFilaDePrioridade(i);
                        if (checarSeAdicionou(i)){
                            TelaPrincipal.setContDisco(TelaPrincipal.getContDisco() + i.getDisco());
                            aux.add(i);
                        }
                    }

                }

            }  
            //CALCULA A MEMÓRIA LIVRE DO SISTEMA
            calcularMemoriaLivre();

        }
        //PERCORRE A LISTA AUX E REMOVE DA LISTA DE CHEGADA OS ELEMENTOS QUE ESTÃO EM AUX
        //E PERTENCEM A LISTA DE CHEGADA
        for (Processos i: aux){
            lista.remove(i);
        }
       
    }
    
}
