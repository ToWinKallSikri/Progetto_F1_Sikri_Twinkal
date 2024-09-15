package didattica.cs.unicam.vectorally.Model;

/**
 * La classe FormulaUnoMove rappresenta una mossa effettuata da un giocatore in un gioco di Formula Uno.
 * Ogni mossa include le informazioni sulla velocità precedente, la nuova velocità, la posizione precedente,
 * la nuova posizione e lo stato del giocatore dopo la mossa.
 *
 * @author Twinkal Sikri - 119251
 */
public class FormulaUnoMove {

    private final Coordinate oldSpeed, newSpeed, chosenMove, lastPosition, newPosition;
    private final PlayerStatus newStatus;
    private int currentSection;

    /**
     * Costruisce una nuova mossa con la posizione precedente, la vecchia velocità,
     * la mossa scelta e il nuovo stato del giocatore.
     *
     * @param lastPosition La posizione del giocatore prima della mossa.
     * @param oldSpeed La velocità del giocatore prima della mossa.
     * @param chosenMove La mossa scelta dal giocatore.
     * @param newStatus Il nuovo stato del giocatore dopo la mossa.
     */
    public FormulaUnoMove(Coordinate lastPosition, Coordinate oldSpeed,
                          Coordinate chosenMove, PlayerStatus newStatus,
                          int currentSection) {
        this.lastPosition = lastPosition;
        this.oldSpeed = oldSpeed;
        this.chosenMove = chosenMove;
        this.newSpeed = oldSpeed.sum(chosenMove);
        this.newPosition = lastPosition.sum(this.newSpeed);
        this.newStatus = newStatus;
        this.currentSection = currentSection;
    }

    /**
     * Restituisce la nuova posizione del giocatore dopo la mossa.
     *
     * @return La nuova posizione del giocatore.
     */
    public Coordinate getNewPosition() {
        return newPosition;
    }

    /**
     * Restituisce la posizione precedente del giocatore prima della mossa.
     *
     * @return La posizione precedente del giocatore.
     */
    public Coordinate getLastPosition() {
        return lastPosition;
    }

    /**
     * Restituisce la mossa scelta dal giocatore.
     *
     * @return La mossa scelta.
     */
    public Coordinate getChosenMove() {
        return chosenMove;
    }

    /**
     * Restituisce la nuova velocità del giocatore dopo la mossa.
     *
     * @return La nuova velocità del giocatore.
     */
    public Coordinate getNewSpeed() {
        return newSpeed;
    }

    /**
     * Restituisce la velocità precedente del giocatore prima della mossa.
     *
     * @return La velocità precedente del giocatore.
     */
    public Coordinate getOldSpeed() {
        return oldSpeed;
    }

    /**
     * Restituisce il nuovo stato del giocatore dopo la mossa.
     *
     * @return Il nuovo stato del giocatore.
     */
    public PlayerStatus getNewStatus() {
        return this.newStatus;
    }

    /**
     * Restituisce la sezione corrente in cui si trova il giocatore.
     *
     * @return La sezione corrente.
     */
    public int getCurrentSection(){
        return this.currentSection;
    }
}
