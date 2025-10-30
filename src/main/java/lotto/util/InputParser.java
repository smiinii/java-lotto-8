package lotto.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputParser {

    public static final String DELIMITER = ",";
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
        validateDelimiter(input);
        List<String> winningNumbers = List.of(input.split(DELIMITER));
        return winningNumbers.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    private static void validateEmpty(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력이 비어있습니다.");
        }
    }

    private static void validateDelimiter(String input) {
        Pattern pattern = Pattern.compile(DELIMITER);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("[ERROR] 구분자는 \"" + DELIMITER + "\"만 사용 가능합니다.");
        }
    }
}
