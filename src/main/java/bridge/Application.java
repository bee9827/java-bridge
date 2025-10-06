package bridge;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        Controller controller = new Controller(inputView,outputView,bridgeMaker);

        controller.run();
    }
}
