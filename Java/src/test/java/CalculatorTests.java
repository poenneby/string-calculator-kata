import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class CalculatorTests {

    @Test
    void should_return_0_when_empty_input() {
        // Given
        String emptyString = "";

        // When
        int result = Calculator.add(emptyString);

        // Then
        assertThat(result).isEqualTo(0);
    }

    @Test
    void should_return_number_when_single_string_input() {
        assertThat(Calculator.add("1")).isEqualTo(1);
        assertThat(Calculator.add("2")).isEqualTo(2);
    }

    @Test
    void should_return_sum_of_delimited_string_input() {
        assertThat(Calculator.add("1,2")).isEqualTo(3);
        assertThat(Calculator.add("2,3")).isEqualTo(5);
        assertThat(Calculator.add("1,2,3")).isEqualTo(6);
    }

    @Test
    void should_return_sum_of_new_line_delimited_string() {
        assertThat(Calculator.add("1\n2")).isEqualTo(3);
        assertThat(Calculator.add("1\n2\n3")).isEqualTo(6);
    }

    @Test
    void should_return_sum_of_mixed_delimited_string() {
        assertThat(Calculator.add("1\n2,3")).isEqualTo(6);
    }

    @Test
    void should_return_sum_of_custom_delimited_string() {
        assertThat(Calculator.add("//;\n1;2")).isEqualTo(3);
        assertThat(Calculator.add("//@\n1@2@3")).isEqualTo(6);
    }

    @Test
    void should_throw_exception_when_negative_numbers() {
        try {
            Calculator.add("1,4,-1,-2");
            fail("Expected exception");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("negatives not allowed: [-1, -2]");

        }
    }

    @Test
    void should_ignore_numbers_over_1000() {
        assertThat(Calculator.add("2,1001")).isEqualTo(2);
    }
}
