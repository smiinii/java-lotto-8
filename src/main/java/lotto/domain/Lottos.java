package lotto.domain;

import lotto.domain.winning.WinningNumbers;

import java.util.EnumMap;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public EnumMap<Rank, Integer> matchResult(WinningNumbers winningNumbers) {
        EnumMap<Rank, Integer> result = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
        for (Lotto lotto : lottos) {
            Rank rank = winningNumbers.match(lotto);
            result.put(rank, result.get(rank) + 1);
        }
        return result;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int getLottoCount() {
        return lottos.size();
    }
}
