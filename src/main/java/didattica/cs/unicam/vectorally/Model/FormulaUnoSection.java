package didattica.cs.unicam.vectorally.Model;

import java.util.List;

/**
 * La classe FormulaUnoSection rappresenta una sezione di una pista di Formula 1.
 * Ogni sezione è definita da un punto di inizio, un punto di fine e un raggio che determina l'area della sezione.
 * Include metodi per determinare se un punto si trova all'interno della sezione e per calcolare
 * i punti di partenza per i giocatori.
 *
 * @author Twinkal Sikri - 119251
 */
public class FormulaUnoSection {

    private final double radius;
    private final Coordinate start;
    private final Coordinate end;
    private final Coordinate vertexStartLeft;
    private final Coordinate vertexStartRight;
    private final Coordinate vertexEndLeft;
    private final Coordinate vertexEndRight;

    /**
     * Costruisce una nuova sezione della pista con i punti di inizio e fine e il raggio specificati.
     * Calcola i vertici della sezione basati sui punti di inizio e fine.
     *
     * @param start Il punto di inizio della sezione.
     * @param end Il punto di fine della sezione.
     * @param radius Il raggio della sezione.
     */
    public FormulaUnoSection(Coordinate start, Coordinate end, double radius) {
        this.radius = radius;
        this.start = start;
        this.end = end;
        Coordinate dir = calculateDirection(start, end);
        vertexStartLeft = dir.sum(start);
        vertexStartRight = dir.revertSign().sum(start);
        dir = calculateDirection(end, start);
        vertexEndRight = dir.sum(end);
        vertexEndLeft = dir.revertSign().sum(end);
    }

    /**
     * Restituisce il punto di inizio della sezione.
     *
     * @return Il punto di inizio della sezione.
     */
    public Coordinate getStart() {
        return start;
    }

    /**
     * Restituisce il punto di fine della sezione.
     *
     * @return Il punto di fine della sezione.
     */
    public Coordinate getEnd() {
        return end;
    }

    /**
     * Determina se una coordinata si trova all'interno della sezione.
     * La sezione è rappresentata come un poligono definito dai vertici calcolati e il raggio.
     *
     * @param coordinate La coordinata da verificare.
     * @return true se la coordinata si trova all'interno della sezione, false altrimenti.
     */
    public boolean isIntoSection(Coordinate coordinate) {
        if(coordinate.getDistance(start) <= radius || coordinate.getDistance(end) <= radius){
            return true;
        }
        double areaABCD = getArea(vertexStartLeft, vertexStartRight, vertexEndRight, vertexEndLeft);
        double areaABE = getArea(vertexStartLeft, vertexStartRight, coordinate);
        double areaBCE = getArea(vertexStartRight, vertexEndRight, coordinate);
        double areaCDE = getArea(vertexEndRight, vertexEndLeft, coordinate);
        double areaDAE = getArea(vertexEndLeft, vertexStartLeft, coordinate);
        return Math.abs(areaABCD - (areaABE + areaBCE + areaCDE + areaDAE)) < 1e-6;
    }

    /**
     * Calcola l'area di un poligono definito da quattro coordinate.
     * Il poligono è suddiviso in due triangoli e l'area totale è la somma delle aree di questi due triangoli.
     *
     * @param A Il primo vertice del poligono.
     * @param B Il secondo vertice del poligono.
     * @param C Il terzo vertice del poligono.
     * @param D Il quarto vertice del poligono.
     * @return L'area del poligono.
     */
    private double getArea(Coordinate A, Coordinate B, Coordinate C, Coordinate D) {
        return getArea(A, B, D) + getArea(B, C, D);
    }

    /**
     * Calcola l'area di un triangolo definito da tre coordinate.
     *
     * @param A Il primo vertice del triangolo.
     * @param B Il secondo vertice del triangolo.
     * @param C Il terzo vertice del triangolo.
     * @return L'area del triangolo.
     */
    private double getArea(Coordinate A, Coordinate B, Coordinate C) {
        double a = A.getDistance(B);
        double b = B.getDistance(C);
        double c = C.getDistance(A);
        double p = (a + b + c) / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    /**
     * Calcola la direzione tra due punti e restituisce un vettore con la lunghezza del raggio.
     * La direzione è normalizzata e scalata in base al raggio della sezione.
     *
     * @param a Il punto di partenza.
     * @param b Il punto di arrivo.
     * @return Il vettore direzionale tra i due punti.
     */
    private Coordinate calculateDirection(Coordinate a, Coordinate b) {
        double py = b.getX() - a.getX();
        double px = -(b.getY() - a.getY());
        double len = Math.sqrt(px * px + py * py);
        double unitPx = (px / len) * radius;
        double unitPy = (py / len) * radius;
        return new Coordinate(unitPx, unitPy);
    }

    /**
     * Calcola i punti di partenza per un dato numero di giocatori lungo la sezione.
     * La sezione deve avere abbastanza punti per tutti i giocatori.
     *
     * @param playerCount Il numero di giocatori.
     * @return Un array di coordinate che rappresentano i punti di partenza per i giocatori.
     * @throws IllegalArgumentException Se il numero di giocatori è maggiore del numero di punti disponibili.
     */
    public Coordinate[] getStartingPointsForPlayers(int playerCount) {
        Coordinate[] startingPoints = new Coordinate[playerCount];
        List<Coordinate> steps = start.getIntegerRoutePoints(end);
        if(steps.size() < playerCount)
            throw new IllegalArgumentException("Mappa troppo stretta per questo numero di giocatori.");
        for (int i = 0; i < playerCount; i++) {
            startingPoints[i] = start.sum(steps.get(i));
        }
        return startingPoints;
    }
    
    @Override
    public String toString(){
        return "{ start : " + start + ", end : " + end + " }";
    }
}
