///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.thirteendolars;
//import static java.lang.Math.pow;
//import java.math.BigInteger;
//import java.security.SecureRandom;
///**
// *
// * @author damian
// */
//
//
//public class AlgorithmRSA {
//    
//    
//    
//    private final int CERTAINTY= 128; // probability that the new BigInteger represents a prime number will exceed (1 - 1/2^certainty)
//
//  private BigInteger n;
//  private BigInteger d;
//  private BigInteger e;
//
//  private int mBitsLength;
//  private int MODE;
//  
//  private String mPublicKey;
//  private String mPrivateKey;
//
//
//  public AlgorithmRSA(String pubKey) {
//    n = newn;
//    e = newe;
//    MODE=RSA.ENCRYPTION;
//  }
//
//  /** AlgorithmRSA an instance that can both encrypt and decrypt. */
//  public AlgorithmRSA(int bitsLength) {
//    MODE=RSA.DECRYPTION;
//    
//    mBitsLength = bitsLength;
//    
//    generateKeys(bitsLength);
//    
//    
//    
//   
//  }
//
//  
//  
//
//  public synchronized String encrypt(String message) {
//    return (new BigInteger(message.getBytes())).modPow(e, n).toString();
//  }
//
//  public synchronized String decrypt(String message) {
//    return new String((new BigInteger(message)).modPow(d, n).toByteArray());
//  }
//
// 
//
//  public synchronized void generateKeys(int keyLength) {
//      
//    SecureRandom rand = new SecureRandom();
//    BigInteger pValue = new BigInteger(keyLength/2, CERTAINTY, rand);
//    BigInteger qValue = new BigInteger(keyLength/2, CERTAINTY, rand);
//    
//    n = pValue.multiply(qValue); //   n=p*q
//    
//    //  value= (p-1)*(q-1)
//    BigInteger value = (pValue.subtract(BigInteger.ONE)).multiply(qValue.subtract(BigInteger.ONE));
//    
//    // find such "e" that GCD( e,value ) == 1
//    e = new BigInteger("3"); //start from 3
//    while ( value.gcd(e).intValue() != 1 ) {
//      e = e.add( new BigInteger("2") );
//    }
//    
//    // generate "d" private key
//    d = e.modInverse(value);
//    
//    mPrivateKey= d.toString(16);
//    mPublicKey=        
//  }
//
//
//
////  /** Trivial test program. */
////  public static void main(String[] args) {
////    AlgorithmRSA rsa = new AlgorithmRSA(1024);
////
////    String text1 = "Yellow and Black Border Collies";
////    System.out.println("Plaintext: " + text1);
////    System.out.println("Bytes text: " + text1.getBytes() );
////    BigInteger plaintext = new BigInteger(text1.getBytes());
////
////    BigInteger ciphertext = rsa.encrypt(plaintext);
////    System.out.println("Ciphertext: " + ciphertext);
////    plaintext = rsa.decrypt(ciphertext);
////    System.out.println("decrypt text: " + plaintext);
////    System.out.println("byte array text: " + plaintext.toByteArray());
////
////    String text2 = new String(plaintext.toByteArray());
////    System.out.println("Plaintext: " + text2);
////    
////    
////  
////  }
//
//}
//
//   