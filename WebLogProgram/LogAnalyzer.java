import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;
    
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();         
    }
    
    public void readFile(String filename) {
        FileResource log = new FileResource(filename);
        WebLogParser WebParser = new WebLogParser();
        for (String s : log.lines()) {
            records.add(WebParser.parseEntry(s));
        }
    }
    
    public void printAllHigherThanNum(int num) {
        for (LogEntry le: records) {
            int status = le.getStatusCode();
            if (status > num) {
                System.out.println(le);
            }
        }
    }
    
    public int uniqueIPVisitsOnDay (String someday){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        System.out.println("Unique IP Addresses " + someday + ":");
        for(LogEntry le : records){
            String logDate = le.getAccessTime()
                                .toString()
                                .substring(4, 11)
                                .replaceAll(" ","");
            someday = someday.replaceAll(" ","");
            if(logDate.compareTo(someday) == 0){
                if(!uniqueIPs.contains(le.getIpAddress())){
                    uniqueIPs.add(le.getIpAddress());
                    System.out.println(le.getIpAddress());
                }
            }
        }
        return uniqueIPs.size();
    }
    
    public int countUniqueIPsInRange (int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry le : records){
            if(le.getStatusCode()>=low && le.getStatusCode()<=high){
                if(!uniqueIPs.contains(le.getIpAddress())){
                    uniqueIPs.add(le.getIpAddress());
                }     
            }
        }
        return uniqueIPs.size();
    }
    
    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(LogEntry le : records){
            if(!uniqueIPs.contains(le.getIpAddress())){
                uniqueIPs.add(le.getIpAddress());
            }
        }
        return uniqueIPs.size();
    }
    
    public HashMap<String,Integer> countVisitsByIP () {
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for (LogEntry le : records){
            String ip = le.getIpAddress();
            if(!counts.containsKey(ip)){
                counts.put(ip,1);
            }
            else{
                counts.put(ip,counts.get(ip)+1);
            }
        }
        return counts;
    }
    
    public int mostNumberVisitsByIP ( HashMap<String,Integer> counts) {
        int mostVisits = 0;
        for (Map.Entry me : counts.entrySet()){
            int ipVisits = (Integer) me.getValue();
            if(ipVisits > mostVisits){
                mostVisits = ipVisits;
            }
        }
        return mostVisits;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts) {
        ArrayList<String> iPsWithMostVisits = new ArrayList<String>();
        int mostVisistedFromOneIp = mostNumberVisitsByIP(counts);
        for (Map.Entry me : counts.entrySet()){
            int ipVisits = (Integer) me.getValue();
            if(ipVisits == mostVisistedFromOneIp){
                String ip = (String) me.getKey();
                iPsWithMostVisits.add(ip);
            }
        }
        return iPsWithMostVisits;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> iPsFromDays = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records){
            String logDate = le.getAccessTime()
                                .toString()
                                .substring(4, 11)
                                .replaceAll(" ","");
            if(!iPsFromDays.containsValue(logDate)){
                ArrayList<String> IpAddresses = new ArrayList<String>();
                for (LogEntry ips : records){
                    if(ips.getAccessTime()
                          .toString()
                          .substring(4, 11)
                          .replaceAll(" ","")
                          .equals(le.getAccessTime()
                                    .toString()
                                    .substring(4, 11)
                                    .replaceAll(" ",""))){
                        IpAddresses.add(ips.getIpAddress());
                    }
                }
                String DateOfIp = le.getAccessTime()
                                    .toString()
                                    .substring(4, 11);
                iPsFromDays.put(DateOfIp, IpAddresses);
            }
        }
        return iPsFromDays;
    }
    
    public String dayWithMostIPVisits (HashMap<String, ArrayList<String>> iPsFromDays){
        String DateWithMostConnections = new String();
        int MostVisists = 0;
        for (Map.Entry<String,ArrayList<String>> ee : iPsFromDays.entrySet()) {
            String key = ee.getKey();
            if(iPsFromDays.get(key).size() > MostVisists){
                 MostVisists = iPsFromDays.get(key).size();
                 DateWithMostConnections = key;
            }
        }
        System.out.println("Most visists in a day: " + MostVisists);
        return DateWithMostConnections;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay (HashMap<String, ArrayList<String>> iPsFromDays, String lookupDate){
        ArrayList<String> ipsInGivenDate = new ArrayList<String>();
        ArrayList<String> MostVisitedIps = new ArrayList<String>();
        for (Map.Entry<String,ArrayList<String>> ee : iPsFromDays.entrySet()) {
            if(ee.getKey().replaceAll(" ","").equals(lookupDate.replaceAll(" ",""))){
                ipsInGivenDate = ee.getValue();
                break;
            }
        }
        int mostTimesOneIpAddressConnected = 0;
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for(int i = 0; i < ipsInGivenDate.size(); i++){
            if(!uniqueIPs.contains(ipsInGivenDate.get(i))){
                uniqueIPs.add(ipsInGivenDate.get(i));
            }
        }
        for(int i = 0; i < uniqueIPs.size(); i++){
            int occurrences = Collections.frequency(ipsInGivenDate, uniqueIPs.get(i));
            if(occurrences > mostTimesOneIpAddressConnected){
                mostTimesOneIpAddressConnected = occurrences;
            }
        }
        for(int i = 0; i < uniqueIPs.size(); i++){
            int occurrences = Collections.frequency(ipsInGivenDate, uniqueIPs.get(i));
            if(occurrences == mostTimesOneIpAddressConnected){
                MostVisitedIps.add(uniqueIPs.get(i));
            }
        }
        return MostVisitedIps;
    }
    
    public void printAll() {
     for (LogEntry le : records) {
         System.out.println(le);
     }
    }
}
