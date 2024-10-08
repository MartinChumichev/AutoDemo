package ru.example.qa.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootTest
@Slf4j
class SelenideApplicationTests {

    @Test
    @DisplayName("Тестирование верстки")
    void testScreenIphone12Pro(TestInfo info) {
        Configuration.browserSize = "390x844";
        Selenide.open("https://threadqa.ru");
        assertScreen(info);
    }

    private void assertScreen(TestInfo info) {
        String expectedFileName = info.getTestMethod().get().getName() + ".png";
        String expectedScreensDir = "src/test/resources/screens/";

        File actualScreenShot = Selenide.screenshot(OutputType.FILE);
        File expectedScreenShot = new File(expectedScreensDir + expectedFileName);

        if(!expectedScreenShot.exists()) {
            addImgToAllure("actual", actualScreenShot);
            log.info("Cant assert image, because there is no reference. Actual screen can be downloaded from allure");
        }

        BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(expectedScreensDir + expectedFileName);
        BufferedImage actualImage = ImageComparisonUtil.readImageFromResources(actualScreenShot.toPath().toString());

        File resultDestination = new File("target/diff_" + expectedFileName);

        ImageComparison imageComparison = new ImageComparison(expectedImage, actualImage, resultDestination);
        ImageComparisonResult result = imageComparison.compareImages();

        if (!result.getImageComparisonState().equals(ImageComparisonState.MATCH)) {
            addImgToAllure("actual", actualScreenShot);
            addImgToAllure("expected", expectedScreenShot);
            addImgToAllure("diff", resultDestination);
        }

        Assertions.assertEquals(ImageComparisonState.MATCH, result.getImageComparisonState());
    }

    private void addImgToAllure(String name, File file) {
        try {
            byte[] image = Files.readAllBytes(file.toPath());
            saveScreenShote(name, image);
        } catch (IOException e) {
            log.info("Cant read bytes - " + e);
        }
    }

    @Attachment(value = "{name}", type = "image/png")
    private static byte[] saveScreenShote(String name, byte[] image) {
        return image;
    }
}
