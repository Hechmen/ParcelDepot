import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    @Test
    void testFullSystem() {
        ParcelMap parcelMap = new ParcelMap();
        CustomerQueue customerQueue = new CustomerQueue();
        Manager manager = new Manager(parcelMap, customerQueue);

        manager.initializeData("src/Parcels.csv", "src/Custs.csv");

        // Check initial load


        assertFalse(parcelMap.getAllParcels().isEmpty(), "ParcelMap should not be empty after loading data");
        assertFalse(customerQueue.isEmpty(), "CustomerQueue should not be empty after loading data");

        // Process customers
        manager.startProcessing();
        assertTrue(customerQueue.isEmpty(), "CustomerQueue should be empty after processing all customers");

        // Verify log messages
        String logs = Log.getInstance().getLogs();
        System.out.println("Logs after loading data: " + logs);
        assertTrue(logs.contains("Data loaded successfully."), "Log should indicate data was loaded");
        assertTrue(logs.contains("No customers in the queue."), "Log should indicate no customers in the queue");

        // Test repeated load data
        manager.initializeData("src/Parcels.csv", "src/Custs.csv");
        assertTrue(customerQueue.size() <= parcelMap.getAllParcels().size(), "CustomerQueue should not duplicate entries");

        // Test already collected parcel
        Customer customer = new Customer(1, "Test User", "P001");
        customerQueue.addCustomer(customer);
        manager.startProcessing();
        logs = Log.getInstance().getLogs();
        assertTrue(logs.contains("Parcel with ID P001 not found for customer Test User"), "Log should indicate no more customers to process on repeated attempts.");
    }

}
