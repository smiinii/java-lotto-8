package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputParserTest {

    @Test
    @DisplayName("구입 금액 파싱 검증")
    void parserMoneyTest() {
        // given
        String input = "12";
        // when
        int parserInput = InputParser.parserMoney(input);
        // then
        assertThat(parserInput).isEqualTo(12);
    }

    @Test
    @DisplayName("구입 금액이 빈값이면 예외처리")
    void parserMoneyEmptyTest() {
        // given
        String input = "";
        // when & then
        assertThatThrownBy(() -> InputParser.parserMoney(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력이 비어있습니다.");
    }
}
