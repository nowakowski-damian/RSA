/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thirteendolars;
import com.thirteendolars.windows.MainWindow;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author damian
 */


public class AlgorithmRSA {
    
    
  private MainWindow mContext;
  private Timer mProgressTimer;
  private int mProgress;
  
  private final int CERTAINTY= 128; // probability that the new BigInteger represents a prime number will exceed (1 - 1/2^certainty)

  private BigInteger n;
  private BigInteger d;
  private BigInteger e;

  private String mPublicKeyE;
  private String mPublicKeyN;
  private String mPrivateKeyD;
  private int mKeyLength;

    public AlgorithmRSA(MainWindow mContext) {
        this.mContext = mContext;
    }
  
    public String getPublicKeyE() {
        return mPublicKeyE;
    }
    
    public String getPublicKeyN() {
        return mPublicKeyN;
    }

    public String getPrivateKeyD() {
        return mPrivateKeyD;
    }



  
  public void setPublicKeyE(String pubKeyE){
      BigInteger eValue = new BigInteger(pubKeyE,16);
      e = eValue;
  }
  
  public void setPublicKeyN(String pubKeyN){
      BigInteger nValue = new BigInteger(pubKeyN,16);
      n = nValue;
      
  }
  
  public void setPrivateKeyD(String privKeyD){
      BigInteger dValue = new BigInteger(privKeyD,16);
      d = dValue;
  }
  
  public void setKeyLength( int keyLength ){
      mKeyLength=keyLength;
  }
 
  
  public synchronized String encrypt(String text) {
      
       if( text.length()*8 > mKeyLength-11 ){
          
           return "Your key is too short for above message\nIncrease your key size or decrease your message length and try again.";
  
      }
      else{
          return new String( new BigInteger( text.getBytes() ).modPow(e, n).toString() );
      }

  }

  
  public synchronized String decrypt(String text) {
      
      for( char sign : text.toCharArray() ){
          if( !Character.isDigit(sign) ){
            return "Your input is invalid";
          }
      }

      return new String( (new BigInteger(text)).modPow(d, n).toByteArray() );
}
  

 
  
 public synchronized void generateKeys(int keyLength) {
     
    mContext.setButtonsEnabled(false);
    setProgress(1);
    
    mKeyLength=keyLength;  
    SecureRandom rand = new SecureRandom();
    
    setProgress(3);
    startCountProgress(40,keyLength);
    BigInteger pValue = new BigInteger(keyLength/2, CERTAINTY, rand); // long last
    mProgressTimer.cancel();
    setProgress(40);
    startCountProgress(70,keyLength);
    BigInteger qValue = new BigInteger(keyLength/2, CERTAINTY, rand);  // long last
    mProgressTimer.cancel();
    setProgress(70);
    startCountProgress(95,keyLength);
    
    n = pValue.multiply(qValue); //   n=p*q
    
    //  value= (p-1)*(q-1)
    BigInteger value = (pValue.subtract(BigInteger.ONE)).multiply(qValue.subtract(BigInteger.ONE));
    
    // find such "e" that GCD( e,value ) == 1
    e = new BigInteger(keyLength/2, CERTAINTY, rand);       // long last
    mProgressTimer.cancel();
    setProgress(95);
    startCountProgress(99,keyLength);
    int i=0;
    while ( value.gcd(e).intValue() != 1 ) {
      e = e.subtract(new BigInteger("2") );
      System.out.println(++i+"");
    }
    
    // generate "d" private key
    d = e.modInverse(value);
    
    mPrivateKeyD = d.toString(16);
    mPublicKeyE = e.toString(16);
    mPublicKeyN = n.toString(16);
    mProgressTimer.cancel();
    setProgress(100);
    mContext.setButtonsEnabled(true);
    setProgress(100);
  }
 
 
 private void startCountProgress(int max,int keyLength) {
     
     mProgressTimer = new Timer();
     TimerTask task = new TimerTask() {

         @Override
         public void run() {
            if(mProgress<max) {
                mProgress++;
                setProgress(mProgress);
            }
            else mProgressTimer.cancel();
             
         }

     }; 
     
     int period;
     
     switch(keyLength){
         
         case 64: period=1;
             break;
         case 128: period=2;
             break;
         case 256: period=5;
             break;
         case 512:  period=10;
             break;
         case 1024: period=20;
             break;
         case 2048: period=70;
             break;
         case 4096: period=200;
             break;
         case 8192: period=550;
             break;
         case 16384: period=22000;
             break;
         default: period=200;
  
     }
     mProgressTimer.scheduleAtFixedRate(task, 0,period );
   
 }
     
  
    private void setProgress(int i) {
       mContext.setProgressBar(i);
       mProgress=i;
    }



}

   