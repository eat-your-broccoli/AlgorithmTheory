package engineer.trustmeimansoftware.algtheory.week05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CharSort {

    public <T> void qsort(T[] a, int l, int r, Comparator<T> c, IPivotFinder p) {
        int m = p.getPivot(a, l, r, c);

        if ((r - l) > 2){ // large problem:
            int ll = l;
            int rr = r;
            T pivot = a[m]; // pivot element
            do {
                // find an element from the left that is larger than pivot
                while (c.compare(a[ll], pivot) < 0) ll++; // search ll
                // find a element from the right that is smaller than pivot
                while (c.compare(a[rr], pivot) > 0) rr--; // search rr
                if (ll <= rr) swap (a, ll++, rr--); // swap
            } while (ll <= rr); // until done
            if (l < rr) qsort (a, l, rr, c, p); // conquer left
            if (ll < r) qsort(a, ll, r, c, p); // conquer right
        }
    }

    public static <T> void swap(T[] a, int l, int r) {
        if(l == r) return;
        T tmp = a[l];
        a[l] = a[r];
        a[r] = tmp;
    }
}

interface IPivotFinder {
    <T> int getPivot(T[] a, int l, int r, Comparator<T> c);
}

class PivotFinder {
    static class ThreeWayPivot implements IPivotFinder {
        public <T> int getPivot(T[] a, int l, int r, Comparator<T> c) {
            int m = (l+r)/2;
            if (c.compare(a[l], a[m]) > 0) CharSort.swap(a, l, m); // small problem
            if (c.compare(a[m], a[r]) > 0) CharSort.swap(a, m, r); // and presort
            if (c.compare(a[l], a[m]) > 0) CharSort.swap(a, l, m); // with bubble sort

            return m;
        }
    }

    static class RandomPivot implements IPivotFinder {
        RNG rng = new RNG();
        public <T> int getPivot(T[] a, int l, int r, Comparator<T> c) {
            int m = rng.rand(l, r);
            if (c.compare(a[l], a[m]) > 0) CharSort.swap(a, l, m); // small problem
            if (c.compare(a[m], a[r]) > 0) CharSort.swap(a, m, r); // and presort
            if (c.compare(a[l], a[m]) > 0) CharSort.swap(a, l, m); // with bubble sort

            return m;
        }
    }

    public static ThreeWayPivot threeWayPivot = new ThreeWayPivot();
    public static RandomPivot randomPivot = new RandomPivot();
}

class CharArrayComparator implements Comparator<Character[]> {
    int counter = 0;
    @Override
    public int compare(Character[] c1, Character[] c2) {
        counter++;
        int length = Math.min(c1.length, c2.length);
        for(int i = 0; i < length; i++) {
            int res = c1[i].compareTo(c2[i]);
            if(res != 0 ) return res;
        }
        return Integer.compare(c1.length, c2.length);
    }
}