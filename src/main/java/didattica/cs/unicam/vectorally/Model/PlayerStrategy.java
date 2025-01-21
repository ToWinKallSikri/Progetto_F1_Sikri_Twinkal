package didattica.cs.unicam.vectorally.Model;

/**
 * Enumerazione che rappresenta le strategie dei giocatori (bot)
 * Ogni strategia ha un valore di velocità associato, che determina la velocità di avanzamento del giocatore.
 * La velocità è un valore intero compreso tra 1 e 5, che corrisponde al limite di velocità massimo che
 * raggiungerà il giocatore con quella determinata strategia. Una velocità massima minore consente al giocatore di
 * essere più cauto e di evitare collisioni, più alta è la velocità massima, più il giocatore sarà veloce ma anche
 * più soggetto a collisioni.
 *
 * @author Twinkal Sikri - 119251
 */

public enum PlayerStrategy {
    WISETURTLE(1),
    SPEEDYTURTLE(2),
    OOGWAY(3),
    RABBIT(4),
    BEEPBEEP(5);

    private final int speedValue;

    /**
     * Costruisce una nuova strategia di velocità con il valore di velocità specificato.
     * @param speedValue
     */
    PlayerStrategy(int speedValue) {
        this.speedValue = speedValue;
    }

    /**
     * Ottiene il valore di velocità associato alla strategia.
     * @return il valore di velocità associato alla strategia
     */
    public int getSpeedValue() {
        return speedValue;
    }
}
