package didattica.cs.unicam.vectorally.View;

import didattica.cs.unicam.vectorally.Controller.ControllerFormulaUnoEngine;
import didattica.cs.unicam.vectorally.Model.PlayerStatus;
import didattica.cs.unicam.vectorally.Model.Dto.FormulaUnoInfo;

/**
 * Controlla la vista del gioco di Formula 1, gestendo l'inizializzazione del controller,
 * l'esecuzione del gioco e la stampa dei risultati.
 *
 * @author Twinkal Sikri - 119251
 */
public class FormulaUnoViewController {

    /**
     * Avvia il gioco di Formula 1 leggendo gli argomenti, inizializzando il controller,
     * avviando il gioco e stampando il risultato finale.
     *
     * @param args Gli argomenti della riga di comando per configurare il gioco.
     */
    public void runGame(String[] args) {
        final FormulaUnoViewArgsReader argsReader = new FormulaUnoViewArgsReader(args); // Legge gli argomenti della riga di comando
        final ControllerFormulaUnoEngine controller = initializeController(argsReader); // Inizializza il controller del gioco

        controller.startGame(); // Avvia il gioco
        executeGame(controller); // Esegue il gioco
        printGameResult(controller.getInfo()); // Stampa il risultato finale del gioco
    }

    /**
     * Inizializza il controller del gioco basandosi sugli argomenti forniti.
     * Se non è fornito un percorso della mappa, viene utilizzata una mappa predefinita.
     *
     * @param argsReader Oggetto per leggere gli argomenti della riga di comando.
     * @return Il controller del gioco inizializzato.
     */
    private ControllerFormulaUnoEngine initializeController(FormulaUnoViewArgsReader argsReader) {
        ControllerFormulaUnoEngine controller = new ControllerFormulaUnoEngine(argsReader.getPlayerNumber()); // Crea il controller con il numero di giocatori
        if (argsReader.getMapPath().isEmpty()) {
            addDefaultMap(controller); // Aggiunge una mappa predefinita se non è fornito un percorso della mappa
        } else {
            controller.addMap(argsReader.getMapPath()); // Aggiunge la mappa dal percorso fornito
        }
        return controller;
    }

    /**
     * Aggiunge una mappa predefinita al controller.
     *
     * @param controller Il controller a cui aggiungere la mappa predefinita.
     */
    private void addDefaultMap(ControllerFormulaUnoEngine controller) {
        FormulaUnoDefaultMap defMap = new FormulaUnoDefaultMap(); // Crea una mappa predefinita
        controller.addMap(defMap.getDefaultPoints(), defMap.getDefaultRadius()); // Aggiunge la mappa predefinita al controller
    }

    /**
     * Esegue il gioco fino al termine, aggiornando e stampando lo stato del gioco ad ogni turno.
     *
     * @param controller Il controller del gioco da eseguire.
     */
    private void executeGame(ControllerFormulaUnoEngine controller) {
        FormulaUnoInfo info = controller.getInfo(); // Ottiene le informazioni iniziali del gioco
        System.out.println(info.mapInfo());
        int r = 0;
        while (!info.finished()) { // Continua a eseguire finché il gioco non è finito
            System.out.println("\n\t*** Round " + ++r + " ***");
            printCurrentGameState(info); // Stampa lo stato attuale del gioco
            for(int i = 0; i < info.playerInfo().stream().filter(x -> x.status() == PlayerStatus.ON_TRACK).count(); i++)
                controller.runRound(); // Esegue un turno del gioco
            info = controller.getInfo(); // Aggiorna le informazioni del gioco
        }
    }

    /**
     * Stampa lo stato attuale del gioco.
     *
     * @param info Le informazioni correnti del gioco.
     */
    private void printCurrentGameState(FormulaUnoInfo info) {
        info.playerInfo().forEach(System.out::println); // Stampa le informazioni del gioco
    }

    /**
     * Stampa il risultato finale del gioco.
     *
     * @param info Le informazioni finali del gioco.
     */
    private void printGameResult(FormulaUnoInfo info) {
        System.out.println("\n\t*^*^* GAME OVER *^*^*\n"); // Stampa un messaggio di fine gioco
        printCurrentGameState(info); // Stampa le informazioni finali del gioco
    }
}
