package didattica.cs.unicam.vectorally.Model;

public enum PlayerStrategy {
    WISETURTLE(1),
    SPEEDYTURTLE(2),
    OOGWAY(3),
    RABBIT(4),
    BEEPBEEP(5);

    private final int speedValue;

    PlayerStrategy(int speedValue) {
        this.speedValue = speedValue;
    }

    public int getSpeedValue() {
        return speedValue;
    }
}
