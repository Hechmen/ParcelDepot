import java.util.LinkedList;
import java.util.Queue;

public class CustomerQueue {
    private Queue<Customer> queue;

    public CustomerQueue() {
        this.queue = new LinkedList<>();
    }

    public void addCustomer(Customer customer) {
        this.queue.add(customer);
    }
    public Customer removeCustomer() {
        return this.queue.remove();
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
    public int getSize() {
        return this.queue.size();
    }
    public void getQueue() {
        System.out.println("Current queueu: ");
        for (Customer customer : this.queue) {
            System.out.println(customer);
        }
    }
}
