//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ParcelMap parcelMap = new ParcelMap();
        Parcel parcel1 = new Parcel("C101", 10.5f, 5, 10, 15, 2, Status.UNCOLLECTED);
        Parcel parcel2 = new Parcel("C102", 8.0f, 6, 12, 18, 3, Status.UNCOLLECTED);

        // Adding parcels
        parcelMap.addParcel(parcel1);
        parcelMap.addParcel(parcel2);

//        System.out.println(parcelMap.toString());

        // Finding a parcel
        Parcel foundParcel = parcelMap.findParcel("C101");
//        System.out.println(foundParcel); // Calls Parcel's toString() method

        // Removing a parcel
        parcelMap.removeParcel("C101");

        // Checking if a parcel exists
        boolean exists = parcelMap.containsParcel("C102");
//        System.out.println("Parcel C102 exists: " + exists);
        CustomerQueue customerQueue = new CustomerQueue();
        Customer customer1 = new Customer(1, "John", "C101");
        Customer customer2 = new Customer(2, "Jane", "C102");
        customerQueue.addCustomer(customer1);
        customerQueue.addCustomer(customer2);
        Worker worker = new Worker(customerQueue, parcelMap);
        System.out.println(worker.getCustomerQueue());
        System.out.println(worker.getParcelMap());


    }
}