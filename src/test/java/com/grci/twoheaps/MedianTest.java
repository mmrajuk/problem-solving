package com.grci.twoheaps;

import com.common.Interval;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

public class MedianTest {

    @Test
    public void testMedianOfANumberStream(){
        Median median = new Median();
        median.insertNum(3);
        median.insertNum(1);
        Assert.assertEquals(2,median.findMedianOfANumberStream(),0);
        median.insertNum(5);
        Assert.assertEquals(3,median.findMedianOfANumberStream(),0);
        median.insertNum(4);
        Assert.assertEquals(3.5,median.findMedianOfANumberStream(),0);

        median = new Median();
        median.insertNum(19);
        median.insertNum(27);
        Assert.assertEquals(23,median.findMedianOfANumberStream(),0);
        median.insertNum(44);
        Assert.assertEquals(27,median.findMedianOfANumberStream(),0);
        median.insertNum(26);
        Assert.assertEquals(26.5,median.findMedianOfANumberStream(),0);
        median.insertNum(31);
        Assert.assertEquals(27,median.findMedianOfANumberStream(),0);

    }

    @Test
    public void testSlidingWindowMedian(){
        Median median = new Median();
        Assert.assertEquals(Lists.newArrayList(1.5,0.5,1.0,4.0),median.slidingWindowMedian(new int[]{1,2,-1,3,5},2));
    }

    @Test
    public void testMaximizeCapital(){
        Median median = new Median();
        Assert.assertEquals(6,
                median.findMaximumCapital(new int[] { 0, 1, 2 },
                        new int[] { 1, 2, 3 },2, 1));
        Assert.assertEquals(8,
                median.findMaximumCapital(new int[] { 0, 1, 2, 3 },
                        new int[] { 1, 2, 3, 5 }, 3, 0));
    }

    @Test
    public void testNextInterval(){
        Median median = new Median();
        Interval[] intervals = new Interval[] { new Interval(2, 3),
                new Interval(3, 4), new Interval(5, 6) };
        Assert.assertArrayEquals(new int[]{1,2,-1},median.findNextInterval(intervals));

        intervals = new Interval[] { new Interval(3, 4),
                new Interval(1, 5), new Interval(4, 6) };
        Assert.assertArrayEquals(new int[]{2,-1,-1},median.findNextInterval(intervals));
    }

}
