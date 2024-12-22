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
                float weight = Float.parseFloat(data[1].trim());
                int width = Integer.parseInt(data[2].trim());
                int length = Integer.parseInt(data[3].trim());
                int height = Integer.parseInt(data[4].trim());
                int daysInDepot = Integer.parseInt(data[5].trim());
                Parcel parcel = new Parcel(id, daysInDepot, weight, width, length, height, Status.UNCOLLECTED);
                parcelMap.addParcel(parcel);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading parcels file: " + e.getMessage());
        }
    }

    private void readCustomersFromFile(String customersFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(customersFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length != 3) continue;

                int seqNo = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String parcelID = data[2].trim();
                Customer customer = new Customer(seqNo, name, parcelID);
                customerQueue.addCustomer(customer);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading customers file: " + e.getMessage());
        }
    }

    public void startProcessing() {
        Worker worker = new Worker(customerQueue, parcelMap);
        worker.processCustomers();
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        String parcelsFile = "Parcels.csv";
        String customersFile = "Custs.csv";
        manager.initializeData(parcelsFile, customersFile);
        manager.startProcessing();
    }
}
