package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTest extends BaseTest {

    static RegisterPage registerPage;

    @BeforeAll
    static void initPage() {
        registerPage = new RegisterPage(driver);
    }

    @Test
    void testRegisterSuccess() {
        registerPage.navigate();
        registerPage.fillRequiredFields("Danh", "Lo", "danhlo@test.com", "0123456789");
        registerPage.clickSubmit();

        assertEquals("Thanks for submitting the form", registerPage.getModalTitle());
    }
}