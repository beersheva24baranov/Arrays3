
package telran.util;

public class Arrays {
public static int search(int[] ar, int key){
    int index = 0;
    while(index < ar.length && key != ar[index]) {
        index++;
    }
    return index == ar.length ? -1 : index;
}
public static int[] add(int [] ar, int number) {
    int [] res = java.util.Arrays.copyOf(ar, ar.length + 1);
    res[ar.length] = number;
    return res;
}
/**
 * 
 * @param ar
 * @param index
 * @param number
 * @return reference to a new array containing @param number at @param index
 */
public static int[] insert(int[] ar, int index, int number) {
    //creates new array with all elements from the given "ar" and
    //the given "number" at the given index
    //to apply System.arraycopy method 
    int[] res = java.util.Arrays.copyOf(ar, ar.length + 1);
    System.arraycopy(ar, index, res, index + 1, ar.length - index);
    res[index] = number;
    return res; 
}
/**
 * 
 * @param numbers
 * @param index
 * @return new array with no removed from @param numbers number at @param index
 */
public static int[] remove(int[] numbers, int index) {
    //creates new array with no element in "numbers" at "index"
    //to apply System.arraycopy method 
    int[] res = java.util.Arrays.copyOf(numbers, numbers.length - 1);
    System.arraycopy(numbers, index + 1, res, index, res.length - index);
    return res;

}
private static boolean pushMaxAtEnd(int [] ar, int length) {
    boolean res = true;
    for(int i = 0; i < length; i++) {
        if (ar[i] > ar[i + 1]) {
            res = false;
            swap(ar, i, i + 1);
        }
    }
    return res;
}
private static void swap(int[] ar, int i, int j) {
    int tmp = ar[i];
    ar[i] = ar[j];
    ar[j] = tmp;
}
public static void sort(int [] ar) {
    int length = ar.length ;
    boolean flSorted = false;
    while(!flSorted) {
        length--;
        flSorted = pushMaxAtEnd(ar, length);
    }
}

/**
 * 
 * @param ar - sorted array
 * @param key - being searched number 
 * @return see comments definition
 */
public static int binarySearch(int [] ar, int key) {
    int left = 0;
    int right = ar.length - 1;

    while ( left <= right) {
        int middle = (left + right) / 2;

        if (ar[middle] == key) {
            return middle;
        }

        if (ar[middle] < key) {
            left = middle + 1;
        } else {
            right = middle - 1;
        }
    }
    return -(left + 1);
}
public static int[] insertSorted(int[] arSorted, int number) {

    int  insPosition = binarySearch(arSorted, number);

    if ( insPosition < 0) {
        insPosition = -(insPosition + 1);
    }

    int [] newAr = new int[arSorted.length + 1];

    System.arraycopy(arSorted, 0, newAr, 0, insPosition);

    newAr[insPosition] = number;

    System.arraycopy(arSorted, insPosition, newAr, insPosition + 1, arSorted.length - insPosition);

    return newAr;
}
public static boolean isOneSwap(int[] array) {
    int n = array.length;
    int first = -1, second = -1;

    for (int i = 0; i < n - 1; i++) {
        if (array[i] > array[i + 1]) {
            first = i;
            break;
        }
    }
    
    if (first == -1) {
        return false;
    }

    for (int i = n - 1; i > first; i--) {
        if (array[i] < array[first]) {
            second = i;
            break;
        }
    }

    swap(array, first, second);

    for (int i = 0; i < n - 1; i++) {
        if (array[i] > array[i + 1]) {
            return false;
        }
    }

    return true;
}

}
