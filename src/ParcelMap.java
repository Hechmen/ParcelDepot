import java.util.Collection;
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

    public Collection<Parcel> getAllParcels() {
        return parcels.values();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parcels in Depot:\n");
        for (Parcel parcel : parcels.values()) {
            sb.append(parcel).append("\n");
        }
        if (parcels.isEmpty()) {
            sb.append("No parcels in the depot.");
        }
        return sb.toString();
    }

}
