import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerQueueTest {
    @Test
    void testAddAndGetCustomer() {
        CustomerQueue queue = new CustomerQueue();
        Customer customer = new Customer(1, "John Doe", "X123");
        queue.addCustomer(customer);

        assertFalse(queue.isEmpty());
        assertEquals(customer, queue.getNextCustomer());
        assertTrue(queue.isEmpty());
    }
}
