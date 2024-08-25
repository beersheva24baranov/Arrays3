package telran.util;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class Arrays {
public static int search(int[] ar, int key){
    int index = 0; // начинаем с 0
    while(index < ar.length && key != ar[index]) { // пока индекс будет меньше длины массива и значение  из массива не будет равно  ключу перебираем индекс
        index++;
    }
    return index == ar.length ? -1 : index; // если длина массива совпадает с индексом то выводим -1 если не совпадает то индекс (искомого значения)
}
public static int[] add(int [] ar, int number) {
    int [] res = java.util.Arrays.copyOf(ar, ar.length + 1); // копируем массив т.к.  модифицировать нельзя с длиной + 1 от исходного)  
    res[ar.length] = number; // добавляем значение в последний индекс равный длине массива 
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
    // ar                -  src - исходный массив, 
    // index             -  srcPos - начальная позиция в исходном массиве (для вставки)
    // res               -  dest - целевой массив. 
    // index + 1         -  dstpos - начальная позиция в целевых данных.
    // ar.length - index - length - количество элементов массива, которые необходимо скопировать.
    res[index] = number; //добавляем нужное значение в нужны     индекс
    return res; // ar {10, 7, 12, -4, 13, 3, 14} newNumber = 30; int[] expected_0 ={newNumber, 10, 7, 12, -4, 13, 3, 14}; nsert(numbers, 0, newNumber)
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
    int[] res = java.util.Arrays.copyOf(numbers, numbers.length - 1); // Копируем массив  с длинной на 1 короче
        System.arraycopy(numbers, index + 1, res, index, res.length - index);// {10, 7, 12, -4, 13, 3, 14} ->  (expected_last, remove(numbers, numbers.length-1));  =    int[] expected_last = {10, 7, 12, -4, 13, 3};
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
public static String matchesRules(char[] chars, CharacterRule[] mustBeRules, CharacterRule[] mustNotBeRule) {
    List<String> errorMessages = new ArrayList<String>();
    errorMessages.addAll(checkRules(chars, mustBeRules));
    errorMessages.addAll(checkRules(chars, mustNotBeRule));
    return String.join(", ", errorMessages);
 }

 private static List<String> checkRules(char[] chars, CharacterRule[] rules) {
    List<String> result = new ArrayList<String>();
    for (int i = 0; i < rules.length; i++) {
        String errorMessage = checkRule(chars, rules[i]);
        if (errorMessage != null) result.add(errorMessage);
    }
    return result;
}
 private static String checkRule(char[] chars, CharacterRule rule) {
        String errorMessage = null;
        boolean isAbsent = true;
        int j = 0;
        while (isAbsent && j < chars.length) {
            if (rule.predicate.test(chars[j])) {
                isAbsent = false;
            }
            j++;
        }

        if ((rule.flag && isAbsent) || (!rule.flag && !isAbsent)) {
            errorMessage = rule.errorMessage;
        }

        return errorMessage;
    }
}