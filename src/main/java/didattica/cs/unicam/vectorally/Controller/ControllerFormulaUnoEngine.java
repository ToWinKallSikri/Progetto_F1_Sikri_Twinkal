package didattica.cs.unicam.vectorally.Controller;

import didattica.cs.unicam.vectorally.Model.*;
import didattica.cs.unicam.vectorally.Model.Dto.FormulaUnoInfo;

/**
 * Il ControllerFormulaUnoEngine gestisce l'inizio e il controllo di una partita di Formula 1.
 * Implementa l'interfaccia ControllerGameEngine e si occupa della creazione della mappa,
 * dell'inizio del gioco e della gestione delle informazioni di gioco.
 *
 * @author Twinkal Sikri - 119251
 */
public class ControllerFormulaUnoEngine implements ControllerGameEngine<FormulaUnoInfo> {

    private FormulaUnoMap map;
    private FormulaUnoGameDirector gameDirector;
    private final FormulaUnoMapCreator mapCreator;
    private final FormulaUnoMapParser mapParser;
    private final int numberOfPlayers;
    private FormulaUnoInfoBuilder infoBuilder;

    /**
     * Costruisce un nuovo ControllerFormulaUnoEngine con il numero di giocatori predefinito (4).
     */
    public ControllerFormulaUnoEngine() {
        this(1);
    }

    /**
     * Costruisce un nuovo ControllerFormulaUnoEngine con il numero specificato di giocatori.
     *
     * @param numberOfPlayers Il numero di giocatori nella partita.
     */
    public ControllerFormulaUnoEngine(int numberOfPlayers) {
        this.mapCreator = new FormulaUnoMapCreator();
        this.mapParser = new FormulaUnoMapParser();
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * Aggiunge una mappa al motore di gioco leggendo il percorso del file.
     *
     * @param path Il percorso del file contenente la mappa.
     */
    @Override
    public void addMap(String path) {
        this.mapParser.parseMapFromFile(path);
        this.map = this.mapCreator.createMap(path);
    }

    /**
     * Aggiunge una mappa al motore di gioco utilizzando coordinate e raggio forniti.
     *
     * @param map L'array di coordinate che rappresentano la mappa.
     * @param radius Il raggio da utilizzare nella creazione della mappa.
     */
    public void addMap(Coordinate[] map, double radius) {
        this.mapParser.parseMapFromCoordinates(map);
        this.map = this.mapCreator.createMap(map, radius);
    }

    /**
     * Inizia una nuova partita, creando i giocatori e il simulatore del bot.
     */
    @Override
    public void startGame() {
        FormulaUnoEmptyPositionChecker emptyChecker = new FormulaUnoEmptyPositionChecker();
        ControllerFormulaUnoBotSimulator botSimulator = new ControllerFormulaUnoBotSimulator(emptyChecker, map);
        FormulaUnoPlayer[] players = new FormulaUnoPlayer[this.numberOfPlayers];
        Coordinate[] startingPositions = map.getSection(0).getStartingPointsForPlayers(this.numberOfPlayers);
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new FormulaUnoBotPlayer("player_" + i, startingPositions[i], botSimulator);
            emptyChecker.addGameObject(players[i]);
        }
        this.gameDirector = new FormulaUnoGameDirector(players);
        this.infoBuilder = new FormulaUnoInfoBuilder(map);
    }

    /**
     * Restituisce le informazioni correnti del gioco.
     *
     * @return Un oggetto FormulaUnoInfo contenente le informazioni della partita.
     */
    @Override
    public FormulaUnoInfo getInfo() {
        return this.infoBuilder == null ? null : this.infoBuilder.buildInfo(this.gameDirector.getPlayers());
    }

    /**
     * Esegue un turno di gioco, ottenendo la mossa del giocatore corrente e applicandola.
     */
    @Override
    public void runRound() {
        FormulaUnoPlayer player = this.gameDirector.getCurrentPlayer();
        FormulaUnoMove move = player.getNextMove();
        this.gameDirector.checkAndApplyMove(player, move);
    }

}
