package bridge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final List<BridgeDirection> bridge;
    private final List<BridgeMoveResult> moveResult = new ArrayList<>();
    private int retryCount = 0;

    public BridgeGame(List<String> bridge) {
        validate(bridge);
        this.bridge = toBridgeValue(bridge);
    }

    private void validate(List<String> bridge) {
        for (String direction : bridge) {
            if(!BridgeDirection.isInstance(direction)){
                throw new IllegalArgumentException("Invalid direction: " + direction);
            }
        }
    }

    private List<BridgeDirection> toBridgeValue(List<String> bridge) {
        return bridge.stream()
                .map(BridgeDirection::valueOf)
                .collect(Collectors.toList());
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(BridgeDirection direction) {
        int location = getLocation();
        if (location > bridge.size()) {
            throw new IllegalStateException("더이상 움직일 수 없습니다.");
        }
        moveResult.add(new BridgeMoveResult(direction, bridge.get(location).equals(direction)));
    }

    private int getLocation() {
        return moveResult.size();
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        moveResult.remove(moveResult.size() - 1);
        retryCount++;
    }

    public boolean canContinue() {
        return moveResult.get(moveResult.size() - 1)
                .isSuccess();
    }

    public List<BridgeMoveResult> getMoveResult() {
        return Collections.unmodifiableList(moveResult);
    }

    public int getRetryCount() {
        return retryCount;
    }
}
