package bridge;

import java.util.function.Supplier;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeMaker bridgeMaker;

    public Controller(InputView inputView, OutputView outputView, BridgeMaker bridgeMaker) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeMaker = bridgeMaker;
    }

    public void run() {
        outputView.printStartInstruction();

        BridgeGame bridgeGame = (BridgeGame) runWithRetry(() ->
                getBridgeGame(inputView.readBridgeSize()));

        runWithRetry(() -> playGame(bridgeGame));
        outputView.printResult(
                bridgeGame.getMoveResult(),
                bridgeGame.isFinished(),
                bridgeGame.getRetryCount());

    }

    private BridgeGame getBridgeGame(int bridgeSize) {
        return new BridgeGame(bridgeMaker.makeBridge(bridgeSize));
    }

    private void playGame(BridgeGame bridgeGame) {
        while(bridgeGame.canContinue() || retry(bridgeGame)){
            String move = inputView.readMoving();
            BridgeDirection direction = BridgeDirection.valueOf(move);
            bridgeGame.move(direction);
            outputView.printMap(bridgeGame.getMoveResult());
        }
    }

    private boolean retry(BridgeGame bridgeGame) {
        if (bridgeGame.canRetry() && (boolean) runWithRetry(inputView::readToRetry)){
            bridgeGame.retry();
            return true;
        }
        return false;
    }

    private Object runWithRetry(Supplier supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException illegalArgumentException) {
                outputView.printErrorMessage(illegalArgumentException.getMessage());
            }
        }
    }

    private void runWithRetry(Runnable runnable){
        while (true) {
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException illegalArgumentException) {
                outputView.printErrorMessage(illegalArgumentException.getMessage());
            }
        }
    }
}
