package pages.elementsPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class TextBoxPage {
    private final SelenideElement fullName = $x("//input[@id='userName']");
    private final SelenideElement email = $x("//input[@id='userEmail']");
    private final SelenideElement currentAddress = $x("//textarea[@id='currentAddress']");
    private final SelenideElement permanentAddress = $x("//textarea[@id='permanentAddress']");
    private final SelenideElement submitButton = $x("//button[text()='Submit']");
    private final ElementsCollection valueOutput = $$x("//div[@id='output']/div/p");

    public void fillEmployeeForm(String name, String em, String ca, String pa) {
        fullName.click();
        fullName.sendKeys(name);
        email.setValue(em);
        currentAddress.setValue(ca);
        permanentAddress.setValue(pa);
        submitButton.click();
    }

    public List<String> getOutputValues() {
        return valueOutput.stream().map(SelenideElement::getText).toList();
    }
}
