public class Customer {
    private int sequenceNumber;
    private String name;
    private String parcelID;

    public Customer(int sequenceNumber, String name, String parcelID) {
        this.sequenceNumber = sequenceNumber;
        this.name = name;
        this.parcelID = parcelID;
    }

    public int getSequenceNumber() {
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
        return "Sequence Number= " + sequenceNumber +
                ", Name= " + name + ", Parcel ID= " + parcelID + "]";

    }
}
