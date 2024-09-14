package didattica.cs.unicam.vectorally.Controller;

import didattica.cs.unicam.vectorally.Model.Coordinate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe astratta che gestisce la lettura e il parsing delle coordinate e del raggio da un file
 * per la creazione di mappe di Formula Uno.
 *
 * @author Twinkal Sikri - 119251
 */
public abstract class FormulaUnoMapHandler {

    /**
     * Legge le coordinate da un file e le restituisce come un array di oggetti {@link Coordinate}.
     * La prima riga del file viene ignorata, poiché contiene il raggio della mappa.
     *
     * @param path Il percorso del file da cui leggere le coordinate.
     * @return Un array di {@link Coordinate} letto dal file.
     * @throws RuntimeException Se si verifica un errore nella lettura del file.
     */
    protected Coordinate[] getCoordinatesFromFile(String path) {
        List<Coordinate> coordinatesList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            // Salta la prima riga che contiene il raggio
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                Coordinate coord = parseCoordinate(line);
                coordinatesList.add(coord);
            }
        } catch (IOException e) {
            throw new RuntimeException("Errore nella lettura del file: " + path, e);
        }
        return coordinatesList.toArray(new Coordinate[0]);
    }

    /**
     * Converte una stringa che rappresenta una coordinata in un oggetto {@link Coordinate}.
     * La stringa deve essere nel formato "x, y", dove x e y sono numeri decimali.
     *
     * @param line La stringa contenente la coordinata.
     * @return Un oggetto {@link Coordinate} rappresentante la coordinata.
     * @throws IllegalArgumentException Se il formato della stringa è errato.
     */
    protected Coordinate parseCoordinate(String line) {
        String[] parts = line.split("\\s*,\\s*");
        if (parts.length == 2) {
            return new Coordinate(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
        } else {
            throw new IllegalArgumentException("Formato errato nella riga: " + line);
        }
    }

    /**
     * Legge il raggio della mappa dal file.
     * La prima riga del file deve contenere il raggio, che deve essere un numero decimale valido.
     *
     * @param path Il percorso del file da cui leggere il raggio.
     * @return Il valore del raggio letto dal file.
     * @throws RuntimeException Se si verifica un errore nella lettura del file.
     * @throws IllegalArgumentException Se il formato del raggio nel file non è valido.
     */
    protected double getRadius(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            if (line != null && line.matches("\\d+(\\.\\d+)?")) {
                return Double.parseDouble(line);
            } else {
                throw new IllegalArgumentException("Formato del raggio non valido nel file: " + path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Errore nella lettura del file: " + path, e);
        }
    }
}
