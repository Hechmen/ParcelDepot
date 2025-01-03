import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WorkerTest {
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
