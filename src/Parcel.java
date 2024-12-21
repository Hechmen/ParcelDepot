public class Parcel {
    private String ID;
    private float weight;
    private int width;
    private int length;
    private int height;
    private int daysInDepot;
    private Status status;

    public Parcel(String ID, float weight, int width, int length, int height, int daysInDepot, Status status) {
        this.ID = ID;
        this.weight = weight;
        this.width = width;
        this.length = length;
        this.height = height;
        this.daysInDepot = daysInDepot;
        this.status = status;
    }

    public String getID() {
        return ID;
    }
    public float getWeight() {
        return weight;
    }
    public int getWidth() {
        return width;
    }
    public int getLength() {
        return length;
    }
    public int getHeight() {
        return height;
    }
    public int getDaysInDepot() {
        return daysInDepot;
    }
    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Parcel [ID=" + ID + ", weight=" + weight + ", dimensions=" + width + " x " + length + " x " + height +
                ", daysInDepot=" + daysInDepot + ", status=" + status + "]";
    }

}
