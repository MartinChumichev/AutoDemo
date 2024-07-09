package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.TestConfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.elementsPages.TextBoxPage;

import java.util.List;

public class ElementsSteps {

    TextBoxPage textBoxPage = new TextBoxPage();

    @Given("Открыть страницу: {string}")
    public void openTestPage(String page) {
        Configuration.pageLoadTimeout = 2000;
        Configuration.browserSize = "1920x1080";
        try {
            Selenide.open(TestConfig.URL + page);
        } catch (Exception ignore) {
        }
    }

    @When("Заполнить форму пользователя: {string}, {string}, {string}, {string}")
    public void fillEmployeeForm(String name, String email, String currentAddress, String permanentAddress) {
        textBoxPage.fillEmployeeForm(name, email, currentAddress, permanentAddress);
    }

    @Then("Проверить, что output форма содержит: {string}")
    public void checkOutputForm(String values) {
        List<String> containsValues = List.of(values.split(","));
        List<String> outputValues = textBoxPage.getOutputValues();
        for (int i = 0; i < containsValues.size(); i++) {
            Assertions.assertTrue(outputValues.get(i).contains(containsValues.get(i)),
                   "Текст " + containsValues.get(i) + " содержится в output форме");
        }
    }
}
