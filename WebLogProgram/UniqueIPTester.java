import java.util.*;
import edu.duke.*;

public class UniqueIPTester {
    public UniqueIPTester() {}
    
    public void TestUniqueIPTester() {
        LogAnalyzer logB = new LogAnalyzer();
        logB.readFile("weblog2_log");
        System.out.println("Unique IP addresses: " + logB.countUniqueIPs());
    }
}
