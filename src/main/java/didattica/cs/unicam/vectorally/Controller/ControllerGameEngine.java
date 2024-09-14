package didattica.cs.unicam.vectorally.Controller;

/**
 * Interfaccia per il controllo del motore di gioco.
 * @param <I> tipo delle informazioni di gioco
 *
 * @author Twinkal Sikri - 119251
 */
public interface ControllerGameEngine<I> {
    /**
     * Aggiunge una mappa al motore di gioco.
     * @param path il percorso della mappa
     */
    void addMap(String path);
    /**
     * Metodo per iniziare il gioco.
     */
    void startGame();

    /**
     * Metodo per ottenere le informazioni di gioco.
     * @return le informazioni di gioco
     */
    I getInfo();

    /**
     * Metodo per eseguire un turno del gioco.
     */
    void runRound();
}
