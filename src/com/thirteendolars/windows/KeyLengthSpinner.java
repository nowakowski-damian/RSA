/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thirteendolars.windows;

import javax.swing.JSpinner;

/**
 *
 * @author damian
 */
public class KeyLengthSpinner extends JSpinner{
    
    
    public KeyLengthSpinner(){
        super();
        ( (DefaultEditor)this.getEditor() ).getTextField().setEditable(false);
        this.setValue(256);
    }
    
    
    
    

    @Override
    public Object getPreviousValue() {
        
        Integer currentValue = (Integer) getValue();

        if( currentValue.equals(Integer.valueOf(8192))) return 4096;
        if( currentValue.equals(Integer.valueOf(4096))) return 2048;
        if( currentValue.equals(Integer.valueOf(2048))) return 1024;
        if( currentValue.equals(Integer.valueOf(1024))) return 512;
        if( currentValue.equals(Integer.valueOf(512))) return 256;
        if( currentValue.equals(Integer.valueOf(256))) return 128;
        if( currentValue.equals(Integer.valueOf(128))) return 64;
       
        
        return 64;
    }

    @Override
    public Object getNextValue() {
        
        Integer currentValue = (Integer) getValue();

        if( currentValue.equals(Integer.valueOf(64))) return 128;
        if( currentValue.equals(Integer.valueOf(128))) return 256;
        if( currentValue.equals(Integer.valueOf(256))) return 512;
        if( currentValue.equals(Integer.valueOf(512))) return 1024;
        if( currentValue.equals(Integer.valueOf(1024))) return 2048;
        if( currentValue.equals(Integer.valueOf(2048))) return 4096;
        if( currentValue.equals(Integer.valueOf(4096))) return 8192;
        
        
        return 8192;
    }
   
    
    
    
}
