package didattica.cs.unicam.vectorally.Model.Dto;

import java.util.List;

/**
 * Questa classe record contiene informazioni sulla partita di Formula Uno.
 * @param finished true se la partita è finita, false altrimenti
 * @param playerInfo lista di informazioni sui giocatori
 * @param mapInfo informazioni sulla mappa
 *
 * @author Twinkal Sikri - 119251
 */
public record FormulaUnoInfo(boolean finished, List<FormulaUnoPlayerInfo> playerInfo, FormulaUnoMapInfo mapInfo) {

    @Override
    public String toString() {
        return "GameInfo{" +
                "finished=" + finished +
                ", playerInfo=" + playerInfo +
                ", mapInfo=" + mapInfo +
                '}';
    }
}
