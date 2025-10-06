package bridge;

public enum BridgeValue {
    UP(1, "U"),
    DOWN(0, "D"),
    ;
    private int value;
    private String name;

    private BridgeValue(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
