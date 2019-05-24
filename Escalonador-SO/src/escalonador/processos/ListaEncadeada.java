package escalonador.processos;

import java.util.ArrayList;

/**
 *
 * @author aliss
 */
public class ListaEncadeada {
    
    //CRIA NOVO ARRAY LIST COM TIPO PROCESSOS
    ArrayList<Processos> lista = new ArrayList<>();
    
    
    //INSERE NO FINAL DA LISTA
    public void inserirProcesso(Processos processo){
        
        this.lista.add(processo);
        
    }
    
    //IMPRIME OS PROCESSOS EXISTENTES NA LISTA
    public void imprimirLista(){
               
        for (int i = 0; i < lista.size(); i++){

            //RETORNA O PROCESSO REFERENTE AO INDICE DO ITERADOR
            lista.get(i).imprimirProcesso();

        }
    }
}
