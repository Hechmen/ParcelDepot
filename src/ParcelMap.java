import java.util.*;
import java.util.stream.Collectors;

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

    public List<Parcel> getUncollectedParcels() {
        return parcels.values().stream()
                .filter(parcel -> parcel.getStatus() == Status.UNCOLLECTED)
                .collect(Collectors.toList());
    }

    public int countUncollectedParcels() {
        return (int) parcels.values().stream()
                .filter(parcel -> parcel.getStatus() == Status.UNCOLLECTED)
                .count();
    }

    public int countParcelsInDepotForDays(int days) {
        return (int) parcels.values().stream()
                .filter(parcel -> parcel.getDaysInDepot() > days)
                .count();
    }

    public double calculateTotalFeesCollected() {
        return parcels.values().stream()
                .filter(parcel -> parcel.getStatus() == Status.COLLECTED)
                .mapToDouble(Parcel::getFee) // Add a `fee` attribute to Parcel if not already present
                .sum();
    }

    /**
     * Sort parcels by a given comparator.
     *
     * @param comparator Comparator to determine the sorting order.
     * @return List of parcels sorted by the given comparator.
     */
    public List<Parcel> sortParcels(Comparator<Parcel> comparator) {
        return parcels.values().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
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