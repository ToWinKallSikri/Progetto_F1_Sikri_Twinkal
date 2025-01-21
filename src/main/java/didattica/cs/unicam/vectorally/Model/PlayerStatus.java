package didattica.cs.unicam.vectorally.Model;

/**
 * Enumerazione che rappresenta lo stato di un giocatore
 *
 * @author Twinkal Sikri - 119251
 */
public enum PlayerStatus {
    ON_TRACK, // il giocatore è ancora in strada
    FINISHED, // il giocatore ha terminato il tracciato
    CRASHED  // il giocatore è uscito di strada o ha avuto una collisione
}
