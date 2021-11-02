package engineer.trustmeimansoftware.algtheory.week05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharArrayComparatorTest {

    @Test
    public void test() {
        CharArrayComparator c = new CharArrayComparator();
        Character[] a = new Character[] {'a', 'b', 'c'};
        Character[] b = new Character[] {'b', 'b', 'd'};
        assertTrue(c.compare(a, b) < 0);
    }

    @Test
    public void test2() {
        CharArrayComparator c = new CharArrayComparator();
        Character[] a = new Character[] {'a', 'x', 'x', 'd'};
        Character[] b = new Character[] {'a', 'x', 'x'};
        assertTrue(c.compare(a, b) > 0);
    }

    @Test
    public void test3() {
        CharArrayComparator c = new CharArrayComparator();
        Character[] a = new Character[] {};
        Character[] b = new Character[] {'a', 'x', 'x'};
        assertTrue(c.compare(a, b) < 0);
    }

    @Test
    public void test4() {
        CharArrayComparator c = new CharArrayComparator();
        Character[] a = new Character[] {'a', 'b', 'c'};
        Character[] b = new Character[] {'a', 'b', 'c'};
        assertTrue(c.compare(a, b) == 0);
    }

    @Test
    public void test5() {
        String abc = "abcdefghijklmnopqrstuvwxyz";
        CharArrayComparator c = new CharArrayComparator();
        for(int i = 0; i < abc.length(); i++) {
            for(int j = 0; j < abc.length(); j++) {
                Character[] a = new Character[] {abc.charAt(i)};
                Character[] b = new Character[] {abc.charAt(j)};
                if(i < j) assertTrue(c.compare(a,b) < 0);
                else if(i > j) assertTrue(c.compare(a,b) > 0);
                else assertEquals(0, c.compare(a, b));
            }
        }
    }
}