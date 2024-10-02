package ru.example.qa.flaky.testng;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Arrays;

public class TestNGTests {
    @BeforeSuite
    public void setUp(ITestContext context) {
        Arrays.stream(context.getAllTestMethods()).forEach(x -> x.setRetryAnalyzerClass(TestNGRetry.class));
    }
    @Test
    public void testNG() {
        Assert.assertFalse(true);
    }

    @Test
    public void testNG_2() {
        Assert.assertFalse(true);
    }
}
