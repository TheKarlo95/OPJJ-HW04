package hr.fer.zemris.java.graphics.shapes;

/**
 * The {@code Circle} class represents a circle with the specified radius and
 * center location measured in pixels.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class Circle extends Ellipse {

    /**
     * Constructs a {@code Circle} object with the specified radius and center
     * location measured in pixels.
     * 
     * @param x
     *            the X coordinate of the center
     * @param y
     *            the Y coordinate of the center
     * @param radius
     *            the radius of this circle
     */
    public Circle(int x, int y, int radius) {
        super(x, y, radius, radius);
    }

}
