package hr.fer.zemris.java.cstr;

/**
 * The {@code ArrayUtils} class contains various methods for manipulating
 * arrays.
 * 
 * @author Karlo Vrbic
 * @version 1.0
 */
public class ArrayUtils {

    /**
     * Copies the specified range of the specified array into a new array. The
     * initial index of the range (from) must lie between zero and
     * original.length, inclusive. The value at original[from] is placed into
     * the initial element of the copy (unless from == original.length or from
     * == to). Values from subsequent elements in the original array are placed
     * into subsequent elements in the copy. The final index of the range (to),
     * which must be greater than or equal to from, may be greater than
     * original.length, in which case (short)0 is placed in all elements of the
     * copy whose index is greater than or equal to original.length - from. The
     * length of the returned array will be to - from.
     * 
     * @param original
     *            the array from which a range is to be copied
     * @param from
     *            the initial index of the range to be copied, inclusive
     * @param to
     *            the final index of the range to be copied, exclusive. (This
     *            index may lie outside the array.)
     * @return a new array containing the specified range from the original
     *         array, truncated or padded with null characters to obtain the
     *         required length
     */
    public static char[] copyOfRange(char[] original, int from, int to) {
        if (original == null) {
            throw new NullPointerException("Argument original can't be null!");
        }

        if (to < 0) {
            throw new NegativeArraySizeException("Array size can't be negative!");
        }

        if (from < 0 || from > original.length) {
            throw new IndexOutOfBoundsException("Offset is out of bounds!");
        }

        char[] copy = new char[Math.min(original.length, to)];

        System.arraycopy(original, from, copy, 0, Math.min(original.length, to));

        return copy;
    }

    /**
     * Copies the specified array, truncating or padding with nulls (if
     * necessary) so the copy has the specified length. For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values. For any indices that are valid in the copy but
     * not the original, the copy will contain null. Such indices will exist if
     * and only if the specified length is greater than that of the original
     * array. The resulting array is of exactly the same class as the original
     * array.
     * 
     * @param original
     *            the array to be copied
     * @param newLength
     *            the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with nulls to
     *         obtain the specified length
     */
    public static char[] copyOf(char[] original, int newLength) {
        return copyOfRange(original, 0, newLength);
    }

    /**
     * Copies the specified array with length or number of chars in the same
     * array. For all indices that are valid in both the original array and the
     * copy, the two arrays will contain identical values. The resulting array
     * is of exactly the same class as the original array.
     * 
     * @param original
     *            the array to be copied
     * @return a copy of the original array, truncated or padded with nulls to
     *         obtain the specified length
     */
    public static char[] copyOf(char[] original) {
        return copyOfRange(original, 0, numberOfChars(original));
    }

    /**
     * Returns the number of characters(except '\0' character) is char array
     * 
     * @param array
     *            the array to count characters from
     * @return the number of characters(except '\0' character) is char array
     */
    public static int numberOfChars(char[] array) {
        int size = 0;

        while (size < array.length && array[size] != '\0') {
            size++;
        }

        return size;
    }
}
