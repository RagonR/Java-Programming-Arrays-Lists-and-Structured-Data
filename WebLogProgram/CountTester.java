import java.util.*;
import edu.duke.*;

public class CountTester {
    public void testCounts(){
        LogAnalyzer logB = new LogAnalyzer();
        logB.readFile("weblog2_log");
        //logB.readFile("short-test_log");
        HashMap<String,Integer> counts = logB.countVisitsByIP();
        System.out.println(counts);
        int mostVisistedFromOneIp = logB.mostNumberVisitsByIP(counts);
        System.out.println("Most visists from one IP: " + mostVisistedFromOneIp);
        ArrayList<String> iPsWithMostVisits = logB.iPsMostVisits(counts);
        System.out.println(iPsWithMostVisits);
        HashMap<String, ArrayList<String>> IpsFromDates = logB.iPsForDays();
        System.out.println(IpsFromDates);
        String DateWithMostConnections = logB.dayWithMostIPVisits(IpsFromDates);
        System.out.println(DateWithMostConnections);
        ArrayList<String> ipsInGivenDate = logB.iPsWithMostVisitsOnDay(IpsFromDates, "Sep 30");
        System.out.println("IP addresses that had the most accesses on the given day: " + ipsInGivenDate);
    }
}
