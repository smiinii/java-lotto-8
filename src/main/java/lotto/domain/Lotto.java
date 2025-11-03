package lotto.domain;

import java.util.List;

public class Lotto {

    private final LottoNumber numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = LottoNumber.from(numbers);
    }

    public LottoNumber getNumbers() {
        return numbers;
    }

    public List<Integer> values() {
        return numbers.getNumbers();
    }
}
