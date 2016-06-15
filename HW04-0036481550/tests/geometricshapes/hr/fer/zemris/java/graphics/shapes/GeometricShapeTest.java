package hr.fer.zemris.java.graphics.shapes;

import static org.junit.Assert.*;

import org.junit.Test;

@SuppressWarnings("javadoc")
public class GeometricShapeTest {

	@Test
	public void testRectangleContainsPoint() {
		Rectangle rectangle = new Rectangle(0, 0, 5, 5);

		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 5; x++) {
				assertTrue(rectangle.containsPoint(x, y));
			}
		}

		assertFalse(rectangle.containsPoint(-1, 0));
		assertFalse(rectangle.containsPoint(0, -1));
		assertFalse(rectangle.containsPoint(-1, -1));

		assertFalse(rectangle.containsPoint(5, 4));
		assertFalse(rectangle.containsPoint(4, 5));
		assertFalse(rectangle.containsPoint(5, 5));
	}
	
	@Test
	public void testSquareContainsPoint() {
		Square square = new Square(0, 0, 5);

		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 5; x++) {
				assertTrue(square.containsPoint(x, y));
			}
		}

		assertFalse(square.containsPoint(-1, 0));
		assertFalse(square.containsPoint(0, -1));
		assertFalse(square.containsPoint(-1, -1));

		assertFalse(square.containsPoint(5, 4));
		assertFalse(square.containsPoint(4, 5));
		assertFalse(square.containsPoint(5, 5));
	}
}
