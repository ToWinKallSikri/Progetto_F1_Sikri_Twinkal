package didattica.cs.unicam.vectorally.View;

import didattica.cs.unicam.vectorally.Model.Coordinate;

/**
 * Classe che definisce la mappa di default per il gioco FormulaUno.
 *
 * @author Twinkal Sikri - 119251
 */
public class FormulaUnoDefaultMap {

    /**
     * Restituisce il raggio di default per la mappa.
     * @return il raggio di default
     */
    public double getDefaultRadius() {
        return 5;
    }

    /**
     * Restituisce i punti di default per la mappa.
     * @return i punti di default
     */
    public Coordinate[] getDefaultPoints() {
        return new Coordinate[]{
                new Coordinate(0, 0),
                new Coordinate(-50, 60),
                new Coordinate(100, 80),
                new Coordinate(100, -20),
                new Coordinate(40, -50),
                new Coordinate(-30, -50)
        };
    }

}
