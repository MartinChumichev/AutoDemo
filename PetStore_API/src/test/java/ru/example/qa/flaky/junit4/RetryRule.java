package ru.example.qa.flaky.junit4;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

@AllArgsConstructor
@Slf4j
public class RetryRule implements TestRule {
    private int retryCount;

    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Throwable error = null;
                for (int i = 0; i < retryCount; i++) {
                    try {
                        statement.evaluate();
                        return;
                    } catch (Throwable throwable) {
                        error = throwable;
                        log.info(description.getDisplayName() + " был запущен " + (i + 1) + " раз");
                    }
                }
                log.info(description.getDisplayName() + " тест упал после " + retryCount + " попыток");
            }
        };
    }
}
