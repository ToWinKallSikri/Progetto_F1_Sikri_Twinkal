package didattica.cs.unicam.Test;

import didattica.cs.unicam.vectorally.Controller.ControllerFormulaUnoBotSimulator;
import didattica.cs.unicam.vectorally.Controller.ControllerFormulaUnoEngine;
import didattica.cs.unicam.vectorally.Controller.FormulaUnoEmptyPositionChecker;
import didattica.cs.unicam.vectorally.Controller.FormulaUnoGameDirector;
import didattica.cs.unicam.vectorally.Model.Coordinate;
import didattica.cs.unicam.vectorally.Model.FormulaUnoBotPlayer;
import didattica.cs.unicam.vectorally.Model.FormulaUnoMap;
import didattica.cs.unicam.vectorally.Model.FormulaUnoPlayer;
import didattica.cs.unicam.vectorally.View.FormulaUnoDefaultMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerFormulaUnoBotSimulatorTest {

    private ControllerFormulaUnoBotSimulator botSimulator;
    private ControllerFormulaUnoEngine engine;
    private FormulaUnoGameDirector gameDirector;
    private FormulaUnoEmptyPositionChecker emptyPositionChecker;
    private FormulaUnoMap map;
    private FormulaUnoDefaultMap defaultMap;

    private final FormulaUnoPlayer[] players = new FormulaUnoPlayer[1];
    @BeforeEach
    void setUp() {
        defaultMap = new FormulaUnoDefaultMap();
        map = new FormulaUnoMap(defaultMap.getDefaultRadius(), defaultMap.getDefaultPoints());
        emptyPositionChecker = new FormulaUnoEmptyPositionChecker();
        engine = new ControllerFormulaUnoEngine();
        engine.addMap(defaultMap.getDefaultPoints(), defaultMap.getDefaultRadius());
        botSimulator = new ControllerFormulaUnoBotSimulator(emptyPositionChecker, map);
        players[0] = new FormulaUnoBotPlayer("player_0", map
                .getSection(0)
                .getStartingPointsForPlayers(1)[0]
                , botSimulator);
        gameDirector = new FormulaUnoGameDirector(players);
        engine.startGame();
        engine.runRound();
    }

    @Test
    void testGetMove() {
        Coordinate c2 = new Coordinate(-1, 1);
        Coordinate c1 = new Coordinate(-2, 2);
        Coordinate speed = new Coordinate(-1, 1);
        int section = 0;
        assertEquals(c2, botSimulator.getMove(gameDirector.getCurrentPlayer()).getLastPosition());
        assertEquals(c1, botSimulator.getMove(gameDirector.getCurrentPlayer()).getNewPosition());
        assertEquals(speed, botSimulator.getMove(gameDirector.getCurrentPlayer()).getNewSpeed());
        assertEquals(section, botSimulator.getMove(gameDirector.getCurrentPlayer()).getCurrentSection());
    }
}
