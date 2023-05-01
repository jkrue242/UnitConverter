/*
    This class is the driver code of the program
    Joseph Krueger
    2/6/2023
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;
public class MetricConversionMain {

    // driver code
    public static void main(String[] args)
    {
        boolean running = true;
        boolean converted = false;
        Scanner scanner = new Scanner(System.in);
        Converter converter = new Converter();

        System.out.println("Welcome to Metric Conversion. Enter a unit and value and it will be converted to another unit.\n" +
                "Supported base units (metric) are:\n" +
                "meters\n" +
                "grams\n" +
                "liters\n" +
                "\nSupported prefixes (metric) are:\n" +
                "mega\n" +
                "kilo\n" +
                "hecto\n" +
                "deca\n" +
                "deci\n" +
                "centi\n" +
                "milli\n" +
                "micro\n" +
                "\nSupported units for length (us system) are:\n" +
                "inches\n" +
                "feet\n" +
                "yards\n" +
                "miles\n" +
                "\nSupported units for mass (us system) are:\n" +
                "ounces\n" +
                "pounds\n" +
                "tons\n"+
                "\nSupported units for volume (us system) are:\n" +
                "teaspoons\n" +
                "tablespoons\n"+
                "fluid ounces\n" +
                "cups\n" +
                "pints\n" +
                "quarts\n" +
                "gallons\n" +
                "\nInput a question with this format:\n" +
                "How many (unit) are in (value) (unit)?\n"+
                "");

        // while the user still wants to convert, loop continues
        while (running)
        {
            Double final_value = 0.0;

            // while we have not successfully converted, loop continues
            while (!converted)
            {
                System.out.println("Enter your conversion (ex. 'How many feet are in 5 kilometers?): ");
                String input = scanner.nextLine();

                // parse our input
                Parser parser = new Parser(input);
                // make sure we have a valid parse input
                if (parser.parse())
                {
                    // get the units, value from parser
                    ArrayList<String> units= parser.getUnits();
                    Double value_to_convert_from = parser.getValueToConvertFrom();
                    final_value = converter.convert(value_to_convert_from, units.get(0), units.get(1));
                    // get the final value
                    converted = converter.convertable();
                }
                // if we failed to parse
                else
                {
                    converted = false;
                }

            }

            // round to 4 decimal places, display converted value
            DecimalFormat rounded_2_places = new DecimalFormat("0.000");
            System.out.println("The converted value is: " + rounded_2_places.format(final_value));

            // this handles yes/no for user on if to continue converting
            String key = "";
            while (!key.equals("y") && !key.equals("n"))
            {
                System.out.println("Run another conversion? (y/n):");
                key = scanner.nextLine();
                converted = false;
            }
            if (key.equals("n"))
            {
                running = false;
            }

        }
    }
}
