package com;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConcordanceTest {


    @Test
    public void testGenerateConcordance(){
        List<String> input = new ArrayList<>();
        input.add("2");
        input.add("Wait a minute. a minute, Doc.");
        input.add("Are you telling me that you built a time machine out of a DeLorean?");
        GenerateConcordance.generateAndPrintConcordance(input);
    }
}
