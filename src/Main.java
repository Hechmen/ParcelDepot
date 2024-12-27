//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Step 1: Create mock data for parcels
        ParcelMap parcelMap = new ParcelMap();
        parcelMap.addParcel(new Parcel("P123", 5, 2, 10, 15, 20, Status.UNCOLLECTED));
        parcelMap.addParcel(new Parcel("P124", 3, 1, 5, 8, 12, Status.UNCOLLECTED));
        parcelMap.addParcel(new Parcel("N125", 7, 3, 20, 25, 30, Status.UNCOLLECTED));

        // Step 2: Create mock data for customers
        CustomerQueue customerQueue = new CustomerQueue();
        customerQueue.addCustomer(new Customer(1, "Alice", "P123"));
        customerQueue.addCustomer(new Customer(2, "Bob", "P124"));
        customerQueue.addCustomer(new Customer(3, "Charlie", "N125"));

        // Step 3: Initialize Log
        Log log = Log.getInstance();

        // Step 4: Create Worker and process customers
        Worker worker = new Worker(customerQueue, parcelMap, log);
        while (!customerQueue.isEmpty()) {
            worker.processNextCustomer();
        }

        // Step 5: Output logs
        System.out.println("Processing Log:");
        log.displayLog();

        // Step 6: Check parcel statuses
        System.out.println("\nUpdated Parcel Statuses:");
        for (Parcel parcel : parcelMap.getAllParcels()) {
            System.out.println(parcel);
        }

        // Step 7: Write logs to a file
        String logFile = "ProcessingLog.txt";
        log.writeLogToFile(logFile);
        System.out.println("\nLog has been written to: " + logFile);
    }
}