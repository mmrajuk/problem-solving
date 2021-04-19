package com;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConcordanceTest {


    @Test
    public void testGenerateConcordance(){
        List<String> input = new ArrayList<>();
        input.add("3");
        input.add("Note that the result messages colored in green: \"Compiled successfully.  ");
        input.add("All available test cases passed\" and \"Test case 0 (with a check mark)\" indicate that your code compiled successfully only and is NOT indicating your code output matches the expected output.  ");
        input.add("The message \"You received a total score of XX.X out of 15.0\" for each test case indicates how close your code output matches the expected output for that test case.");
        GenerateConcordance.generateAndPrintConcordance(input);

        input.clear();
        input.add("2");
        input.add("The desired output format is exactly as the below sample output shows (including the case, colons, sorting, spacing, commas, and braces).");
        input.add("Please do not make your own substitutions.");
        GenerateConcordance.generateAndPrintConcordance(input);


    }
}
