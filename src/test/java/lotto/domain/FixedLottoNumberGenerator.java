package lotto.domain;

import java.util.List;

public class FixedLottoNumberGenerator implements LottoNumberGenerator {

    private final List<Integer> fixedNumbers;

    public FixedLottoNumberGenerator(List<Integer> fixedNumbers) {
        this.fixedNumbers = fixedNumbers;
    }

    @Override
    public List<Integer> getNumbers() {
        return fixedNumbers;
    }
}
