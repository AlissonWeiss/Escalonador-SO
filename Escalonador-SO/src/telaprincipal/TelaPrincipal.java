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
    private static int cont_impressora = 0;
    private static int cont_disco = 0;
    
    
    public TelaPrincipal() {
        initComponents();         
        func = new FuncoesTelaPrincipal(); 
        tempoAtual = -1;
        lista = func.getArrayList();
        atualizaTelaPrincipal();
        
    }
    
    public static void setContImpressora(int cont){
        TelaPrincipal.cont_impressora = cont;
    }
    public static void setContDisco(int cont){
        TelaPrincipal.cont_disco = cont;
    }
    public static int getContImpressora(){
        return TelaPrincipal.cont_impressora;
    }
    public static int getContDisco(){
        return TelaPrincipal.cont_disco;
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
    
    public static void setLblImpressora(String texto){
        
        lbl_impressoras.setText(texto+"/2");
        
    }
    
    public static void setLblDisco(String texto){
        
        lbl_discos.setText(texto+"/2");
        
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
    
    public static void setFilaProntoSuspenso(String texto){
        
        lbl_fila_pronto_suspenso.setText(texto);
        
    }
    
    
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
    
    public static void setLblMemoriaLivre(String texto){
        
        lbl_mem_disponivel.setText(texto + " MB");
        
    }
    
    private void atualizaTelaPrincipal(){
        
        
        setLblLog("\nCiclo: " + (getTempoAtual() + 1));
        incrementaTempoAtual();
        func.atualizaListaDeChegada(lista, getTempoAtual());
        func.getEscalonador().escalonar();
        func.atualizarListasLBL();
        setLblTempoAtual(getTempoAtual());
        func.calcularMemoriaUtilizada();
        func.calcularMemoriaLivre();
        func.setLblDiscosEimpressoras();
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
        jLabel9 = new javax.swing.JLabel();
        lbl_fila_pronto_suspenso = new javax.swing.JLabel();
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
        jLabel8 = new javax.swing.JLabel();
        lbl_mem_disponivel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 800));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 40)); // NOI18N
        jLabel1.setText("Escalonador de Processos");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 61, -1, 64));

        jLabel2.setText("Trabalho de Sistemas Operacionais");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1688, 13, -1, -1));

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
                .addContainerGap(95, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbl_tempo_atual))
                .addGap(18, 18, 18)
                .addComponent(btn_avancar)
                .addGap(56, 56, 56))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, 210));

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

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, 1110, 160));

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

        jLabel9.setText("Fila Pronto Suspenso: ");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        lbl_fila_pronto_suspenso.setText("Fila Pronto Suspenso");
        jPanel4.add(lbl_fila_pronto_suspenso, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 1200, 260));

        pnl_log.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "LOG DO SISTEMA"));
        pnl_log.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_log.setEditable(false);
        lbl_log.setColumns(20);
        lbl_log.setRows(5);
        jScrollPane1.setViewportView(lbl_log);

        pnl_log.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 31, 600, 410));

        jPanel1.add(pnl_log, new org.netbeans.lib.awtextra.AbsoluteConstraints(1239, 282, 640, 460));

        jLabel3.setText("Memória Total:   16384 MB");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 240, -1, -1));

        lbl_memoria_utilizada.setText("0 MB");
        jPanel1.add(lbl_memoria_utilizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(1151, 239, -1, -1));

        jLabel5.setText("Memória Utilizada:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1026, 239, -1, -1));

        jLabel6.setText("Impressoras:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 240, -1, -1));

        lbl_impressoras.setText("0/2");
        jPanel1.add(lbl_impressoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(1650, 240, -1, -1));

        jLabel7.setText("Discos:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1770, 240, -1, -1));

        lbl_discos.setText("0/2");
        jPanel1.add(lbl_discos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1830, 240, -1, -1));

        jLabel8.setText("Memória Disponível:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 240, -1, -1));

        lbl_mem_disponivel.setText("16384 MB");
        jPanel1.add(lbl_mem_disponivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 240, -1, -1));

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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JLabel lbl_discos;
    private static javax.swing.JLabel lbl_fila0;
    private static javax.swing.JLabel lbl_fila1;
    private static javax.swing.JLabel lbl_fila2;
    private static javax.swing.JLabel lbl_fila3;
    private static javax.swing.JLabel lbl_fila_pronto_suspenso;
    private static javax.swing.JLabel lbl_impressoras;
    private static javax.swing.JTextArea lbl_log;
    private static javax.swing.JLabel lbl_mem_disponivel;
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
