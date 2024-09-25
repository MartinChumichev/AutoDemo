package ru.example.qa.flaky.junit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class Junit4Tests {
    @Rule
    public RetryRule rule = new RetryRule(3);

    @Test
    public void testJunit4() {
        Assert.assertFalse(true);
    }

    @Test
    public void testJunit4_2() {
        Assert.assertFalse(true);
    }
}
