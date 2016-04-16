/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thirteendolars.windows;

import com.thirteendolars.RSA;
import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author damian
 */
public class FileChooserDialog {
    
    public static final int FOR_INPUT_TEXT=1;
    public static final int FOR_OUTPUT_TEXT=2;
    public static final int FOR_PUB_KEY=3;
    public static final int FOR_PRIV_KEY=4;
    public static final int FOR_IN_OUT_TEXT=5;
    
    private JFileChooser openFileDialog;
    private JFileChooser saveFileDialog;
    private Component context;
    
    
    public FileChooserDialog(Component context){
        
        openFileDialog=new JFileChooser();
        saveFileDialog=new JFileChooser();
        this.context=context;
             
    }
    
    
    public File showSaveDialog(int mode){
        int action;
        FileNameExtensionFilter filter = getExtensionFilterForMode(mode);
        saveFileDialog.setAcceptAllFileFilterUsed(false);
        saveFileDialog.setFileFilter(filter);
        action=saveFileDialog.showSaveDialog(context);
                
        if(action==JFileChooser.APPROVE_OPTION){
            return saveFileDialog.getSelectedFile();
        }
        else return null;  
    }
    
    
       public File showOpenDialog(int mode){
        int action;
        FileNameExtensionFilter filter = getExtensionFilterForMode(mode);
        openFileDialog.setAcceptAllFileFilterUsed(false);
        openFileDialog.setFileFilter(filter);
        action=openFileDialog.showOpenDialog(context);
        
        if(action==JFileChooser.APPROVE_OPTION){
            return openFileDialog.getSelectedFile();
        }
        else return null;  
    }

       
    private FileNameExtensionFilter getExtensionFilterForMode(int mode) {
        
        switch(mode){
            case FOR_INPUT_TEXT:
                return new FileNameExtensionFilter("*.in",RSA.INPUT_EXTENSION);
                
            case FOR_OUTPUT_TEXT:
                return new FileNameExtensionFilter("*.out",RSA.OUTPUT_EXTENSION);
                
            case FOR_PUB_KEY:
                return new FileNameExtensionFilter("*.pub",RSA.PUB_KEY_EXTENSION);
                
            case FOR_PRIV_KEY:
                return new FileNameExtensionFilter("*.priv",RSA.PRIV_KEY_EXTENSION);
            case FOR_IN_OUT_TEXT:
                return new FileNameExtensionFilter("*.in , *.out",RSA.INPUT_EXTENSION,RSA.OUTPUT_EXTENSION);
        }
        
        return null;
   }
    
    
}
