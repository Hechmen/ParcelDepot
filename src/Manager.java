import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager {
    private ParcelMap parcelMap;
    private CustomerQueue customerQueue;
    private Log log;
    private Worker worker;
    private View view;

    public Manager(ParcelMap parcelMap, CustomerQueue customerQueue) {
        this.parcelMap = parcelMap;
        this.customerQueue = customerQueue;
        this.log = Log.getInstance();
        this.worker = new Worker(customerQueue, parcelMap, log);
        this.view = new View();

        initializeListeners();
    }

    private void initializeListeners() {
        view.addLoadButtonListener(e -> loadData());
        view.addProcessButtonListener(e -> processNextCustomer());
        view.addDisplayLogButtonListener(e -> updateLogArea());
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

    private void processNextCustomer() {
        Customer nextCustomer = customerQueue.getNextCustomer();
        if (nextCustomer == null) {
            log.addLog("No more customers to process.");
            updateLogArea();
            return;
        }

        Parcel parcel = parcelMap.findParcel(nextCustomer.getParcelID());
        if (parcel != null) {
            worker.processNextCustomer();
            view.getCurrentParcelArea().setText(parcel.toString()); // Highlight the current parcel
            updateParcelArea();
            updateCustomerQueueArea();
            updateLogArea();
        } else {
            log.addLog("Parcel not found for customer: " + nextCustomer.getName());
            updateLogArea();
        }
    }

    private void loadData() {
        try {
            String parcelsFile = "src/Parcels.csv";
            String customersFile = "src/Custs.csv";
            initializeData(parcelsFile, customersFile);
            updateParcelArea();
            updateCustomerQueueArea();
            log.addLog("Data loaded successfully.");
            updateLogArea();
        } catch (Exception e) {
            log.addLog("Error loading data: " + e.getMessage());
            updateLogArea();
        }
    }

    private void updateParcelArea() {
        view.getParcelArea().setText(parcelMap.toString());
    }

    private void updateCustomerQueueArea() {
        view.getCustomerQueueArea().setText(customerQueue.toString());
    }

    private void updateLogArea() {
        view.getLogArea().setText(log.getLogs());
    }


    public static void main(String[] args) {
        ParcelMap parcelMap = new ParcelMap();
        CustomerQueue customerQueue = new CustomerQueue();
        new Manager(parcelMap, customerQueue);
    }
}
