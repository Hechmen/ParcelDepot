import java.util.HashMap;

public class ParcelMap {
    private HashMap<String, Parcel> parcels;

    public ParcelMap() {
        this.parcels = new HashMap<>();
    }

    public void addParcel(Parcel parcel) {
        String ID = parcel.getID();
        parcels.put(ID, parcel);
    }
    public Parcel removeParcel(String ID) {
        return parcels.remove(ID);
    }
    public Parcel findParcel(String ID) {
        return parcels.get(ID);
    }
    public boolean containsParcel(String ID) {
        return parcels.containsKey(ID);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parcels in Depot:\n");
        for (String id : parcels.keySet()) {
            sb.append("Parcel: ").append(parcels.get(id)).append("\n");
        }
        return sb.toString();
    }


}
