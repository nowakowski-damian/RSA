/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thirteendolars;

import com.thirteendolars.windows.MainWindow;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 *
 * @author damian
 */
public class RSA extends MainWindow {
    
    public static final String OUTPUT_EXTENSION="out";
    public static final String INPUT_EXTENSION="in";
    public static final String PUB_KEY_EXTENSION="pub";
    public static final String PRIV_KEY_EXTENSION="priv";
    public static final int ENCRYPTION=1;
    public static final int DECRYPTION=2;
    
    public static int MODE;
    
    private AlgorithmRSA RSAManager;
    
    public RSA(){
        RSA.MODE = ENCRYPTION;
        RSAManager=new AlgorithmRSA( this);
    }

   
    
    public static void main(String[] args) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new RSA().setVisible(true);
        });

    }
    
    
    
    
    
    
    @Override
    protected void saveInputText(String directory,String text) {
        
        BufferedWriter writer = null;
        try
        {
            File file = new File(directory+"."+INPUT_EXTENSION);
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
        
    @Override
    protected String openInputText(String directory){
        String text="";
        try {
            text= new String(Files.readAllBytes(Paths.get(directory)));
        } catch (IOException ex) {
           showErrorWindow("Cannot open input text, try again.");
        }
        
        return text;
    }
    
    @Override
    protected void saveOutputText(String directory,String text){

        BufferedWriter writer = null;
        try
        {
            File file = new File(directory+"."+OUTPUT_EXTENSION);
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
    
    @Override
    protected void savePublicKey(String directory, String keyE, String keyN) {

        BufferedWriter writer = null;
        try
        {
            File file = new File(directory+"."+PUB_KEY_EXTENSION);
            writer = new BufferedWriter( new FileWriter(file));
            String textFormatted=keyE+","+keyN;
            writer.write( textFormatted);

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
    
    @Override
    protected void savePrivateKey(String directory,String key) {
        
        BufferedWriter writer = null;
        try
        {
            File file = new File(directory+"."+PRIV_KEY_EXTENSION);
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
    
    @Override
    protected String openPublicKeyE(String directory) {
       String key="";
        try {
            key= new String(Files.readAllBytes(Paths.get(directory)));
            key= key.substring( 0, key.indexOf(",") );
        } catch (IOException ex) {
           showErrorWindow("Cannot open public key, try again.");
        } 
        return key;
    }
    
    @Override
    protected String openPublicKeyN(String directory) {
       String key="";
        try {
            key= new String(Files.readAllBytes(Paths.get(directory)));
            key= key.substring( key.indexOf(",")+1 );
        } catch (IOException ex) {
           showErrorWindow("Cannot open public key, try again.");
        } 
        return key;
    }

    @Override
    protected String openPrivateKey(String directory) {
        String key="";
        try {
            key= new String(Files.readAllBytes(Paths.get(directory)));
        } catch (IOException ex) {
           showErrorWindow("Cannot open private key, try again.");
        }   
        
        return key;
    }

    @Override
    protected synchronized void generateKeys(int keyLength) {
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                RSAManager.generateKeys(keyLength);
                setPublicKeyE(  RSAManager.getPublicKeyE() );
                setPublicKeyN(  RSAManager.getPublicKeyN() );
                setPrivateKeyD( RSAManager.getPrivateKeyD() );
            }
            
        }).start();
 
    }
    
    

    
    @Override
    protected String startRSA(String keyEorD, String keyN, String inputText) {
        
        
        // Check data correctness
        if( keyEorD.length()<8 || keyN.length()<10 ){
            showErrorWindow("Your key is invalid.");
            return "";
        }
        
        if( inputText.isEmpty() ){
            showErrorWindow("Your input is empty.");
            return "";
        }
        
        // if data are correct, continue
        
       String out="";
       RSAManager.setPublicKeyN(keyN);
        
        switch(MODE){
            
            case ENCRYPTION:
                
                RSAManager.setPublicKeyE(keyEorD);
                out=RSAManager.encrypt(inputText);
                break;
                
            case DECRYPTION:
                
                RSAManager.setPrivateKeyD(keyEorD);
                out=RSAManager.decrypt(inputText);
                break;
                
                
        }
            
    
        return out;
    }




    
   
    
    
}
