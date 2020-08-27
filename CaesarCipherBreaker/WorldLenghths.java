import edu.duke.*;

public class WorldLenghths {
    public void countWordLengths (FileResource resource, int counts[]) {
	String Text = resource.asString();
	int wordlength = 0;
	for(int i = 0; i < Text.length(); i++) {
            char currChar = Text.charAt(i);
            if(Character.isLetter(currChar)){
                wordlength++;
            }
            else if(currChar == '\''){
                wordlength++;
            }else{
                if(wordlength != 0)
                    counts[wordlength]++;
                wordlength = 0;
            }
        }
    }
    
    public int indexOfMax (int values[]){
        int indexOfMax = -1;
        for(int i=0; i<values.length; i++){
            if(values[i]!=0 && i!=0){
                if(values[i]>indexOfMax){
                    indexOfMax = values[i];
                }
            }
        }
        return indexOfMax;
    }
    
    public void testCountWordLengths (){
        FileResource TxtFile = new FileResource();
        int[] counts = new int[45];
        countWordLengths (TxtFile, counts);
        for(int i=0; i<counts.length; i++){
            if(counts[i]!=0 && i!=0){
                System.out.println(counts[i] + " words of length: " + i);
            }
        }
        System.out.println("Biggest index " + indexOfMax(counts));
    }
}