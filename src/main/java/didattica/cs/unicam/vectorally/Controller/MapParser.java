package didattica.cs.unicam.vectorally.Controller;

import didattica.cs.unicam.vectorally.Model.Coordinate;

/**
 * Questa interfaccia ha la responsabilità di definire i metodi per il parsing di una mappa.
 *
 * @author Twinkal Sikri - 119251
 */
public interface MapParser {
    /**
     * Parsa una mappa da un file di testo.
     * @param path il percorso del file di testo
     */
    void parseMapFromFile(String path);

    /**
     * Fa il parsing di una mappa a partire da un array di Coordinate.
     * @param coordinates l'array di Coordinate
     */
    void parseMapFromCoordinates(Coordinate[] coordinates);
}
