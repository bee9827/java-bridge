package bridge;

import java.util.ArrayList;
import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {
    public static final String ERROR_PREFIX = "[ERROR] ";

    public void printStartInstruction() {
        System.out.println("다리 건너기 게임을 시작합니다.");
        System.out.println();
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(List<BridgeMoveResult> moveResults) {
        List<String> upResult = getUpResult(moveResults);
        List<String> downResult = getDownResult(moveResults);

        System.out.printf("[ %s ]%n", String.join(" | ", upResult));
        System.out.printf("[ %s ]%n", String.join(" | ", downResult));
        System.out.println();
    }

    private List<String> getUpResult(List<BridgeMoveResult> moveResults) {
        List<String> upResult = new ArrayList<>();
        for (BridgeMoveResult moveResult : moveResults) {
            if (moveResult.getDirection().equals(BridgeDirection.U)) {
                upResult.add(movePrintFormat(moveResult.isSuccess()));
                continue;
            }
            upResult.add(" ");
        }
        return upResult;
    }

    private List<String> getDownResult(List<BridgeMoveResult> moveResults) {
        List<String> downResult = new ArrayList<>();
        for (BridgeMoveResult moveResult : moveResults) {
            if (moveResult.getDirection().equals(BridgeDirection.D)) {
                downResult.add(movePrintFormat(moveResult.isSuccess()));
                continue;
            }
            downResult.add(" ");
        }
        return downResult;
    }

    private String movePrintFormat(boolean isSuccess) {
        if (isSuccess) {
            return "O";
        }
        return "X";
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(List<BridgeMoveResult> moveResult, boolean finished, int retryCount) {
        System.out.println("최종 게임 결과");
        printMap(moveResult);
        System.out.printf("게임 성공 여부: %s%n", getFinishInstruction(finished));
        System.out.printf("총 시도한 횟수: %d%n", retryCount);
    }

    private String getFinishInstruction(boolean finished) {
        if (finished) {
            return "성공";
        }
        return "실패";
    }

    public void printErrorMessage(String message){
        System.out.printf("%s%s%n",ERROR_PREFIX,message);
    }
}
