package didattica.cs.unicam.vectorally.Controller;

/**
 * Interfaccia per la simulazione di un bot in un gioco.
 * @param <P> tipo del giocatore
 * @param <M> tipo della mossa
 *
 * @author Twinkal Sikri - 119251
 */
public interface ControllerBotSimulator <P,M> {
    /**
     * Ottiene la mossa del giocatore.
     * @param player il giocatore
     * @return la mossa del giocatore
     */
    M getMove(P player);
}
