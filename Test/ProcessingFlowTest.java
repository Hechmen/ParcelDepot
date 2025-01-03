import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProcessingFlowTest {
    @Test
    void testFullProcessing() {
        ParcelMap parcelMap = new ParcelMap();
        parcelMap.addParcel(new Parcel("X001", 10.0, 10, 10, 10, 5, Status.UNCOLLECTED));

        CustomerQueue customerQueue = new CustomerQueue();
        customerQueue.addCustomer(new Customer(1, "John Doe", "X001"));

        Log log = Log.getInstance();
        log.clearLog();
        Worker worker = new Worker(customerQueue, parcelMap, log);

        worker.processNextCustomer();

        assertTrue(customerQueue.isEmpty(), "The customer queue should be empty after processing");
        assertEquals(Status.COLLECTED, parcelMap.findParcel("X001").getStatus(), "The parcel status should be COLLECTED");
        assertTrue(log.getLogs().contains("John Doe collected parcel"), "The log should reflect the processing");
    }
}
