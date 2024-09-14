package didattica.cs.unicam.vectorally.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Questa classe rappresenta una mappa del gioco di Formula Uno, composta da una serie di sezioni
 * e una linea di arrivo. La mappa tiene traccia delle coordinate dei punti chiave e della larghezza
 * della pista, rappresentata dal raggio.
 *
 * @author Twinkal Sikri - 119251
 */
public class FormulaUnoMap {
    private final double radius;
    private final FormulaUnoSection[] sections;
    private final FormulaUnoSection finishLineSection; // Sezione per la linea di arrivo
    private final Coordinate[] coordinates;

    /**
     * Costruttore che inizializza la mappa con il raggio e le coordinate fornite.
     *
     * @param radius Il raggio della pista.
     * @param coors Le coordinate che definiscono i punti chiave della mappa.
     */
    public FormulaUnoMap(double radius, Coordinate[] coors) {
        this.coordinates = coors;
        this.radius = radius;
        this.sections = createSections(coors, radius);
        this.finishLineSection = createFinishLineSection();
    }

    /**
     * Verifica se una determinata coordinata è all'interno della mappa nella sezione specificata o nelle sue vicinanze.
     *
     * @param coor La coordinata da verificare.
     * @param lastSection L'indice della sezione precedente.
     * @return true se la coordinata è all'interno della mappa, false altrimenti.
     * @throws IllegalArgumentException se la sezione specificata non esiste.
     */
    public int isIntoMap(Coordinate coor, int lastSection) {
        if (lastSection < 0 || lastSection >= sections.length) {
            throw new IllegalArgumentException(lastSection + " section not found :(");
        }
        if( lastSection + 1 == sections.length ){
            if(isIntoFinalSection(coor))
                return sections.length;
        }else{
            if(sections[lastSection + 1].isIntoSection(coor))
                return lastSection + 1;
        }
        if(sections[lastSection].isIntoSection(coor))
            return lastSection;
        if((lastSection > 0 && sections[lastSection - 1].isIntoSection(coor)))
            return lastSection - 1;
        return -1;
    }

    /**
     * Restituisce la sezione della mappa all'indice specificato.
     *
     * @param i L'indice della sezione.
     * @return La sezione corrispondente all'indice.
     * @throws IllegalArgumentException se l'indice specificato non esiste.
     */
    public FormulaUnoSection getSection(int i) {
        if (i < 0 || i >= sections.length) {
            throw new IllegalArgumentException("Sezione non esistente.");
        }
        return sections[i];
    }

    /**
     * Restituisce una lista di tutte le sezioni della mappa.
     *
     * @return Una lista contenente tutte le sezioni della mappa.
     */
    public List<FormulaUnoSection> getSections() {
        return new ArrayList<>(Arrays.asList(sections));
    }

    /**
     * Crea la sezione per la linea di arrivo basandosi sulle ultime coordinate.
     *
     * @return La sezione per la linea di arrivo.
     * @throws IllegalStateException se non ci sono abbastanza coordinate per creare la linea di arrivo.
     */
    private FormulaUnoSection createFinishLineSection() {
        if (coordinates.length >= 2) {
            Coordinate start = coordinates[coordinates.length - 2];
            Coordinate end = coordinates[coordinates.length - 1];
            Coordinate newEnd = start.getVector(end).sum(end);
            return new FormulaUnoSection(end, newEnd, radius);
        } else {
            throw new IllegalStateException("Non ci sono abbastanza coordinate per creare la linea di arrivo.");
        }
    }

    /**
     * Verifica se un determinato punto è sulla linea di arrivo.
     *
     * @param point Il punto da verificare.
     * @return true se il punto è sulla linea di arrivo, false altrimenti.
     */
    public boolean isIntoFinalSection(Coordinate point) {
        return finishLineSection.isIntoSection(point);
    }

    /**
     * Crea le sezioni della mappa basandosi sulle coordinate fornite.
     *
     * @param coors Le coordinate che definiscono i punti chiave della mappa.
     * @param radius Il raggio della pista.
     * @return Un array di sezioni della mappa.
     */
    private FormulaUnoSection[] createSections(Coordinate[] coors, double radius) {
        FormulaUnoSection[] sections = new FormulaUnoSection[coors.length - 1];
        for (int i = 0; i < coors.length - 1; i++) {
            sections[i] = new FormulaUnoSection(coors[i], coors[i + 1], radius);
        }
        return sections;
    }

    /**
     * Restituisce la velocità massima consentita sulla mappa.
     *
     * @return La velocità massima consentita.
     */
    public double getMapRadius() {
        return this.radius;
    }

    /**
     * Ritorna una rappresentazione testuale della mappa, includendo la larghezza della pista e le coordinate.
     *
     * @return Una stringa che rappresenta la mappa.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Larghezza tracciato: ").append(radius).append("\n");
        sb.append("Coordinate:\n");
        for (Coordinate coor : coordinates) {
            sb.append(coor).append("\n");
        }
        return sb.toString();
    }
}
