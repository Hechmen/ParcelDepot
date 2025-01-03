import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WorkerTest {
    private Worker worker;
    private ParcelMap parcelMap;
    private CustomerQueue customerQueue;
    private Log log;

    @BeforeEach
    void setUp() {
        parcelMap = new ParcelMap();
        parcelMap.addParcel(new Parcel("P001", 5.0, 10, 10, 10, 3, Status.UNCOLLECTED));
        parcelMap.addParcel(new Parcel("P002", 10.0, 15, 15, 15, 7, Status.COLLECTED));
        parcelMap.addParcel(new Parcel("P003", 8.0, 12, 12, 12, 10, Status.UNCOLLECTED));

        customerQueue = new CustomerQueue();
        log = Log.getInstance();
        log.clearLog(); // Reset log before each test

        worker = new Worker(customerQueue, parcelMap, log);
    }

    @Test
    void testGenerateStatistics() {
        Parcel collectedParcel = parcelMap.findParcel("P002");
        collectedParcel.setFee(50.0); // Simulate fee calculation for the collected parcel

        String stats = worker.generateStatistics();
        assertTrue(stats.contains("Uncollected parcels: 2"), "Statistics should include 2 uncollected parcels.");
        assertTrue(stats.contains("Parcels in depot for more than 5 days: 2"), "Statistics should include 2 parcels in depot for more than 5 days.");
        assertTrue(stats.contains("Total fees collected: $50.0"), "Statistics should include total fees collected as $50.0.");
    }

    @Test
    void testProcessNextCustomer() {
        ParcelMap parcelMap = new ParcelMap();
        parcelMap.addParcel(new Parcel("X123", 5.0, 10, 15, 20, 3, Status.UNCOLLECTED));

        CustomerQueue queue = new CustomerQueue();
        queue.addCustomer(new Customer(1, "John Doe", "X123"));

        Log log = Log.getInstance();
        Worker worker = new Worker(queue, parcelMap, log);
        worker.processNextCustomer();

        assertTrue(queue.isEmpty());
        assertEquals(Status.COLLECTED, parcelMap.findParcel("X123").getStatus());
        assertTrue(log.getLogs().contains("John Doe collected parcel"));
    }
}
