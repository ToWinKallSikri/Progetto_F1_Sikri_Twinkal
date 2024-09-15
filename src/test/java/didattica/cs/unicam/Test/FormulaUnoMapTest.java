package didattica.cs.unicam.Test;

import didattica.cs.unicam.vectorally.Model.Coordinate;
import didattica.cs.unicam.vectorally.Model.FormulaUnoMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test della classe FormulaUnoMap.
 * Verifica la corretta creazione della mappa, il posizionamento nelle sezioni e il comportamento della linea di arrivo.
 */
class FormulaUnoMapTest {

    private FormulaUnoMap map;
    private Coordinate[] coordinates;

    @BeforeEach
    void setUp() {
        coordinates = new Coordinate[]{
                new Coordinate(0, 0),
                new Coordinate(10, 0),
                new Coordinate(20, 0),
                new Coordinate(30, 0)
        };
        map = new FormulaUnoMap(5.0, coordinates);
    }

    @Test
    void testMapCreation() {
        assertNotNull(map);
        assertEquals(3, map.getSections().size(), "Numero di sezioni non corretto");
    }

    @Test
    void testIsIntoMap() {
        assertTrue(map.isIntoMap(new Coordinate(4, 0), 0) == 0);
        assertFalse(map.isIntoMap(new Coordinate(15, 10), 1) != -1);
        assertTrue(map.isIntoMap(new Coordinate(24, 0), 2) == 2);
    }

    @Test
    void testGetSection() {
        assertNotNull(map.getSection(0));
        assertThrows(IllegalArgumentException.class, () -> map.getSection(-1));
        assertThrows(IllegalArgumentException.class, () -> map.getSection(3)); // Indice fuori dai limiti
    }

    @Test
    void testIsIntoFinalSection() {
        Coordinate pointOnFinishLine = new Coordinate(30, 0);
        assertTrue(map.isIntoFinalSection(pointOnFinishLine));

        Coordinate pointOffFinishLine = new Coordinate(46, 0);
        assertFalse(map.isIntoFinalSection(pointOffFinishLine));
        pointOffFinishLine = new Coordinate(24, 0);
        assertFalse(map.isIntoFinalSection(pointOffFinishLine));
    }

    @Test
    void testToString() {
        String mapString = map.toString();
        assertTrue(mapString.contains("Larghezza tracciato: 5.0"));
        assertTrue(mapString.contains("Coordinate:"));
        for (Coordinate coor : coordinates) {
            assertTrue(mapString.contains(coor.toString()));
        }
    }
}
