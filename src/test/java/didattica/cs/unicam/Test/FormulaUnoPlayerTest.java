package didattica.cs.unicam.Test;

import didattica.cs.unicam.vectorally.Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FormulaUnoPlayerTest {

    // Sottoclasse fittizia di FormulaUnoPlayer per i test
    static class TestPlayer extends FormulaUnoPlayer {
        public TestPlayer(String name, Coordinate startPosition) {
            super(name, startPosition);
        }

        @Override
        public FormulaUnoMove getNextMove() {
            return null; // Implementazione fittizia per il test
        }

        @Override
        public PlayerStrategy getStrategy() {
            return null; // Implementazione fittizia per il test
        }
    }

    private TestPlayer player;

    @BeforeEach
    void setUp() {
        // Impostazione di base del giocatore per i test
        player = new TestPlayer("Player1", new Coordinate(0, 0));
    }

    @Test
    void testInitialValues() {
        assertEquals("Player1", player.getName());
        assertEquals(new Coordinate(0, 0), player.getCurrentPosition());
        assertEquals(new Coordinate(0, 0), player.getVelocity());
        Assertions.assertEquals(PlayerStatus.ON_TRACK, player.getStatus());
        assertEquals(-1, player.getFinishPosition());
        assertEquals(0, player.getFinishTime());
    }

    @Test
    void testMove() {
        // Muoviamo il giocatore e verifichiamo l'aggiornamento di posizione e velocità
        Coordinate speed = new Coordinate(2, 3);
        player.move(speed);
        assertEquals(new Coordinate(2, 3), player.getCurrentPosition());
        assertEquals(speed, player.getVelocity());
        assertEquals(1, player.getFinishTime());
    }

    @Test
    void testGetPrincipalPoint() {
        // Testiamo il calcolo del punto principale
        player.move(new Coordinate(2, 2));
        Coordinate principalPoint = player.getPrincipalPoint();
        assertEquals(new Coordinate(4, 4), principalPoint);
    }

    @Test
    void testWin() {
        // Verifica che lo stato e la posizione di arrivo vengano aggiornati correttamente
        player.win(1);
        assertEquals(PlayerStatus.FINISHED, player.getStatus());
        assertEquals(1, player.getFinishPosition());
    }

    @Test
    void testCrash() {
        // Verifica che lo stato venga aggiornato correttamente in caso di crash
        player.crash();
        assertEquals(PlayerStatus.CRASHED, player.getStatus());
    }

    @Test
    void testMoveThrowsExceptionIfNotOnTrack() {
        // Verifica che venga lanciata un'eccezione se il giocatore non è in stato ON_TRACK
        player.crash();
        assertThrows(IllegalStateException.class, () -> player.move(new Coordinate(1, 1)));
    }

    @Test
    void testSetAndGetCurrentSection() {
        // Verifica dell'impostazione e lettura della sezione corrente
        player.setCurrentSection(5);
        assertEquals(5, player.getCurrentSection());
    }
}

