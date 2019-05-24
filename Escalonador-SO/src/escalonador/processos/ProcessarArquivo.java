/*
Classe ProcessarArquivo receberá arquivo de processos (txt)
abrir arquivo com JFileChooser


*/


package escalonador.processos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author aliss
 */


public class ProcessarArquivo {
    
    private boolean abriu;
    
    public boolean getAbriu(){
        return this.abriu;
    }
       
    
    public String escolherArquivo(){
        
        String caminho = null;
        
        String finder = "C:\\Users\\aliss\\Documents\\GitHub\\Escalonador-SO\\Escalonador-SO\\src\\files";
        
        JFileChooser arquivo = new JFileChooser(finder);
             
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivo TXT", "txt");
        arquivo.setAcceptAllFileFilterUsed(false);
        arquivo.addChoosableFileFilter(filtro);
        arquivo.setDialogTitle("Selecione o arquivo de processos");
        
        if(arquivo.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            caminho = arquivo.getSelectedFile().getAbsolutePath();
            JOptionPane.showMessageDialog(null, "Arquivo aberto com sucesso!");
        }
        
        abrirArquivo(caminho);
        
        return caminho;
    }
    
    public void abrirArquivo(String caminho){
      
        
        try {
              
            BufferedReader br = new BufferedReader(new FileReader(caminho));
                       
            this.abriu = true;
               
            try {
                
                ListaEncadeada lista = lerEGravarProcessoEmLista(br);
                
                lista.imprimirLista();
                
            } catch (Exception e) {
            }
            
            
        } catch (FileNotFoundException e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo!", "Erro!", 0);
            
            this.abriu = false;
        }
                
    }
    
    public ListaEncadeada lerEGravarProcessoEmLista(BufferedReader file) throws IOException{
               
        
        ListaEncadeada lista = null;
        
        try {        
            
            int arrival_time;
            int priority;
            int service_time;
            int tamanho;
            int impressora;
            int disco;
                        
            String linha = file.readLine();            
            lista = new ListaEncadeada();
            
            while (linha != null){
                                
                //SEPARA LINHA LIDA DO ARQUIVO DE PROCESSOS
                if (linha.length() > 0){
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
                    novo.setArrival_time(arrival_time);
                    novo.setPriority(priority);
                    novo.setService_time(service_time);
                    novo.setTamanho(tamanho);
                    novo.setImpressora(impressora);
                    novo.setDisco(disco);

                    //ADICIONA PROCESSO NO FINAL DA LISTA
                    lista.inserirProcesso(novo);
                    
                }
                //LE PROXIMA LINHA
                linha = file.readLine();
            }
            
            return lista;   
            
            
        } catch (Exception e) {
            System.out.println(e);
        }

        //RETORNA LISTA NULL CASO DE ALGUM PROBLEMA NA EXECUÇÃO
        return lista;
        
    }
    
}

