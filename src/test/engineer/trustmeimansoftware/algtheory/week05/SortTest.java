package engineer.trustmeimansoftware.algtheory.week05;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    public void sort() {
        int[] a = new int[] {2,5,65,3,92,1,43,655,6,7};
        Sort.quickSort(a);
        System.out.println(Arrays.toString(a));
        assertEquals("[1, 2, 3, 5, 6, 7, 43, 65, 92, 655]", Arrays.toString(a));
        System.out.println("Comparisons: "+Sort.countCompare);
        System.out.println("Swaps: "+Sort.countSwap);
    }

    @Test
    public void sort20() {
        int[] a = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        shuffleArray(a);
        System.out.println(Arrays.toString(a));
        Sort.quickSort(a);
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]", Arrays.toString(a));
        System.out.println("Comparisons: "+Sort.countCompare);
        System.out.println("Swaps: "+Sort.countSwap);
    }


    // most inefficient so far [0, 17, 2, 10, 4, 14, 6, 12, 8, 1, 3, 5, 7, 9, 11, 13, 15, 16, 18, 19]
    @Test
    public void sort20worstcase() {
        int[] a = Sort.breakQSort(20);

        System.out.println("Trying to break quicksort");
        System.out.println("worst case problem: "+Arrays.toString(a));
        Sort.quickSort(a);
        System.out.println("breaking Comparisons: "+Sort.countCompare);
    }

    private static void shuffleArray(int[] array)
    {
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            if (index != i)
            {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }


}