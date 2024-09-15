package didattica.cs.unicam.Test;

import didattica.cs.unicam.vectorally.Controller.ControllerFormulaUnoBotSimulator;
import didattica.cs.unicam.vectorally.Controller.ControllerFormulaUnoEngine;
import didattica.cs.unicam.vectorally.Controller.FormulaUnoEmptyPositionChecker;
import didattica.cs.unicam.vectorally.Controller.FormulaUnoGameDirector;
import didattica.cs.unicam.vectorally.Model.*;
import didattica.cs.unicam.vectorally.View.FormulaUnoDefaultMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormulaUnoGameDirectorTest {

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
    void testCheckAndApplyMove() {
        assertEquals(players[0].getCurrentPosition(), gameDirector.getCurrentPlayer().getCurrentPosition());
        Coordinate c1 = players[0].getCurrentPosition();
        Coordinate speed1 = players[0].getVelocity();
        Coordinate t1 = new Coordinate(c1.getX() + speed1.getX(), c1.getY() + speed1.getY());
        PlayerStatus status = players[0].getStatus();
        gameDirector.checkAndApplyMove(players[0], new FormulaUnoMove(c1, speed1, t1, status, 0));
        Coordinate c2 = new Coordinate(-2, 2);
        assertEquals(c2, players[0].getCurrentPosition());
    }
}
