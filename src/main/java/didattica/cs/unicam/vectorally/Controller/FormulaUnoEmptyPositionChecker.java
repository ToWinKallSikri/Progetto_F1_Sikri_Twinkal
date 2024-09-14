package didattica.cs.unicam.vectorally.Controller;

import didattica.cs.unicam.vectorally.Model.Coordinate;
import didattica.cs.unicam.vectorally.Model.PositionedGameObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe ha la responsabilità
 *  di controllare le attuali posizioni dei giocatori
 *  e assicurarsi che una data posizione sia libera
 *
 * @author Twinkal Sikri - 119251
 */
public class FormulaUnoEmptyPositionChecker {

    List<PositionedGameObject> objects;

    /**
     * Costruttore della classe, inizializza la lista degli oggetti
     */
    public FormulaUnoEmptyPositionChecker() {
        this.objects = new ArrayList<>();
    }

    /**
     * Verifica se una determinata posizione è vuota
     * @param c la posizione da verificare
     * @return true se la posizione è vuota, altrimenti false
     */
    public boolean isEmpty(Coordinate c) {
        return objects.stream().noneMatch(x -> c.equals(x.getCurrentPosition()));
    }

    /**
     * Aggiunge un oggetto alla lista degli oggetti
     * @param gameObject l'oggetto da aggiungere
     */
    public void addGameObject(PositionedGameObject gameObject) {
        if(!(gameObject == null || objects.contains(gameObject)))
            objects.add(gameObject);
    }
}
