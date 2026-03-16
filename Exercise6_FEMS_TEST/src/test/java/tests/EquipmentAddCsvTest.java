package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.EquipmentPage;
import utils.CSVUtils;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EquipmentAddCsvTest extends BaseTest {

    static Stream<String[]> equipmentData() {
        return CSVUtils.read("testdata/equipment-data.csv").stream();
    }

    @ParameterizedTest
    @MethodSource("equipmentData")
    public void testAddEquipment(String name, String type, String quantity) {
        EquipmentPage equipmentPage = new EquipmentPage(driver);
        equipmentPage.open();

        System.out.println("Current URL: " + driver.getCurrentUrl());

        assertTrue(equipmentPage.isEquipmentPageDisplayed(), "Không mở được trang thiết bị");

        equipmentPage.addEquipment(name, type, quantity);

        assertTrue(
        equipmentPage.isEquipmentDisplayedInTable(name, type, quantity),
        "Thiết bị chưa xuất hiện trong bảng sau khi thêm"
);
    }
}