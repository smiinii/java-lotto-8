package lotto.domain.generator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.ticket.Lotto;

import java.util.List;

public class UniqueRandomNumberGenerator implements LottoNumberGenerator {

    @Override
    public List<Integer> getNumbers() {
        return Randoms.pickUniqueNumbersInRange(
                Lotto.LOTTO_NUMBER_MIN,
                Lotto.LOTTO_NUMBER_MAX,
                Lotto.LOTTO_NUMBER_COUNT);
    };
}
