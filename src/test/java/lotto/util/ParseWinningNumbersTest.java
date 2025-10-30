package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParseWinningNumbersTest {

    @Test
    @DisplayName("당첨 번호가 빈값이면 예외처리")
    void validateEmptyTest() {
        // given & when & then
        assertThatThrownBy(() -> InputParser.parseWinningNumbers(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력이 비어있습니다.");
    }
}
