package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputParserTest {

    @Test
    @DisplayName("양수 입력 시 파싱 성공")
    void parseMoneyTest() {
        // given
        String input = "12";
        // when
        int parserInput = InputParser.parseMoney(input);
        // then
        assertThat(parserInput).isEqualTo(12);
    }

    @Test
    @DisplayName("음수 입력 시 파싱 성공")
    void parseMoneyTest2() {
        // given
        String input = "-12";
        // when
        int parserInput = InputParser.parseMoney(input);
        // then
        assertThat(parserInput).isEqualTo(-12);
    }

    @Test
    @DisplayName("구입 금액이 빈값이면 예외처리")
    void parseMoneyEmptyTest() {
        // given
        String input = "";
        // when & then
        assertThatThrownBy(() -> InputParser.parseMoney(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력이 비어있습니다.");
    }

    @Test
    @DisplayName("구입 금액이 숫자가 아니면 예외처리")
    void parseMoneyValidateDigitTest() {
        // given
        String input = "a";
        // when & then
        assertThatThrownBy(() -> InputParser.parseMoney(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자가 아닙니다.");
    }
}
