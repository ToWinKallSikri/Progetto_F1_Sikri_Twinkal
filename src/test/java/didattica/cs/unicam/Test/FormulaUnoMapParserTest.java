package didattica.cs.unicam.Test;

import didattica.cs.unicam.vectorally.Model.Coordinate;
import didattica.cs.unicam.vectorally.Controller.FormulaUnoMapParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FormulaUnoMapParserTest {

    private FormulaUnoMapParser mapParser;

    @BeforeEach
    void setUp() {
        mapParser = new FormulaUnoMapParser();
    }

    @Test
    void testParseMapFromCoordinatesValid() {
        // Coordinate di esempio valide
        Coordinate[] validCoordinates = {
                new Coordinate(0, 0),
                new Coordinate(10, 10),
                new Coordinate(20, 20)
        };

        // Dovrebbe passare senza eccezioni
        assertDoesNotThrow(() -> mapParser.parseMapFromCoordinates(validCoordinates));
    }

    @Test
    void testParseMapFromCoordinatesLessThanThreePoints() {
        // Coordinate con meno di 3 punti
        Coordinate[] fewCoordinates = {
                new Coordinate(0, 0),
                new Coordinate(10, 10)
        };

        // Verifica che venga lanciata IllegalArgumentException per meno di 3 coordinate
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mapParser.parseMapFromCoordinates(fewCoordinates);
        });
        assertEquals("La mappa deve contenere almeno 3 coordinate.", exception.getMessage());
    }

    @Test
    void testParseMapFromCoordinatesPointsTooClose() {
        // Coordinate con punti troppo vicini
        Coordinate[] closeCoordinates = {
                new Coordinate(0, 0),
                new Coordinate(2, 2),
                new Coordinate(20, 20)
        };

        // Verifica che venga lanciata IllegalArgumentException per coordinate troppo vicine
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mapParser.parseMapFromCoordinates(closeCoordinates);
        });
        assertTrue(exception.getMessage().contains("sono troppo vicine"));
    }

    @Test
    void testParseMapFromFileValid() {
        // Test del parsing da file (simulazione, il file dovrebbe essere disponibile)
        // Usa un file fittizio o simula i metodi chiamati all'interno della classe
        String validFilePath = "path/to/valid/map.txt";

        // Simulazione dell'override dei metodi per testare senza leggere il file
        mapParser = new FormulaUnoMapParser() {
            @Override
            public double getRadius(String path) {
                return 10.0; // Simula un raggio letto da file
            }

            @Override
            public Coordinate[] getCoordinatesFromFile(String path) {
                return new Coordinate[]{
                        new Coordinate(0, 0),
                        new Coordinate(10, 10),
                        new Coordinate(20, 20)
                }; // Simula coordinate lette da file
            }
        };

        // Dovrebbe passare senza eccezioni
        assertDoesNotThrow(() -> mapParser.parseMapFromFile(validFilePath));
    }

    @Test
    void testParseMapFromFileInvalidCoordinates() {
        // Test del parsing da file con coordinate non valide (simulazione)
        String invalidFilePath = "path/to/invalid/map.txt";

        // Simulazione dell'override dei metodi per testare senza leggere il file
        mapParser = new FormulaUnoMapParser() {
            @Override
            public double getRadius(String path) {
                return 10.0; // Simula un raggio letto da file
            }

            @Override
            public Coordinate[] getCoordinatesFromFile(String path) {
                return new Coordinate[]{
                        new Coordinate(0, 0),
                        new Coordinate(1, 1), // Troppo vicina
                        new Coordinate(2, 2)
                }; // Simula coordinate non valide lette da file
            }
        };

        // Verifica che venga lanciata IllegalArgumentException per coordinate troppo vicine
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            mapParser.parseMapFromFile(invalidFilePath);
        });
        assertTrue(exception.getMessage().contains("sono troppo vicine"));
    }
}

