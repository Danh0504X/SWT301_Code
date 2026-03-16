package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.URL;

public class EquipmentPage extends BasePage {

    private final By nameInput = By.id("equipment-name");
    private final By typeInput = By.id("equipment-type");
    private final By quantityInput = By.id("equipment-quantity");
    private final By addButton = By.id("add-btn");

    private final By tableBody = By.cssSelector("#equipment-table tbody");

    public EquipmentPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        URL url = getClass().getClassLoader().getResource("mock-pages/equipment.html");
        if (url == null) {
            throw new RuntimeException("Không tìm thấy file: src/test/resources/mock-pages/equipment.html");
        }
        driver.get(url.toExternalForm());
    }

    public boolean isEquipmentPageDisplayed() {
        return isDisplayed(nameInput) && isDisplayed(typeInput) && isDisplayed(quantityInput);
    }

    public void addEquipment(String name, String type, String quantity) {
        type(nameInput, name);
        type(typeInput, type);
        type(quantityInput, quantity);
        click(addButton);
    }

    public boolean isEquipmentDisplayedInTable(String name, String type, String quantity) {
        try {
            WebElement tbody = find(tableBody);
            String tableText = tbody.getText();

            System.out.println("Table text = " + tableText);

            return tableText.contains(name)
                    && tableText.contains(type)
                    && tableText.contains(quantity);
        } catch (Exception e) {
            return false;
        }
    }
}