package hr.fer.zemris.java.graphics.shapes;

/**
 * The {@code Square} class represents a square. In order to construct it you
 * must provide coordinates of the upper left corner and size.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class Square extends Rectangle {

    /**
     * Constructs a {@code Square} object with the specified coordinates of
     * upper left corner and size of the square.
     * 
     * @param x
     *            the X coordinate of the upper left corner.
     * @param y
     *            the Y coordinate of the upper left corner.
     * @param size
     *            the size of the rectangle.
     */
    public Square(int x, int y, int size) {
        super(x, y, size, size);
    }

}
