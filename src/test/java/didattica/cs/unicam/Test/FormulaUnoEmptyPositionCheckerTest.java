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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormulaUnoEmptyPositionCheckerTest {

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
    void testIsEmpty() {
        assertTrue(emptyPositionChecker.isEmpty(map.getSection(0).getStartingPointsForPlayers(1)[0]));
        Coordinate c = new Coordinate(-2, 2);
        assertTrue(emptyPositionChecker.isEmpty(c));
    }

}
