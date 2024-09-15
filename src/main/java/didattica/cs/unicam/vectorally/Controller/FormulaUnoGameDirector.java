package didattica.cs.unicam.vectorally.Controller;

import didattica.cs.unicam.vectorally.Model.FormulaUnoMove;
import didattica.cs.unicam.vectorally.Model.FormulaUnoPlayer;
import didattica.cs.unicam.vectorally.Model.PlayerStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Classe che gestisce la direzione del gioco di Formula Uno.
 * Controlla i turni, applica le mosse dei giocatori e verifica le condizioni di vittoria.
 *
 * @author Twinkal Sikri - 119251
 */
public class FormulaUnoGameDirector implements GameDirector<FormulaUnoPlayer, FormulaUnoMove> {

    private final FormulaUnoPlayer[] players;
    private int currentPlayerIndex;
    private int winningPositionCounter;

    /**
     * Crea un nuovo direttore di gioco per la partita di Formula Uno.
     *
     * @param players i giocatori partecipanti alla partita
     */
    public FormulaUnoGameDirector(FormulaUnoPlayer[] players) {
        this.players = determineTurnOrder(players);
        this.currentPlayerIndex = 0;
        this.winningPositionCounter = 1;
    }

    /**
     * Determina l'ordine di turno dei giocatori mescolandoli casualmente.
     *
     * @param players i giocatori partecipanti
     * @return un array di giocatori nell'ordine dei turni
     */
    private FormulaUnoPlayer[] determineTurnOrder(FormulaUnoPlayer[] players) {
        List<FormulaUnoPlayer> turnOrderList = new ArrayList<>(Arrays.asList(players));
        Collections.shuffle(turnOrderList);
        return turnOrderList.toArray(new FormulaUnoPlayer[0]);
    }

    /**
     * Restituisce il giocatore corrente che deve effettuare la mossa.
     *
     * @return il giocatore corrente
     */
    @Override
    public FormulaUnoPlayer getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    /**
     * Passa al prossimo giocatore nel turno.
     */
    public void nextPlayer() {
        if(Arrays.asList(players).stream().allMatch(x -> x.getStatus() != PlayerStatus.ON_TRACK))
            return;
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        if(players[currentPlayerIndex].getStatus() != PlayerStatus.ON_TRACK)
            nextPlayer();
    }

    /**
     * Controlla la validità di una mossa e la applica, aggiornando lo stato del giocatore.
     *
     * @param currentPlayer il giocatore che sta effettuando la mossa
     * @param move la mossa da verificare e applicare
     * @throws IllegalStateException se la mossa è fuori dai limiti della mappa
     */
    @Override
    public void checkAndApplyMove(FormulaUnoPlayer currentPlayer, FormulaUnoMove move) {
        if (currentPlayer.getStatus() != PlayerStatus.ON_TRACK) 
            throw new IllegalArgumentException("A player that isn't on track cannot be moved.");
        if (move.getNewStatus() == PlayerStatus.ON_TRACK) {
            currentPlayer.move(move.getNewSpeed());
            currentPlayer.setCurrentSection(move.getCurrentSection());
        } else if (move.getNewStatus() == PlayerStatus.CRASHED) {
            currentPlayer.move(move.getNewSpeed());
            currentPlayer.crash();
        } else if (move.getNewStatus() == PlayerStatus.FINISHED) {
            currentPlayer.move(move.getNewSpeed());
            currentPlayer.win(winningPositionCounter++);
        }
        nextPlayer();
    }

    /**
     * Restituisce l'array dei giocatori partecipanti.
     *
     * @return l'array dei giocatori
     */
    public FormulaUnoPlayer[] getPlayers() {
        return players;
    }
}
