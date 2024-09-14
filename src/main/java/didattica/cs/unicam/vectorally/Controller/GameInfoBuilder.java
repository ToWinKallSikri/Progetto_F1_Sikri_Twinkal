package didattica.cs.unicam.vectorally.Controller;

/**
 * Questa interfaccia ha la responsabilità di definire i metodi per la creazione di un'informazione di gioco.
 * @param <P> Il tipo di giocatori
 * @param <I> Il tipo di informazione di gioco
 *
 * @author Twinkal Sikri - 119251
 */
public interface GameInfoBuilder<P, I> {
    /**
     * Costruisce l'informazione di gioco
     * @param players i giocatori
     * @return l'informazione di gioco
     */
    I buildInfo(P players);
}

