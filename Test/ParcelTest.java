import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParcelTest {
    @Test
    void testParcelInitialization() {
        Parcel parcel = new Parcel("X123", 5.0, 10, 15, 20, 3, Status.UNCOLLECTED);
        assertEquals("X123", parcel.getID());
        assertEquals(5.0, parcel.getWeight());
        assertEquals(10, parcel.getWidth());
        assertEquals(Status.UNCOLLECTED, parcel.getStatus());
    }

    @Test
    void testParcelStatusUpdate() {
        Parcel parcel = new Parcel("X123", 5.0, 10, 15, 20, 3, Status.UNCOLLECTED);
        parcel.setStatus(Status.COLLECTED);
        assertEquals(Status.COLLECTED, parcel.getStatus());
    }
}