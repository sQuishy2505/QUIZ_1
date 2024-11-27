import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BillCalculationTest {

    @Test
    void testWaterBillCalculation() {
        
        int lastMeter = 200;
        int currentMeter = 250;
        int ratePerUnit = 5; 

        // Act
        int units = currentMeter - lastMeter;
        int total = units * ratePerUnit;

        // Assert
        assertEquals(250, units, "The number of units calculated is incorrect.");
        assertEquals(1250, total, "The total water bill calculation is incorrect.");
    }

    @Test
    void testElectricBillCalculation() {
        
        int lastMeter = 300;
        int currentMeter = 400;
        int ratePerUnit = 6; 

        
        int units = currentMeter - lastMeter;
        int total = units * ratePerUnit;

        assertEquals(100, units, "The number of units calculated is incorrect.");
        assertEquals(600, total, "The total electric bill calculation is incorrect.");
    }

    @Test
    void testInvalidMeterValues() {
    
        int lastMeter = 300;
        int currentMeter = 200;

    
        assertThrows(IllegalArgumentException.class, () -> {
            if (currentMeter < lastMeter) {
                throw new IllegalArgumentException("Error: Current Meter must be greater than Last Meter");
            }
        });
    }
    
    @Test
    void testNoBillTypeSelected() {
        boolean isBillTypeSelected = false;

        assertFalse(isBillTypeSelected, "Bill type must be selected.");
    }

    @Test
    void testResetForm() {

        int lastMeter = 100;
        int currentMeter = 150;

    
        lastMeter = 0;
        currentMeter = 0;

        assertEquals(0, lastMeter, "The last meter should be reset to 0.");
        assertEquals(0, currentMeter, "The current meter should be reset to 0.");
    }
}
