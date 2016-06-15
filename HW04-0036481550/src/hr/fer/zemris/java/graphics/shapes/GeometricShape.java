package hr.fer.zemris.java.graphics.shapes;

import hr.fer.zemris.java.graphics.raster.BWRaster;

/**
 * The {@code GeomtericShape} is an abstract class representing geometric
 * shapes. YOu need to implement {@link #containsPoint(int, int)} and it already
 * has {@link #draw(BWRaster)} method implemented.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public abstract class GeometricShape {

    /**
     * Checks if this geometric shape contains pixel at the specified position.
     * 
     * @param x
     *            the X coordinate of pixel we are checking out
     * @param y
     *            the Y coordinate of pixel we are checking out
     * @return {@code true} if this shape contains specified pixel;
     *         {@code false} otherwise
     */
    public abstract boolean containsPoint(int x, int y);

    /**
     * Draws geometric shape on the provided raster.
     * 
     * @param r
     *            {@code Raster} on which this method will draw
     */
    public void draw(BWRaster r) {
        int height = r.getHeight();
        int width = r.getWidth();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (containsPoint(x, y)) {
                    r.turnOn(x, y);
                }
            }
        }
    }

}
