
package aees;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Methods 
{
    private static String algorithm = "AES";
    private static byte[] keyValue=new byte[] 
    { 'A', 'S', 'e', 'c', 'u', 'r', 'e', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
    static ArrayList<String> encryptedWords = new ArrayList<>();
    static ArrayList<String> decryptedWords = new ArrayList<>();
    
        public static void disectPlainText(String plainText) throws Exception
        {
            int count = 0;
            Scanner chopper = new Scanner(plainText);
            while(chopper.hasNext())
            {
                count++;
                String word = chopper.next();
                encryptedWords.add(encrypt(word));
                
                }

                rotateKeyValue();
                
                
            }
            
    
        public static void rotateKeyValue()
        {
            byte last = keyValue[keyValue.length-1];     
            for( int index = keyValue.length-2; index >= 0 ; index-- )
            {
                keyValue[index+1] = keyValue[index];
            }
            keyValue[0] = last;
        }
        
        public static void unrotateKeyValue()
        {
            byte first = keyValue[0];     
            for( int index = 0; index <= keyValue.length-2 ; index-- )
            {
                keyValue[index] = keyValue[index++];
            }
            keyValue[keyValue.length-1] = first;
        }
    
        /*
        Performs Encryption
        */
        public static String encrypt(String plainText) throws Exception 
        {
            
                Key key = generateKey();
                Cipher chiper = Cipher.getInstance(algorithm);
                chiper.init(Cipher.ENCRYPT_MODE, key);
                byte[] encVal = chiper.doFinal(plainText.getBytes());
                String encryptedValue = new BASE64Encoder().encode(encVal);
                return encryptedValue;
        }

        /*
        Performs decryption
        */
        public static String decrypt(String encryptedText) throws Exception 
        {
                // generate key 
                Key key = generateKey();
                Cipher chiper = Cipher.getInstance(algorithm);
                chiper.init(Cipher.DECRYPT_MODE, key);
                byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedText);
                byte[] decValue = chiper.doFinal(decordedValue);
                String decryptedValue = new String(decValue);
                return decryptedValue;
        }

        /*
            generateKey() is used to generate a secret key for AES algorithm
        */
        private static Key generateKey() throws Exception 
        {
                Key key = new SecretKeySpec(keyValue, algorithm);
                return key;
        }
        
        public static void printEncryptedText()
        {
            int count = 0;
            for(String a : encryptedWords)
            {
                if(count == 10)
                {
                    System.out.println();
                    count = 0;
                }
                System.out.print(a+"");
                count++;
            }
        }
        
        public static void decryptPlainText() throws Exception
        {
            for(String a : encryptedWords)
            {
                decryptedWords.add(decrypt(a));
                unrotateKeyValue();
            }
            
        }
}
