package bridge;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class BridgeMakerTest {

    @InjectMocks
    private BridgeMaker bridgeMaker;

    @Test
    void makeBridge() {
        bridgeMaker = new BridgeMaker(new UpGenerator());
        List<String> upBridge = bridgeMaker.makeBridge(3);
        bridgeMaker = new BridgeMaker(new DownGenerator());
        List<String> downBridge = bridgeMaker.makeBridge(3);


        assertThat(upBridge).hasSize(3);
        assertThat(downBridge).hasSize(3);
        for (int i = 0; i < upBridge.size(); i++) {
            assertThat(upBridge.get(0)).isEqualTo("U");
            assertThat(downBridge.get(i)).isEqualTo("D");
        }

    }

    private class UpGenerator implements BridgeNumberGenerator {
        @Override
        public int generate() {
            return 1;
        }
    }
    private class DownGenerator implements BridgeNumberGenerator {
        @Override
        public int generate() {
            return 0;
        }
    }
}