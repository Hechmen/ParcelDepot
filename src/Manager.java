import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Manager {
    private ParcelMap parcelMap;
    private CustomerQueue customerQueue;

    public Manager(ParcelMap parcelMap, CustomerQueue customerQueue) {
        this.parcelMap = new ParcelMap();
        this.customerQueue = new CustomerQueue();
    }

    public void initializeData(String parcelsFile, String customersFile) {
        readParcelsFromFile(parcelsFile);
        readCustomersFromFile(customersFile);
    }

    private void readParcelsFromFile(String parcelsFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(parcelsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length != 6) continue;

                String id = data[0].trim();
                double weight = Double.parseDouble(data[1].trim());
                int width = Integer.parseInt(data[2].trim());
                int length = Integer.parseInt(data[3].trim());
                int height = Integer.parseInt(data[4].trim());
                int daysInDepot = Integer.parseInt(data[5].trim());
                Parcel parcel = new Parcel(id, weight, width, length, height, daysInDepot, Status.UNCOLLECTED);
                parcelMap.addParcel(parcel);
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
                String[] data = line.split(",");
                if (data.length != 2) continue;

                String name = data[0].trim();
                String parcelID = data[1].trim();
                Customer customer = new Customer(seqNo, name, parcelID);
                customerQueue.addCustomer(customer);
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
        ParcelMap parcelMap = new ParcelMap();
        CustomerQueue customerQueue = new CustomerQueue();
        Manager manager = new Manager(parcelMap, customerQueue);
        String parcelsFile = "src/Parcels.csv";
        String customersFile = "src/Custs.csv";
        manager.initializeData(parcelsFile, customersFile);
        manager.startProcessing();
    }
}
