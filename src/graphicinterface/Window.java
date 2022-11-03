package graphicinterface;

import filemanagment.CustomJFileChooser;
import filemanagment.FileIO;
import data.Component;
import syntacticanalyzer.SyntacticAnalyzer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import lexicalanalysis.Lexer;
import lexicalanalysis.Tokens;
import semanticanalysis.SemanticAnalysis;

/**
 * Clase de la interfaz gr�fica del compilador.
 *
 * @author Juan Jos� Silva L�pez
 * @version 1.0
 */
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
        jTableAnalysis = new javax.swing.JTable();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemNew = new javax.swing.JMenuItem();
        jMenuItemOpen = new javax.swing.JMenuItem();
        jMenuItemSave = new javax.swing.JMenuItem();
        jMenuItemSaveAs = new javax.swing.JMenuItem();
        jMenuItemClose = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuAnalysis = new javax.swing.JMenu();
        jMenuItemLexicAnalysis = new javax.swing.JMenuItem();
        jMenuItemLexicalOutput = new javax.swing.JMenuItem();
        jMenuItemsSntacticAnalysis = new javax.swing.JMenuItem();

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

        jTableAnalysis.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPaneTable.setViewportView(jTableAnalysis);

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

        jMenuItemLexicAnalysis.setText("Lexical analysis");
        jMenuAnalysis.add(jMenuItemLexicAnalysis);

        jMenuItemLexicalOutput.setText("Lexical output");
        jMenuAnalysis.add(jMenuItemLexicalOutput);

        jMenuItemsSntacticAnalysis.setText("Syntactic analysis");
        jMenuAnalysis.add(jMenuItemsSntacticAnalysis);

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

    //<editor-fold defaultstate="collapsed" desc="IDE inicialization">
    /**
     * Inicializa los comonentes personalizados de la interfaz gr�fica.
     */
    private void initIDE() {
        file = null;
        io = new FileIO();
        filechooser = new CustomJFileChooser();
        tableModel = new CustomTableModel();
        jTableAnalysis.setModel(tableModel);
        this.setLocationRelativeTo(null);
        updateLineCount();
        addEvents();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Events">
    /**
     * A?ade los eventos a las scrollbars y a los menus.
     */
    private void addEvents() {
        jScrollPaneCode.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                jScrollPaneLines.getVerticalScrollBar().setValue(jScrollPaneCode.getVerticalScrollBar().getValue());
            }
        });
        jTextPaneCode.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER | e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    updateLineCount();
                }
                fileModified();
            }
        });
        jMenuItemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFile();
            }
        });
        jMenuItemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        jMenuItemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (saveFile()) {
                    updateFileTitle();
                }
            }
        });
        jMenuItemSaveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (saveFileAs()) {
                    updateFileTitle();
                }
            }
        });
        jMenuItemClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeFile();
            }
        });
        jMenuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (saveBefore("close the program") != JOptionPane.CANCEL_OPTION) {
                    Window.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    Window.this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        jMenuItemLexicAnalysis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTerminal();
                lexicalAnalysis();
            }
        });
        jMenuItemLexicalOutput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTerminal();
                lexicalOutput();
            }
        });
        jMenuItemsSntacticAnalysis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTerminal();
                syntacticAnalysis();
            }
        });
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="User experience">
    /**
     * Actualiza el n�mero del contador de l�neas.
     */
    private void updateLineCount() {
        int count = 1;
        String lines = "1";

        for (char car : jTextPaneCode.getText().toCharArray()) {
            if (car == '\n') {
                count++;
                lines += "\n" + count;
            }
        }

        jTextPaneLines.setText(lines);
    }

    /**
     * Actualiza el t�tulo del archivo.
     */
    private void updateFileTitle() {
        this.setTitle(file.getName());
    }

    /**
     * Valida que el archivo a guardar tenga la extenci�n ".sg".
     */
    private void validateExtension() {
        if (!file.getAbsolutePath().endsWith(".sg")) {
            file = new File(file.getAbsolutePath() + ".sg");
        }
    }

    /**
     * Muestra en pantalla el contenido del archivo.
     *
     * @param cont
     */
    private void updateFileContent(String cont) {
        jTextPaneCode.setText(cont);
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="File managment">
    /**
     * Determina si el archivo ha sido modificados desde que se abri�.
     */
    private void fileModified() {
        if (this.getTitle().indexOf("*") == -1) {
            this.setTitle("*" + this.getTitle());
        }
    }

    /**
     * Determina si hay un archivo abierto.
     *
     * @return true: si hay un archivo | false: si no hay un archivo abierto.
     */
    private boolean isFileLoaded() {
        return file != null;
    }

    /**
     * Determina si un archivo est� guardado o no.
     *
     * @return true: si el archivo est� guardado | false: si el archivo no est�
     * guardado
     */
    private boolean getFileSaveState() {
        if (this.getTitle().indexOf("*") == -1) {
            return true;
        }
        return false;
    }

    /**
     * Determina el estado de guardado del archivo 0: no hay un archivo abierto
     * y no ha sido guardado 1: hay un archivo abierto que no se ha guardado 2:
     * no hay un archivo abierto y ha sido guardado 3: hay un archivo abierto y
     * ha sido guardado
     *
     * @return Estado de guardado
     */
    private byte getSaveState() {
        byte state = 0;
        if (isFileLoaded()) {
            state += 1;
        }
        if (getFileSaveState()) {
            state += 2;
        }
        return state;
    }

    /**
     * Guarda el archivo o no antes de realizar una accion.
     *
     * @param action Accci�n a realizar
     * @return 0: se gurda el archivo | 1: no se guarda el archivo | 3: no se
     * realiza la acci�n
     */
    private byte saveBefore(String action) {
        switch (getSaveState()) {
            case 0:
            case 1:
                switch (JOptionPane.showConfirmDialog(this, "Do you want to save the file before to " + action + "?",
                        "Save before to " + action, JOptionPane.YES_NO_CANCEL_OPTION)) {
                    case JOptionPane.YES_OPTION:
                        if (saveFile()) {
                            return JOptionPane.YES_OPTION;
                        } else {
                            return JOptionPane.CANCEL_OPTION;
                        }
                    case JOptionPane.NO_OPTION:
                        return JOptionPane.NO_OPTION;
                    case JOptionPane.CANCEL_OPTION:
                        return JOptionPane.CANCEL_OPTION;
                    case JOptionPane.CLOSED_OPTION:
                        return JOptionPane.CANCEL_OPTION;
                }
        }
        return JOptionPane.YES_OPTION;
    }

    /**
     * Crea un nuevo archivo.
     */
    private void newFile() {
        if (saveBefore("create a new file") != JOptionPane.CANCEL_OPTION && saveFileAs()) {
            updateFileTitle();
            updateFileContent("");
            updateLineCount();
        }
    }

    /**
     * Abre un archivo.
     */
    private void openFile() {
        String text;
        if (saveBefore("open a new file") != JOptionPane.CANCEL_OPTION) {
            if (filechooser.showOpenDialog(this) == filechooser.APPROVE_OPTION) {
                file = filechooser.getSelectedFile();
                if ((text = io.readFile(file)) != null) {
                    updateFileTitle();
                    updateFileContent(text);
                    updateLineCount();
                }
            }
        }
    }

    /**
     * Guarda el archivo.
     * @return true: si el archivo se guard� | false: si el archivo no se guard�.
     */
    private boolean saveFile() {
        if (isFileLoaded()) {
            return io.writeFile(file, jTextPaneCode.getText());
        } else {
            return saveFileAs();
        }
    }

    /**
     * Guarda el archivo con otro nombre.
     * @return true: si el archivo se guard� | false: si el archivo no se guard�.
     */
    private boolean saveFileAs() {
        if (filechooser.showSaveDialog(this) == filechooser.APPROVE_OPTION) {
            file = filechooser.getSelectedFile();
            validateExtension();
            return io.writeFile(file, jTextPaneCode.getText());
        }

        return false;
    }

    /**
     * Cierra el programa.
     */
    private void close() {
        if (saveBefore("close the program") != JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Cierra el archivo.
     */
    private void closeFile() {
        if (saveBefore("close the file") != JOptionPane.CANCEL_OPTION) {
            file = null;
            this.setTitle(DEFAULT_WINDOW_TITLE);
            updateFileContent("");
            updateLineCount();
        }
    }
    // </editor-fold>             

    /**
     * Muestra en consola los tokens que componen el c�digo ingresado en el editor.
     */
    private void lexicalAnalysis() {
        if (!saveFile()) {
            return;
        }

        updateFileTitle();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Lexer lexer = new Lexer(reader);
            Tokens token;
            int line = 1;
            String result = "";

            while (true) {
                token = lexer.yylex();

                if (token == Tokens.Linea) {
                    line++;
                    continue;
                }

                if (token == null) {
                    result += "Linea " + line + ": " + "$ es el s�mbolo terminal";
                    jTextPaneTerminal.setText(result);
                    return;
                }

                result += "Linea " + line + ": " + lexer.yytext() + " es un " + token + "\n";
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Window.this, "Error: " + ex.getMessage(), "IOError", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra en consola los tokens que ingresar�n al analizador sint�ctico.
     */
    private void lexicalOutput() {
        if (!saveFile()) {
            return;
        }

        updateFileTitle();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Lexer lexer = new Lexer(reader);
            Tokens token;
            Component component;
            int line = 1;
            String result = "", text;

            while (true) {
                token = lexer.yylex();

                if (token == Tokens.Linea) {
                    line++;
                    result += "\n";
                    continue;
                }

                if (token == Tokens.ERROR) {
                    result = "Error lexico en la linea " + line + ": " + lexer.yytext() + " no es valido";
                    return;
                }

                if (token == null) {
                    result += " $";
                    jTextPaneTerminal.setText(result);
                    return;
                }

                text = getSyntacticEntrance(token, lexer.yytext());
                component = new Component(line, null, lexer.yytext(), text, null);

                result += component.getToken() + " ";
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Window.this, "Error: " + ex.getMessage(), "IOError", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra los resultados de realizar an�lisis l�xico, sint�ctico y sem�ntico al codigo ingresado.
     */
    private void syntacticAnalysis() {
        if (!saveFile()) {
            return;
        }

        updateFileTitle();
        clearTable();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Lexer lexer = new Lexer(reader);
            Tokens token;
            Component component;
            SyntacticAnalyzer syncAnalyzer = new SyntacticAnalyzer();
            SemanticAnalysis semAnalyzer = new SemanticAnalysis();
            int line = 1;
            String text;

            while (true) {
                token = lexer.yylex();

                if (token == Tokens.ERROR) {
                    jTextPaneTerminal.setText("Error lexico en la linea " + line + ": " + lexer.yytext() + " no es valido");
                    return;
                }

                if (syncAnalyzer.isError()) {
                    jTextPaneTerminal.setText(syncAnalyzer.getError());
                    return;
                }

                if (semAnalyzer.getError() != null) {
                    jTextPaneTerminal.setText(semAnalyzer.getError());
                    return;
                }

                if (token == Tokens.Linea) {
                    line++;
                    continue;
                }

                if (token == null) {
                    component = new Component(line, null, "$", "$", null);
                    syncAnalyzer.syntacticAnalysis(component, semAnalyzer, tableModel);
                    jTextPaneTerminal.setText("Successful compilation!");
                    return;
                }

                text = getSyntacticEntrance(token, lexer.yytext());
                component = new Component(line, null, lexer.yytext(), text, null);

                syncAnalyzer.syntacticAnalysis(component, semAnalyzer, tableModel);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Window.this, "Error: " + ex.getMessage(), "IOError", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearTable(){
        while (tableModel.getRowCount() != 0) {            
            tableModel.removeRow(0);
        }
    }

    /**
     * Regresa el tipo de token que encontr� el analizador l�xico.
     * @param t Clase Enum que contiene todos los tokens que acepta el analizador l�xico
     * @param text Texto que fue reconocido como token por la gram�tica.
     * @return Tipo de token encontrado.
     */
    private String getSyntacticEntrance(Tokens t, String text) {
        switch (t) {
            case T_Dato:
            case Suma:
            case Resta:
            case Multiplicacion:
            case Division:
            case Igual:
            case Parentesis_a:
            case Parentesis_c:
            case Coma:
            case P_coma:
                return text;
            case Identificador:
                return "id";
            case Entero:
            case Flotante:
                return "num";
        }
        return null;
    }

    /**
     * Limpia la terminal.
     */
    private void clearTerminal() {
        jTextPaneTerminal.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenuAnalysis;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemClose;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemLexicAnalysis;
    private javax.swing.JMenuItem jMenuItemLexicalOutput;
    private javax.swing.JMenuItem jMenuItemNew;
    private javax.swing.JMenuItem jMenuItemOpen;
    private javax.swing.JMenuItem jMenuItemSave;
    private javax.swing.JMenuItem jMenuItemSaveAs;
    private javax.swing.JMenuItem jMenuItemsSntacticAnalysis;
    private javax.swing.JPanel jPanelCode;
    private javax.swing.JScrollPane jScrollPaneCode;
    private javax.swing.JScrollPane jScrollPaneLines;
    private javax.swing.JScrollPane jScrollPaneTable;
    private javax.swing.JScrollPane jScrollPaneTerminal;
    private javax.swing.JTabbedPane jTabbedPaneOutput;
    private javax.swing.JTable jTableAnalysis;
    private javax.swing.JTextPane jTextPaneCode;
    private javax.swing.JTextPane jTextPaneLines;
    private javax.swing.JTextPane jTextPaneTerminal;
    // End of variables declaration//GEN-END:variables
    private final String DEFAULT_WINDOW_TITLE = "Lexical Syntactic Analyser LR";
    private File file;
    private CustomJFileChooser filechooser;
    private FileIO io;
    private CustomTableModel tableModel;
}
