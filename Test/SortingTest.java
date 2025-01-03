import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SortingTest {
    @Test
    void testSortingByDaysInDepot() {
        ParcelMap parcelMap = new ParcelMap();
        parcelMap.addParcel(new Parcel("P001", 10.0, 5, 5, 5, 3, Status.UNCOLLECTED));
        parcelMap.addParcel(new Parcel("P002", 5.0, 10, 10, 10, 1, Status.UNCOLLECTED));

        List<Parcel> sortedParcels = parcelMap.sortParcels((a, b) -> Integer.compare(a.getDaysInDepot(), b.getDaysInDepot()));

        assertEquals("P002", sortedParcels.get(0).getID(), "Parcel with fewer days should come first");
        assertEquals("P001", sortedParcels.get(1).getID(), "Parcel with more days should come second");
    }
}
