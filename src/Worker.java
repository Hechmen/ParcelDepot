public class Worker {
    private CustomerQueue customerQueue;
    private ParcelMap parcelMap;
    private Log log;

    public Worker(CustomerQueue customerQueue, ParcelMap parcelMap, Log log) {
        this.customerQueue = customerQueue;
        this.parcelMap = parcelMap;
        this.log = log;
    }

    public CustomerQueue getCustomerQueue(){
        return this.customerQueue;
    }

    public ParcelMap getParcelMap(){
        return this.parcelMap;
    }

    public double calculateFee(Parcel parcel) {
        double baseFee = 5;
        double weightFee = parcel.getWeight() * 0.2;
        double sizeFee = (parcel.getWidth() * parcel.getHeight() * parcel.getLength()) / 2;
        double daysFee = parcel.getDaysInDepot() * 0.2;

        double discount;
        if (parcel.getID().startsWith("N")) discount = 5.0;
        else discount = 0.0;

        return baseFee + weightFee + sizeFee + daysFee - discount;
    }

    public void processNextCustomer() {
        Customer nextCustomer = customerQueue.getNextCustomer();
        if (nextCustomer == null) {
            log.addLog("No customer in the queue.");
            return;
        }

        String parcelID = nextCustomer.getParcelID();
        Parcel parcel = parcelMap.findParcel(parcelID);
        if (parcel == null) {
            log.addLog("Parcel with ID " + parcelID + " not found for customer " + nextCustomer.getName());
            return;
        }

        double fee = calculateFee(parcel);
        parcel.setStatus(Status.COLLECTED);
        log.addLog("Customer " + nextCustomer.getName() + " collected parcel " + parcelID + " with fee " + fee);
    }

    // New method to process all customers in the queue
    public void startProcessing() {
        while (!customerQueue.isEmpty()) {
            processNextCustomer();
        }
    }

}
