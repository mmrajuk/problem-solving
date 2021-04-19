package com.grci.sw;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SlidingWindowTests {

    SWProblems swp = new SWProblems();

    @Test
    public void testSubListAverageSW(){
        List<Integer> input = Lists.newArrayList(1, 3, 2, 6, -1, 4, 1, 8, 2);
        int sublistSize = 5;
        Assert.assertEquals(Lists.newArrayList(2.2, 2.8, 2.4, 3.6, 2.8),
                swp.findAverageUsingSW(input,sublistSize));
    }

    /*@Test
    public void testSubListAverageBF(){
        List<Integer> input = Lists.newArrayList(1, 3, 2, 6, -1, 4, 1, 8, 2);
        int sublistSize = 5;
        Assert.assertEquals(Lists.newArrayList(2.2, 2.8, 2.4, 3.6, 2.8),
                swp.findAverageUsingBF(input,sublistSize));
    }*/

    @Test
    public void testMaxSumSublist() {
        List<Integer> input = Lists.newArrayList(2, 1, 5, 1, 3, 2);
        int sublistSize = 3;
        Assert.assertEquals(9,swp.findMaxSumOfSublist(input,sublistSize));

        input = Lists.newArrayList(2, 3, 4, 1, 5);
        sublistSize = 2;
        Assert.assertEquals(7,swp.findMaxSumOfSublist(input,sublistSize));
    }

    @Test
    public void testSmallestSublistSizeOfGivenSum(){
        List<Integer> input = Lists.newArrayList(2, 1, 5, 2, 3, 2);
        Assert.assertEquals(2,swp.findSmallestSubListSize(input,7));

        input = Lists.newArrayList(2, 1, 5, 2, 8);
        Assert.assertEquals(1,swp.findSmallestSubListSize(input,7));

        input = Lists.newArrayList(3, 4, 1, 1, 6);
        Assert.assertEquals(3,swp.findSmallestSubListSize(input,8));
    }

    @Test
    public void testLongestSubstringLengthOfKDistinctCharacters(){
        String input = "araaci";
        int distinctChars = 2;
        Assert.assertEquals(4,swp.longestSubstringWithKDistinctCharacters(input,distinctChars));

        input = "araaci";
        distinctChars = 1;
        Assert.assertEquals(2,swp.longestSubstringWithKDistinctCharacters(input,distinctChars));

        input = "cbbebi";
        distinctChars = 3;
        Assert.assertEquals(5,swp.longestSubstringWithKDistinctCharacters(input,distinctChars));
    }

}
