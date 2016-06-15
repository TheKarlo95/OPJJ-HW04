package hr.fer.zemris.java.cstr;

import static org.junit.Assert.*;

import org.junit.Test;

@SuppressWarnings("javadoc")
public class CStringTest {

	@Test
	public void testFromString() {
		assertEquals(
				new CString(new char[] { 'a', 'b', 'c' }),
				CString.fromString("abc\0\0"));

		assertEquals(
				new CString(new char[] { 'a', 'b', 'c' }),
				CString.fromString("abc"));

		assertEquals(new CString(new char[] {}), CString.fromString(""));
	}

	@Test(
		expected = IllegalArgumentException.class)
	public void testFromStringIllegalData() {
		new CString(null, 0, 1);
	}

	@Test(
		expected = IndexOutOfBoundsException.class)
	public void testFromStringIllegalOffsetAndLength() {
		new CString(new char[] { 'a', 'b', 'c' }, 2, 2);
	}

	@Test
	public void testLength() {
		assertEquals(3, CString.fromString("abc").length());

		assertEquals(3, CString.fromString("abc\0\0").length());

		assertEquals(0, CString.fromString("").length());
	}

	@Test
	public void testCharAt() {
		CString str = CString.fromString("abc");

		assertEquals('a', str.charAt(0));
		assertEquals('b', str.charAt(1));
		assertEquals('c', str.charAt(2));

	}

	@Test(
		expected = IndexOutOfBoundsException.class)
	public void testCharAtError() {
		CString str = CString.fromString("abc");

		assertEquals('a', str.charAt(-1));
		assertEquals('b', str.charAt(3));
	}

	@Test
	public void testToCharArray() {
		assertArrayEquals(
				new char[] { 'a', 'b', 'c' },
				CString.fromString("abc\0\0").toCharArray());

		assertArrayEquals(
				new char[] { 'a', 'b', 'c' },
				CString.fromString("abc").toCharArray());

		assertArrayEquals(new char[] {}, CString.fromString("").toCharArray());

	}

	@Test
	public void testToString() {
		assertEquals("abc", CString.fromString("abc\0\0").toString());

		assertEquals("abc", CString.fromString("abc").toString());

		assertEquals("", CString.fromString("").toString());

	}

	@Test
	public void testFromHomework() {
		CString str = new CString("ababab")
				.replaceAll(new CString("ab"), new CString("abab"));

		assertEquals(new CString("abababababab"), str);
	}
}
