import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ParcelMapTest {
    @Test
    void testAddAndFindParcel() {
        ParcelMap parcelMap = new ParcelMap();
        Parcel parcel = new Parcel("X123", 5.0, 10, 15, 20, 3, Status.UNCOLLECTED);
        parcelMap.addParcel(parcel);

        assertTrue(parcelMap.containsParcel("X123"));
        assertEquals(parcel, parcelMap.findParcel("X123"));
    }

    @Test
    void testSortParcels() {
        ParcelMap parcelMap = new ParcelMap();
        parcelMap.addParcel(new Parcel("X001", 10.0, 5, 5, 5, 2, Status.UNCOLLECTED));
        parcelMap.addParcel(new Parcel("X002", 5.0, 10, 10, 10, 5, Status.UNCOLLECTED));

        List<Parcel> sortedParcels = parcelMap.sortParcels((a, b) -> Integer.compare(a.getDaysInDepot(), b.getDaysInDepot()));
        assertEquals("X001", sortedParcels.get(0).getID());
        assertEquals("X002", sortedParcels.get(1).getID());
    }
}
