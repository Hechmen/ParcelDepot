//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CustomerQueue CustomerQueue = new CustomerQueue();
        // Adding customers
        CustomerQueue.addCustomer(new Customer(1, "John Brown", "C101"));
        CustomerQueue.addCustomer(new Customer(2, "Mary Smith", "C200"));
        CustomerQueue.addCustomer(new Customer(3, "Sue Jones", "X59"));

        // Peek at the first customer
        System.out.println("\nNext customer: " + CustomerQueue.peekNextCustomer());
        System.out.println("Queue size after peek: " + CustomerQueue.getSize());

        // Process the first customer
        System.out.println("\nProcessing: " + CustomerQueue.getNextCustomer());
        System.out.println("Queue size after processing: " + CustomerQueue.getSize());

        // Peek again to check the next customer
        System.out.println("\nNext customer: " + CustomerQueue.peekNextCustomer());

        // Process the next customer
        System.out.println("\nProcessing: " + CustomerQueue.getNextCustomer());
        System.out.println("Queue size after second processing: " + CustomerQueue.getSize());
    }
}