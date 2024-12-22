public class LogTester {
    public static void main(String[] args) {
        Log log = Log.getInstance();

        log.addLog("Customer added to the queue");
        log.addLog("Parcel ID C101 processed");
        log.addLog("Error: Parcel ID C100 not found");

        log.displayLog();
        log.writeLogToFile("depot_log.txt");
        log.clearLog();
    }
}
