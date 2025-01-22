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
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";

        String statusColor = status == PlayerStatus.CRASHED ? RED : (status == PlayerStatus.FINISHED ? GREEN : "");

        return "PlayerInfo { " +
                "Name = '" + statusColor + name + RESET + '\'' +
                ", Strategy = " + statusColor + strategy + RESET +
                ", Status = " + statusColor + status + RESET +
                (status != PlayerStatus.CRASHED ?
                        ", CurrentPosition = " + currentPosition +
                                ", Velocity = " + velocity +
                                (status == PlayerStatus.FINISHED ?
                                        ", finishPosition = " + statusColor + finishPosition + RESET +
                                                ", finishTime = " + finishTime +
                                                " }" : "") : " }");
    }
}


