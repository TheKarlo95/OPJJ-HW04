package hr.fer.zemris.java.graphics.shapes;

import hr.fer.zemris.java.graphics.raster.BWRaster;

/**
 * The {@code Rectangle} class represents a rectangle. In order to construct it
 * you must provide coordinates of the upper left corner, width and height.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class Rectangle extends GeometricShape {

    /**
     * The X coordinate of the upper left corner.
     */
    private int x;

    /**
     * The Y coordinate of the upper left corner.
     */
    private int y;

    /**
     * The width of the rectangle.
     */
    private int width;

    /**
     * The height of the rectangle.
     */
    private int height;

    /**
     * Constructs a {@code Rectangle} object with the specified coordinates of
     * upper left corner, width and height of the rectangle.
     * 
     * @param x
     *            the X coordinate of the upper left corner.
     * @param y
     *            the Y coordinate of the upper left corner.
     * @param width
     *            the width of the rectangle.
     * @param height
     *            the height of the rectangle.
     */
    public Rectangle(int x, int y, int width, int height) {
        if (width <= 0) {
            throw new IllegalArgumentException(
                    "Width coordinate can't be negative or zero!");
        } else if (height <= 0) {
            throw new IllegalArgumentException(
                    "Height coordinate can't be negative or zero!");
        }

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * {@inheritDoc}
     * 
     * @param x
     *            {@inheritDoc}
     * @param y
     *            {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean containsPoint(int x, int y) {
        if (y >= this.y && y < this.y + height && x >= this.x
                && x < this.x + width) {
            return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @param r
     *            {@inheritDoc}
     */
    @Override
    public void draw(BWRaster r) {
        int height = r.getHeight();
        int width = r.getWidth();

        for (int y = this.y; y < height; y++) {
            for (int x = this.x; x < width; x++) {
                if (containsPoint(x, y)) {
                    r.turnOn(x, y);
                }
            }
        }
    }

}
