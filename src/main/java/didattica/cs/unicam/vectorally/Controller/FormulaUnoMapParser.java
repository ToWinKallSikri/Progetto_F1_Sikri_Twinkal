package didattica.cs.unicam.vectorally.Controller;

import didattica.cs.unicam.vectorally.Model.Coordinate;

/**
 * Classe che estende {@link FormulaUnoMapHandler} e implementa l'interfaccia {@link MapParser}.
 * Gestisce il parsing della mappa da un file o da un array di coordinate.
 *
 * @author Twinkal Sikri - 119251
 */
public class FormulaUnoMapParser extends FormulaUnoMapHandler implements MapParser {

    private static final double MIN_DISTANCE = 5.0; // Distanza minima tra due punti

    /**
     * Effettua il parsing della mappa da un file. Legge il raggio e le coordinate dal file e poi chiama il metodo
     * {@link #parseMapFromCoordinates(Coordinate[])} per elaborare le coordinate.
     *
     * @param path Il percorso del file contenente le informazioni della mappa.
     */
    @Override
    public void parseMapFromFile(String path) {
        getRadius(path); // Legge il raggio dalla prima riga del file
        Coordinate[] coordinates = getCoordinatesFromFile(path); // Legge le coordinate dal file
        parseMapFromCoordinates(coordinates); // Elabora le coordinate
    }

    /**
     * Effettua il parsing della mappa da un array di coordinate. Verifica che ci siano almeno 3 coordinate e che
     * nessuna coppia di coordinate sia troppo vicina tra loro.
     *
     * @param coordinates L'array di coordinate da cui creare la mappa.
     * @throws IllegalArgumentException Se ci sono meno di 3 coordinate o se due coordinate
     * sono troppo vicine tra loro.
     */
    @Override
    public void parseMapFromCoordinates(Coordinate[] coordinates) {
        if (coordinates.length < 3) {
            throw new IllegalArgumentException("La mappa deve contenere almeno 3 coordinate.");
        }
        for (int i = 0; i < coordinates.length - 1; i++) {
            if (coordinates[i].getDistance(coordinates[i + 1]) < MIN_DISTANCE) {
                throw new IllegalArgumentException("Le coordinate " + coordinates[i] + " e " + coordinates[i + 1] +
                        " sono troppo vicine (distanza minore di " + MIN_DISTANCE + ")" + "o sovrapposte.");
            }
        }
    }
}
