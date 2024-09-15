package didattica.cs.unicam.vectorally.Model;

/**
 * La classe astratta FormulaUnoPlayer rappresenta un giocatore in un gioco di Formula Uno.
 * Ogni giocatore ha un nome, una posizione attuale, una velocità, uno stato e informazioni sul tempo e posizione di arrivo.
 *
 * @author Twinkal Sikri - 119251
 */


public abstract class FormulaUnoPlayer implements PositionedGameObject {
    private final String name;
    private Coordinate currentPosition;
    private Coordinate velocity; // Rappresenta il vettore di velocità attuale
    private PlayerStatus status;
    private int finishPosition;
    private int finishTime;
    private int currentSection;

    /**
     * Costruisce un nuovo giocatore con il nome e la posizione di partenza specificati.
     * Inizialmente, il giocatore ha una velocità di (0,0) e uno stato di "ON_TRACK".
     *
     * @param name Il nome del giocatore.
     * @param startPosition La posizione di partenza del giocatore.
     */
    public FormulaUnoPlayer(String name, Coordinate startPosition) {
        this.name = name;
        this.currentPosition = startPosition;
        this.velocity = new Coordinate(0, 0); // Inizialmente a riposo
        this.status = PlayerStatus.ON_TRACK;
        this.finishPosition = -1;
        this.currentSection = 0;
    }

    /**
     * Muove il giocatore aggiornando la posizione in base alla velocità fornita.
     * Lancia un'eccezione se il giocatore non è in stato "ON_TRACK".
     *
     * @param speed La velocità da applicare al giocatore.
     */
    public void move(Coordinate speed) {
        if (status != PlayerStatus.ON_TRACK) {
            throw new IllegalStateException("" + status);
        }
        // Calcola la nuova velocità in base alla posizione corrente e alla posizione di destinazione
        this.velocity = speed;
        // Aggiorna la posizione corrente
        this.currentPosition = this.currentPosition.sum(speed);
        this.finishTime++;
    }

    /**
     * Calcola il punto principale basato sull'ultimo movimento del giocatore.
     *
     * @return Il punto principale.
     */
    public Coordinate getPrincipalPoint() {
        return this.currentPosition.sum(this.velocity);
    }

    /**
     * Aggiorna lo stato del giocatore a "finito" e imposta il tempo e la posizione di arrivo.
     *
     * @param finishPosition La posizione di arrivo del giocatore.
     */
    public void win(int finishPosition) {
        this.status = PlayerStatus.FINISHED;
        this.finishPosition = finishPosition;
    }

    /**
     * Aggiorna lo stato del giocatore a "crashed".
     */
    public void crash() {
        this.status = PlayerStatus.CRASHED;
    }

    /**
     * Restituisce il nome del giocatore.
     *
     * @return Il nome del giocatore.
     */
    public String getName() {
        return name;
    }

    /**
     * Restituisce la posizione attuale del giocatore.
     *
     * @return La posizione attuale del giocatore.
     */
    @Override
    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Restituisce lo stato del giocatore.
     *
     * @return Lo stato del giocatore.
     */
    public PlayerStatus getStatus() {
        return status;
    }

    /**
     * Restituisce la velocità attuale del giocatore.
     *
     * @return La velocità del giocatore.
     */
    public Coordinate getVelocity() {
        return velocity;
    }

    /**
     * Restituisce una rappresentazione testuale del giocatore, inclusi nome, posizione, velocità e stato.
     *
     * @return La stringa di rappresentazione del giocatore.
     */
    @Override
    public String toString() {
        return "Player: " + name +
                "\nPosition: " + currentPosition +
                "\nVelocity: " + velocity +
                "\nStatus: " + status;
    }

    /**
     * Restituisce il tempo di arrivo del giocatore.
     *
     * @return Il tempo di arrivo del giocatore.
     */
    public int getFinishTime() {
        return finishTime;
    }

    /**
     * Restituisce la posizione di arrivo del giocatore.
     *
     * @return La posizione di arrivo del giocatore.
     */
    public int getFinishPosition() {
        return finishPosition;
    }

    /**
     * Imposta la sezione corrente del giocatore.
     * @param currentSection La sezione corrente del giocatore.
     */
    public void setCurrentSection(int currentSection){
        this.currentSection = currentSection;
    }

    /**
     * Restituisce la sezione corrente del giocatore.
     * @return La sezione corrente del giocatore.
     */
    public int getCurrentSection(){
        return this.currentSection;
    }

    /**
     * Metodo astratto per ottenere la prossima mossa del giocatore.
     * Deve essere implementato dalle classi concrete.
     *
     * @return La prossima mossa del giocatore.
     */
    public abstract FormulaUnoMove getNextMove();
}
