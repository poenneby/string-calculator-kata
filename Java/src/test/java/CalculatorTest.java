import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class CalculatorTest {

    @Test
    public void should_return_0_on_empty_input() throws Exception {
        assertThat(Calculator.add("")).isEqualTo(0);
    }

    @Test
    public void should_return_single_input_as_string() throws Exception {
        assertThat(Calculator.add("1")).isEqualTo(1);
        assertThat(Calculator.add("2")).isEqualTo(2);
    }

    @Test
    public void should_sum_comma_delimited_numbers() throws Exception {
        assertThat(Calculator.add("1,2")).isEqualTo(3);
        assertThat(Calculator.add("2,3")).isEqualTo(5);
        assertThat(Calculator.add("1,2,3")).isEqualTo(6);
    }

    @Test
    public void should_handle_new_lines_between_numbers() throws Exception {
        assertThat(Calculator.add("1\n2")).isEqualTo(3);
        assertThat(Calculator.add("1\n2,3")).isEqualTo(6);
    }

    @Test
    public void should_support_different_delimiters() throws Exception {
        assertThat(Calculator.add("//;\n1;2")).isEqualTo(3);
        assertThat(Calculator.add("//;\n2;3")).isEqualTo(5);
        assertThat(Calculator.add("//;\n1;2;3")).isEqualTo(6);
    }

    @Test
    public void should_throw_exception_for_single_negative_number() throws Exception {
        assertException("-1", "[-1]");
        assertException("-2", "[-2]");
    }

    @Test
    public void should_throw_exception_for_multiple_negative_numbers() throws Exception {
        assertException("1,-2,3", "[-2]");
        assertException("1,-2,-3", "[-2, -3]");
    }

    private void assertException(String numbers, String negativeNumbers) {
        try {
            Calculator.add(numbers);
            fail("Should throw exception");
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo(String.format("negatives not allowed: %s", negativeNumbers));
        }
    }

    @Test
    public void should_ignore_numbers_over_1000() throws Exception {
        assertThat(Calculator.add("2,1001")).isEqualTo(2);
        assertThat(Calculator.add("2,1001,1000")).isEqualTo(1002);
    }
}
