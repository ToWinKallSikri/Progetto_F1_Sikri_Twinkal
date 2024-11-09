package didattica.cs.unicam.vectorally.Model.Dto;

import didattica.cs.unicam.vectorally.Model.PlayerStatus;
import didattica.cs.unicam.vectorally.Model.Coordinate;

/**
 * Questa classe record contiene informazioni sui giocatori di Formula Uno.
 *
 * @param name            nome del giocatore
 * @param currentPosition posizione corrente del giocatore
 * @param velocity        velocità del giocatore
 * @param status          stato del giocatore
 * @param finishPosition  posizione in cui il giocatore ha finito la gara
 * @param finishTime      tempo impiegato dal giocatore per finire la gara
 * @param strategy
 * @author Twinkal Sikri - 119251
 */
public record FormulaUnoPlayerInfo(String name, Coordinate currentPosition, Coordinate velocity, PlayerStatus status,
                                   int finishPosition, int finishTime, String strategy) {

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "name='" + name + '\'' +
                ", currentPosition=" + currentPosition +
                ", velocity=" + velocity +
                ", status=" + status + (status == PlayerStatus.FINISHED ?
                                        ", finishPosition=" + finishPosition +
                                        ", finishTime=" + finishTime +
                                        ", strategy=" + strategy +
                                        '}' : "");
    }
}


