import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Manager {
    private ParcelMap parcelMap;
    private CustomerQueue customerQueue;

    public Manager(ParcelMap parcelMap, CustomerQueue customerQueue) {
        this.parcelMap = parcelMap;
        this.customerQueue = customerQueue;
    }

    public void initializeData(String parcelsFile, String customersFile) {
        readParcelsFromFile(parcelsFile);
        readCustomersFromFile(customersFile);
    }

    private void readParcelsFromFile(String parcelsFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(parcelsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Reading line: " + line); // Debugging line
                String[] data = line.split(",");
                if (data.length != 6) {
                    System.err.println("Invalid line format: " + line); // Debugging invalid lines
                    continue;
                };

                String id = data[0].trim();
                double weight = Double.parseDouble(data[1].trim());
                int width = Integer.parseInt(data[2].trim());
                int length = Integer.parseInt(data[3].trim());
                int height = Integer.parseInt(data[4].trim());
                int daysInDepot = Integer.parseInt(data[5].trim());
                Parcel parcel = new Parcel(id, weight, width, length, height, daysInDepot, Status.UNCOLLECTED);
                parcelMap.addParcel(parcel);
                System.out.println("Added parcel: " + parcel); // Debugging successful additions
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading parcels file: " + e.getMessage());
        }
    }

    private void readCustomersFromFile(String customersFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(customersFile))) {
            String line;
            int seqNo = 1;
            while ((line = br.readLine()) != null) {
                System.out.println("Reading line: " + line); // Debugging line
                String[] data = line.split(",");
                if (data.length != 2) {
                    System.err.println("Invalid line format: " + line); // Debugging invalid lines
                    continue;
                };

                String name = data[0].trim();
                String parcelID = data[1].trim();
                Customer customer = new Customer(seqNo, name, parcelID);
                customerQueue.addCustomer(customer);
                System.out.println("Added customer: " + customer); // Debugging successful additions
                seqNo++;
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading customers file: " + e.getMessage());
        }
    }

    public void startProcessing() {
        Log log = Log.getInstance();
        Worker worker = new Worker(customerQueue, parcelMap, log);
        while (!customerQueue.isEmpty()) {
            worker.processNextCustomer();
        }
    }

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
        worker.startProcessing(); // Ensure startProcessing exists and functions correctly

        // Write logs to file and display in console
        String logFile = "src/logs.txt"; // File to save logs
        log.writeLogToFile(logFile);
        System.out.println("Logs written to file: " + logFile);

        System.out.println("Logs:");
        log.displayLog();
    }
}
