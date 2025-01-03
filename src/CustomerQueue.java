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

    public boolean containsCustomer(String parcelID) {
        return queue.stream().anyMatch(customer -> customer.getParcelID().equals(parcelID));
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer Queue:\n");
        for (Customer customer : queue) { // Iterate over all customers in the queue
            sb.append(customer).append("\n");
        }
        if (queue.isEmpty()) {
            sb.append("No customers in the queue.");
        }
        return sb.toString();
    }

    public int size() {
        return this.queue.size();
    }
}
