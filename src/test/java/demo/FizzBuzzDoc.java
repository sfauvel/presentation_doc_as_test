package demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.sfvl.docformatter.AsciidocFormatter;
import org.sfvl.doctesting.junitextension.ApprovalsExtension;
import org.sfvl.doctesting.utils.CodeExtractor;
import org.sfvl.doctesting.utils.DocWriter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * link:https://en.wikipedia.org/wiki/Fizz_buzz[Fizz buzz] is a group word game for children to teach them about division.
 *
 * Write a program that prints the numbers from 1 to 100. But for multiples of three print “Fizz” instead of the number
 * and for the multiples of five print “Buzz”. For numbers which are multiples of both three and five print “FizzBuzz “.
 *
 * http://codingdojo.org/kata/FizzBuzz/
 */
class FizzBuzzDoc {

    private static final DocWriter doc = new DocWriter();
    @RegisterExtension
    static ApprovalsExtension extension = new ApprovalsExtension(doc);


    @Test
    public void sample_output() {
        doc.write(IntStream.rangeClosed(1, 20)
                .mapToObj(FizzBuzz::get)
                .collect(Collectors.joining(" +\n")));
        doc.write("... etc up to 100");
    }

    /**
     * When number is not divisible by five or three, returns the number.
     */
    @Test
    public void return_given_number() {
        callFizzBuzzWith(1);
        callFizzBuzzWith(2);
        callFizzBuzzWith(4);
        callFizzBuzzWith(22);
    }

    /**
     * When number is divisible by three, returns Fizz.
     */
    @Test
    public void return_fizz_when_divisible_by_three() {
        callFizzBuzzWith(3);
        callFizzBuzzWith(9);
    }

    /**
     * When number is divisible by five, returns Buzz.
     */
    @Test
    public void return_buzz_when_divisible_by_five() {
        callFizzBuzzWith(5);
        callFizzBuzzWith(25);
    }

    /**
     * When number is divisible by three and five, returns FizzBuzz.
     */
    @Test
    public void return_fizzbuzz_when_divisible_by_three_and_five() {
        callFizzBuzzWith(15);
        callFizzBuzzWith(60);
    }

    @Test
    public void fizzbuzz_usage(TestInfo testInfo) {
        // >>>
        final String result = FizzBuzz.get(2);
        // <<<
        final AsciidocFormatter formatter = new AsciidocFormatter();
        doc.write(String.format("With the following code, result is `%s`", result));
        doc.write(formatter.sourceCode(
                CodeExtractor.extractPartOfMethod(testInfo.getTestMethod().get())
        ));
    }

    @Test
    @DisplayName(value = "Values by result")
    public void values_for_a_result() {
        final int maxValue = 30;
        final int minValue = 1;

        final Map<String, List<Integer>> groupedValues = IntStream.rangeClosed(minValue, maxValue).boxed()
                .collect(Collectors.groupingBy(
                        i -> returnValueType(FizzBuzz.get(i))
                ));

        doc.write(String.format("List of values (between %d to %d) returned by FizzBuzz:", minValue, maxValue),
                "",
                groupedValues.entrySet().stream()
                        .map(e -> String.format("* *%s*: %s", e.getKey(), e.getValue()))
                        .collect(Collectors.joining("\n")));
    }

    public static String returnValueType(String fizzBuzzResult) {
        try {
            Integer.parseInt(fizzBuzzResult);
            return "Number";
        } catch (NumberFormatException nfe) {
            return fizzBuzzResult;
        }
    }

    public static boolean isNumeric(String strNum) {
        try {
            Integer.parseInt(strNum);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private void callFizzBuzzWith(int value) {
        doc.write(String.format("FizzBuzz(%d) = %s +\n ", value, FizzBuzz.get(value)));
    }

}