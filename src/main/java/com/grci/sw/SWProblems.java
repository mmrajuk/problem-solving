package com.grci.sw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SWProblems {

    /**
     *
     * @param input
     * @param k
     * @return
     *
     * Given an array, find the average of all contiguous subarrays of size ‘K’ in it using sliding window pattern.
     *
     */
    public List<Double> findAverageUsingSW(List<Integer> input, int k){
        double window_sum = 0;
        List<Double> avgList = new ArrayList<>();
        for(int i=0; i<k; i++)
            window_sum += input.get(i);
        avgList.add(window_sum/k);
        for(int i=k; i<input.size(); i++){
            window_sum -= input.get(i-k);
            window_sum += input.get(i);
            avgList.add(window_sum/k);
        }
        return avgList;
    }





    /**
     *
     * @param input
     * @param k sublist
     * @return
     *
     * Given an array of positive numbers and a positive number ‘k,’ find the maximum sum of any contiguous subarray of size ‘k’.
     *
     */
    public int findMaxSumOfSublist(List<Integer> input, int k) {
        int maxSum = 0;
        int window_sum = 0;
        for(int i=0; i<input.size(); i++){
            window_sum += input.get(i);
            if(i>=k){
                window_sum -= input.get(i-k);
                maxSum = Math.max(window_sum,maxSum);
            } else {
                if(i%k-1==0) maxSum = Math.max(window_sum,maxSum);
            }
        }
        return maxSum;
    }


    /**
     *
     * @param input
     * @param sum given sum
     * @return
     *
     * Given an array of positive numbers and a positive number ‘S,’ find the length of the smallest contiguous subarray whose sum is greater than or equal to ‘S’. Return 0 if no such subarray exists.
     */
    public int findSmallestSubListSize(List<Integer> input, int sum){
        //first find out the first sublist of sum equals to or greater than the given sum
        int window_start = 0;
        int window_sum = 0;
        int min_size = Integer.MAX_VALUE;
        for(int window_end=0; window_end<input.size(); window_end++){
            window_sum += input.get(window_end);
            while (window_sum >= sum){
                min_size = Math.min(min_size,window_end-window_start+1);
                window_sum -= input.get(window_start);
                window_start++;
            }
        }
        return min_size;
    }


    /**
     *
     * @param input
     * @param k
     * @return
     *
     * Given a string, find the length of the longest substring in it with no more than K distinct characters.
     */
    public int longestSubstringWithKDistinctCharacters(String input, int k){
        int result = Integer.MIN_VALUE;
        char[] inp = input.toCharArray();
        int win_start = 0;
        Map<Character,Integer> charMap = new HashMap<>();
        for(int win_end=0; win_end<inp.length; win_end++){
            charMap.put(inp[win_end],charMap.getOrDefault(inp[win_end],0)+1);
            while(charMap.size()>k){
                int occurences = charMap.get(inp[win_start])-1;
                if(occurences == 0)
                    charMap.remove(inp[win_start]); //remove from map
                else
                    charMap.put(inp[win_start],occurences);
                win_start++;
            }
            result = Math.max(result,win_end-win_start+1);
        }
        return  result;
    }

    /**
     *
     * @param fruits
     * @param k
     * @return
     *
     * Given an array of characters where each character represents a fruit tree, you are given two baskets,
     * and your goal is to put maximum number of fruits in each basket.
     * The only restriction is that each basket can have only one type of fruit.
     */
    public int fruitsIntoBaskets(List<Character> fruits,int k){
        int win_start = 0;
        int max_fruits = Integer.MIN_VALUE;
        Map<Character,Integer> fruitsMap = new HashMap<>();
        for(int win_end=0; win_end<fruits.size(); win_end++){
            fruitsMap.put(fruits.get(win_end),fruitsMap.getOrDefault(fruits.get(win_end),0)+1);
            if(win_end>=k){

            }
        }
        return 0;
    }
}
