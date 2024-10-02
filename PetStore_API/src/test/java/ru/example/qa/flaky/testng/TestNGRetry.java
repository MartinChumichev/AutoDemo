package ru.example.qa.flaky.testng;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestNGRetry implements IRetryAnalyzer {
    private int count = 0;
    private int maxCount = 3;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if(count < maxCount) {
                count++;
                return true;
            }
        }
        return false;
    }
}
