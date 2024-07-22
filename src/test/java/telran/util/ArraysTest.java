
package telran.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static telran.util.Arrays.*;

import java.util.Random;
public class ArraysTest {
private static final int N_ELEMENTS = 1_000;
int[] numbers = {10, 7, 12, -4, 13, 3, 14};
@Test
void searchTest() {
    assertEquals(0, search(numbers, 10));
    assertEquals(6, search(numbers, 14));
    assertEquals(3, search(numbers, -4));
    assertEquals(-1, search(numbers,100));
}
@Test
void addTest() {
    int newNumber = 100;
    int [] expected = {10, 7, 12, -4, 13, 3, 14, newNumber};
    assertArrayEquals(expected, add(numbers, newNumber));
}
@Test
void insertTest(){
    //{10, 7, 12, -4, 13, 3, 14} - all numbers
    int newNumber = 30;
    int[] expected_0 ={newNumber, 10, 7, 12, -4, 13, 3, 14};
    int[] expected_3 = {10, 7, 12, newNumber, -4, 13, 3, 14};
    int[] expected_last = {10, 7, 12,  -4, 13, 3, 14, newNumber};
    assertArrayEquals(expected_0, insert(numbers, 0, newNumber));
    assertArrayEquals(expected_3, insert(numbers, 3, newNumber));
    assertArrayEquals(expected_last, insert(numbers, numbers.length, newNumber));
    assertThrowsExactly(ArrayIndexOutOfBoundsException.class, ()->insert(numbers, numbers.length + 1, newNumber));
    assertThrowsExactly(ArrayIndexOutOfBoundsException.class, ()->insert(numbers, -1, newNumber));
}
@Test
void removeTest(){
    //{10, 7, 12, -4, 13, 3, 14} - all numbers
    int[] expected_0 ={ 7, 12, -4, 13, 3, 14};
    int[] expected_3 = {10, 7, 12, 13, 3, 14};
    int[] expected_last = {10, 7, 12, -4, 13, 3};
    assertArrayEquals(expected_0, remove(numbers,0));
    assertArrayEquals(expected_3, remove(numbers, 3));
    assertArrayEquals(expected_last, remove(numbers, numbers.length-1));
    assertThrowsExactly(ArrayIndexOutOfBoundsException.class, ()->remove(numbers, numbers.length));
    assertThrowsExactly(ArrayIndexOutOfBoundsException.class, ()->remove(numbers, -1));
}

@Test
void sortTest() {
    int [] testNumbers = java.util.Arrays.copyOf(numbers, numbers.length);
    int[] expected = {-4, 3, 7, 10,  12,  13,  14};
    sort(testNumbers);
    assertArrayEquals(expected, testNumbers);
}
@Test
void sortTestRandomArray() {
    int[] array = getRandomArray(N_ELEMENTS);
    int limit = array.length - 1;
    sort(array);
    for(int i = 0; i < limit; i++) {
        assertTrue(array[i] <= array[i + 1]);
    }
}
private int[] getRandomArray(int nElements) {
    int[] res = new int[nElements];
    Random random = new Random();
    for(int i = 0; i < nElements; i++) {
        res[i] = random.nextInt();
    }
    return res;
}
@Test 
void binarySearchTest(){
    int [] sortedArrays= {-4, 3, 7, 10, 12, 13, 14 };
    assertEquals(0, binarySearch(sortedArrays, -4));
    assertEquals(1, binarySearch(sortedArrays, 3));
    assertEquals(4, binarySearch(sortedArrays, 12));

    assertEquals(-1, binarySearch(sortedArrays, -5));

    assertEquals(-4, binarySearch(sortedArrays, 8));

    assertEquals(-8, binarySearch(sortedArrays, 15));
}
@Test 
void insertSortedTest(){
    int[] sortedArray = { -4, 3, 7, 10, 13, 14, 16 };
        int[] testArray1 = { -8, -4, 3, 7, 10, 13, 14, 16 };
        int[] testArray2 = { -4, 3, 7, 10, 12, 13, 14, 16 };
        int[] testArray3 = { -4, 3, 7, 10, 13, 14, 16, 19 };
        assertArrayEquals(testArray1, insertSorted(sortedArray, -8));
        assertArrayEquals(testArray2, insertSorted(sortedArray, 12));
        assertArrayEquals(testArray3, insertSorted(sortedArray, 19));
    
}
@Test
    void isOneSwapTest() {
        int[] testArrayTrue1 = { 16, -4, 3, 7, 10, 13, 14, -8 };
        int[] testArrayTrue2 = { 12, 3, 7, 11, -4, 13, 14, 16 };
        int[] testArrayTrue3 = { -4, 3, 7, 10, 19, 14, 16, 13 };
        int[] testArrayFalse1 = { 16, 1, 3, 10, 7, 13, 14, 5 };
        int[] testArrayFalse2 = { 10, 7, 15, -4, 13, 3, 14 };
        int[] testArrayFalse3 = { -4, 3, 7, 10, 13, 14, 16 };
        assertEquals(true, isOneSwap(testArrayTrue1));
        assertEquals(true, isOneSwap(testArrayTrue2));
        assertEquals(true, isOneSwap(testArrayTrue3));
        assertEquals(false, isOneSwap(testArrayFalse1));
        assertEquals(false, isOneSwap(testArrayFalse2));
        assertEquals(false, isOneSwap(testArrayFalse3));
    }
    @Test
void sortAnyTypeTest() {
    String[] strings = {"lmn", "cfta", "w", "aa"};
    String[] expectedASCII = {"aa", "cfta", "lmn", "w"};
    String[] expectedLength = {"w", "aa", "lmn", "cfta"};
    sort(strings, new ComparatorASCII());
    assertArrayEquals(expectedASCII, strings);
    sort(strings, new ComparatorLength());
    assertArrayEquals(expectedLength, strings);
}

@Test
void binarySearchAnyTypesTest(){
    String[] strings = {"aa", "cfta", "lmn", "k"};
    Integer[] numbers = {5, 10, 32, 47, 50};
    assertEquals(0, binarySearch(strings, "aa", new ComparatorASCII()));
    assertEquals(-2, binarySearch(strings, "ad", new ComparatorASCII()));
    assertEquals(-3, binarySearch(strings, "k", new ComparatorASCII()));
    assertEquals(4, binarySearch(numbers, 50, new ComparatorIntegers()));
    assertEquals(0, binarySearch(numbers, 5, new ComparatorIntegers()));
    assertEquals(-6, binarySearch(numbers, 78, new ComparatorIntegers()));
}
@Test
void binarySearchNoComparator() {
    String [] strings ={"aa", "cfta", "lmn", "w"};
    Person prs1 = new Person(10, "Vasya");
    Person prs2 = new Person(20, "Itay");
    Person prs3 = new Person(30, "Sara");
    Person [] persons = {
        prs1, prs2, prs3
    };
    assertEquals(1, binarySearch(strings, "cfta"));
    assertEquals(0, binarySearch(persons, prs1));
    assertEquals(-1, binarySearch(persons, new Person(5, "Serg")));
}
@Test
void evenOddSorting() {
    Integer[] array = {7, -8, 10, -100, 13, -10, 99};
    Integer[] expected = {-100, -10, -8, 10, 99, 13, 7}; //even numbers in ascending order first, odd numbers in descending order after that
    sort(array, new EvenOddComparator());
    assertArrayEquals(expected, array);
}
@Test
void findTest() {
    Integer[] array = {7, -8, 10, -100, 13, -10, 99};
    Integer [] expected = {7, 13, 99};
    assertArrayEquals(expected, find(array, new OddNumbersPredicate()));
}
@Test
    void removeIfTest() {
        Integer[] array = { 7, -8, 10, -100, 13, -10, 99 };
        Integer[] expected = { -8, 10, -100, -10 };
        assertArrayEquals(expected, removeIf(array, new OddNumbersPredicate()));
    }
}

