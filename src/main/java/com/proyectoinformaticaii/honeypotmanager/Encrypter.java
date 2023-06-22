package com.proyectoinformaticaii.honeypotmanager;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Encrypter {
  private byte[] aesKey = null;
  private SecretKeySpec secretKeySpec = null;
  private Cipher encrypter = null;
  private Cipher decrypter = null;

  
  public Encrypter() throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException {
    this.aesKey = new byte[]{'a', 'a', 'a', 'a',
                             'b', 'b', 'b', 'b',
                             'c', 'c', 'c', 'c',
                             'd', 'd', 'd', 'd'};
    this.secretKeySpec = new SecretKeySpec(this.aesKey, "AES");
    this.encrypter = Cipher.getInstance("AES");
    this.encrypter.init(Cipher.ENCRYPT_MODE, this.secretKeySpec);
    this.decrypter = Cipher.getInstance("AES");
    this.decrypter.init(Cipher.DECRYPT_MODE, this.secretKeySpec);    
  }
  
  public String encryptString(String strToEncrypt) throws IllegalBlockSizeException, 
                                                          BadPaddingException {
    byte[] encryptedBytes = this.encrypter.doFinal(strToEncrypt.getBytes());
    String base64AESEncryptedString = Base64.getEncoder().encodeToString(encryptedBytes);
    return base64AESEncryptedString;
  }
  
  public String decryptString(String base64AESEncryptedString) throws IllegalBlockSizeException, 
                                                                      BadPaddingException {
    byte[] encryptedBytes = Base64.getDecoder().decode(base64AESEncryptedString);
    byte[] decryptedBytes = this.decrypter.doFinal(encryptedBytes);
    String decryptedString = new String(decryptedBytes);
    return decryptedString;
  }
}
