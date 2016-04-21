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

  private String mPublicKey;
  private String mPrivateKey;
  private int mKeyLength;

    public AlgorithmRSA(MainWindow mContext) {
        this.mContext = mContext;
    }
  
  

    public String getPublicKey() {
        return mPublicKey;
    }



    public String getPrivateKey() {
        return mPrivateKey;
    }



  
  public void setPublicKey(String pubKey){
            int indexOfSpace=pubKey.indexOf(",");
      String eString = pubKey.substring(0, indexOfSpace);
      String nString =pubKey.substring(indexOfSpace+1);
      BigInteger nValue = new BigInteger(nString,16);
      BigInteger eValue = new BigInteger(eString,16);
      n = nValue;
      e = eValue;
      
  }
  
  public void setPrivateKey(String privKey){
      
      int indexOfSpace=privKey.indexOf(",");
      String dString = privKey.substring(0, indexOfSpace);
      String nString =privKey.substring(indexOfSpace+1);
      BigInteger nValue = new BigInteger(nString,16);
      BigInteger dValue = new BigInteger(dString,16);
      n = nValue;
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
      
    return new String( (new BigInteger(text)).modPow(d, n).toByteArray() );
     
}
  

 
  
 public synchronized void generateKeys(int keyLength) {
     
    mContext.setGenerateKeysEnabled(false);
    mContext.setStartEnabled(false);
    mContext.setMenuEnabled(false);
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
    
    mPrivateKey= d.toString(16) +","+n.toString(16);
    mPublicKey=  e.toString(16) +","+n.toString(16);
    mProgressTimer.cancel();
    mContext.setGenerateKeysEnabled(true);
    mContext.setStartEnabled(true);
    mContext.setMenuEnabled(true);
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
     
     
     
     
 



//  public static void main(String[] args) {
//    AlgorithmRSA rsa = new AlgorithmRSA();
//
//    String text1 = "aco kiedy";
//    
//    System.out.println("Bytes text: " + text1.getBytes());
//    System.out.println("Length: " + text1.length());
//
//
//   
//    rsa.generateKeys(72);
//    
////    String pubKey = rsa.getPublicKey();
////    String privKey = rsa.getPrivateKey();
////   
////    rsa.setPrivateKey(privKey);
////    rsa.setPublicKey(pubKey);
//    
//    String newText=rsa.encrypt(text1);
//    
//    System.out.println("Encrypted text: " + newText);
//    System.out.println("Decrypted text: " + rsa.decrypt(newText));
//   
//  
//  }

    private void setProgress(int i) {
       mContext.setProgressBar(i);
       mProgress=i;
    }



}

   