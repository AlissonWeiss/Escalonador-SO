/*
Classe ProcessarArquivo receberá arquivo de processos (txt)
abrir arquivo com JFileChooser

*/


package escalonador.processos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author aliss
 */


public class ProcessarArquivo {
    
    private boolean abriu;
    
    private static ArrayList<Processos> lista;
        
    //GETTERS
    public boolean getAbriu(){
        return this.abriu;
    }
    
    public static ArrayList<Processos> getArrayList(){
        return lista;
    }
    
    //FUNÇÃO PARA ESCOLHER ARQUIVO DE PROCESSOS
    public String escolherArquivo(){
        
        String caminho = null;
        
        String finder = "C:\\Users\\aliss\\Documents\\GitHub\\Escalonador-SO\\Escalonador-SO\\src\\files";
        
        JFileChooser arquivo = new JFileChooser(finder);
             
        //ADICIONA FILTRO PARA APENAS ARQUIVOS COM EXTENSÃO TXT
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivo TXT", "txt");
        arquivo.setAcceptAllFileFilterUsed(false);
        arquivo.addChoosableFileFilter(filtro);
        arquivo.setDialogTitle("Selecione o arquivo de processos");
        
        //VERIFICA SE O ARQUIVO FOI ABERTO 
        if(arquivo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            caminho = arquivo.getSelectedFile().getAbsolutePath();
            JOptionPane.showMessageDialog(null, "Arquivo aberto com sucesso!");
        }
        
        abrirArquivo(caminho);
        
        return caminho;
    }
    
    //FUNÇÃO PARA ABRIR O ARQUIVO
    public void abrirArquivo(String caminho){
      
        
        try {
            
            //CRIA UM BUFFER PARA LEITURA
            BufferedReader br = new BufferedReader(new FileReader(caminho));
                      
            //SETA A VARIÁVEL DE CONTROLE
            this.abriu = true;
            
            lista = new ArrayList<>();
            
            try {
                
                
                //RETORNA LISTA DE PROCESSOS LIDOS EM ARQUIVO TEXTO
                lista = lerEGravarProcessoEmLista(br);
                
                
                //ORDENA LISTA POR ARRIVAL TIME
                lista = ordenarArrayList(lista);
                
                //IMPRIME OS PROCESSOS PRESENTES NA LISTA
                for (int i = 0; i < lista.size(); i++){
                    
                    lista.get(i).imprimirProcesso();
                    
                }                            
                
            } catch (IOException e) {
                
                System.out.println("ERRO: " + e);
                
            }
            
            
        } 
        //CASO OCORRA QUALQUER ERRO NA ABERTURA DO ARQUIVO
        catch (FileNotFoundException e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo!", "Erro!", 0);
            
            this.abriu = false;
        }
                
    }
    
    //LÊ O ARQUIVO, ARMAZENA OS DADOS EM UM OBJETO PROCESSOS E ADICIONA-OS A LISTA
    public ArrayList<Processos> lerEGravarProcessoEmLista(BufferedReader file) throws IOException{
               
        
        try {        

            //INICIALIZAÇÃO DAS VARIÁVEIS LOCAIS
            int id = 0;
            int arrival_time;
            int priority;
            int service_time;
            int tamanho;
            int impressora;
            int disco;
                        
            String linha = file.readLine();            
            lista = new ArrayList<>();
           
            while (linha != null){
                         
                //SEPARA LINHA LIDA DO ARQUIVO DE PROCESSOS
                if (linha.length() > 0){
                    
                    //SEPARA A LISTA PELO PARAMETRO PASSADO
                    String vetor[] = linha.split(", ");
                        Processos novo = new Processos();

                    //ATRIBUI OS VALORES LIDOS AS VARIAVEIS LOCAIS
                    arrival_time = Integer.parseInt(vetor[0]);
                    priority     = Integer.parseInt(vetor[1]);
                    service_time = Integer.parseInt(vetor[2]);
                    tamanho      = Integer.parseInt(vetor[3]);
                    impressora   = Integer.parseInt(vetor[4]);
                    disco        = Integer.parseInt(vetor[5]);

                    //SETA OS VALORES PARA O OBJETO PROCESSOS
                    novo.setID(id);
                    novo.setArrival_time(arrival_time);
                    novo.setPriority(priority);
                    novo.setService_time(service_time);
                    novo.setTamanho(tamanho);
                    novo.setImpressora(impressora);
                    novo.setDisco(disco);
                    novo.setService_time_restante(service_time);
                    
                    //ADICIONA PROCESSO NO FINAL DA LISTA
                    lista.add(novo);
                    
                    if (priority == 0 && tamanho > 512)
                        JOptionPane.showMessageDialog(null, "MAIOR " + id);
                    
                    //INCREMENTA O ID DO PROCESSO
                    id++;
                    
                }
                //LE PROXIMA LINHA DE PROCESSOS
                linha = file.readLine();
            }
            
            //RETORNA LISTA FINAL
            return lista;   
            
            
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }

        //RETORNA LISTA NULL CASO DE ALGUM PROBLEMA NA EXECUÇÃO
        return lista;
        
    }
    
    //ORDENA A LISTA POR ARRIVAL TIME, MELHORANDO O FLUXO POSTERIORMENTE
    public ArrayList<Processos> ordenarArrayList(ArrayList<Processos> lista){
        
        //PEGA O PRIMEIRO ARRIVAL TIME COMO O MENOR
        int arrival_time;
        
        ArrayList<Processos> lista_aux = new ArrayList<>();
        
        Processos processo_aux;
        
        
        //PERCORRE A LISTA ENQUANTO ELA NÃO ESTÁ VAZIA
        while(!lista.isEmpty()){
            
            arrival_time  = lista.get(0).getArrival_time();
        
            processo_aux = null;
            
            //FOR ITERATIVO PERCORRENDO A LISTA E PEGANDO PROCESSO
            for (Processos i: lista){

                //COMPARA OS ARRIVAL TIME
                if (i.getArrival_time() <= arrival_time){
                    arrival_time = i.getArrival_time();
                    processo_aux = i;
                }
            }
            
            //VERIFICA SE PROCESSO FOI ENCONTRADO
            if (processo_aux != null){
                
                lista_aux.add(processo_aux);
                lista.remove(processo_aux);
            }

        }
        //RETORNA LISTA ORDENADA
        return lista_aux;
    }
    
}
