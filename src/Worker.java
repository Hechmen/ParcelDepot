public class Worker {
    private CustomerQueue customerQueue;
    private ParcelMap parcelMap;
    private Log log;

    public Worker(CustomerQueue customerQueue, ParcelMap parcelMap, Log log) {
        this.customerQueue = customerQueue;
        this.parcelMap = parcelMap;
        this.log = log;
    }

    //This constructor was only used for the test of the getCustomerQueue() and getParcelMap()
//    public Worker(CustomerQueue customerQueue, ParcelMap parcelMap) {
//        this.customerQueue = customerQueue;
//        this.parcelMap = parcelMap;
//    }

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

        double discount = parcel.getID().startsWith("N") ? 2.0 : 0.0;

        return baseFee + weightFee + sizeFee + discount;
    }

    public void processNextCustomer() {
        Customer nextCustomer = customerQueue.getNextCustomer();
        if (nextCustomer != null) {
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
}
