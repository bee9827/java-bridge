package bridge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BridgeDirectionTest {

    @Test
    void valueOf() {
        assertThat(BridgeDirection.valueOf("U")).isEqualTo(BridgeDirection.U);
        assertThat(BridgeDirection.valueOf("D")).isEqualTo(BridgeDirection.D);
    }

    @Test
    void isInstance() {

    }
}