package graphicinterface;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Window extends javax.swing.JFrame {

    /**
     * Creates new form Window
     */
    public Window() {
        initComponents();
        initIDE();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCode = new javax.swing.JPanel();
        jScrollPaneLines = new javax.swing.JScrollPane();
        jTextPaneLines = new javax.swing.JTextPane();
        jScrollPaneCode = new javax.swing.JScrollPane();
        jTextPaneCode = new javax.swing.JTextPane();
        jTabbedPaneOutput = new javax.swing.JTabbedPane();
        jScrollPaneTerminal = new javax.swing.JScrollPane();
        jTextPaneTerminal = new javax.swing.JTextPane();
        jScrollPaneTable = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemNew = new javax.swing.JMenuItem();
        jMenuItemOpen = new javax.swing.JMenuItem();
        jMenuItemSave = new javax.swing.JMenuItem();
        jMenuItemSaveAs = new javax.swing.JMenuItem();
        jMenuItemClose = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuAnalysis = new javax.swing.JMenu();
        jMenuItemAnalyse = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lexical Syntactic Analyser LR");

        jScrollPaneLines.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPaneLines.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextPaneLines.setEditable(false);
        jTextPaneLines.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jScrollPaneLines.setViewportView(jTextPaneLines);

        jTextPaneCode.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jScrollPaneCode.setViewportView(jTextPaneCode);

        javax.swing.GroupLayout jPanelCodeLayout = new javax.swing.GroupLayout(jPanelCode);
        jPanelCode.setLayout(jPanelCodeLayout);
        jPanelCodeLayout.setHorizontalGroup(
            jPanelCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCodeLayout.createSequentialGroup()
                .addComponent(jScrollPaneLines, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneCode))
        );
        jPanelCodeLayout.setVerticalGroup(
            jPanelCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneLines)
            .addComponent(jScrollPaneCode, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );

        jTextPaneTerminal.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        jScrollPaneTerminal.setViewportView(jTextPaneTerminal);

        jTabbedPaneOutput.addTab("Terminal", jScrollPaneTerminal);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPaneTable.setViewportView(jTable1);

        jTabbedPaneOutput.addTab("Table", jScrollPaneTable);

        jMenuFile.setText("File");

        jMenuItemNew.setText("New");
        jMenuFile.add(jMenuItemNew);

        jMenuItemOpen.setText("Open...");
        jMenuFile.add(jMenuItemOpen);

        jMenuItemSave.setText("Save");
        jMenuFile.add(jMenuItemSave);

        jMenuItemSaveAs.setText("Save as...");
        jMenuFile.add(jMenuItemSaveAs);

        jMenuItemClose.setText("Close");
        jMenuFile.add(jMenuItemClose);

        jMenuItemExit.setText("Exit");
        jMenuFile.add(jMenuItemExit);

        jMenuBar.add(jMenuFile);

        jMenuAnalysis.setText("Analysis");

        jMenuItemAnalyse.setText("Analyse");
        jMenuAnalysis.add(jMenuItemAnalyse);

        jMenuBar.add(jMenuAnalysis);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPaneOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPaneOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initIDE(){
        this.setLocationRelativeTo(null);
        jScrollPaneCode.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                jScrollPaneLines.getVerticalScrollBar().setValue(jScrollPaneCode.getVerticalScrollBar().getValue());
            }
        });
        updateLineCount();
        addEvents();
    }
    
    private void addEvents(){
        jTextPaneCode.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER | e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                    updateLineCount();
            }
        });
    }
    
    private void updateLineCount(){
        int count = 1;
        String lines = "1";
        
        for (char car : jTextPaneCode.getText().toCharArray()) {
            if (car == '\n'){
                count++;
                lines += "\n" + count;
            }
        }
        
        jTextPaneLines.setText(lines);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenuAnalysis;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemAnalyse;
    private javax.swing.JMenuItem jMenuItemClose;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemNew;
    private javax.swing.JMenuItem jMenuItemOpen;
    private javax.swing.JMenuItem jMenuItemSave;
    private javax.swing.JMenuItem jMenuItemSaveAs;
    private javax.swing.JPanel jPanelCode;
    private javax.swing.JScrollPane jScrollPaneCode;
    private javax.swing.JScrollPane jScrollPaneLines;
    private javax.swing.JScrollPane jScrollPaneTable;
    private javax.swing.JScrollPane jScrollPaneTerminal;
    private javax.swing.JTabbedPane jTabbedPaneOutput;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPaneCode;
    private javax.swing.JTextPane jTextPaneLines;
    private javax.swing.JTextPane jTextPaneTerminal;
    // End of variables declaration//GEN-END:variables
}
