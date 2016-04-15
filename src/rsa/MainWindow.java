package rsa;

import java.awt.Toolkit;
import javax.jnlp.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author damian
 */
public class MainWindow extends JFrame implements ActionListener{
	

    
    private static final long serialVersionUID = 1L;
    private FileChooserDialog chooseDialog;
    
    
    private JButton generateKeyButton;
    private JButton inOpenFromFileButton;
    private JButton inSaveToFileButton;
    private JButton outSaveToFileButton;
    private JButton privKeyFromFileButton;
    private JButton savePrivKeyButton;
    private JButton savePubKeyButton;
    private JButton startButton;
    private JButton pubKeyFromFileButton;
    
    private JLabel generateKeysLabel;
    private JLabel inputLabel;
    private JLabel keyLengthLabel;
    private JLabel outputLabel;
    private JLabel privKeyLabel;
    private JLabel pubKeyLabel;
    private JLabel statusLabel;
    

    private JMenuBar jMenuBar1;
    private JMenu infoMenu;
    private JMenu modeMenu;
    private JCheckBoxMenuItem decryptModeItem;
    private JCheckBoxMenuItem encryptModeItem;
    
    private JProgressBar jProgressBar1;
   
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    
    private JSpinner jSpinner1;
    
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    
    private JTextField publicKeyTextField;
    private JTextField privateKeyTextField;


    public MainWindow() {
        createMainViews();
        setActionsListeners();
        chooseDialog=new FileChooserDialog(MainWindow.this);
       
    }

  
    private void createMainViews() {

        inputLabel = new JLabel();
        inSaveToFileButton = new JButton();
        inOpenFromFileButton = new JButton();
        jScrollPane1 = new JScrollPane();
        inputTextArea = new JTextArea();
        jScrollPane2 = new JScrollPane();
        outputTextArea = new JTextArea();
        outputLabel = new JLabel();
        outSaveToFileButton = new JButton();
        jProgressBar1 = new JProgressBar();
        statusLabel = new JLabel();
        startButton = new JButton();
        generateKeysLabel = new JLabel();
        keyLengthLabel = new JLabel();
        jSpinner1 = new JSpinner();
        generateKeyButton = new JButton();
        pubKeyLabel = new JLabel();
        pubKeyFromFileButton = new JButton();
        savePubKeyButton = new JButton();
        privKeyLabel = new JLabel();
        savePrivKeyButton = new JButton();
        privKeyFromFileButton = new JButton();
        publicKeyTextField = new JTextField();
        privateKeyTextField = new JTextField();
        jMenuBar1 = new JMenuBar();
        modeMenu = new JMenu();
        encryptModeItem = new JCheckBoxMenuItem();
        decryptModeItem = new JCheckBoxMenuItem();
        infoMenu = new JMenu();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        inputLabel.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        inputLabel.setText("Input");

        inSaveToFileButton.setText("Save");

        inOpenFromFileButton.setText("Open...");

        inputTextArea.setColumns(20);
        inputTextArea.setRows(5);
        jScrollPane1.setViewportView(inputTextArea);

        outputTextArea.setColumns(20);
        outputTextArea.setRows(5);
        outputTextArea.setEnabled(false);
        jScrollPane2.setViewportView(outputTextArea);

        outputLabel.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        outputLabel.setText("Output");

        outSaveToFileButton.setText("Save");

        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setText("Status");

        startButton.setText("START");

        generateKeysLabel.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        generateKeysLabel.setText("Generate your own keys");

        keyLengthLabel.setText("Length [bits]");

        generateKeyButton.setText("Generate");

        pubKeyLabel.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        pubKeyLabel.setText("Public Key");

        pubKeyFromFileButton.setText("Open...");

        savePubKeyButton.setText("Save...");

        privKeyLabel.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        privKeyLabel.setText("Private Key");

        savePrivKeyButton.setText("Save...");

        privKeyFromFileButton.setText("Open...");

        modeMenu.setText("Mode");

        encryptModeItem.setSelected(true);
        encryptModeItem.setText("Encryption");
        modeMenu.add(encryptModeItem);

        decryptModeItem.setSelected(true);
        decryptModeItem.setText("Decryption");
        modeMenu.add(decryptModeItem);

        jMenuBar1.add(modeMenu);

        infoMenu.setText("Info");
        jMenuBar1.add(infoMenu);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(statusLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startButton, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(generateKeysLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(keyLengthLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pubKeyLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(privKeyLabel, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(privKeyFromFileButton, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(jSpinner1)
                            .addComponent(pubKeyFromFileButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(savePrivKeyButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(generateKeyButton, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(savePubKeyButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(publicKeyTextField)
                    .addComponent(privateKeyTextField))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(outputLabel, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                        .addGap(298, 298, 298))
                    .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inputLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inOpenFromFileButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(inSaveToFileButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(outSaveToFileButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(inSaveToFileButton)
                    .addComponent(inOpenFromFileButton)
                    .addComponent(inputLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jProgressBar1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startButton, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(outSaveToFileButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(outputLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(generateKeysLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jSpinner1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(generateKeyButton))
                            .addComponent(keyLengthLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(pubKeyFromFileButton)
                            .addComponent(savePubKeyButton)
                            .addComponent(pubKeyLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(publicKeyTextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(savePrivKeyButton)
                            .addComponent(privKeyFromFileButton)
                            .addComponent(privKeyLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(privateKeyTextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(32, 32, 32))
        );

        pack();
    }
    
    
    private void showErrorWindow(String mssg) {
        
    Toolkit.getDefaultToolkit().beep();
    JOptionPane optionPane = new JOptionPane(mssg,JOptionPane.ERROR_MESSAGE);
    JDialog dialog = optionPane.createDialog("Error");
    dialog.setAlwaysOnTop(true);
    dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    dialog.setVisible(true);
        
    }
    
    private void showInfoWindow(String mssg) { 
    Toolkit.getDefaultToolkit().beep();
    JOptionPane optionPane = new JOptionPane(mssg,JOptionPane.INFORMATION_MESSAGE);
    JDialog dialog = optionPane.createDialog("Information");
    dialog.setAlwaysOnTop(true);
    dialog.setVisible(true);  
    }
    
    
    private void setActionsListeners(){
        
    generateKeyButton.addActionListener(this);
    inOpenFromFileButton.addActionListener(this);
    inSaveToFileButton.addActionListener(this);
    outSaveToFileButton.addActionListener(this);
    privKeyFromFileButton.addActionListener(this);
    savePrivKeyButton.addActionListener(this);
    savePubKeyButton.addActionListener(this);
    startButton.addActionListener(this);
    pubKeyFromFileButton.addActionListener(this);
       
    }

    
   
    
  

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // Input from file
        if( e.getSource() == inOpenFromFileButton ){
            File chosenFile=chooseDialog.showOpenDialog(FileChooserDialog.FOR_IN_OUT_TEXT);
            if( chosenFile!= null ){
                openInputText( chosenFile.getPath() );
            }
        }
         
        // Input to file                                   
        else if( e.getSource() == inSaveToFileButton ){
           File chosenFile=chooseDialog.showSaveDialog(FileChooserDialog.FOR_INPUT_TEXT);
            if( chosenFile != null ){
                saveInputText( chosenFile.getPath()) ;
            }
        }
        
        // Output to file
        else if( e.getSource() == outSaveToFileButton ){
            File chosenFile=chooseDialog.showSaveDialog(FileChooserDialog.FOR_OUTPUT_TEXT);
            if( chosenFile != null ){
                saveOutputText( chosenFile.getPath() );
            }
        }
        
        // Public key to file
        else if( e.getSource() == savePubKeyButton ){
            File chosenFile=chooseDialog.showSaveDialog(FileChooserDialog.FOR_PUB_KEY);
            if( chosenFile != null ){
                savePublicKey( chosenFile.getPath() );
            }
        }
        
        // Private key to file
        else if( e.getSource() == savePrivKeyButton ){
            File chosenFile=chooseDialog.showSaveDialog(FileChooserDialog.FOR_PRIV_KEY);
            if( chosenFile != null ){
                savePrivateKey( chosenFile.getPath() );
            }
        }
        
        // Public key from file
        else if( e.getSource() == pubKeyFromFileButton ){
            File chosenFile=chooseDialog.showOpenDialog(FileChooserDialog.FOR_PUB_KEY);
            if( chosenFile != null ){
                openPublicKey( chosenFile.getPath() );
            }
        }
        
        // Private key from file
        else if( e.getSource() == privKeyFromFileButton){
            File chosenFile=chooseDialog.showOpenDialog(FileChooserDialog.FOR_PRIV_KEY);
            if( chosenFile != null ){
                openPrivateKey( chosenFile.getPath() );
            }        
        }
    }

    
    private void saveInputText(String directory) {
        
        String text;
        text=inputTextArea.getText();
        
        BufferedWriter writer = null;
        try
        {
            File file = new File(directory+"."+RSA.INPUT_EXTENSION);
            writer = new BufferedWriter( new FileWriter(file));
            writer.write( text );

        }
        catch ( IOException e)
        {
            showErrorWindow("Cannot save input text, try again.");
            
        }
        finally
        {
            try
            {
                if ( writer != null){
                writer.close( );
                showInfoWindow("Input saved succesfully");
                }
                
            }
            catch ( IOException e)
            {
                showErrorWindow("Cannot save input text, try again.");
            }
        }

        
    }

    private void openInputText(String directory){
        try {
            String text= new String(Files.readAllBytes(Paths.get(directory)));
            inputTextArea.setText(text);
        } catch (IOException ex) {
           showErrorWindow("Cannot open input text, try again.");
        }
        
    }

    private void saveOutputText(String directory){
        
        String text;
        text=outputTextArea.getText();
        
        BufferedWriter writer = null;
        try
        {
            File file = new File(directory+"."+RSA.OUTPUT_EXTENSION);
            writer = new BufferedWriter( new FileWriter(file));
            writer.write( text );

        }
        catch ( IOException e)
        {
            showErrorWindow("Cannot save output text, try again.");
            
        }
        finally
        {
            try
            {
                if ( writer != null)
                writer.close( );
                showInfoWindow("Output saved succesfully");
            }
            catch ( IOException e)
            {
                showErrorWindow("Cannot save output text, try again.");
            }
        }
      
        
    }

    private void savePublicKey(String directory) {
        
        String key;
        key=publicKeyTextField.getText();
        
        BufferedWriter writer = null;
        try
        {
            File file = new File(directory+"."+RSA.PUB_KEY_EXTENSION);
            writer = new BufferedWriter( new FileWriter(file));
            writer.write( key);

        }
        catch ( IOException e)
        {
            showErrorWindow("Cannot save public key try again.");
            
        }
        finally
        {
            try
            {
                if ( writer != null)
                writer.close( );
                showInfoWindow("Key saved succesfully");
            }
            catch ( IOException e)
            {
                showErrorWindow("Cannot save public key try again.");
            }
        }
    }

    private void savePrivateKey(String directory) {
        String key;
        key=privateKeyTextField.getText();
        
        BufferedWriter writer = null;
        try
        {
            File file = new File(directory+"."+RSA.PRIV_KEY_EXTENSION);
            writer = new BufferedWriter( new FileWriter(file));
            writer.write( key);

        }
        catch ( IOException e)
        {
            showErrorWindow("Cannot save private key try again.");
            
        }
        finally
        {
            try
            {
                if ( writer != null)
                writer.close( );
                showInfoWindow("Key saved succesfully");
            }
            catch ( IOException e)
            {
                showErrorWindow("Cannot save private key try again.");
            }
        }
        
    }

    private void openPublicKey(String directory) {
        try {
            String key= new String(Files.readAllBytes(Paths.get(directory)));
            publicKeyTextField.setText(key);
        } catch (IOException ex) {
           showErrorWindow("Cannot open public key, try again.");
        }  
    }

    private void openPrivateKey(String directory) {
        try {
            String key= new String(Files.readAllBytes(Paths.get(directory)));
            privateKeyTextField.setText(key);
        } catch (IOException ex) {
           showErrorWindow("Cannot open private key, try again.");
        }        
    }


    
}
            
          
       


      
            
        
    




