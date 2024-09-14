package didattica.cs.unicam.vectorally.Controller;

import didattica.cs.unicam.vectorally.Model.Coordinate;
import didattica.cs.unicam.vectorally.Model.FormulaUnoMap;

/**
 * Questa classe ha la responsabilità di creare una mappa FormulaUno a partire da un file di testo
 *  o da un array di Coordinate.
 *
 * @author Twinkal Sikri - 119251
 */
public class FormulaUnoMapCreator extends  FormulaUnoMapHandler {
    /**
     * Crea una mappa FormulaUno a partire da un file di testo.
     * @param path il percorso del file di testo
     * @return la mappa FormulaUno creata
     */
    public FormulaUnoMap createMap(String path) {
            return createMap(getCoordinatesFromFile(path), getRadius(path));
        }

    /**
     * Crea una mappa FormulaUno a partire da un array di Coordinate e un raggio.
     * @param coors l'array di Coordinate
     * @param radius il raggio della mappa
     * @return la mappa FormulaUno creata
     */
    public FormulaUnoMap createMap(Coordinate[] coors, double radius) {
            return new FormulaUnoMap(radius, coors);
        }
}