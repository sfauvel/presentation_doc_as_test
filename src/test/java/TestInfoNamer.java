import org.approvaltests.namer.ApprovalNamer;
import org.junit.jupiter.api.TestInfo;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestInfoNamer implements ApprovalNamer {

    private final Path TEST_PATH = Paths.get("src", "test", "java");
    private final TestInfo testInfo;

    public TestInfoNamer(TestInfo testInfo) {
        this.testInfo = testInfo;
    }

    @Override
    public String getApprovalName() {
        return String.format("%s.%s",
                testInfo.getTestClass().map(Class::getSimpleName).get(),
                testInfo.getTestMethod().map(Method::getName).get());
    }

    @Override
    public String getSourceFilePath() {
        return TEST_PATH.toString() + File.separator;
    }
}
