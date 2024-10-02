package ru.example.qa.flaky.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

public class Junit5Tests {
    @RepeatedTest(3)
    public void testJunit5() {
        Assertions.assertFalse(true);
    }
}
