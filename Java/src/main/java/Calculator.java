import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private static final int ZERO = 0;
    private static final String COMMA = ",";
    private static final String NEW_LINE = "\n";
    private static final String COMMA_OR_NEW_LINE = String.format("[%s%s]", COMMA, NEW_LINE);

    public static int add(String input) {
        if (input.isEmpty()) {
            return ZERO;
        }
        if (input.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(.+)\n(.+)").matcher(input);
            if (matcher.matches()) {
                String delimiter = matcher.group(1);
                String numbers = matcher.group(2);
                return total(delimiter, numbers);
            }
        }
        if (input.contains(NEW_LINE) || input.contains(COMMA)) {
            return total(COMMA_OR_NEW_LINE, input);
        }
        return intOf(input);
    }

    private static int total(String delimiter, String input) {
        String[] parts = input.split(delimiter);
        List<Integer> negativeNumbers = new ArrayList<>();
        int total = totalOf(parts, negativeNumbers);
        if (negativeNumbers.isEmpty()) {
            return total;
        }
        throw new IllegalArgumentException(String.format("negatives not allowed: %s", negativeNumbers));
    }

    private static int totalOf(String[] parts, List<Integer> negativeNumbers) {
        return Arrays.asList(parts).stream()
                .map(p -> intOf(p))
                .filter(integer -> integer <= 1000)
                .map(number -> collectNegativeNumbers(number, negativeNumbers))
                .reduce((op1, op2) -> op1 + op2).get();
    }

    private static Integer collectNegativeNumbers(Integer number, List<Integer> negativeNumbers) {
        if (number < 0) {
            negativeNumbers.add(number);
        }
        return number;
    }

    private static int intOf(String part) {
        return Integer.parseInt(part);
    }
}
