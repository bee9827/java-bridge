package bridge;

public class BridgeMoveResult {
    private BridgeDirection direction;
    private boolean success;

    public BridgeMoveResult(BridgeDirection direction, boolean success) {
        this.direction = direction;
        this.success = success;
    }

    public BridgeDirection getDirection() {
        return direction;
    }

    public boolean isSuccess() {
        return success;
    }
}
