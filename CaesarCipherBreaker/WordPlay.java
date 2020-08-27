public class WordPlay {
    public boolean isVowel (char ch){
        ch = Character.toLowerCase(ch);
        if(!Character.isDigit(ch)){
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'i' || ch == 'o' || ch == 'u'){
                return true;
            }
            else{
                return false;
            }  
        }
        else{
            return false;
        }    
    }
    
    public String replaceVowels (String phrase, char ch){
        StringBuilder phraseEditor = new StringBuilder(phrase);
        for(int i = 0; i<phrase.length(); i++){
            char currChar = phraseEditor.charAt(i);
            if(isVowel(currChar) == true){
                phraseEditor.replace(i, i+1, Character.toString(ch));
            }
        }
        return phraseEditor.toString();
    }
    
    public String emphasize (String phrase, char ch){
        StringBuilder phraseEditor = new StringBuilder(phrase);
        for(int i = 0; i<phrase.length(); i++){
            char currChar = phraseEditor.charAt(i);
            if(Character.toLowerCase(currChar) == Character.toLowerCase(ch)){
                if(i%2 == 0){
                    phraseEditor.replace(i, i+1, "*");
                }
                else{
                    phraseEditor.replace(i, i+1, "+");
                }
            }
        }
        return phraseEditor.toString();
    }
    
        public void TestisEmphasize() {
        String Test1 = emphasize("dna ctgaaactga", 'a') ;
        System.out.println(Test1);
        String Test2 = emphasize("Mary Bella Abracadabra", 'a') ;
        System.out.println(Test2);
    }    
    
    public void TestisReplaceVowels() {
        String NewPhrase = replaceVowels("Hello World", '*');
        System.out.println(NewPhrase);
    }    
    
    public void TestisVowel() {
        char newChar = 'a';
        System.out.println(isVowel(newChar));
    
    }    
}
