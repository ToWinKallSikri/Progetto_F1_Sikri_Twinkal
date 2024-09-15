package didattica.cs.unicam.vectorally.test.Model;

import didattica.cs.unicam.vectorally.Model.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test della classe Coordinate.
 * Verifica il corretto funzionamento dei metodi per le operazioni geometriche tra coordinate.
 */
class CoordinateTest {

    private Coordinate coord1;
    private Coordinate coord2;

    @BeforeEach
    void setUp() {
        coord1 = new Coordinate(3.0, 4.0);
        coord2 = new Coordinate(6.0, 8.0);
    }

    @Test
    void testRevertSign() {
        Coordinate reverted = coord1.revertSign();
        assertEquals(-3.0, reverted.getX());
        assertEquals(-4.0, reverted.getY());
    }

    @Test
    void testSum() {
        Coordinate sum = coord1.sum(coord2);
        assertEquals(9.0, sum.getX());
        assertEquals(12.0, sum.getY());
    }

    @Test
    void testGetDistance() {
        double distance = coord1.getDistance(coord2);
        assertEquals(5.0, distance, 0.001);
    }

    @Test
    void testGetVector() {
        Coordinate vector = coord1.getVector(coord2);
        assertEquals(3.0, vector.getX());
        assertEquals(4.0, vector.getY());
    }

    @Test
    void testCastToInteger() {
        Coordinate intCoord = new Coordinate(3.7, 4.3).castToInteger();
        assertEquals(3, intCoord.getX());
        assertEquals(4, intCoord.getY());
    }

    @Test
    void testGetIntegerRoutePoints() {
        List<Coordinate> points = coord1.getIntegerRoutePoints(coord2);
        assertFalse(points.isEmpty());
        assertEquals(4, points.size());
        assertEquals(new Coordinate(1, 1), points.get(0));
    }

    @Test
    void testEquals() {
        Coordinate sameCoord = new Coordinate(3.0, 4.0);
        Coordinate differentCoord = new Coordinate(1.0, 2.0);
        assertEquals(coord1, sameCoord);
        assertNotEquals(coord1, differentCoord);
    }
}

