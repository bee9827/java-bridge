package bridge;

import java.util.Arrays;

public enum BridgeDirection {
    U(1, "UP"),
    D(0, "DOWN"),
    ;
    private final int value;
    private final String label;

    BridgeDirection(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static boolean isInstance(String name) {
        return Arrays.stream(BridgeDirection.values())
                .anyMatch(bridgeValue -> bridgeValue.name().equals(name));
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
