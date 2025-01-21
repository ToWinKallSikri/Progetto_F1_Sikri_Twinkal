package didattica.cs.unicam.vectorally.Controller;

import didattica.cs.unicam.vectorally.Model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Il ControllerFormulaUnoBotSimulator gestisce la simulazione dei movimenti di un bot
 * all'interno di una pista di Formula 1.
 * Implementa l'interfaccia ControllerBotSimulator e fornisce logica per determinare la prossima mossa del bot.
 *
 * @author Twinkal Sikri - 119251
 */
public class ControllerFormulaUnoBotSimulator implements ControllerBotSimulator<FormulaUnoPlayer, FormulaUnoMove> {

    private final FormulaUnoEmptyPositionChecker emptyChecker;
    private final FormulaUnoMap map;
    private final double maxSpeed; // Limite massimo di velocità
    private PlayerStrategy strategy;
    /**
     * Costruisce un nuovo ControllerFormulaUnoBotSimulator con il checker delle posizioni vuote e la mappa della pista.
     *
     * @param emptyChecker Il checker per le posizioni vuote.
     * @param map La mappa della pista.
     */
    public ControllerFormulaUnoBotSimulator(FormulaUnoEmptyPositionChecker emptyChecker, FormulaUnoMap map) {
        this.emptyChecker = emptyChecker;
        this.map = map;
        this.strategy = PlayerStrategySelector.getRandomSpeedEnum();
        this.maxSpeed = strategy.getSpeedValue();
    }

    /**
     * Determina la prossima mossa del giocatore bot. Se non ci sono mosse legali, gestisce un incidente.
     * Altrimenti, sceglie la mossa migliore e verifica se la velocità è troppo alta.
     * Se il bot ha raggiunto la linea del traguardo, gestisce la vittoria.
     * Verifica se il bot è uscito dalla pista o ha avuto una collisione.
     *
     * @param player Il giocatore bot per cui calcolare la mossa.
     * @return La mossa da effettuare.
     */
    @Override
    public FormulaUnoMove getMove(FormulaUnoPlayer player) {
        List<FormulaUnoMove> moves = generateAndFilterLegalMoves(player);
        if (moves.isEmpty()) {
            return handleNoMoves(player);
        }
        if(moves.stream().anyMatch(x -> x.getNewStatus() == PlayerStatus.FINISHED))
            return moves.stream().filter(x -> x.getNewStatus() == PlayerStatus.FINISHED).findFirst().get();
        if(moves.stream().anyMatch(x -> x.getNewStatus() == PlayerStatus.ON_TRACK))
            moves = moves.stream().filter(x -> x.getNewStatus() == PlayerStatus.ON_TRACK).toList();
        moves = filterBySpeed(moves);
        return chooseBestMove(moves);
    }

    /**
     * Genera e filtra le mosse legali per il giocatore bot.
     *
     * @param player Il giocatore bot per cui generare le mosse.
     * @return Una lista di mosse legali.
     */
    private List<FormulaUnoMove> generateAndFilterLegalMoves(FormulaUnoPlayer player) {
        List<FormulaUnoMove> possibleMoves = generatePossibleMoves(player);
        return filterLegalMoves(possibleMoves, player.getCurrentSection());
    }

    /**
     * Genera tutte le possibili mosse basate sulla posizione principale del giocatore e le sue velocità.
     *
     * @param player Il giocatore bot per cui generare le mosse.
     * @return Una lista di mosse possibili.
     */
    private List<FormulaUnoMove> generatePossibleMoves(FormulaUnoPlayer player) {
        return getNineVectors().stream()
                .map(n -> {
                    int newSection = player.getCurrentSection();
                    int winnerSection = map.getSections().size();
                    Coordinate start = player.getCurrentPosition();
                    Coordinate end = player.getPrincipalPoint().sum(n);
                    List<Coordinate> list = start.getIntegerRoutePoints(end);
                    for(Coordinate vector : list){
                        start = start.sum(vector);
                        newSection = map.isIntoMap(start, newSection);
                        if(newSection == -1 || newSection == winnerSection)
                            break;
                    }
                    PlayerStatus status = newSection == winnerSection ? PlayerStatus.FINISHED : newSection == -1 ? PlayerStatus.CRASHED : PlayerStatus.ON_TRACK;
                    return new FormulaUnoMove(player.getCurrentPosition(), player.getVelocity(), n, status, newSection);
                })
                .collect(Collectors.toList());
    }

    
    /**
     * Metodo per ottenere le 9 posizioni possibili in cui un giocatore può andare dal punto corrente
     * @return lista di coordinate delle 9 posizioni possibili
     */
    private List<Coordinate> getNineVectors() {
        List<Coordinate> list = new ArrayList<>();
        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                list.add(new Coordinate(i, j));
            }
        }
        return list;
    }

    /**
     * Filtra le mosse legali da una lista di mosse possibili.
     *
     * @param possibleMoves La lista di mosse possibili.
     * @return Una lista di mosse legali.
     */
    private List<FormulaUnoMove> filterLegalMoves(List<FormulaUnoMove> possibleMoves, int lastSection) {
        return possibleMoves.stream()
                .filter(move -> emptyChecker.isEmpty(move.getNewPosition()))
                .collect(Collectors.toList());
    }

    /**
     * Gestisce un caso speciale in cui il giocatore non ha nessuna mossa
     * senza collisioni, per cui resta fermo
     * @param player il giocatore che fa la mossa
     * @return una mossa per gestire il caso in cui non ci sono mosse (il giocatore non si muove)
     */
    private FormulaUnoMove handleNoMoves(FormulaUnoPlayer player) {
        return new FormulaUnoMove(player.getCurrentPosition(), new Coordinate(0, 0), new Coordinate(0, 0),
                                  PlayerStatus.ON_TRACK, player.getCurrentSection());
    }

    /**
     * Metodo per filtrare le mosse in base alla velocità
     * @param moves lista delle mosse possibili da filtrare
     * @return lista delle mosse filtrate
     */
    private List<FormulaUnoMove> filterBySpeed(List<FormulaUnoMove> moves) {
       List<FormulaUnoMove> list = moves.stream().filter(x -> (Math.abs(x.getNewSpeed().getX()) <= maxSpeed) &&
                                                              (Math.abs(x.getNewSpeed().getY()) <= maxSpeed ))
                                                              .filter(x -> x.getCurrentSection() != -1 &&
                                                                    map.isIntoMap(x.getNewPosition().sum(x.getNewSpeed()),
                                                                                  x.getCurrentSection()) != -1)
                                                              .toList();
       if(list.isEmpty()){
            FormulaUnoMove move = moves.stream().reduce(null, (a,b) -> {
                if(a == null)
                    return b;
                double ax = Math.abs(a.getNewSpeed().getX());
                double ay = Math.abs(a.getNewSpeed().getY());
                double bx = Math.abs(b.getNewSpeed().getX());
                double by = Math.abs(b.getNewSpeed().getY());
                return Math.max(ax, ay) <= Math.max(bx, by) ? a : b;
            });
            list = new ArrayList<>();
            list.add(move);
       }
       return list;
    }

    /**
     * Metodo per scegliere la mossa migliore, in base a distanza e la sezione che può raggiungere
     * @param moves lista delle mosse possibili
     * @return la mossa migliore
     */
    private FormulaUnoMove chooseBestMove(List<FormulaUnoMove> moves) {
        int maxSection = moves.stream().map(x -> x.getCurrentSection()).reduce(0, (a,b) -> Math.max(a,b));
        moves.stream().filter(x -> x.getCurrentSection() == maxSection).toList();
        FormulaUnoMove move = moves.get(0);
        Coordinate target = map.getSection(maxSection).getEnd();
        for(int i = 1; i < moves.size(); i++){
            move = move.getNewPosition().getDistance(target) < moves.get(i).getNewPosition().getDistance(target) ? move : moves.get(i);
        }
        return move;
    }

    /**
     * Metodo per ottenere la strategia di gioco del giocatore
     * @return la strategia di gioco del giocatore
     */
    public String getStrategy() {
        return strategy.name();
    }

    /**
     * Metodo per impostare la strategia di gioco del giocatore
     * @param str la strategia di gioco del giocatore
     */
    public void setStrategy(PlayerStrategy str) {
        this.strategy = str;
    }
}
