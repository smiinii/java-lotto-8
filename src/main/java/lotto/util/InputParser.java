package lotto.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    private InputParser() {}

    public static int parseMoney(String input) {
        validateEmpty(input);
        try {
            return Integer.parseInt(input.trim());
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자가 아닙니다.");
        }
    }

    public static List<Integer> parseWinningNumbers(String input) {
        validateEmpty(input);
        return new ArrayList<>();
    }

    private static void validateEmpty(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력이 비어있습니다.");
        }
    }
}
