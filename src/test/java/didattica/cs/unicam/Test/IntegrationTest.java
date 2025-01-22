package didattica.cs.unicam.Test;

import didattica.cs.unicam.vectorally.Controller.*;
import didattica.cs.unicam.vectorally.Model.*;
import didattica.cs.unicam.vectorally.View.FormulaUnoDefaultMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {

    private ControllerFormulaUnoEngine engine;
    private FormulaUnoGameDirector gameDirector;
    private final FormulaUnoPlayer[] players = new FormulaUnoPlayer[1];

    @BeforeEach
    void setUp() {
        FormulaUnoDefaultMap defaultMap = new FormulaUnoDefaultMap();
        FormulaUnoMap map = new FormulaUnoMap(defaultMap.getDefaultRadius(), defaultMap.getDefaultPoints());
        FormulaUnoEmptyPositionChecker emptyPositionChecker = new FormulaUnoEmptyPositionChecker();
        engine = new ControllerFormulaUnoEngine();
        engine.addMap(defaultMap.getDefaultPoints(), defaultMap.getDefaultRadius());
        ControllerFormulaUnoBotSimulator botSimulator = new ControllerFormulaUnoBotSimulator(emptyPositionChecker, map);
        players[0] = new FormulaUnoBotPlayer("player_0", map.getSection(0).getStartingPointsForPlayers(1)[0], botSimulator);
        gameDirector = new FormulaUnoGameDirector(players);
        engine.startGame();
    }

    @Test
    void testRunRound() {
        engine.runRound();
        assertNotNull(gameDirector.getCurrentPlayer().getCurrentPosition());
    }
}