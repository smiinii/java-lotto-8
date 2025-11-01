package lotto.domain.result;


import lotto.domain.purchase.Money;
import lotto.domain.ticket.Lotto;
import lotto.domain.ticket.Lottos;
import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.WinningNumbers;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResultCalculator {

    public Map<Rank, Integer> matchReult(Lottos lottos, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        Map<Rank, Integer> result = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
        for (Lotto lotto : lottos.getLottos()) {
            Rank rank = winningNumbers.match(lotto, bonusNumber);
            result.put(rank, result.get(rank) + 1);
        }
        return result;
    }

    public double calculateYield(Map<Rank, Integer> matchResult, Money money) {
        double totalPrizeAmount = matchResult.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();

        double yield = (totalPrizeAmount / money.getMoney()) * 100;

        return Math.round(yield * 10.0) / 10.0;
    }
}
