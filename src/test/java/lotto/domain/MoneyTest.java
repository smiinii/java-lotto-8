package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    @Test
    @DisplayName("양의 정수가 아니면 예외처리")
    void validateNegativeTest() {
        // given & when & then
        assertThatThrownBy(() -> Money.of(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양의 정수가 아닙니다.");
    }

    @Test
    @DisplayName("양의 정수면 성공")
    void validatePositiveTest() {
        // given
        Money money = Money.of(1000);
        // when & then
        assertThat(money.getMoney()).isEqualTo(1000);
    }

    @Test
    @DisplayName("로또 개당 가격으로 나누어 떨어지지 않으면 예외처리")
    void validateDivisibleByLottoPriceTest() {
        // given & when & then
        assertThatThrownBy(() -> Money.of(1500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("딱 나누어 떨어져야 합니다.");
    }

    @Test
    @DisplayName("로또 개당 가격으로 나누어 떨어지면 성공")
    void validateDivisibleByLottoPriceTest2() {
        // given
        Money money = Money.of(2000);
        // when & then
        assertThat(money.getMoney()).isEqualTo(2000);
    }
}
