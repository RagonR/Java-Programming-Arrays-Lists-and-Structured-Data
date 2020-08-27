
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer logA = new LogAnalyzer();
        logA.readFile("weblog2_log");
        System.out.println("With error code 400: ");
        logA.printAllHigherThanNum(400);
        System.out.println("---------------");
        System.out.println(logA.uniqueIPVisitsOnDay("Sep 24"));
        System.out.println("---------------");
        System.out.println("200,299:");
        System.out.println(logA.countUniqueIPsInRange(200,299));
    }
}
