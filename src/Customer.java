public class Customer {
    private String sequenceNumber;
    private String name;
    private String parcelID;

    public Customer(String sequenceNumber, String name, String parcelID) {
        this.sequenceNumber = sequenceNumber;
        this.name = name;
        this.parcelID = parcelID;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }
    public String getName() {
        return name;
    }
    public String getParcelID() {
        return parcelID;
    }

    @Override
    public String toString() {
        return "Customer [sequenceNumber=" + sequenceNumber +
                ", name=" + name + ", parcelID=" + parcelID + "]";

    }
}
