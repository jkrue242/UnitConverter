/*
    This class handles the testing of conversions and parsing
    Joseph Krueger
    2/6/2023
 */
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    /*
    The following tests are conversion from metric to metric (length)
    Checking that the absolute value of the difference between expected
    and actual values are within 3 decimal places to account for rounding.
     */
    @Test
    void testMetersToKilometers()
    {
        // How many meters are in 5 kilometers?
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(5.0, "meters", "kilometers");
        assertTrue(Math.abs(5000.0-val_to_check)<1e-3);
    }

    @Test
    void testCentimetersToHectometers()
    {
        // How many hectometers are in 15897 centimeters?
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(15897.0, "hectometers", "centimeters");
        assertTrue(Math.abs(1.5897-val_to_check)<1e-3);
    }

    @Test
    void testDecimetersToDecameters()
    {
        // How many decameters are in 267 decimeters?
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(267.0, "decameters", "decimeters");
        assertTrue(Math.abs(2.67-val_to_check)<1e-3);
    }

    /*
    The following tests are conversion from metric to metric (mass)
    Checking that the absolute value of the difference between expected
    and actual values are within 3 decimal places to account for rounding.
     */
    @Test
    void testMilligramsToGrams()
    {
        // How many grams are in 50.08 milligrams?
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(50.08, "grams", "milligrams");
        assertTrue(Math.abs(0.05008-val_to_check)<1e-3);
    }

    @Test
    void testMilligramsToHectograms()
    {
        // How many hectograms are in 1529.5 milligrams?
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(1529.5, "hectograms", "milligrams");
        assertTrue(Math.abs(0.015295-val_to_check)<1e-3);
    }

    @Test
    void testKilogramsToCentigrams()
    {
        // How many centigrams are in 0.034 kilograms?
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(0.034, "centigrams", "kilograms");
        assertTrue(Math.abs(3400.0-val_to_check)<1e-3);
    }

    /*
    The following tests are conversion from metric to metric (volume)
    Checking that the absolute value of the difference between expected
    and actual values are within 3 decimal places to account for rounding.
    */
    @Test
    void testMilliLitersToLiters()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(50.08, "liters", "milliliters");
        assertTrue(Math.abs(0.05008-val_to_check)<1e-3);
    }

    @Test
    void testHectolitersToMilliliters()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(1529.5, "hectoliters", "milliliters");
        assertTrue(Math.abs(0.015295-val_to_check)<1e-3);
    }

    @Test
    void testKilolitersToCentiliters()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(0.034, "centiliters", "kiloliters");
        assertTrue(Math.abs(3400.0-val_to_check)<1e-3);
    }

    /*
    The following tests are conversion from us to metric (length)
    Checking that the absolute value of the difference between expected
    and actual values are within 3 decimal places to account for rounding.
    */
    @Test
    void testFeetToMeters()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(12.0, "meters", "feet");
        assertTrue(Math.abs(3.6576-val_to_check)<1e-3);
    }

    @Test
    void testYardsToKilometers()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(3000.0, "kilometers", "yards");
        assertTrue(Math.abs(2.7432-val_to_check)<1e-3);
    }

    @Test
    void testInchesToMillimeters()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(120.0, "millimeters", "inches");
        assertTrue(Math.abs(3048.0-val_to_check)<1e-3);
    }

    /*
    The following tests are conversion us to metric (mass)
    Checking that the absolute value of the difference between expected
    and actual values are within 3 decimal places to account for rounding.
     */
    @Test
    void testPoundsToKilograms()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(250.0, "kilograms", "pounds");
        assertTrue(Math.abs(113.398-val_to_check)<1e-3);
    }

    @Test
    void testOuncesToCentigrams()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(2.0, "centigrams", "ounces");
        assertTrue(Math.abs(5669.9-val_to_check)<1e-3);
    }

    @Test
    void testPoundsToHectograms()
    {
        // How many centigrams are in 0.034 kilograms?
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(100.0, "hectograms", "pounds");
        assertTrue(Math.abs(453.592-val_to_check)<1e-3);
    }

    /*
    The following tests are conversion from metric to us (volume)
    Checking that the absolute value of the difference between expected
    and actual values are within 3 decimal places to account for rounding.
    */
    @Test
    void testFluidOuncesToCentiliters()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(50.0, "centiliters", "fluid ounces");
        assertTrue(Math.abs(147.868-val_to_check)<1e-3);
    }

    @Test
    void testQuartsToLiters()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(15.0, "liters", "quarts");
        assertTrue(Math.abs(14.1953-val_to_check)<1e-3);
    }

    @Test
    void testGallonsToKiloliters()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(20000.0, "kiloliters", "gallons");
        assertTrue(Math.abs(75.708-val_to_check)<1e-3);
    }

    /*
    The following tests are conversion from us to us (length)
    Checking that the absolute value of the difference between expected
    and actual values are within 3 decimal places to account for rounding.
    */
    @Test
    void testFeetToYards()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(12.0, "yards", "feet");
        assertTrue(Math.abs(4.0-val_to_check)<1e-3);
    }

    @Test
    void testYardsToMiles()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(3000.0, "miles", "yards");
        assertTrue(Math.abs(1.7045-val_to_check)<1e-3);
    }

    @Test
    void testInchesToFeet()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(120.0, "feet", "inches");
        assertTrue(Math.abs(10.0-val_to_check)<1e-3);
    }

    /*
    The following tests are conversion from us to us (mass)
    Checking that the absolute value of the difference between expected
    and actual values are within 3 decimal places to account for rounding.
     */
    @Test
    void testPoundsToOunces()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(250.0, "ounces", "pounds");
        assertTrue(Math.abs(4000.0-val_to_check)<1e-3);
    }

    @Test
    void testOuncesToPounds()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(24.3, "pounds", "ounces");
        assertTrue(Math.abs(1.51875-val_to_check)<1e-3);
    }

    @Test
    void testPoundsToTons()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(100.0, "tons", "pounds");
        assertTrue(Math.abs(0.05-val_to_check)<1e-3);
    }

    /*
    The following tests are conversion from us to us (volume)
    Checking that the absolute value of the difference between expected
    and actual values are within 3 decimal places to account for rounding.
    */
    @Test
    void testFluidOuncesToCups()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(50.0, "cups", "fluid ounces");
        assertTrue(Math.abs(6.25-val_to_check)<1e-3);
    }

    @Test
    void testQuartsToGallons()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(15.0, "gallons", "quarts");
        assertTrue(Math.abs(3.75-val_to_check)<1e-3);
    }

    @Test
    void testGallonsToPints()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(2.0, "pints", "gallons");
        assertTrue(Math.abs(16.0-val_to_check)<1e-3);
    }

    /*
The following tests are conversion from metric to us (length)
Checking that the absolute value of the difference between expected
and actual values are within 3 decimal places to account for rounding.
 */
    @Test
    void testMetersToFeet()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(5.0, "feet", "meters");
        assertTrue(Math.abs(16.4042-val_to_check)<1e-3);
    }

    @Test
    void testCentimetersToYards()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(15897.0, "yards", "centimeters");
        assertTrue(Math.abs(173.8517-val_to_check)<1e-3);
    }

    @Test
    void testDecametersToMiles()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(267.0, "miles", "decameters");
        assertTrue(Math.abs(1.659-val_to_check)<1e-3);
    }

    /*
    The following tests are conversion from metric to us (mass)
    Checking that the absolute value of the difference between expected
    and actual values are within 3 decimal places to account for rounding.
     */
    @Test
    void testMilligramsToPounds()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(5000.0, "pounds", "milligrams");
        assertTrue(Math.abs(0.011-val_to_check)<1e-3);
    }

    @Test
    void testMilligramsToOunces()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(1529.5, "ounces", "milligrams");
        assertTrue(Math.abs(0.05395-val_to_check)<1e-3);
    }

    @Test
    void testKilogramsToTons()
    {
        // How many centigrams are in 0.034 kilograms?
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(34.0, "tons", "kilograms");
        assertTrue(Math.abs(0.03748-val_to_check)<1e-3);
    }

    /*
    The following tests are conversion from metric to us (volume)
    Checking that the absolute value of the difference between expected
    and actual values are within 3 decimal places to account for rounding.
    */
    @Test
    void testMillilitersToTeaspoons()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(50.0, "teaspoons", "milliliters");
        assertTrue(Math.abs(10.1442-val_to_check)<1e-3);
    }

    @Test
    void testHectolitersToQuarts()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(1529.5, "hectoliters", "milliliters");
        assertTrue(Math.abs(0.015295-val_to_check)<1e-3);
    }

    @Test
    void testKilolitersToGallons()
    {
        Converter test_converter = new Converter();
        Double val_to_check = test_converter.convert(0.034, "gallons", "kiloliters");
        assertTrue(Math.abs(8.98185-val_to_check)<1e-3);
    }

    /*
    The following tests test parsing of different inputs
     */
    @Test
    void testParsingValidInput()
    {
        String test_input = "How many feet are in 5 kilometers?";
        Parser test_parser = new Parser(test_input);
        assertTrue(test_parser.parse());
    }

    @Test
    void testParsingInvalidValue()
    {
        String test_input = "How many feet are in x kilometers?";
        Parser test_parser = new Parser(test_input);
        assertFalse(test_parser.parse());
    }

    @Test
    void testParsingInvalidInput1()
    {
        String test_input = "How many feet are in 5 this is invalid?";
        Parser test_parser = new Parser(test_input);
        assertFalse(test_parser.parse());
    }

    @Test
    void testParsingInvalidInput2()
    {
        String test_input = "";
        Parser test_parser = new Parser(test_input);
        assertFalse(test_parser.parse());
    }

}