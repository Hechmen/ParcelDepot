public class Main {
    public static void main(String[] args) {
        // Paths to files (update as necessary)
        String parcelsFile = "src/Parcels.csv";
        String customersFile = "src/Custs.csv";

        // Initialize ParcelMap and CustomerQueue
        ParcelMap parcelMap = new ParcelMap();
        CustomerQueue customerQueue = new CustomerQueue();

        // Initialize Manager
        Manager manager = new Manager(parcelMap, customerQueue);
        manager.initializeData(parcelsFile, customersFile);

        // Display loaded parcels and customers for verification
        System.out.println("Parcels loaded:");
        System.out.println(parcelMap);

        System.out.println("Customers in queue:");
        System.out.println(customerQueue);

        // Initialize Worker and process customers
        Log log = Log.getInstance();
        Worker worker = new Worker(customerQueue, parcelMap, log);
        System.out.println("Processing customers...");
        worker.startProcessing();

        // Write logs to file and display in console
        String logFile = "src/logs.txt"; // File to save logs
        log.writeLogToFile(logFile);
        System.out.println("Logs written to file: " + logFile);

        System.out.println("Logs:");
        log.displayLog();
    }
}
