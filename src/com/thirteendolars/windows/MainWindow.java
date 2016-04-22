package com.thirteendolars.windows;

import com.thirteendolars.RSA;
import com.thirteendolars.windows.InfoDialog;
import com.thirteendolars.windows.FileChooserDialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import sun.rmi.runtime.Log;

/**
 *
 * @author damian
 */
public abstract class MainWindow extends JFrame implements ActionListener {

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
    private JButton infoMenu;
    private JMenu modeMenu;
    private JCheckBoxMenuItem decryptModeItem;
    private JCheckBoxMenuItem encryptModeItem;

    private JProgressBar progressBar;

    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;

    private KeyLengthSpinner keyLengthSpinner;

    private JTextArea inputTextArea;
    private JTextArea outputTextArea;

    private JTextField publicKeyTextField;
    private JTextField privateKeyTextField;
    
    

    public MainWindow() {
        createMainViews();
        setActionsListeners();
        chooseDialog = new FileChooserDialog(this);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(800, 500));
        this.setTitle("RSA - Encryption");
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
        progressBar = new JProgressBar();
        statusLabel = new JLabel();
        startButton = new JButton();
        generateKeysLabel = new JLabel();
        keyLengthLabel = new JLabel();
        keyLengthSpinner = new KeyLengthSpinner();
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
        infoMenu = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        inputLabel.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        inputLabel.setText("Input");

        inSaveToFileButton.setText("Save...");

        inOpenFromFileButton.setText("Open...");

        inputTextArea.setColumns(20);
        inputTextArea.setRows(5);
        jScrollPane1.setViewportView(inputTextArea);

        outputTextArea.setColumns(20);
        outputTextArea.setRows(5);
        jScrollPane2.setViewportView(outputTextArea);

        outputLabel.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        outputLabel.setText("Output");

        outSaveToFileButton.setText("Save...");

        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setText("Status");

        startButton.setText("START ENCRYPTION");

        generateKeysLabel.setFont(new java.awt.Font("Noto Sans", Font.BOLD, 14)); // NOI18N
        generateKeysLabel.setText("Generate your own keys");

        keyLengthLabel.setText("Length [bits]");

        generateKeyButton.setText("Generate");

        pubKeyLabel.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        pubKeyLabel.setText("Public Key (e,n)");

        pubKeyFromFileButton.setText("Open...");

        savePubKeyButton.setText("Save...");

        privKeyLabel.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        privKeyLabel.setText("Private Key (d,n)");

        savePrivKeyButton.setText("Save...");

        privKeyFromFileButton.setText("Open...");

        modeMenu.setText("Mode");

        encryptModeItem.setSelected(true);
        encryptModeItem.setText("Encryption");
        modeMenu.add(encryptModeItem);

        decryptModeItem.setSelected(false);
        decryptModeItem.setText("Decryption");
        modeMenu.add(decryptModeItem);

        jMenuBar1.add(modeMenu);

        infoMenu.setText("Info");
        infoMenu.setContentAreaFilled(false);
        infoMenu.setBorder(null);
        infoMenu.setAlignmentY(0.6f);

        jMenuBar1.add(infoMenu);

        setJMenuBar(jMenuBar1);

        progressBar.setMinimum(0);
        progressBar.setMaximum(100);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(statusLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(progressBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                                .addComponent(keyLengthSpinner)
                                                .addComponent(pubKeyFromFileButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(savePrivKeyButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(generateKeyButton, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                                .addComponent(savePubKeyButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(publicKeyTextField,200,300,Short.MAX_VALUE)
                                .addComponent(privateKeyTextField,200,300,Short.MAX_VALUE))
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
                                        .addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
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
                                                        .addComponent(keyLengthSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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

    private void setActionsListeners() {

        generateKeyButton.addActionListener(this);
        inOpenFromFileButton.addActionListener(this);
        inSaveToFileButton.addActionListener(this);
        outSaveToFileButton.addActionListener(this);
        privKeyFromFileButton.addActionListener(this);
        savePrivKeyButton.addActionListener(this);
        savePubKeyButton.addActionListener(this);
        startButton.addActionListener(this);
        pubKeyFromFileButton.addActionListener(this);
        decryptModeItem.addActionListener(this);
        encryptModeItem.addActionListener(this);
        infoMenu.addActionListener(this);
    }

    protected void showErrorWindow(String mssg) {

        Toolkit.getDefaultToolkit().beep();
        JOptionPane optionPane = new JOptionPane(mssg, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog("Error");
        dialog.setAlwaysOnTop(true);
        dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);

    }

    protected void showInfoWindow(String mssg) {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane optionPane = new JOptionPane(mssg, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog("Information");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Input from file
        if (e.getSource() == inOpenFromFileButton) {
            File chosenFile = chooseDialog.showOpenDialog(FileChooserDialog.FOR_IN_OUT_TEXT);
            if (chosenFile != null) {
                inputTextArea.setText(openInputText(chosenFile.getPath()));
            }
        } // Input to file                                   
        else if (e.getSource() == inSaveToFileButton) {
            File chosenFile = chooseDialog.showSaveDialog(FileChooserDialog.FOR_INPUT_TEXT);
            if (chosenFile != null) {
                saveInputText(chosenFile.getPath(), inputTextArea.getText());
            }
        } // Output to file
        else if (e.getSource() == outSaveToFileButton) {
            File chosenFile = chooseDialog.showSaveDialog(FileChooserDialog.FOR_OUTPUT_TEXT);
            if (chosenFile != null) {
                saveOutputText(chosenFile.getPath(), outputTextArea.getText());
            }
        } // Public key to file
        else if (e.getSource() == savePubKeyButton) {
            File chosenFile = chooseDialog.showSaveDialog(FileChooserDialog.FOR_PUB_KEY);
            if (chosenFile != null) {
                savePublicKey(chosenFile.getPath(), publicKeyTextField.getText());
            }
        } // Private key to file
        else if (e.getSource() == savePrivKeyButton) {
            File chosenFile = chooseDialog.showSaveDialog(FileChooserDialog.FOR_PRIV_KEY);
            if (chosenFile != null) {
                savePrivateKey(chosenFile.getPath(), privateKeyTextField.getText());
            }
        } // Public key from file
        else if (e.getSource() == pubKeyFromFileButton) {
            File chosenFile = chooseDialog.showOpenDialog(FileChooserDialog.FOR_PUB_KEY);
            if (chosenFile != null) {
                publicKeyTextField.setText(openPublicKey(chosenFile.getPath()));
            }
        } // Private key from file
        else if (e.getSource() == privKeyFromFileButton) {
            File chosenFile = chooseDialog.showOpenDialog(FileChooserDialog.FOR_PRIV_KEY);
            if (chosenFile != null) {
                privateKeyTextField.setText(openPrivateKey(chosenFile.getPath()));
            }
        } // Generate keys
        else if (e.getSource() == generateKeyButton) {
            int keyLength = (int) keyLengthSpinner.getValue();
            generateKeys(keyLength);
        } // Start work
        else if (e.getSource() == startButton) {

            String inputText = inputTextArea.getText();
            String key;
            switch (RSA.MODE) {
                case RSA.ENCRYPTION:
                    key = publicKeyTextField.getText();
                    break;
                case RSA.DECRYPTION:
                    key = privateKeyTextField.getText();
                    break;
                default:
                    key = "";
            }
            String output = startRSA(key, inputText);
            outputTextArea.setText(output);
        } else if (e.getSource() == decryptModeItem) {
            
            encryptModeItem.setSelected(false);
            decryptModeItem.setSelected(true);
            startButton.setText("START DECRYPTION");
            this.setTitle("RSA - Decryption");
            setEncryptionViewsEnabled(false);
            RSA.MODE = RSA.DECRYPTION;
        } else if (e.getSource() == encryptModeItem) {
            decryptModeItem.setSelected(false);
            encryptModeItem.setSelected(true);
            startButton.setText("START ENCRYPTION");
            this.setTitle("RSA - Encryption");
            setEncryptionViewsEnabled(true);
            RSA.MODE = RSA.ENCRYPTION;
        } else if (e.getSource() == infoMenu) {
            new InfoDialog(MainWindow.this).setVisible(true);
        }

    }

    public void setProgressBar(int progress) {
        statusLabel.setText(progress + " %");
        progressBar.setValue(progress);
    }

    protected int getKeyLength() {
        return (int) keyLengthSpinner.getValue();
    }

    protected void setPublicKey(String pubKey) {
        publicKeyTextField.setText(pubKey);
    }

    protected void setPrivateKey(String privKey) {
        privateKeyTextField.setText(privKey);
    }

    protected void getPublicKey(String pubKey) {
        publicKeyTextField.getText();
    }

    protected void getPrivateKey(String privKey) {
        privateKeyTextField.getText();
    }

    protected abstract void saveInputText(String directory, String inputText);

    protected abstract String openInputText(String directory);

    protected abstract void saveOutputText(String directory, String outputText);

    protected abstract void savePublicKey(String directory, String publicKey);

    protected abstract void savePrivateKey(String directory, String privateKey);

    protected abstract String openPublicKey(String directory);

    protected abstract String openPrivateKey(String directory);

    protected abstract void generateKeys(int keyLength);

    protected abstract String startRSA(String key, String inputText);

    private void setEncryptionViewsEnabled(boolean encryptionEnabled) {
        
            pubKeyFromFileButton.setEnabled(encryptionEnabled);
            publicKeyTextField.setEnabled(encryptionEnabled);
            savePubKeyButton.setEnabled(encryptionEnabled);
            generateKeyButton.setEnabled(encryptionEnabled);
            keyLengthSpinner.setEnabled(encryptionEnabled);
            generateKeysLabel.setEnabled(encryptionEnabled);
            keyLengthLabel.setEnabled(encryptionEnabled);
            pubKeyLabel.setEnabled(encryptionEnabled);
    }
    
    public void setGenerateKeysEnabled(boolean generateKeysEnabled){
        
        
            pubKeyFromFileButton.setEnabled(generateKeysEnabled);
            publicKeyTextField.setEnabled(generateKeysEnabled);
            savePubKeyButton.setEnabled(generateKeysEnabled);
            generateKeyButton.setEnabled(generateKeysEnabled);
            keyLengthSpinner.setEnabled(generateKeysEnabled);
            generateKeysLabel.setEnabled(generateKeysEnabled);
            keyLengthLabel.setEnabled(generateKeysEnabled);
            pubKeyLabel.setEnabled(generateKeysEnabled);
            privKeyFromFileButton.setEnabled(generateKeysEnabled);
            privKeyLabel.setEnabled(generateKeysEnabled);
            privateKeyTextField.setEnabled(generateKeysEnabled);;
            savePrivKeyButton.setEnabled(generateKeysEnabled);   
    }
    
    public void setStartEnabled(boolean setStartEnabled){
        startButton.setEnabled(setStartEnabled);
    }
    
    public void setMenuEnabled(boolean setMenuEnabled){
        modeMenu.setEnabled(setMenuEnabled);
    }
    
    

}
