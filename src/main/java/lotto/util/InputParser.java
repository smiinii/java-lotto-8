package lotto.util;

public class InputParser {

    private InputParser() {}

    public static int parserMoney(String input) {
        validateEmpty(input);
        return Integer.parseInt(input);
    }

    private static void validateEmpty(String number) {
        if (number.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력이 비어있습니다.");
        }
    }
}
