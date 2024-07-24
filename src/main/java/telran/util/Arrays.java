package telran.util;
import java.util.function.Predicate;
import java.util.Comparator;
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

public static <T> void sort(T[] array, Comparator<T> comparator) {
    int length = array.length;
    boolean flSort = false;
    do {
        length--;
        flSort = true;
        for (int i = 0; i < length; i++) {
            if (comparator.compare(array[i], array[i + 1]) > 0) {
                swap(array, i, i + 1);
                flSort = false;
            }
        }
    } while (!flSort);
}

private static <T> void swap(T[] array, int i, int j) {
    T tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
}

public static <T> int binarySearch(T[] array, T key, Comparator<T> comp) {
    int left = 0;
    int right = array.length - 1;
    int middle = (left + right) / 2;
    while (left <= right && comp.compare(key, array[middle]) != 0) {
        if (comp.compare(key, array[middle]) < 0) {
            right = middle - 1;
        } else {
            left = middle + 1;
        }
        middle = (left + right) / 2;
    }
    return left > right ? -(left + 1) : middle;
}
@SuppressWarnings("unchecked")
public static <T> int binarySearch(T[] array, T key) {
    return binarySearch(array, key, (Comparator<T>) Comparator.naturalOrder());
}
public static <T> T[] insert(T [] array, int index, T item) {
    T[] res = java.util.Arrays.copyOf(array, array.length + 1);
    System.arraycopy(array, index, res, index + 1, array.length - index);
    res[index] = item;
    return res;
}
public static <T> T[] find(T[]array, Predicate<T> predicate) {
    T[] result = java.util.Arrays.copyOf(array, 0);
    for(int i = 0; i < array.length; i++) {
        if(predicate.test(array[i])) {
            result = insert(result, result.length, array[i]);
        }
    }
    return result;
}
public static <T> T[] removeIf (T[] array, Predicate<T> predicate){
    return find(array, predicate.negate());
}
public static String matchesRules(char[] chars,
CharacterRule[] mustBeRules, CharacterRule[] mustNotBeRule) {
   //TODO
   //consider the class Character for rules definition
   return "";
}
}
