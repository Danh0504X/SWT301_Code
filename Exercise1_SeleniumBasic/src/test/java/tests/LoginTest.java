package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.By;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends BaseTest {

    static WebDriverWait wait;
    static LoginPage loginPage;

    @BeforeAll
    static void initPage() {
        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    @Order(1)
    void testLoginSuccess() {
        loginPage.navigate();
        loginPage.login("tomsmith", "SuperSecretPassword!");

        WebElement success = wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginPage.getSuccessLocator())
        );

        assertTrue(success.getText().contains("You logged into a secure area!"));
    }

    @Test
    @Order(2)
    void testLoginFail() {
        loginPage.navigate();
        loginPage.login("wronguser", "wrongpassword");

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginPage.getErrorLocator())
        );

        assertTrue(error.getText().toLowerCase().contains("invalid"));
    }

@ParameterizedTest
@Order(3)
@CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
void testLoginFromCSV(String username, String password, String expected) {

    loginPage.navigate();

    username = (username == null) ? "" : username.trim();
    password = (password == null) ? "" : password.trim();

    loginPage.login(username, password);

    By resultLocator = expected.equals("success")
            ? loginPage.getSuccessLocator()
            : loginPage.getErrorLocator();

    WebElement result = wait.until(
            ExpectedConditions.visibilityOfElementLocated(resultLocator)
    );

    if (expected.equals("success")) {
        assertTrue(result.getText().contains("You logged into a secure area!"));
    } else {
        assertTrue(result.getText().toLowerCase().contains("invalid"));
    }
}
}