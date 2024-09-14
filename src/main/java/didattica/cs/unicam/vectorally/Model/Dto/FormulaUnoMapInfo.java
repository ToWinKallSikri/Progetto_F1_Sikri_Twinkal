package didattica.cs.unicam.vectorally.Model.Dto;

import didattica.cs.unicam.vectorally.Model.FormulaUnoSection;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Questa classe record contiene informazioni sulla mappa del gioco Formula Uno.
 * @param sections lista di sezioni della mappa
 *
 * @author Twinkal Sikri - 119251
 */
public record FormulaUnoMapInfo(List<FormulaUnoSection> sections) {

    @Override
    public String toString() {
        return "\t*** Map Info ***" + 
            IntStream.range(0, sections.size()).boxed().map(x -> "Section n° "  + (x + 1) + " " + sections.get(x)).reduce("", (a, b) -> a + "\n" + b);
    }
}


