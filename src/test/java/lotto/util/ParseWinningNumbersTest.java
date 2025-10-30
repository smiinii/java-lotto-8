package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    @DisplayName("구분자가 쉼표가 아니면 예외처리")
    void validateDelimiterTest() {
        // given
        String input = "1; 2; 3";
        // when & then
        assertThatThrownBy(() -> InputParser.parseWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자는");
    }

    @Test
    @DisplayName("구분자가 쉼표면 성공")
    void validateDecimalTest2() {
        // given
        String input = "1, 2, 3";
        // when & then
        assertThat(input).isEqualTo("1, 2, 3");
    }
}
