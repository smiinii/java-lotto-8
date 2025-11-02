package lotto.domain.result;


import lotto.domain.purchase.Money;

import java.util.EnumMap;

public class ResultCalculator {

    public double calculateYield(EnumMap<Rank, Integer> matchResult, Money money) {
        double totalPrizeAmount = matchResult.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();

        double yield = (totalPrizeAmount / money.getMoney()) * 100;

        return Math.round(yield * 10.0) / 10.0;
    }
}
