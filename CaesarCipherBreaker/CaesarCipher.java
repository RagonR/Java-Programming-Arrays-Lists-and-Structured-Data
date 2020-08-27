import edu.duke.*;
import java.util.Random;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
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
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encryptedTwoKeys = new StringBuilder(input);
        for(int i = 0; i<input.length(); i++){
            char newChar = encryptedTwoKeys.charAt(i);
            String encryptIt = Character.toString(newChar);
            if(i%2 == 0){
                encryptedTwoKeys.replace(i, i+1, encrypt(encryptIt, key1));
            }
            else{
                encryptedTwoKeys.replace(i, i+1, encrypt(encryptIt, key2));
            }        
        } 
        return encryptedTwoKeys.toString();
    }
        
    public void testRandom (){
        Random rand = new Random();
        rand.nextInt(10);
    }
    
    public void testEncryptTwoKeys() {
/*        for(int i = 1; i<27; i++){
            for(int ii = 1; ii<27; ii++){*/
                System.out.println(encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8) + " x = " + 21 + " y = " + 8);
            
        
    }
    
    public void testCaesar() {
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        //String encrypted = encrypt(message, key);
        String encrypted = encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", key);        
        System.out.println(encrypted);
        /*String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);*/
    }
}

