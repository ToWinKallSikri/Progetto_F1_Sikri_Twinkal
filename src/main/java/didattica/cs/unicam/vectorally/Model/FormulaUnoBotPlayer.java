package didattica.cs.unicam.vectorally.Model;

import didattica.cs.unicam.vectorally.Controller.ControllerFormulaUnoBotSimulator;
import didattica.cs.unicam.vectorally.Controller.PlayerStrategySelector;

/**
 * La classe FormulaUnoBotPlayer estende FormulaUnoPlayer per rappresentare un giocatore controllato dal bot
 * in un gioco di Formula Uno. Questa classe si integra con un simulatore di bot per determinare
 * le mosse successive del giocatore.
 *
 * @author Twinkal Sikri - 119251
 */
public class FormulaUnoBotPlayer extends FormulaUnoPlayer {

    private final ControllerFormulaUnoBotSimulator botSimulator;
    private final PlayerStrategy strategy;

    /**
     * Costruisce un nuovo giocatore bot con il nome specificato, la posizione iniziale e il simulatore del bot.
     *
     * @param name            Il nome del giocatore bot.
     * @param startingPosition La posizione iniziale del giocatore bot.
     * @param botSimulator    Il simulatore del bot che determinerà le mosse del giocatore.
     */
    public FormulaUnoBotPlayer(String name, Coordinate startingPosition,
                               ControllerFormulaUnoBotSimulator botSimulator) {
        super(name, startingPosition);
        this.botSimulator = botSimulator;
        this.strategy = PlayerStrategySelector.getRandomSpeedEnum();
    }

    /**
     * Restituisce la prossima mossa del giocatore bot, determinata dal simulatore del bot.
     *
     * @return La prossima mossa calcolata dal bot.
     */
    @Override
    public FormulaUnoMove getNextMove() {
        return botSimulator.getMove(this);
    }

    @Override
    public PlayerStrategy getStrategy() {
        return this.strategy;
    }




}





