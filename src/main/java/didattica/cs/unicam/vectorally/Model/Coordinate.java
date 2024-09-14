package didattica.cs.unicam.vectorally.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Questa classe rappresenta una coordinata bidimensionale (x, y).
 * Fornisce metodi per operazioni geometriche tra coordinate.
 *
 * @author Twinkal Sikri - 119251
 */
public class Coordinate {
    private final double x;
    private final double y;

    /**
     * Costruisce una nuova coordinata con i valori specificati.
     *
     * @param x la coordinata x
     * @param y la coordinata y
     */
    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Restituisce una nuova coordinata con i segni invertiti rispetto a questa coordinata.
     *
     * @return una nuova coordinata con i segni invertiti
     */
    public Coordinate revertSign() {
        return new Coordinate(-x, -y);
    }

    /**
     * Somma questa coordinata con un'altra e restituisce il risultato.
     *
     * @param other la coordinata da sommare
     * @return la somma delle due coordinate
     */
    public Coordinate sum(Coordinate other) {
        return new Coordinate(x + other.x, y + other.y);
    }

    /**
     * Calcola la distanza euclidea tra questa coordinata e un'altra.
     *
     * @param other la coordinata di destinazione
     * @return la distanza euclidea tra le due coordinate
     */
    public double getDistance(Coordinate other) {
        double px = other.x - this.x;
        double py = other.y - this.y;
        return Math.sqrt(px * px + py * py);
    }

    /**
     * Restituisce il vettore che punta da questa coordinata all'altra coordinata specificata.
     *
     * @param other la coordinata di destinazione
     * @return il vettore come nuova coordinata
     */
    public Coordinate getVector(Coordinate other) {
        return new Coordinate(other.x - this.x, other.y - this.y);
    }

    /**
     * Converte le coordinate x e y in valori interi e restituisce una nuova coordinata.
     *
     * @return una nuova coordinata con valori interi
     */
    public Coordinate castToInteger() {
        int ix = (int)this.x;
        int iy = (int)this.y;
        return new Coordinate(ix, iy);
    }

    /**
     * Restituisce una rappresentazione testuale della coordinata.
     *
     * @return una stringa che rappresenta la coordinata
     */
    @Override
    public String toString() {
        return "{'x': " + x + ", 'y': " + y + "}";
    }

    /**
     * Restituisce il valore della coordinata x.
     *
     * @return il valore di x
     */
    public double getX() {
        return x;
    }

    /**
     * Restituisce il valore della coordinata y.
     *
     * @return il valore di y
     */
    public double getY() {
        return y;
    }

    /**
     * Calcola i punti intermedi di un percorso che va da questa coordinata all'altra.
     * Il percorso è rappresentato come una lista di coordinate con valori interi.
     *
     * @param other la coordinata di destinazione
     * @return una lista di coordinate intere che rappresentano il percorso
     */
    public List<Coordinate> getIntegerRoutePoints(Coordinate other) {
        List<Coordinate> pathPoints = new ArrayList<>();
        Coordinate vector = other.getVector(this).castToInteger();
        while(!vector.equals(new Coordinate(0, 0))){
            Coordinate newVector = new Coordinate(
                    vector.getX() > 0 ? -1 : vector.getX() < 0 ? 1 : 0,
                    vector.getY() > 0 ? -1 : vector.getY() < 0 ? 1 : 0
            );
            pathPoints.add(newVector);
            vector = vector.sum(newVector);
        }
        return pathPoints;
    }

    /**
     * Verifica se questa coordinata è uguale a un'altra.
     *
     * @param o l'oggetto da confrontare
     * @return true se le coordinate sono uguali, altrimenti false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Double.compare(x, that.x) == 0 && Double.compare(y, that.y) == 0;
    }

    /**
     * Calcola il codice hash per questa coordinata.
     *
     * @return il codice hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
