package engineer.trustmeimansoftware.algtheory.week05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Sort {

    static int countCompare = 0;
    static int countSwap = 0;
    private static Comparator<Integer> compareInteger = new CompareInteger();

    public static void quickSort(int[] a, int l, int r) {
        quickSort(a, l, r, compareInteger);
    }

    public static void quickSort(int[] a, int l, int r, Comparator<Integer> comp) {
        int m = (l+r)/2;
        if (comp.compare(a[l], a[m]) > 0) swap(a, l, m); // small problem
        if (comp.compare(a[m], a[r]) > 0) swap(a, m, r); // and presort
        if (comp.compare(a[l], a[m]) > 0) swap(a, l, m); // with bubble sort

        if ((r - l) > 2){ // large problem:
            int ll = l;
            int rr = r;
            int pivot = a[m]; // pivot element
            do {
                // find an element from the left that is larger than pivot
                while (comp.compare(a[ll], pivot) < 0) ll++; // search ll
                // find a element from the right that is smaller than pivot
                while (comp.compare(a[rr], pivot) > 0) rr--; // search rr
                if (ll <= rr) swap (a, ll++, rr--); // swap
            } while (ll <= rr); // until done
            if (l < rr) quickSort (a, l, rr, comp); // conquer left
            if (ll < r) quickSort (a, ll, r, comp); // conquer right
        }
    }

    public static void quickSort(int[] a) {
        quickSort(a, compareInteger);
    }

    public static void quickSort(int[] a, Comparator<Integer> comp) {
        countSwap = 0;
        countCompare = 0;
        quickSort(a, 0, a.length-1, comp);
    }


    public static void swap(int[] a, int i, int j) {
        countSwap++;
        if(i == j) return;
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


    // reference https://www.cs.dartmouth.edu/~doug/mdmspe.pdf
    // http://wwwa.pikara.ne.jp/okojisan/sort-killer/qsort-killer.html
    public static int[] breakQSort(int length)
    {
        int[] arr = new int[length];
        for(int i = 0; i < length; i++) arr[i] = i;
        int[] ret = new int[length];

        QuickSortKiller qsk = new QuickSortKiller();
        Sort.quickSort(arr, qsk);

        for (int i = 0; i < length; i++)
        {
            ret[arr[i]] = i;
        }

        return ret;
    }

    static class CompareInteger implements Comparator<Integer> {
        @Override
        public int compare(Integer x, Integer y) {
            Sort.countCompare++;
            return Integer.compare(x,y);
        }
    }
}

/**
 * credit to http://wwwa.pikara.ne.jp/okojisan/sort-killer/qsort-killer.html
 */
class QuickSortKiller implements Comparator<Integer> {

    HashMap<Integer, Integer> keys = new HashMap<>();

    int candidate = 0;

    @Override
    public int compare(Integer x, Integer y) {
        if (!keys.containsKey(x) && !keys.containsKey(y)) {
            if(x.equals(candidate))
                keys.put(x, keys.size());
            else
                keys.put(y, keys.size());
        }
        if (!keys.containsKey(x)) { candidate = x; return  1; }
        if (!keys.containsKey(y)) { candidate = y; return -1; }
        return keys.get(x) - keys.get(y);
    }
}
