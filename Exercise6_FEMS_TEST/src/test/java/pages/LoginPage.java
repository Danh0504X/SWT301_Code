package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.URL;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.cssSelector("button");
    private final By errorMessage = By.id("error-message");
    private final By successMessage = By.id("success-message");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        URL url = getClass().getClassLoader().getResource("mock-pages/login.html");
        if (url == null) {
            throw new RuntimeException("Không tìm thấy file: src/test/resources/mock-pages/login.html");
        }
        driver.get(url.toExternalForm());
    }

    public boolean isLoginPageDisplayed() {
        return isDisplayed(usernameInput) && isDisplayed(passwordInput);
    }

    public void login(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginButton);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    public boolean isSuccessDisplayed() {
        return isDisplayed(successMessage);
    }
}