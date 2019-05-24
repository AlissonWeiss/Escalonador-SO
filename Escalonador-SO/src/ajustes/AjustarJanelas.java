/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajustes;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author aliss
 */
public class AjustarJanelas {
    
    public void ajustarJanelas(javax.swing.JFrame tela){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        tela.setTitle("Escalonador de Processos");
        tela.setResizable(false);
        
        screenSize.setSize((int)(screenSize.getWidth()), (int)screenSize.getHeight() - (screenSize.getHeight() * 0.1));
        tela.setMaximumSize(screenSize);
        tela.setMinimumSize(screenSize);
        
        
    }

    
}
