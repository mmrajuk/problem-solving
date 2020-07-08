package com.grci.twoheaps;

import com.common.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Median {


    private PriorityQueue<Integer> maxHeap;

    private PriorityQueue<Integer> minHeap;

    public Median(){
        maxHeap = new PriorityQueue<>((a,b)-> b-a); //high to low
        minHeap = new PriorityQueue<>(); //low to high
    }

    /**
     *
     * @param num
     */
    public void insertNum(int num) {
        /**
         * 1. Always maintain max-heap and min-heap balanced for even number insertions.
         * 2. For odd number of elements, have extra element in max-heap
         * 3. Make sure inserting element in max-heap is less than min-heap root element.
         * 4. Make sure inserting element in min-heap is greater than max-heap root element.
         */

        //when max heap empty or both heaps are same, then insert into max-heap
        if(maxHeap.isEmpty() || maxHeap.size()==minHeap.size()){
            maxHeap.add(num);
        } else {
            //insert into min-heap
            minHeap.add(num);
        }

        if(minHeap.size()==0||maxHeap.size()==0)
            return;

        //now, compare max-heap root element with min-heap root element
        //if min-heap root element < max-heap root element, then swap
        if(minHeap.peek()< maxHeap.peek()){
            int small = maxHeap.poll();
            maxHeap.add(minHeap.poll());
            minHeap.add(small);
        }

        if(maxHeap.peek()> minHeap.peek()){
            int big = minHeap.poll();
            minHeap.add(maxHeap.poll());
            maxHeap.add(big);
        }
    }

    /**
     *
     * @return
     *
     * Design a class to calculate the median of a number stream. The class should have the following two methods:

        insertNum(int num): stores the number in the class
        findMedian(): returns the median of all numbers inserted in the class
        If the count of numbers inserted in the class is even, the median will be the average of the middle two numbers.
     */
    public double findMedianOfANumberStream(){
        double median = -1;
        if((maxHeap.size()+ minHeap.size())%2==0){
            median = (maxHeap.peek()+minHeap.peek())/2.0;
        } else {
            median = maxHeap.peek();
        }
        return median;
    }


    /**
     *
     * @param input
     * @param k
     * @return
     *
     * Given an array of numbers and a number ‘k’, find the median of all the ‘k’ sized sub-arrays (or windows) of the array.
     */
    public List<Double> slidingWindowMedian(int[] input, int k){

        int n = input.length;
        int wStart = 0;
        List<Double> kSubMedianList = new ArrayList<>();
        for(int wEnd=0; wEnd<n; wEnd++){
            if(wEnd-wStart==k-1){
                maxHeap.clear();
                minHeap.clear();
                for(int i=wStart; i<=wEnd; i++){
                    insertNum(input[i]);
                }
                kSubMedianList.add(findMedianOfANumberStream());
                wStart++;
            }
        }
        return kSubMedianList;
    }

    /**
     *
     * @param capital
     * @param profit
     * @param maxProjectsToBeSelected
     * @param initialCapital
     * @return
     *
     * Given a set of investment projects with their respective profits, we need to find the most profitable projects.
     * We are given an initial capital and are allowed to invest only in a fixed number of projects.
     * Our goal is to choose projects that give us the maximum profit.
     * We can start an investment project only when we have the required capital.
     * Once a project is selected, we can assume that its profit has become our capital.

        Example 1:

        Input: Project Capitals=[0,1,2], Project Profits=[1,2,3], Initial Capital=1, Number of Projects=2
        Output: 6
        Explanation:

        With initial capital of ‘1’, we will start the second project which will give us profit of ‘2’. Once we selected our first project, our total capital will become 3 (profit + initial capital).
        With ‘3’ capital, we will select the third project, which will give us ‘3’ profit.
        After the completion of the two projects, our total capital will be 6 (1+2+3).

        Example 2:

        Input: Project Capitals=[0,1,2,3], Project Profits=[1,2,3,5], Initial Capital=0, Number of Projects=3
        Output: 8
        Explanation:

        With ‘0’ capital, we can only select the first project, bringing out capital to 1.
        Next, we will select the second project, which will bring our capital to 3.
        Next, we will select the fourth project, giving us a profit of 5.
        After selecting the three projects, our total capital will be 8 (1+2+5).

        Solution #
        While selecting projects we have two constraints:

        We can select a project only when we have the required capital.
        There is a maximum limit on how many projects we can select.
        Since we don’t have any constraint on time, we should choose a project, among the projects for which we have enough capital, which gives us a maximum profit. Following this greedy approach will give us the best solution.

        While selecting a project, we will do two things:

        Find all the projects that we can choose with the available capital.
        From the list of projects in the 1st step, choose the project that gives us a maximum profit.
        We can follow the Two Heaps approach similar to Find the Median of a Number Stream. Here are the steps of our algorithm:

        Add all project capitals to a min-heap, so that we can select a project with the smallest capital requirement.
        Go through the top projects of the min-heap and filter the projects that can be completed within our available capital. Insert the profits of all these projects into a max-heap, so that we can choose a project with the maximum profit.
        Finally, select the top project of the max-heap for investment.
        Repeat the 2nd and 3rd steps for the required number of projects.
     *
     */
    public int findMaximumCapital(int[] capital, int[] profit, int maxProjectsToBeSelected, int initialCapital){

        int n = capital.length;

        PriorityQueue<Integer> minCapital = new PriorityQueue<>(n,(i1,i2)-> capital[i1]-capital[i2]);
        PriorityQueue<Integer> maxProfit = new PriorityQueue<>(n,(i1,i2)-> profit[i2]-profit[i1]);

        //first populate minCapital heap
        for(int i=0; i<n; i++){
            minCapital.add(capital[i]);
        }

        int availableCaptial = initialCapital;
        for(int j=0; j<maxProjectsToBeSelected; j++){
            //find all projects which can be started with available capital and insert it into max profit heap
            while(!minCapital.isEmpty() && minCapital.peek() <= availableCaptial){
                maxProfit.add(minCapital.poll());
            }

            //exit if we don't find any project can be started with available capital
            if(maxProfit.isEmpty())
                break;

            //select the project with the maximum profit
            availableCaptial  += profit[maxProfit.poll()];

        }
        return availableCaptial;
    }


    public int[] findNextInterval(Interval[] intervals){

        int n = intervals.length;
        // heap for finding the maximum start
        PriorityQueue<Integer> maxStartHeap = new PriorityQueue<>(n, (i1, i2) -> intervals[i2].start - intervals[i1].start);
        // heap for finding the minimum end
        PriorityQueue<Integer> maxEndHeap = new PriorityQueue<>(n, (i1, i2) -> intervals[i2].end - intervals[i1].end);
        int[] result = new int[n];
        for (int i = 0; i < intervals.length; i++) {
            maxStartHeap.offer(i);
            maxEndHeap.offer(i);
        }

        // go through all the intervals to find each interval's next interval
        for (int i = 0; i < n; i++) {
            int topEnd = maxEndHeap.poll(); // let's find the next interval of the interval which has the highest 'end'
            result[topEnd] = -1; // defaults to -1
            if (intervals[maxStartHeap.peek()].start >= intervals[topEnd].end) {
                int topStart = maxStartHeap.poll();
                // find the the interval that has the closest 'start'
                while (!maxStartHeap.isEmpty() && intervals[maxStartHeap.peek()].start >= intervals[topEnd].end) {
                    topStart = maxStartHeap.poll();
                }
                result[topEnd] = topStart;
                maxStartHeap.add(topStart); // put the interval back as it could be the next interval of other intervals
            }
        }
        return result;
    }

}
