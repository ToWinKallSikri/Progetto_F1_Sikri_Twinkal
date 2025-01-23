package didattica.cs.unicam.vectorally.Controller;
import didattica.cs.unicam.vectorally.Model.PlayerStrategy;
import java.util.Random;

/**
 * La classe PlayerStrategySelector seleziona casualmente una strategia di velocità per un giocatore.
 * La selezione è casuale, ma con una probabilità del 20% di selezionare una strategia con velocità 4 o 5.
 * Le strategie con velocità minore o uguale di 3 hanno una probabilità del 75% di essere selezionate.
 * @see PlayerStrategy
 *
 * @author Twinkal Sikri - 119251
 */
public class PlayerStrategySelector {
    private static final double HIGH_SPEED_PROBABILITY = 0.25; // Probabilità del 20% di selezionare 4 o 5

    /**
     * Seleziona casualmente una strategia di velocità per un giocatore.
     * @return una strategia di velocità casuale
     */
    public static PlayerStrategy getRandomSpeedEnum() {
        PlayerStrategy[] values = PlayerStrategy.values();
        int[] frequencies = new int[values.length];

        // Calcola le frequenze di ogni elemento dell'enumerazione
        for (PlayerStrategy value : values) {
            frequencies[value.ordinal()]++;
        }

        // Seleziona casualmente un elemento, dando priorità a quelli con frequenza <= 3
        Random random = new Random();
        double randomValue = random.nextDouble();
        if (randomValue <= HIGH_SPEED_PROBABILITY) {
            // 20% di probabilità di selezionare 4 o 5
            return values[random.nextInt(values.length)];
        } else {
            // 80% di probabilità di selezionare un valore <= 3
            while (true) {
                int index = random.nextInt(values.length);
                if (frequencies[index] <= 3) {
                    return values[index];
                }
            }
        }
    }
}
