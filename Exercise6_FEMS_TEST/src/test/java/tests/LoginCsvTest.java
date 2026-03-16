package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.LoginPage;
import utils.CSVUtils;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LoginCsvTest extends BaseTest {

    static Stream<String[]> loginData() {
        return CSVUtils.read("testdata/login-data.csv").stream();
    }

    @ParameterizedTest
    @MethodSource("loginData")
    public void testLogin(String username, String password, String expectedResult) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        System.out.println("Current URL: " + driver.getCurrentUrl());

        assertTrue(loginPage.isLoginPageDisplayed(), "Không mở được trang login");

        loginPage.login(username, password);

        if ("success".equalsIgnoreCase(expectedResult)) {
            assertTrue(loginPage.isSuccessDisplayed(), "Đăng nhập đúng nhưng không hiện success");
            assertFalse(loginPage.isErrorDisplayed(), "Đăng nhập đúng nhưng vẫn hiện lỗi");
        } else {
            assertTrue(loginPage.isErrorDisplayed(), "Đăng nhập sai nhưng không hiện lỗi");
        }
    }
}