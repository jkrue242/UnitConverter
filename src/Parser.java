/*
    This class handles parsing of user input
    Joseph Krueger
    2/6/2023
 */

import java.util.ArrayList;

public class Parser {

    /*
    Constructor
    Inputs: string to parse: String
    Outputs: None
    Purpose: Initializes input word list
     */
    public Parser(String input)
    {
        // Create a list that splits the input string on whitespace (\s matches white space characters,
        //  + matches any more occurrences)
        input_words = input.trim().split("\\s+");
        units = new ArrayList<>();
    }

    /*
    Method name: getUnits
    Inputs: none
    Outputs: Array of strings containing the unit converting from
        and unit converting to
    Purpose: retrieve unit information from input string
     */
    public boolean parse()
    {
        // extracting the units we are converting to and from
        String unit_to_convert_from = "";
        String unit_to_convert_to = "";

        // check for special case 'fluid ounces'
        boolean from_fl_oz;
        if (input_words.length == 9)
        {
            // this will have fluid ounces to fluid ounces
            unit_to_convert_from = input_words[2] + " " + input_words[3];
            unit_to_convert_to = input_words[7] + " " + input_words[8];
            from_fl_oz = true;

        }
        else if (input_words.length == 8)
        {
            // this might be converting to or from fluid ounces, need to check
            if (input_words[3].equals("are"))
            {
                // this is converting to fluid ounces
                unit_to_convert_from = input_words[2];
                unit_to_convert_to = input_words[6] + " " + input_words[7];
                from_fl_oz = false;
            }
            else
            {
                // this is converting from fluid ounces
                unit_to_convert_from = input_words[2] + " " + input_words[3];
                unit_to_convert_to = input_words[7];
                from_fl_oz = true;
            }
        }
        else
        {
            // make sure we have the correct number of words in the input
            if (input_words.length < 6 || input_words.length > 9)
            {
                System.out.println("We could not recognize your input pattern. Please check" +
                        " that you are formatting your query like the example.\n");
                return false;
            }
            else
            {
                // get units if we have a valid input
                unit_to_convert_from = input_words[2];
                unit_to_convert_to = input_words[6];
            }
            from_fl_oz = false;
        }

        // extract the value we are converting from
        // if special case fluid ounces
        if (from_fl_oz)
        {
            // check that our value is a double
            try {
                value_to_convert_from = Double.parseDouble(input_words[6]);
            }
            catch (NumberFormatException e)
            {
                System.out.println("We had an issue parsing your input value. Please double-check that you are" +
                        " following the example format and try again.\n");
                return false;
            }
        }
        // other cases
        else {
            // check that our value is a double
            try {
                value_to_convert_from = Double.parseDouble(input_words[5]);
            }
            catch (NumberFormatException e)
            {
                System.out.println("We had an issue parsing your input value. Please double-check that you are" +
                        " following the example format and try again.\n");
                return false;
            }
            value_to_convert_from = Double.parseDouble(input_words[5]);

        }

        System.out.println("value: "+value_to_convert_from);
        // if parse was valid, update units
        units.add(unit_to_convert_from);
        units.add(unit_to_convert_to);

        return true;
    }

    /*
    Method name: getValueToConvertFrom
    Inputs: none
    Outputs: Double
    Purpose: returns the value we need to convert from
    */
    public Double getValueToConvertFrom(){return value_to_convert_from;}

    /*
    Method name: getUnits
    Inputs: none
    Outputs: ArrayList<String>
    Purpose: returns the units we need for conversion
    */
    public ArrayList<String> getUnits(){return units;}

    private final String[] input_words;
    private Double value_to_convert_from;
    private ArrayList<String> units;
}
