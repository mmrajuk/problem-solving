package easy.sw;

public class MaxSumOfKConsecutiveElems {

    /**
     *
     * @param elements
     * @return
     *
     * Given an array of integers of size ‘n’.
     * Our aim is to calculate the maximum sum possible for ‘k’ consecutive elements in the array.
     */
    public int calculateMaxSum(Integer[] elements, int k) {
        int maxSum = 0; int window_sum=0;
        //calculate 1st window sum
        for(int i=0; i<k; i++) window_sum += elements[i];
        maxSum = window_sum;
        for(int i=k; i<elements.length; i++){
            window_sum = -(elements[i]) + elements[k-i];
            maxSum = Math.max(window_sum,maxSum);
        }
        return maxSum;
    }
}
