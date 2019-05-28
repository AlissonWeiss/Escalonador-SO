/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaprincipal;

import escalonador.processos.ProcessarArquivo;
import escalonador.processos.Processos;
import java.util.ArrayList;

/**
 *
 * @author aliss
 */
public class FuncoesTelaPrincipal {

    private ArrayList<Processos> lista;
    private ArrayList<Processos> fila0;
    private ArrayList<Processos> fila1;
    private ArrayList<Processos> fila2;
    private ArrayList<Processos> fila3;
    
    
    public FuncoesTelaPrincipal(){
        
        this.lista = ProcessarArquivo.getArrayList();
        
        fila0 = new ArrayList<>();
        fila1 = new ArrayList<>();
        fila2 = new ArrayList<>();
        fila3 = new ArrayList<>();
        
        
        for (int i = 0; i < lista.size(); i++){
            
            switch (lista.get(i).getPriority()) {
                
                case 0:
                    fila0.add(lista.get(i));
                    break;
                case 1:
                    fila1.add(lista.get(i));
                    break;
                case 2:
                    fila2.add(lista.get(i));
                    break;
                case 3:
                    fila3.add(lista.get(i));
                    break;
                default:
                    break;
                    
            }

        }
       
        
        TelaPrincipal.setFila0(concatenar_fila(fila0));
        TelaPrincipal.setFila1(concatenar_fila(fila1));
        TelaPrincipal.setFila2(concatenar_fila(fila2));
        TelaPrincipal.setFila3(concatenar_fila(fila3));
        
        
    }        
    public String concatenar_fila(ArrayList<Processos> lista){
            
        String texto = "ID: ";
        String aux;
        
        for (int i = 0; i < lista.size(); i++){
            
            
            aux = Integer.toString(lista.get(i).getID());
            texto = texto.concat(aux + " - ");
            
            
        }
        
        return texto;
            
    }
    
}
