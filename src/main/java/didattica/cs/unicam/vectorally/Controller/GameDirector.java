package didattica.cs.unicam.vectorally.Controller;

/**
 * Questa interfaccia ha la responsabilità di definire i metodi per la direzione di un gioco.
 * @param <P> Il tipo di giocatori
 * @param <M> Il tipo di mosse
 *
 * @author Twinkal Sikri - 119251
 */
public interface GameDirector<P, M> {
    /**
     * Restituisce il giocatore corrente
     * @return il giocatore corrente
     */
    P getCurrentPlayer();

    /**
     * Metodo per controllare e applicare una mossa
     * @param player il giocatore che ha effettuato la mossa
     * @param move la mossa da controllare e applicare
     */
    void checkAndApplyMove(P player, M move);
}
