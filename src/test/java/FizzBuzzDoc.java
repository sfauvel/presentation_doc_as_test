import org.approvaltests.Approvals;
import org.approvaltests.approvers.FileApprover;
import org.approvaltests.writers.ApprovalTextWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FizzBuzzDoc {

    public StringBuffer buffer = new StringBuffer();

    @AfterEach
    public void verifyAfterEach(TestInfo testInfo) {
        Approvals.verify(new FileApprover(
                new ApprovalTextWriter(buffer.toString(), "adoc"),
                new TestInfoNamer(testInfo)));

    }

    @Test
    public void writeDoc() throws IOException {

        write("= FizzBuzz");
        write("");
        write("link:https://en.wikipedia.org/wiki/Fizz_buzz[Fizz buzz] is a group word game for children to teach them about division.");
        write("");
        write("Write a program that prints the numbers from 1 to 100.");
        write("But for multiples of three print “Fizz” instead of the number and for the multiples of five print “Buzz”.");
        write(" For numbers which are multiples of both three and five print “FizzBuzz “.");
        write("");
        write("http://codingdojo.org/kata/FizzBuzz/");
        write("");

        write("include::FizzBuzzDoc.sample_output.approved.adoc[]");
        write("");
        write("include::FizzBuzzDoc.return_given_value.approved.adoc[]");
        write("");
        write("include::FizzBuzzDoc.return_fizz_when_divisible_by_three.approved.adoc[]");
        write("");
        write("include::FizzBuzzDoc.return_buzz_when_divisible_by_five.approved.adoc[]");
        write("");
        write("include::FizzBuzzDoc.return_fizzbuzz_when_divisible_by_three_and_five.approved.adoc[]");

    }

    @Test
    public void return_fizzbuzz_when_divisible_by_three_and_five() throws IOException {
        write("== Return fizzbuzz when divisible by three and five");
        write("When number is divisible by three and five, returns FizzBuzz.");
        write("");
        callFizzBuzzWith(15);
        callFizzBuzzWith(60);
    }

    @Test
    public void return_buzz_when_divisible_by_five() throws IOException {
        write("== Return buzz when divisible by five");
        write("When number is divisible by five, returns Buzz.");
        write("");
        callFizzBuzzWith(5);
        callFizzBuzzWith(25);
    }

    @Test
    public void return_fizz_when_divisible_by_three() throws IOException {
        write("== Return fizz when divisible by three");
        write("When number is divisible by three, returns Fizz.");
        write("");
        callFizzBuzzWith(3);
        callFizzBuzzWith(9);
    }

    @Test
    public void return_given_value() throws IOException {
        write("== Return given number");
        write("When number is not divisible by five or three, returns the number.");
        write("");
        callFizzBuzzWith(1);
        callFizzBuzzWith(2);
        callFizzBuzzWith(4);
        callFizzBuzzWith(22);
    }

    @Test
    public void sample_output() throws IOException {
        write("== Sample output");
        write("");
        write(IntStream.rangeClosed(1, 20)
                .mapToObj(FizzBuzz::get)
                .collect(Collectors.joining(" +\n")));
        write("... etc up to 100");
    }

    private void callFizzBuzzWith(int value) throws IOException {
        write(String.format("FizzBuzz(%d) = %s +\n ", value, FizzBuzz.get(value)));
    }

    private void write(String text) throws IOException {
        buffer.append(text + "\n");
    }

}