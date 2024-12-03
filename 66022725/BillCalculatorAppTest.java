import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BillCalculatorAppTest {

    @Test
    void testCalculateWaterBillSingleRoom() {
        BillCalculatorApp app = new BillCalculatorApp();

        // กำหนดค่า input
        app.lastMeterField.setText("100");
        app.currentMeterField.setText("150");
        app.waterBillRadio.setSelected(true);
        app.roomTypeComboBox.setSelectedItem("Single");

        // เรียกใช้ปุ่มคำนวณ
        app.calculateButton.doClick();

        // ตรวจสอบค่า output
        assertEquals("50", app.unitAmountField.getText()); // หน่วยที่ใช้
        assertEquals("250", app.resultField.getText()); // ค่าบิลน้ำ
        assertEquals("1750", app.totalRentalField.getText()); // ผลรวมทั้งหมด
    }

    @Test
    void testCalculateElectricBillDoubleRoom() {
        BillCalculatorApp app = new BillCalculatorApp();

        // กำหนดค่า input
        app.lastMeterField.setText("200");
        app.currentMeterField.setText("300");
        app.electricBillRadio.setSelected(true);
        app.roomTypeComboBox.setSelectedItem("Double");

        // เรียกใช้ปุ่มคำนวณ
        app.calculateButton.doClick();

        // ตรวจสอบค่า output
        assertEquals("100", app.unitAmountField.getText()); // หน่วยที่ใช้
        assertEquals("600", app.resultField.getText()); // ค่าบิลไฟฟ้า
        assertEquals("2600", app.totalRentalField.getText()); // ผลรวมทั้งหมด
    }

    @Test
    void testInvalidInput() {
        BillCalculatorApp app = new BillCalculatorApp();

        // กำหนดค่า input ที่ไม่ถูกต้อง
        app.lastMeterField.setText("200");
        app.currentMeterField.setText("150");
        app.waterBillRadio.setSelected(true);

        // เรียกใช้ปุ่มคำนวณ
        app.calculateButton.doClick();

        // ตรวจสอบว่าไม่มีการอัปเดตค่าใด ๆ
        assertEquals("Waiting", app.unitAmountField.getText());
        assertEquals("", app.resultField.getText());
        assertEquals("", app.totalRentalField.getText());
    }

    @Test
    void testResetFunctionality() {
        BillCalculatorApp app = new BillCalculatorApp();

        // กำหนดค่า input
        app.lastMeterField.setText("100");
        app.currentMeterField.setText("200");
        app.waterBillRadio.setSelected(false);
        app.roomTypeComboBox.setSelectedItem("Single");
        app.calculateButton.doClick();

        // กดปุ่มรีเซ็ต
        app.resetButton.doClick();

        // ตรวจสอบค่า output
        assertEquals("", app.lastMeterField.getText());
        assertEquals("", app.currentMeterField.getText());
        assertEquals("Waiting", app.unitAmountField.getText());
        assertEquals("", app.resultField.getText());
        assertEquals("", app.totalRentalField.getText());
        assertEquals(0, app.progressBar.getValue());
        assertFalse(app.waterBillRadio.isSelected());
        assertFalse(app.electricBillRadio.isSelected());
        assertEquals(0, app.roomTypeComboBox.getSelectedIndex());
    }
}
