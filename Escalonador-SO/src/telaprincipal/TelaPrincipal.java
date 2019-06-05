/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telaprincipal;

import escalonador.processos.Processos;
import java.util.ArrayList;


/**
 *
 * @author aliss
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    
    private FuncoesTelaPrincipal func;
    private int tempoAtual; 
    private ArrayList<Processos> lista;    
    
    public TelaPrincipal() {
        initComponents();         
        func = new FuncoesTelaPrincipal(); 
        tempoAtual = -1;
        lista = func.getArrayList();
        atualizaTelaPrincipal();
        
    }

    public void setLblMemoriaUsada(int memoriaUsada){
        
        lbl_memoria_utilizada.setText(Integer.toString(memoriaUsada) + " MB");
        
    }
    
    public void incrementaTempoAtual(){
        
        this.tempoAtual++;
        
    }
    
    public int getTempoAtual(){
        
        return tempoAtual;
        
    }
    
    public void setLblTempoAtual(int tempoAtual){
        
        lbl_tempo_atual.setText(Integer.toString(tempoAtual));
        
    }
    
    
    //SETTERS DAS FILAS
    public static void setFila0(String texto){
        
        lbl_fila0.setText(texto);
        
    }
    public static void setFila1(String texto){
        
        lbl_fila1.setText(texto);
        
    }
    public static void setFila2(String texto){
        
        lbl_fila2.setText(texto);
        
    }
    public static void setFila3(String texto){
        
        lbl_fila3.setText(texto);
        
    }
    
    //SETTERS DOS LABLES
    
    public static void setLblCpus(String texto, int id){
    
        if (id == 0)
            setLblCpu0(texto);
        if (id == 1)
            setLblCpu1(texto);
        if (id == 2)
            setLblCpu2(texto);
        if (id == 3)
            setLblCpu3(texto);
        
    }
    
    public static void setLblLog(String linha){
        
        lbl_log.setText(lbl_log.getText() + linha + "\n");
        
    }
    
    
    public static void setLblCpu0(String texto){
        
        lbl_processador0.setText(texto);
        
    }
    
    public static void setLblCpu1(String texto){
        
        lbl_processador1.setText(texto);
        
    }
    
    public static void setLblCpu2(String texto){
        
        lbl_processador2.setText(texto);
        
    }
    
    public static void setLblCpu3(String texto){
        
        lbl_processador3.setText(texto);
        
    }
    
    private void atualizaTelaPrincipal(){
        
        incrementaTempoAtual();
        func.atualizaListaDeChegada(lista, getTempoAtual());
        func.getEscalonador().escalonar();
        func.atualizarListasLBL();
        setLblTempoAtual(getTempoAtual());
        func.calcularMemoriaUtilizada();
        setLblMemoriaUsada(func.getMemoriaUtilizada());
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_avancar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lbl_tempo_atual = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        processador0 = new javax.swing.JLabel();
        processador1 = new javax.swing.JLabel();
        processador2 = new javax.swing.JLabel();
        processador3 = new javax.swing.JLabel();
        lbl_processador0 = new javax.swing.JLabel();
        lbl_processador1 = new javax.swing.JLabel();
        lbl_processador2 = new javax.swing.JLabel();
        lbl_processador3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        title_fila0 = new javax.swing.JLabel();
        lbl_fila0 = new javax.swing.JLabel();
        title_fila1 = new javax.swing.JLabel();
        lbl_fila1 = new javax.swing.JLabel();
        title_fila2 = new javax.swing.JLabel();
        lbl_fila2 = new javax.swing.JLabel();
        title_fila3 = new javax.swing.JLabel();
        lbl_fila3 = new javax.swing.JLabel();
        pnl_log = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbl_log = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        lbl_memoria_utilizada = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_impressoras = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_discos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 800));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 40)); // NOI18N
        jLabel1.setText("Escalonador de Processos");

        jLabel2.setText("Trabalho de Sistemas Operacionais");

        btn_avancar.setText("Avançar Time Slice");
        btn_avancar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_avancarActionPerformed(evt);
            }
        });

        jLabel4.setText("Tempo Atual:");

        lbl_tempo_atual.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_tempo_atual))
                    .addComponent(btn_avancar))
                .addContainerGap(447, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(145, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbl_tempo_atual))
                .addGap(18, 18, 18)
                .addComponent(btn_avancar)
                .addGap(56, 56, 56))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Processadores"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        processador0.setText("PROCESSADOR 0");
        jPanel3.add(processador0, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 38, -1, -1));

        processador1.setText("PROCESSADOR 1");
        jPanel3.add(processador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 38, -1, -1));

        processador2.setText("PROCESSADOR 2");
        jPanel3.add(processador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(677, 38, -1, -1));

        processador3.setText("PROCESSADOR 3");
        jPanel3.add(processador3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 40, -1, -1));

        lbl_processador0.setText("ID PROCESSO");
        jPanel3.add(lbl_processador0, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 102, -1, -1));

        lbl_processador1.setText("ID PROCESSO");
        jPanel3.add(lbl_processador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 102, -1, -1));

        lbl_processador2.setText("ID PROCESSO");
        jPanel3.add(lbl_processador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(677, 102, -1, -1));

        lbl_processador3.setText("ID PROCESSO");
        jPanel3.add(lbl_processador3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 100, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Fila de Processos"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title_fila0.setText("Fila 0:");
        jPanel4.add(title_fila0, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 75, -1, -1));

        lbl_fila0.setText("Fila 0");
        jPanel4.add(lbl_fila0, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 75, -1, -1));

        title_fila1.setText("Fila 1:");
        jPanel4.add(title_fila1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 109, -1, -1));

        lbl_fila1.setText("Fila 1");
        jPanel4.add(lbl_fila1, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 109, -1, -1));

        title_fila2.setText("Fila 2:");
        jPanel4.add(title_fila2, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 143, -1, -1));

        lbl_fila2.setText("Fila 2");
        jPanel4.add(lbl_fila2, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 143, -1, -1));

        title_fila3.setText("Fila 3:");
        jPanel4.add(title_fila3, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 177, -1, -1));

        lbl_fila3.setText("Fila 3");
        jPanel4.add(lbl_fila3, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 177, -1, -1));

        pnl_log.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "LOG DO SISTEMA"));
        pnl_log.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_log.setEditable(false);
        lbl_log.setColumns(20);
        lbl_log.setRows(5);
        jScrollPane1.setViewportView(lbl_log);

        pnl_log.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 31, 517, 395));

        jLabel3.setText("Memória Total: 16384 MB");

        lbl_memoria_utilizada.setText("0 MB");

        jLabel5.setText("Memória Utilizada:");

        jLabel6.setText("Impressoras:");

        lbl_impressoras.setText("0/2");

        jLabel7.setText("Discos:");

        lbl_discos.setText("0/2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(31, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addComponent(pnl_log, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(119, 119, 119)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_memoria_utilizada)
                                .addGap(110, 110, 110)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_impressoras)
                                .addGap(106, 106, 106)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_discos))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(51, 51, 51))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_memoria_utilizada)
                                    .addComponent(jLabel5)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_impressoras)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_discos)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnl_log, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(128, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_avancarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_avancarActionPerformed
        
        atualizaTelaPrincipal();
        
    }//GEN-LAST:event_btn_avancarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
     

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_avancar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_discos;
    private static javax.swing.JLabel lbl_fila0;
    private static javax.swing.JLabel lbl_fila1;
    private static javax.swing.JLabel lbl_fila2;
    private static javax.swing.JLabel lbl_fila3;
    private javax.swing.JLabel lbl_impressoras;
    private static javax.swing.JTextArea lbl_log;
    private javax.swing.JLabel lbl_memoria_utilizada;
    private static javax.swing.JLabel lbl_processador0;
    private static javax.swing.JLabel lbl_processador1;
    private static javax.swing.JLabel lbl_processador2;
    private static javax.swing.JLabel lbl_processador3;
    private javax.swing.JLabel lbl_tempo_atual;
    private javax.swing.JPanel pnl_log;
    private javax.swing.JLabel processador0;
    private javax.swing.JLabel processador1;
    private javax.swing.JLabel processador2;
    private javax.swing.JLabel processador3;
    private javax.swing.JLabel title_fila0;
    private javax.swing.JLabel title_fila1;
    private javax.swing.JLabel title_fila2;
    private javax.swing.JLabel title_fila3;
    // End of variables declaration//GEN-END:variables


}
