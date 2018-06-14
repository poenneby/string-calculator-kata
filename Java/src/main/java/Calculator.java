import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public static final String COMMA = ",";
    public static final String NEW_LINE = "\n";
    public static final String DEFAULT_DELIMITER_REGEX = String.format("[%s%s]", COMMA, NEW_LINE);

    public static int add(String inputNumbers) throws Exception {
        if (inputNumbers.isEmpty()) {
            return 0;
        }
        if (inputNumbers.startsWith("//")) {
            return sumOfCustomDelimited(inputNumbers);
        }
        if (inputNumbers.contains(COMMA) || inputNumbers.contains(NEW_LINE)) {
            return sumOf(inputNumbers, DEFAULT_DELIMITER_REGEX);
        }
        int number = toInt(inputNumbers);
        if (number < 0) {
            throwNegativeNumbersException(Collections.singletonList(number));
        }
        return number;
    }

    private static int sumOfCustomDelimited(String inputNumbers) throws Exception {
        Pattern pattern = Pattern.compile("//(.+)\n(.+)");
        Matcher matcher = pattern.matcher(inputNumbers);
        matcher.matches();
        String delimiter = matcher.group(1);
        String numbers = matcher.group(2);
        return sumOf(numbers, delimiter);
    }

    private static int sumOf(String inputNumber, String delimiter) throws Exception {
        String[] splitNumbers = inputNumber.split(delimiter);
        int total = 0;
        List<Integer> negativeNumbers = new ArrayList<>();
        for (String splitNumber : splitNumbers) {
            int number = toInt(splitNumber);
            if (number < 0) {
                negativeNumbers.add(number);
            }
            if (number <= 1000) {
                total += number;
            }
        }
        if (negativeNumbers.isEmpty()) {
            return total;
        } else {
            return throwNegativeNumbersException(negativeNumbers);
        }
    }

    private static int throwNegativeNumbersException(List<Integer> negativeNumbers) throws Exception {
        throw new Exception(String.format("negatives not allowed: %s", negativeNumbers));
    }

    private static int toInt(String splitNumber) {
        return Integer.parseInt(splitNumber);
    }
}
