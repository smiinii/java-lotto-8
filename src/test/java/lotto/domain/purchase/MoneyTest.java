package lotto.domain.purchase;

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
}
