import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ParcelMapTest {
    private ParcelMap parcelMap;

    @BeforeEach
    void setUp() {
        parcelMap = new ParcelMap();
        parcelMap.addParcel(new Parcel("P001", 5.0, 10, 10, 10, 3, Status.UNCOLLECTED));
        parcelMap.addParcel(new Parcel("P002", 10.0, 15, 15, 15, 7, Status.COLLECTED));
        parcelMap.addParcel(new Parcel("P003", 8.0, 12, 12, 12, 10, Status.UNCOLLECTED));
    }

    @Test
    void testCountUncollectedParcels() {
        assertEquals(2, parcelMap.countUncollectedParcels(), "Should count 2 uncollected parcels.");
    }

    @Test
    void testCountParcelsInDepotForDays() {
        assertEquals(2, parcelMap.countParcelsInDepotForDays(5), "Should count 2 parcels in depot for more than 5 days.");
        assertEquals(1, parcelMap.countParcelsInDepotForDays(9), "Should count 1 parcel in depot for more than 9 days.");
    }

    @Test
    void testCalculateTotalFeesCollected() {
        Parcel collectedParcel = parcelMap.findParcel("P002");
        collectedParcel.setFee(50.0); // Simulate fee calculation for the collected parcel
        assertEquals(50.0, parcelMap.calculateTotalFeesCollected(), "Total fees collected should be 50.0.");
    }

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
