import java.util.*;
import edu.duke.*;
public class TestVinegereBreaker {
    
    public void TestVinegerBreakerSlicer () {
        VigenereBreaker VSlicer = new VigenereBreaker();
        System.out.println(VSlicer.sliceString("abcdefghijklm", 0, 3));
        System.out.println(VSlicer.sliceString("abcdefghijklm", 1, 3));
        System.out.println(VSlicer.sliceString("abcdefghijklm", 2, 3));
        System.out.println(VSlicer.sliceString("abcdefghijklm", 0, 4));
        System.out.println(VSlicer.sliceString("abcdefghijklm", 1, 4));
        System.out.println(VSlicer.sliceString("abcdefghijklm", 2, 4));
        System.out.println(VSlicer.sliceString("abcdefghijklm", 3, 4));
        System.out.println(VSlicer.sliceString("abcdefghijklm", 0, 5));
        System.out.println(VSlicer.sliceString("abcdefghijklm", 1, 5));
        System.out.println(VSlicer.sliceString("abcdefghijklm", 2, 5));
        System.out.println(VSlicer.sliceString("abcdefghijklm", 3, 5));
        System.out.println(VSlicer.sliceString("abcdefghijklm", 4, 5));
    }
    public void TestVinegerBreakerTryKeyLength () {
        FileResource File = new FileResource();
        String message = File.asString();
        VigenereBreaker VKeyLeangth = new VigenereBreaker();
        String keyWord = "flute";
        int[] key = new int[keyWord.length()];
        key = VKeyLeangth.tryKeyLength(message, keyWord.length(), 'e');
        System.out.println(Arrays.toString(key));
    }
}
