package bridge;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {
    private static final int MAX_SIZE = 20;
    private static final int MIN_SIZE = 3;
    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    /**
     * @param size 다리의 길이
     * @return 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현해야 한다.
     */
    public List<String> makeBridge(int size) {
        validateSize(size);
        List<String> bridge = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int value = bridgeNumberGenerator.generate();
            addValue(bridge, value);
        }
        return bridge;
    }

    private void validateSize(int size) {
        if (size < MIN_SIZE || size > MAX_SIZE) {
            throw new IllegalArgumentException(String.format("Size must be between %d and %d", MIN_SIZE, MAX_SIZE));
        }
    }

    private void addValue(List<String> bridge, int value) {
        if (value == BridgeDirection.U.getValue()) {
            bridge.add(BridgeDirection.U.toString());
        }
        if (value == BridgeDirection.D.getValue()) {
            bridge.add(BridgeDirection.D.toString());
        }
    }
}
