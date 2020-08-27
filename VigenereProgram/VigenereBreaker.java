import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {    
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder Slice = new StringBuilder();
        StringBuilder givenMessage = new StringBuilder(message);
        for(int i = whichSlice; i < givenMessage.length(); i+=totalSlices){
            char Slicepart = givenMessage.charAt(i);
            Slice.append(Slicepart);
        }
        return Slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i = 0; i<klength; i++){
            String Slice = sliceString(encrypted, i, klength);
            CaesarCracker Cracker = new CaesarCracker();
            key[i] = Cracker.getKey(Slice);
        }
        return key;
    }

    public HashSet<String> readDictionary  (FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String word : fr.lines()){
            dictionary.add(word.toLowerCase());        
        } 
        return dictionary;
    }
    
    
    public char mostCommonCharIn (HashSet<String> dictionary) {
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for (String word: dictionary){
            String check = word.toLowerCase();
            for (char c: check.toCharArray()){
                if (hm.containsKey(c)){
                    hm.put(c, hm.get(c) + 1);
                }
                else{
                    hm.put(c, 1);
                }
            }
        }
        int max = 0;
        for (char c: hm.keySet()){
            if (hm.get(c) > max){
                max = hm.get(c);
            }
        }
        for (char c: hm.keySet()){
            if (hm.get(c) == max){
                return c;
            }
        }
        return 'n';
    }
    
    public int countWords (String decrypted,  HashSet<String> dictionary) {
        int currentMax = 0;
        int max = 0;
        for (String word : decrypted.split("\\W")){
            if(dictionary.contains(word)){
                currentMax++;
                if(currentMax > max){
                    max = currentMax;
                }
            }            
        }
        return max;
    }
    
    
    public String breakForLanguage (String encrypted, HashSet<String> dictionaries) {
        int max = 0;
        int correctKeylength = 0;
        int[] correctKey = new int[100];
        char CommonChar = mostCommonCharIn(dictionaries);
        String correctDecription = new String();
        for(int key=1; key <= 100; key++){
            int[] keySet = new int[key];
            keySet = tryKeyLength(encrypted.toLowerCase(), key, CommonChar);
            VigenereCipher VCipher = new VigenereCipher(keySet);
            String decrypted = VCipher.decrypt(encrypted.toLowerCase());
            int currentMax = countWords(decrypted, dictionaries);
            if(currentMax > max){
                max = currentMax;
                correctDecription = decrypted;
                correctKey = keySet;
                correctKeylength = key;
            }
        }
        System.out.println("Correct key: " + Arrays.toString(correctKey));
        System.out.println("Correct key length: " + correctKeylength);
        VigenereCipher VCipherAnswer = new VigenereCipher(correctKey);
        return VCipherAnswer.decrypt(encrypted);
    }
    
    public void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages) {
        int max = 0;
        String correctLanguage = new String();
        String correctDecription = new String();
        for (String language: languages.keySet()){
            String decrypted = breakForLanguage(encrypted, languages.get(language));
            int currentMax = countWords(decrypted, languages.get(language));
            if (currentMax > max){
                max = currentMax;
                correctLanguage = language;
                correctDecription = decrypted;
            }
        }
        System.out.println("Max word found: " + max + " with language: " + correctLanguage);
        System.out.println(correctDecription);
    }
    
    
    public void breakVigenere () {
        FileResource File = new FileResource();
        String message = File.asString();
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> directories = new HashMap<String, HashSet<String>>();
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            directories.put(f.getName(), readDictionary(fr));
        }
        breakForAllLanguages(message, directories);
        /*String keyWord = "flute";
        int[] key = new int[5];
        key = tryKeyLength(message, 4, 'e');
        System.out.println(Arrays.toString(key));
        VigenereCipher VCipher = new VigenereCipher(key);
        String decrypted = VCipher.decrypt(message);
        System.out.println(decrypted);*/
    }
}
