package didattica.cs.unicam.vectorally.Controller;

import didattica.cs.unicam.vectorally.Model.Dto.FormulaUnoInfo;
import didattica.cs.unicam.vectorally.Model.Dto.FormulaUnoMapInfo;
import didattica.cs.unicam.vectorally.Model.Dto.FormulaUnoPlayerInfo;
import didattica.cs.unicam.vectorally.Model.FormulaUnoMap;
import didattica.cs.unicam.vectorally.Model.FormulaUnoMove;
import didattica.cs.unicam.vectorally.Model.FormulaUnoPlayer;
import didattica.cs.unicam.vectorally.Model.PlayerStatus;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

/**
 * La classe FormulaUnoInfoBuilder costruisce un oggetto FormulaUnoInfo contenente le informazioni
 * attuali sullo stato del gioco, inclusi i dettagli sui giocatori e sulla mappa.
 * Questa classe implementa l'interfaccia GameInfoBuilder.
 *
 * @author Twinkal Sikri - 119251
 */
public class FormulaUnoInfoBuilder implements GameInfoBuilder<FormulaUnoPlayer[], FormulaUnoInfo> {

    private final FormulaUnoMap map;

    /**
     * Costruisce un nuovo FormulaUnoInfoBuilder con la mappa specificata.
     *
     * @param map La mappa di Formula Uno utilizzata per ottenere le informazioni della mappa.
     */
    public FormulaUnoInfoBuilder(FormulaUnoMap map) {
        this.map = map;
    }

    /**
     * Costruisce un oggetto FormulaUnoInfo che rappresenta lo stato attuale del gioco.
     *
     * @param players L'array di giocatori per i quali raccogliere le informazioni.
     * @return Un oggetto FormulaUnoInfo contenente le informazioni sui giocatori e sulla mappa.
     */
    @Override
    public FormulaUnoInfo buildInfo(FormulaUnoPlayer[] players) {
        Comparator<FormulaUnoPlayerInfo> comparator = Comparator.comparingInt(x -> x.status() != PlayerStatus.FINISHED ? Integer.MAX_VALUE : x.finishPosition());
        // Crea una lista di informazioni sui giocatori
        List<FormulaUnoPlayerInfo> playerInfoList = Arrays.stream(players)
                .map(player -> new FormulaUnoPlayerInfo(
                        player.getName(),
                        player.getCurrentPosition(),
                        player.getVelocity(),
                        player.getStatus(),
                        player.getFinishPosition(),
                        player.getFinishTime()))
                .sorted(comparator)
                .collect(Collectors.toList());
        FormulaUnoMapInfo mapInfo = new FormulaUnoMapInfo(map.getSections());
        boolean finished = playerInfoList.stream().allMatch(info -> info.status() != PlayerStatus.ON_TRACK);
        return new FormulaUnoInfo(finished, playerInfoList, mapInfo);
    }
}
