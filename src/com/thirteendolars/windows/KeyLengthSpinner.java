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
    }
    
    
    
    

    @Override
    public Object getPreviousValue() {
        
        Integer currentValue = (Integer) getValue();

        if( currentValue.equals(Integer.valueOf(30))) return 20;
        if( currentValue.equals(Integer.valueOf(20))) return 10;
        if( currentValue.equals(Integer.valueOf(10))) return 0;
        
        return 0;
    }

    @Override
    public Object getNextValue() {
        
        Integer currentValue = (Integer) getValue();

        if( currentValue.equals(Integer.valueOf(0))) return 10;
        if( currentValue.equals(Integer.valueOf(10))) return 20;
        if( currentValue.equals(Integer.valueOf(20))) return 30;
        
        return 30;
    }
   
    
    
    
}
