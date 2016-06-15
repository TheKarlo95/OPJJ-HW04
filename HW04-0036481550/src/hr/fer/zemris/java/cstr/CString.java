package hr.fer.zemris.java.cstr;

/**
 * The {@code CString} class represents character strings. The class
 * {@code CString} includes methods for examining individual characters of the
 * sequence, for comparing strings, for searching strings, for extracting
 * substrings, and for creating a copy of a string.
 * 
 * @author Karlo Vrbic
 * @version 1.0
 */
public class CString {

    /** Array which contains the data */
    private char[] data;

    /**
     * Constructs a new {@code CString} out of {@code char} array. Offset is the
     * index we start reading from.Length of the string will be defined by
     * argument length or length of the character array if it's smaller than
     * specified length.
     * 
     * @param data
     *            the data to be stored as character string
     * @param offset
     *            the index of the first character to be stored
     * @param length
     *            the index of the last character to be stored
     */
    public CString(char[] data, int offset, int length) {
        if (data == null) {
            throw new IllegalArgumentException("Argument data can't be null");
        } else if (offset < 0 || offset > data.length) {
            throw new IndexOutOfBoundsException(
                    "Offset should be between 0 and " + (data.length - 1));
        } else if (length < 0 || offset + length > data.length) {
            throw new IndexOutOfBoundsException(
                    "Length(+ offset) should be between 0 and "
                            + (data.length - 1));
        }

        this.data = ArrayUtils.copyOfRange(data, offset, length);
    }

    /**
     * Constructs a new {@code CString} out of char array. Offset is set to 0
     * and length is number of characters(except '\0' character) of the
     * specified {@code char} array.
     * 
     * @param data
     *            the data to be stored as character string
     */
    public CString(char[] data) {
        this(data, 0, ArrayUtils.numberOfChars(data));
    }

    /**
     * Constructs a new {@code CString} out of another {@code CString} object
     * effectively making a clone.
     * 
     * @param original
     *            {@code CString} to be cloned
     */
    public CString(CString original) {
        this(original.data);
    }

    /**
     * Constructs a new {@code CString} out of {@link String} object.
     * 
     * @param str
     *            the string to be stored in this object
     */
    public CString(String str) {
        this(str.toCharArray());
    }

    /**
     * Constructs a new {@code CString} out of {@code String} object.
     * 
     * @param s
     *            original {@code String} to be converted
     * @return {@code CString} with same content as {@code String}
     * @throws IllegalArgumentException
     *             if s is null
     */
    public static CString fromString(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Argument s can't be null");
        }
        return new CString(s.toCharArray());
    }

    /**
     * Returns the length of a {@code CString}.
     * 
     * @return the length of a {@code CString}
     */
    public int length() {
        return this.data.length;
    }

    /**
     * Returns the char value at the specified index. An index ranges from 0 to
     * {@link #length()} - 1. The first {@code char} value of the sequence is at
     * index 0, the next at index 1, and so on, as for array indexing.
     * 
     * @param index
     *            the index of the {@code char} value.
     * @return the index of the {@code char} value.
     */
    public char charAt(int index) {
        if (index < 0 || index > this.data.length) {
            throw new IndexOutOfBoundsException(
                    "Index should be between 0 and " + (data.length - 1));
        }

        return this.data[index];
    }

    /**
     * Converts this {@code CString} to a new character array.
     * 
     * @return a newly allocated character array whose length is the length of
     *         this string and whose contents are initialized to contain the
     *         character sequence represented by this string.
     */
    public char[] toCharArray() {
        return this.data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new String(this.data);
    }

    /**
     * Tests if this {@code CString} starts with the specified prefix.
     * 
     * @param s
     *            the suffix
     * @return true {@code true} if the character sequence represented by the
     *         argument is a prefix of the character sequence represented by
     *         this object; {@code false} otherwise. Note that the result will
     *         be {@code true} if the argument is the empty {@code CString} or
     *         is equal to this {@code CString} object as determined by the
     *         {@link #equals(Object)} method.
     * @throws IllegalArgumentException
     *             if s is null
     */
    public boolean startsWith(CString s) {
        if (s == null) {
            throw new IllegalArgumentException("Argument s can't be null");
        }

        return this.toString().startsWith(s.toString());
    }

    /**
     * Tests if this string ends with the specified suffix.
     * 
     * @param s
     *            the prefix
     * @return {@code true} if the character sequence represented by the
     *         argument is a suffix of the character sequence represented by
     *         this object; {@code false} otherwise. Note that the result will
     *         be {@code true} if the argument is the empty {@code CString} or
     *         is equal to this {@code CString} object as determined by the
     *         {@link #equals(Object)} method.
     * @throws IllegalArgumentException
     *             if s is null
     */
    public boolean endsWith(CString s) {
        if (s == null) {
            throw new IllegalArgumentException("Argument s can't be null");
        }

        return this.toString().startsWith(s.toString());
    }

    /**
     * Returns {@code true} if and only if this string contains sequence of char
     * values specified by {@code CString} argument.
     * 
     * @param s
     *            {@code CString} to search for
     * @return {@code true} if this string contains s, {@code false} otherwise
     * @throws IllegalArgumentException
     *             if s is null
     */
    public boolean contains(CString s) {
        if (s == null) {
            throw new IllegalArgumentException("Argument s can't be null");
        }

        return this.toString().contains(s.toString());
    }

    /**
     * Returns a new {@code CString} that is a substring of this {@code CString}
     * . The substring begins at the specified beginIndex and extends to the
     * character at index endIndex - 1. Thus the length of the substring is
     * endIndex-beginIndex.
     * 
     * @param startIndex
     *            the beginning index, inclusive.
     * @param endIndex
     *            the ending index, exclusive.
     * @return the specified substring
     * @throws IndexOutOfBoundsException
     *             if the beginIndex is negative, or endIndex is larger than the
     *             length of this String object, or beginIndex is larger than
     *             endIndex.
     */
    public CString substring(int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex > this.data.length
                || startIndex > endIndex) {
            throw new IndexOutOfBoundsException(
                    "Argument startIndex is greater than endIndex argument!");
        }
        return new CString(data, startIndex, endIndex - startIndex);
    }

    /**
     * Returns a new {@code CString} that is a substring of this {@code CString}
     * . The substring begins at the index 0 and extends to the character at
     * index n. Thus the length of the substring is n.
     * 
     * @param n
     *            the index of a character in this {@code CString}
     * @return substring which contains all of the characters left of the
     *         character at index n
     * @throws IllegalArgumentException
     *             if n is negative
     */
    public CString left(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Argument must be 0 or bigger!");
        }

        return substring(0, n);
    }

    /**
     * Returns a new {@code CString} that is a substring of this {@code CString}
     * . The substring begins at the index n + 1 and extends to the character at
     * index length - 1. Thus the length of the substring is n.
     * 
     * @param n
     *            the index of a character in this {@code CString}
     * @return substring which contains all of the characters right of the
     *         character at index n
     * @throws IllegalArgumentException
     *             if n is negative
     */
    public CString right(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Argument must be 0 or bigger!");
        }

        return substring(n + 1, length());
    }

    /**
     * Concatenates the specified string to the end of this string.
     * 
     * @param s
     *            the {@code CString} that is concatenated to the end of this
     *            {@code CString}
     * @return a string that represents the concatenation of this object's
     *         characters followed by the {@code CString} argument's characters.
     * @throws IllegalArgumentException
     *             if s is null
     */
    public CString add(CString s) {
        if (s == null) {
            throw new IllegalArgumentException("Argument s can't be null");
        }

        return fromString((this.toString().concat(s.toString())));
    }

    /**
     * Returns a new {@code CString} resulting from replacing all occurrences of
     * oldChar in this string with newChar.
     * 
     * @param oldChar
     *            the old character
     * @param newChar
     *            the new character
     * @return the resulting {@code CString}
     * @throws IllegalArgumentException
     *             if oldChar or newChar is '\0' character
     */
    public CString replaceAll(char oldChar, char newChar) {
        if (oldChar == '\0') {
            throw new IllegalArgumentException(
                    "Argument oldChar can't be \'0\' character");
        } else if (newChar == '\0') {
            throw new IllegalArgumentException(
                    "Argument newChar can't be \'0\' character");
        }

        return fromString(this.toString().replace(oldChar, newChar));
    }

    /**
     * Replaces each substring of this string that matches the given regular
     * expression with the given replacement.
     * 
     * @param oldStr
     *            the regular expression to which this string is to be matched
     * @param newStr
     *            the string to be substituted for each match
     * @return the resulting {@code CString}
     * @throws IllegalArgumentException
     *             if oldStr or newStr is null
     */
    public CString replaceAll(CString oldStr, CString newStr) {
        if (oldStr == null) {
            throw new IllegalArgumentException("Argument oldStr can't be null");
        } else if (newStr == null) {
            throw new IllegalArgumentException("Argument newStr can't be null");
        }

        return fromString(
                this.toString().replaceAll(
                        oldStr.toString(),
                        newStr.toString()));
    }

    /*
     * Indicates whether some other {@code CString} is "equal to" this one.
     * 
     * @param obj the reference object with which to compare
     * 
     * @return {@code true} if this object is the same as the obj argument;
     * {@code false} otherwise
     */

    /**
     * {@inheritDoc}
     * 
     * @param obj
     *            {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CString)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (this.data.length != ((CString) obj).data.length) {
            return false;
        }

        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] != ((CString) obj).data[i])
                return false;
        }

        return true;
    }
}
