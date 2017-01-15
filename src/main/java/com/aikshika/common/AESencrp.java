package com.aikshika.common;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESencrp {
    
     private static final String ALGO = "AES";
    private static final byte[] keyValue = 
        new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't',
'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

public static String encrypt(String Data)  {
        Key key;
        String encryptedValue = "";
		try {
			key = generateKey();
			 Cipher c = Cipher.getInstance(ALGO);
		        c.init(Cipher.ENCRYPT_MODE, key);
		        byte[] encVal = c.doFinal(Data.getBytes());
		      encryptedValue = new BASE64Encoder().encode(encVal);
		       
		} catch (Exception e) {
			e.printStackTrace();
		} return encryptedValue;
       
    }

    public static String decrypt(String encryptedData)  {
        Key key;
        String decryptedValue = "";
		try {
			key = generateKey();
			 Cipher c = Cipher.getInstance(ALGO);
		        c.init(Cipher.DECRYPT_MODE, key);
		        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
		        byte[] decValue = c.doFinal(decordedValue);
		        decryptedValue = new String(decValue);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
       
        return decryptedValue;
    }
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
}
   /* public static void main(String[] args) throws Exception {

        String password = "1234";
        String passwordEnc = AESencrp.encrypt(password);
        //String passwordDec = AESencrp.decrypt(passwordEnc);

       // System.out.println("Plain Text : " + password);
        System.out.println("Encrypted Text : " + passwordEnc);
        System.out.println("Decrypted Text : " + passwordDec);
        System.out.println("Decrypted Encrypted again:"+AESencrp.encrypt(passwordDec));
    }
*/
}
