package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("userEmail");
    private By genderMale = By.xpath("//label[@for='gender-radio-1']");
    private By mobile = By.id("userNumber");
    private By submitButton = By.id("submit");
    private By modalTitle = By.id("example-modal-sizes-title-lg");

    public void navigate() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void enterFirstName(String value) {
        type(firstName, value);
    }

    public void enterLastName(String value) {
        type(lastName, value);
    }

    public void enterEmail(String value) {
        type(email, value);
    }

    public void selectMaleGender() {
        click(genderMale);
    }

    public void enterMobile(String value) {
        type(mobile, value);
    }

    public void clickSubmit() {
        WebElement submit = driver.findElement(submitButton);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", submit);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", submit);
    }

    public String getModalTitle() {
        return getText(modalTitle);
    }

    public void fillRequiredFields(String fName, String lName, String mail, String phone) {
        enterFirstName(fName);
        enterLastName(lName);
        enterEmail(mail);
        selectMaleGender();
        enterMobile(phone);
    }
}