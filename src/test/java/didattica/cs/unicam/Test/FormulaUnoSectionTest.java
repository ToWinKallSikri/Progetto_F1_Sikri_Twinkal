package didattica.cs.unicam.Test;

import didattica.cs.unicam.vectorally.Model.Coordinate;
import didattica.cs.unicam.vectorally.Model.FormulaUnoSection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FormulaUnoSectionTest {

    private FormulaUnoSection section;
    private Coordinate start;
    private Coordinate end;
    private double radius;

    @BeforeEach
    void setUp() {
        start = new Coordinate(0, 0);
        end = new Coordinate(10, 0);
        radius = 5;
        section = new FormulaUnoSection(start, end, radius);
    }

    @Test
    void testGetStart() {
        assertEquals(start, section.getStart());
    }

    @Test
    void testGetEnd() {
        assertEquals(end, section.getEnd());
    }

    @Test
    void testIsIntoSection_InsideRadius() {
        Coordinate point = new Coordinate(5, 0);
        assertTrue(section.isIntoSection(point));
    }

    @Test
    void testIsIntoSection_OutsideRadius() {
        Coordinate point = new Coordinate(5, 6);
        assertFalse(section.isIntoSection(point));
    }

    @Test
    void testIsIntoSection_OnBoundary() {
        Coordinate point = new Coordinate(0, radius);
        assertTrue(section.isIntoSection(point));
    }

    @Test
    void testGetStartingPointsForPlayers_ValidPlayerCount() {
        Coordinate[] startingPoints = section.getStartingPointsForPlayers(2);
        assertEquals(2, startingPoints.length );
        assertEquals(start.sum(new Coordinate(1, 0)), startingPoints[0]);
        assertEquals(start.sum(new Coordinate(1, 0)), startingPoints[1]);
    }

    @Test
    void testGetStartingPointsForPlayers_TooManyPlayers() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            section.getStartingPointsForPlayers(100);
        });
        assertEquals("Mappa troppo stretta per questo numero di giocatori.", exception.getMessage());
    }

}

