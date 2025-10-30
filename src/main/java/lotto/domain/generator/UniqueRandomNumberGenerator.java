package lotto.domain.generator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.rule.LottoRule;

import java.util.List;

public class UniqueRandomNumberGenerator implements LottoNumberGenerator {

    @Override
    public List<Integer> getNumbers() {
        return Randoms.pickUniqueNumbersInRange(
                LottoRule.MIN,
                LottoRule.MAX,
                LottoRule.COUNT);
    };
}
