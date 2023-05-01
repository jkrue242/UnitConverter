/*
    This class handles the conversion between systems/units
    Joseph Krueger
    2/6/2023
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Converter {

    /*
    Method name: convert
    Inputs: initial val: Double, unit to convert to: String, unit to convert from: String
    Outputs: returns the value after conversion
    Purpose: driver code for the conversion process
     */
    public Double convert(Double initial_val, String unit_converting_to, String unit_converting_from)
    {
        // initialize convertable status to true
        is_convertable = true;

        // initialize values and constants
        this.initial_val = initial_val;

        // remove question marks from units, make lowercase so we can find in the maps
        unit_converting_from = unit_converting_from.replace("?", "").toLowerCase();
        unit_converting_to = unit_converting_to.replace("?", "").toLowerCase();
        this.unit_to_convert_from = unit_converting_from;
        this.unit_to_convert_to = unit_converting_to;

        // initialize constants
        initConstants();

        // get our measurements
        ArrayList<String> initial_measurement = checkValidity(unit_converting_from);
        ArrayList<String> converted_measurement = checkValidity(unit_converting_to);

        // check that our input units are valid
        if (initial_measurement.size() == 0)
        {
            System.out.println("We could not recognize the unit you are converting from. Please check" +
                    "your spelling and make sure your query is formatted correctly.");
            is_convertable = false;
        }
        if (converted_measurement.size() == 0)
        {
            System.out.println("We could not recognize the unit you are converting to. Please check" +
                    "your spelling and make sure your query is formatted correctly.");
            is_convertable = false;
        }

        // if we are converting from metric system
        if (initial_measurement.get(0).equals("metric"))
        {
            convertFromMetric(initial_measurement.get(1), converted_measurement.get(0));
        }
        // if we are converting from us system
        else if (initial_measurement.get(0).equals("us"))
        {
            convertFromUS(initial_measurement.get(1), converted_measurement.get(0));
        }
        // sanity check
        else
        {
            System.out.println("Error");
            is_convertable = false;
        }
        return converted_val;
    }

    /*
    Method name: convertFromMetric
    Inputs: measurement_type (length, mass, volume): String, system to convert to: String
    Outputs: none
    Purpose: performs conversion from metric system to either metric or US system
     */
    private void convertFromMetric(String measurement_type, String converted_system)
    {
        // check the measurement type
        // length
        if (measurement_type.equals("length"))
        {
            calculateConvertedValue("metric", converted_system, metric_length_map, us_length_map);
        }
        // mass
        else if (measurement_type.equals("mass"))
        {
            calculateConvertedValue("metric", converted_system, metric_mass_map, us_mass_map);
        }
        // volume
        else if (measurement_type.equals("volume"))
        {
            calculateConvertedValue("metric", converted_system, metric_volume_map, us_volume_map);
        }
        // sanity check
        else
        {
            System.out.println("Invalid measurement type.");
        }
    }

    /*
    Method name: convertFromUS
    Inputs: measurement_type (length, mass, volume): String, system to convert to: String
    Outputs: none
    Purpose: performs conversion from US system to either metric or US system
     */
    private void convertFromUS(String measurement_type, String converted_system)
    {
        // check the measurement type

        // length
        if (measurement_type.equals("length"))
        {
            calculateConvertedValue("us", converted_system, metric_length_map, us_length_map);
        }
        // mass
        else if (measurement_type.equals("mass"))
        {

            calculateConvertedValue("us", converted_system, metric_mass_map, us_mass_map);
        }
        // volume
        else if (measurement_type.equals("volume"))
        {

            calculateConvertedValue("us", converted_system, metric_volume_map, us_volume_map);
        }
        // sanity check
        else
        {
            System.out.println("Invalid measurement type");
            return;
        }
    }

    /*
    Method name: calculateConvertedValue
    Inputs: the system that we are converting from: String, the system we are converting
        to: String, the corresponding metric map: HashMap<String, Double>,
        the corresponding us map: HashMap<String, Double>
    Outputs: none
    Purpose: Extracts the conversion factors and applies them to our initial value
        to perform the conversion
     */
    private void calculateConvertedValue(String initial_system, String converted_system,
                                         HashMap<String, Double> metric_map,
                                         HashMap<String, Double> us_map) {
        // if converting from metric
        if (initial_system.equals("metric"))
        {
            // if going from metric to metric
            if (converted_system.equals("metric"))
            {
                // this is ratio from the current unit to base unit (ex. 0.001km in 1m)
                Double to_base_factor = metric_map.get(unit_to_convert_from);
                // this is the ratio from the base unit to the new metric unit (ex. 100cm in 1m)
                Double conversion_factor = metric_map.get(unit_to_convert_to);

                // Perform conversion:
                // Example (5 km to cm): 5km / 0.001 * 100 = 500,000cm
                converted_val = initial_val / to_base_factor * conversion_factor;
            }

            // if going from metric to us
            else if (converted_system.equals("us"))
            {
                // this is the ratio from current unit to base unit (ex. 0.001km in 1m)
                Double to_base_factor = metric_map.get(unit_to_convert_from);
                // this is the ratio from current base unit to us unit (ex. 0.3048 feet in 1 meter)
                Double conversion_factor = us_map.get(unit_to_convert_to);

                // perform conversion
                // example (5 km / 0.001 / 0.3048 = 16404.2 feet)
                converted_val = initial_val / to_base_factor / conversion_factor;
            }

            // sanity check
            else
            {
                System.out.println("Invalid system.");
                return;
            }
        }

        // if converting from us
        else if (initial_system.equals("us"))
        {
            // going from us to metric
            if (converted_system.equals("metric"))
            {
                // this is the ratio
                Double conversion_factor = us_map.get(unit_to_convert_from);
                Double to_base_factor = metric_map.get(unit_to_convert_to);
                System.out.println("conversion factor: " + conversion_factor);
                System.out.println("base factor: " + to_base_factor);
                converted_val = initial_val * conversion_factor * to_base_factor;
            }

            // if going from us to us (will need to go to metric and then back to us)
            else if (converted_system.equals("us"))
            {
                // this is the ratio from the current us unit to metric (ex. 0.3048m per foot)
                Double conversion_factor = us_map.get(unit_to_convert_from);
                // this is the ratio from metric back to us (ex. 0.9144m per yard)
                Double convert_to_us = us_map.get(unit_to_convert_to);

                // perform conversion
                // example: (12 feet / 0.9144 * 0.3048 = 4 yards)
                converted_val = initial_val / convert_to_us * conversion_factor;
            }
        }
    }

    /*
    Method name: convertable
    Inputs: none
    Outputs: boolean
    Purpose: returns the status on if the conversion was convertable or not
    */
    public boolean convertable(){return this.is_convertable;}

    /*
    Method name: checkValidity
    Inputs: unit to check: String
    Outputs: a list with the system and measurement type (empty if none) : ArrayList
    Purpose: checks that a given unit is supported by the conversion system
     */
    private ArrayList<String> checkValidity(String input_unit)
    {
        ArrayList<String> system_and_type = new ArrayList<>();

        // check if the unit belongs to any of the maps
        if (metric_length_map.containsKey(input_unit))
        {
            system_and_type.add("metric");
            system_and_type.add("length");
        }
        else if (metric_mass_map.containsKey(input_unit))
        {
            system_and_type.add("metric");
            system_and_type.add("mass");
        }
        else if (metric_volume_map.containsKey(input_unit))
        {
            system_and_type.add("metric");
            system_and_type.add("volume");
        }
        else if (us_length_map.containsKey(input_unit))
        {
            system_and_type.add("us");
            system_and_type.add("length");
        }
        else if (us_mass_map.containsKey(input_unit))
        {
            system_and_type.add("us");
            system_and_type.add("mass");
        }
        else if (us_volume_map.containsKey(input_unit))
        {
            system_and_type.add("us");
            system_and_type.add("volume");
        }

        return system_and_type;
    }
    /*
    Method name: initConstants
    Inputs: none
    Outputs: none
    Purpose: initializes all the constants (units, conversion factors) that will be used for
        conversion
     */
    private void initConstants()
    {

        // these constants are the conversion factors for base to prefix for metric system
        final Double BASE_TO_DECA = 0.1;
        final Double BASE_TO_HECTO = 0.01;
        final Double BASE_TO_KILO = 0.001;
        final Double BASE_TO_MEGA = 0.000001;
        final Double BASE_TO_DECI = 10.0;
        final Double BASE_TO_CENTI = 100.0;
        final Double BASE_TO_MILLI = 1000.0;
        final Double BASE_TO_MICRO = 1000000.0;
        final Double BASE_TO_BASE = 1.0;

        // this adds units, conversion factors for metric system to each HashMap (plural and singular)
        // length map
        metric_length_map.put("decameter", BASE_TO_DECA);
        metric_length_map.put("hectometer", BASE_TO_HECTO);
        metric_length_map.put("kilometer", BASE_TO_KILO);
        metric_length_map.put("megameter", BASE_TO_MEGA);
        metric_length_map.put("decimeter", BASE_TO_DECI);
        metric_length_map.put("centimeter", BASE_TO_CENTI);
        metric_length_map.put("millimeter", BASE_TO_MILLI);
        metric_length_map.put("micrometer", BASE_TO_MICRO);
        metric_length_map.put("meter", BASE_TO_BASE);
        metric_length_map.put("decameters", BASE_TO_DECA);
        metric_length_map.put("hectometers", BASE_TO_HECTO);
        metric_length_map.put("kilometers", BASE_TO_KILO);
        metric_length_map.put("megameters", BASE_TO_MEGA);
        metric_length_map.put("decimeters", BASE_TO_DECI);
        metric_length_map.put("centimeters", BASE_TO_CENTI);
        metric_length_map.put("millimeters", BASE_TO_MILLI);
        metric_length_map.put("micrometers", BASE_TO_MICRO);
        metric_length_map.put("meters", BASE_TO_BASE);

        // mass map
        metric_mass_map.put("decagram", BASE_TO_DECA);
        metric_mass_map.put("hectogram", BASE_TO_HECTO);
        metric_mass_map.put("kilogram", BASE_TO_KILO);
        metric_mass_map.put("megagram", BASE_TO_MEGA);
        metric_mass_map.put("decigram", BASE_TO_DECI);
        metric_mass_map.put("centigram", BASE_TO_CENTI);
        metric_mass_map.put("milligram", BASE_TO_MILLI);
        metric_mass_map.put("microgram", BASE_TO_MICRO);
        metric_mass_map.put("gram", BASE_TO_BASE);
        metric_mass_map.put("decagrams", BASE_TO_DECA);
        metric_mass_map.put("hectograms", BASE_TO_HECTO);
        metric_mass_map.put("kilograms", BASE_TO_KILO);
        metric_mass_map.put("megagrams", BASE_TO_MEGA);
        metric_mass_map.put("decigrams", BASE_TO_DECI);
        metric_mass_map.put("centigrams", BASE_TO_CENTI);
        metric_mass_map.put("milligrams", BASE_TO_MILLI);
        metric_mass_map.put("micrograms", BASE_TO_MICRO);
        metric_mass_map.put("grams", BASE_TO_BASE);

        // volume map
        metric_volume_map.put("decaliter", BASE_TO_DECA);
        metric_volume_map.put("hectoliter", BASE_TO_HECTO);
        metric_volume_map.put("kiloliter", BASE_TO_KILO);
        metric_volume_map.put("megaliter", BASE_TO_MEGA);
        metric_volume_map.put("deciliter", BASE_TO_DECI);
        metric_volume_map.put("centiliters", BASE_TO_CENTI);
        metric_volume_map.put("milliliter", BASE_TO_MILLI);
        metric_volume_map.put("microliter", BASE_TO_MICRO);
        metric_volume_map.put("liter", BASE_TO_BASE);
        metric_volume_map.put("decaliters", BASE_TO_DECA);
        metric_volume_map.put("hectoliters", BASE_TO_HECTO);
        metric_volume_map.put("kiloliters", BASE_TO_KILO);
        metric_volume_map.put("megaliters", BASE_TO_MEGA);
        metric_volume_map.put("deciliters", BASE_TO_DECI);
        metric_volume_map.put("centiliters", BASE_TO_CENTI);
        metric_volume_map.put("milliliters", BASE_TO_MILLI);
        metric_volume_map.put("microliters", BASE_TO_MICRO);
        metric_volume_map.put("liters", BASE_TO_BASE);

        // these are conversion factors from base metric system to US system

        // meters per __
        final Double M_TO_IN = 0.0254;
        final Double M_TO_FT = 0.3048;
        final Double M_TO_YD = 0.9144;
        final Double M_TO_MI = 1609.34;

        // grams per __
        final Double G_TO_OZ = 28.3495;
        final Double G_TO_LB = 453.592;
        final Double G_TO_TON = G_TO_LB * 2000;

        // liters per __
        final Double L_TO_FOZ = 0.0295735;
        final Double L_TO_CP = 0.236588;
        final Double L_TO_PT = 0.4731763;
        final Double L_TO_QT = 0.946353;
        final Double L_TO_GAL = 3.78541;
        final Double L_TO_TBSP = 0.01479;
        final Double L_TO_TSP = 0.004929;

        // adding the unit, conversion factor to each of the US system HashMaps
        // length map
        us_length_map.put("inches", M_TO_IN);
        us_length_map.put("feet", M_TO_FT);
        us_length_map.put("yards", M_TO_YD);
        us_length_map.put("miles", M_TO_MI);
        us_length_map.put("inch", M_TO_IN);
        us_length_map.put("yard", M_TO_YD);
        us_length_map.put("mile", M_TO_MI);

        // mass map
        us_mass_map.put("ounces", G_TO_OZ);
        us_mass_map.put("pounds", G_TO_LB);
        us_mass_map.put("ounce", G_TO_OZ);
        us_mass_map.put("pound", G_TO_LB);
        us_mass_map.put("tons", G_TO_TON);
        us_mass_map.put("ton", G_TO_TON);

        // volume map
        us_volume_map.put("fluid ounces", L_TO_FOZ);
        us_volume_map.put("cups", L_TO_CP);
        us_volume_map.put("pints", L_TO_PT);
        us_volume_map.put("quarts", L_TO_QT);
        us_volume_map.put("gallons", L_TO_GAL);
        us_volume_map.put("fluid ounce", L_TO_FOZ);
        us_volume_map.put("cup", L_TO_CP);
        us_volume_map.put("pint", L_TO_PT);
        us_volume_map.put("quart", L_TO_QT);
        us_volume_map.put("gallon", L_TO_GAL);
        us_volume_map.put("tablespoons", L_TO_TBSP);
        us_volume_map.put("teaspoons", L_TO_TSP);
        us_volume_map.put("tablespoon", L_TO_TBSP);
        us_volume_map.put("teaspoon", L_TO_TSP);
    }
    private boolean is_convertable;
    private Double initial_val;
    private String unit_to_convert_from;
    private String unit_to_convert_to;

    private Double converted_val;
    private HashMap<String, Double> metric_length_map = new HashMap<>();
    private HashMap<String, Double> metric_mass_map = new HashMap<>();
    private HashMap<String, Double> metric_volume_map = new HashMap<>();
    private HashMap<String, Double> us_length_map = new HashMap<>();
    private HashMap<String, Double> us_mass_map = new HashMap<>();
    private HashMap<String, Double> us_volume_map = new HashMap<>();
}
