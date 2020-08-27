import edu.duke.*;
import java.util.Random;

public class CeasarCipherObject {
    private String alphabet;
    private String shiftedAlphabet;
    private int key;
    public CeasarCipherObject(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";;
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
    }
    
    public String encrypt(String input) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            else if(alphabet.indexOf(Character.toUpperCase(currChar)) != -1){
                int idx1 = alphabet.indexOf(Character.toUpperCase(currChar));                
                char newChar = shiftedAlphabet.charAt(idx1);
                encrypted.setCharAt(i, Character.toLowerCase(newChar));  
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
   
    
    public String decrypt(String input, int key) {
        CeasarCipherObject decryptObcject = new CeasarCipherObject(26-key);
        return decryptObcject.encrypt(input).toString();
    }
    
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encryptedTwoKeys = new StringBuilder(input);
        for(int i = 0; i<input.length(); i++){
            char newChar = encryptedTwoKeys.charAt(i);
            String encryptIt = Character.toString(newChar);
            if(i%2 == 0){
                CeasarCipherObject FirstKey = new CeasarCipherObject(key1);
                encryptedTwoKeys.replace(i, i+1, FirstKey.encrypt(encryptIt));
            }
            else{
                CeasarCipherObject SecondKey = new CeasarCipherObject(key2);
                encryptedTwoKeys.replace(i, i+1, SecondKey.encrypt(encryptIt));
            }        
        } 
        return encryptedTwoKeys.toString();
    }
        
    public void testRandom (){
        Random rand = new Random();
        rand.nextInt(10);
    }
    
    public void testEncryptTwoKeys() {
        
        System.out.println(encryptTwoKeys("Xifqvximt tsdtlxzrx iijirvtl ek Uybi", 21, 8) + " x = " + 21 + " y = " + 8);
    }
    
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        CeasarCipherObject CC = new CeasarCipherObject(key);
        //String encrypted = encrypt(message, key);
        String encrypted = CC.encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!");        
        System.out.println(encrypted);
        String decrypted = CC.decrypt(encrypted, key);
        System.out.println("\n" +decrypted);
        /*String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);*/
    }
}

