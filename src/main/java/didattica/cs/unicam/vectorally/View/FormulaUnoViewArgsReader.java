package didattica.cs.unicam.vectorally.View;

/**
 * Classe che legge gli argomenti passati al programma FormulaUnoView.
 *
 * @author Twinkal Sikri - 119251
 */
public class FormulaUnoViewArgsReader {

    private String mapPath;
    private int playerNumber;

    /**
     * Costruttore della classe FormulaUnoViewArgsReader.
     * @param args gli argomenti passati al programma FormulaUnoView
     */
    public FormulaUnoViewArgsReader(String[] args) {
        mapPath = "";
        playerNumber = 4;
        setPlayerNumber(args);
        setMapPath(args);
    }

    /**
     * Imposta il numero di giocatori.
     * @param args gli argomenti passati al programma FormulaUnoView
     */
    private void setPlayerNumber(String[] args) {
        int index = indexAfter(args, new String[]{"--player-number", "-pn"});
        if(index != -1){
            try{
                this.playerNumber = Integer.parseInt(args[index]);
            }catch(Exception ignored){}
        }
    }

    /**
     * Imposta il percorso della mappa.
     * @param args gli argomenti passati al programma FormulaUnoView (path del file con la il raggio
     *             e le coordinate della mappa)
     */
    private void setMapPath(String[] args) {
        int index = indexAfter(args, new String[]{"--map-path", "-mp"});
        if(index != -1){
            this.mapPath = args[index];
        }
    }

    /**
     * Restituisce il numero di giocatori.
     * @return il numero di giocatori
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Restituisce il percorso della mappa.
     * @return il percorso della mappa
     */
    public String getMapPath() {
        return mapPath;
    }

    /**
     * Restituisce l'indice successivo al target.
     * @param args gli argomenti passati al programma FormulaUnoView
     * @param target il target
     * @return l'indice successivo al target
     */
    protected int indexAfter(String[] args, String[] target) {
        for (int i = 0; i < args.length; i++) {
            for (String t : target){
                if(t.equals(args[i]) && i + 1 < args.length)
                    return i + 1;
            }
        }
        return -1;
    }
}
