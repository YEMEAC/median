package recruitment.median;

import java.util.List;

/**
 *
 * @author Yeison Melo <melo.yeison@gmail.com>
 */
public class QuickSort {

    public static int partition(List<Integer> numbers, int first, int last) {
        int pivot = numbers.get(first);
        int left = first;
        int right = last;

        while (left < right) {
            //Find an element bigger than the pivot from the left
            while (numbers.get(left) <= pivot && left < right) {
                left++;
            }
            //Find an element smaller than the pivot from the right
            while (numbers.get(right) > pivot) {
                right--;
            }
            //Swap the two elements found
            if (left < right) {
                numbers.set(left, numbers.set(right, numbers.get(left)));
            }
        }
        //move the pivot element to the middle
        numbers.set(first, numbers.set(right, numbers.get(first)));
        return right;
    }

    public static void quickSortRecursive(List<Integer> numbers, int first, int last) {
        if (first < last) {
            int pivot = partition(numbers, first, last);
            quickSortRecursive(numbers, first, pivot - 1); // sort the left part
            quickSortRecursive(numbers, pivot + 1, last); // sort the right part
        }
    }
}
