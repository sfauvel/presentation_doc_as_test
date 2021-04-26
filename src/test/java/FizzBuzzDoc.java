import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FizzBuzzDoc {

    public StringBuffer buffer = new StringBuffer();
    private final Path TEST_PATH = Paths.get("src", "test", "java");

    public void generateDoc() throws IOException {

        writeDoc();

        final Path filePath = TEST_PATH.resolve(Paths.get("FizzBuzzDoc.generateDoc.adoc"));
        try (BufferedWriter docFile = new BufferedWriter(new FileWriter(filePath.toString()))) {
            docFile.write(buffer.toString());
        }
    }

    private void writeDoc() throws IOException {

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

        write("== Sample output");
        write("");
        write(IntStream.rangeClosed(1, 20)
                .mapToObj(FizzBuzz::get)
                .collect(Collectors.joining(" +\n")));
        write("... etc up to 100");

        write("== Return given number");
        write("When number is not divisible by five or three, returns the number.");
        write("");
        callFizzBuzzWith(1);
        callFizzBuzzWith(2);
        callFizzBuzzWith(4);
        callFizzBuzzWith(22);

        write("== Return fizz when divisible by three");
        write("When number is divisible by three, returns Fizz.");
        write("");
        callFizzBuzzWith(3);
        callFizzBuzzWith(9);

        write("== Return buzz when divisible by five");
        write("When number is divisible by five, returns Buzz.");
        write("");
        callFizzBuzzWith(5);
        callFizzBuzzWith(25);

        write("== Return fizzbuzz when divisible by three and five");
        write("When number is divisible by three and five, returns FizzBuzz.");
        write("");
        callFizzBuzzWith(15);
        callFizzBuzzWith(60);

    }

    private void callFizzBuzzWith(int value) throws IOException {
        write(String.format("FizzBuzz(%d) = %s +\n ", value, FizzBuzz.get(value)));
    }

    private void write(String text) throws IOException {
        buffer.append(text + "\n");
    }

    public static void main(String... args) throws IOException {
        new FizzBuzzDoc().generateDoc();
    }

}