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

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
    public int getSize() {
        return this.queue.size();
    }

    public Customer peekNextCustomer() {
        return this.queue.peek();
    }
    public Customer getNextCustomer() {
        return this.queue.poll(); //Start to process next customer in the queue
    }
    public void getQueue() {
        System.out.println("Current queue: ");
        for (Customer customer : this.queue) {
            System.out.println(customer);
        }
    }
}
