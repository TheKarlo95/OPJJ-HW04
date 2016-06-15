package hr.fer.zemris.java.graphics.shapes;

import hr.fer.zemris.java.graphics.raster.BWRaster;

/**
 * The {@code Ellipse} class creates a new ellipse with the specified horizontal
 * radius, vertical radius and center location measured in pixels.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class Ellipse extends GeometricShape {

    /**
     * The X coordinate of the center
     */
    private int x;

    /**
     * The Y coordinate of the center
     */
    private int y;

    /**
     * The horizontal radius of this ellipse
     */
    private int horizontal;

    /**
     * The vertical radius of this ellipse
     */
    private int vertical;

    /**
     * Last used raster on this ellipse
     */
    private BWRaster raster;

    /**
     * Constructs a new {@code Ellipse} using center coordinates, horizontal and
     * vertical radius.
     * 
     * @param x
     *            the X coordinate of the center
     * @param y
     *            the Y coordinate of the center
     * @param horizontal
     *            the horizontal radius of this ellipse
     * @param vertical
     *            the vertical radius of this ellipse
     */
    public Ellipse(int x, int y, int horizontal, int vertical) {
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    /**
     * {@inheritDoc}
     * 
     * @param r
     *            {@inheritDoc}
     */
    @Override
    public void draw(BWRaster r) {
        raster = r;
        int a2 = horizontal * horizontal;
        int b2 = vertical * vertical;

        int fa2 = 4 * a2;
        int fb2 = 4 * b2;

        // First half of ellipse
        int x = 0;
        int y = vertical;
        int sigma = 2 * b2 + a2 * (1 - 2 * vertical);

        for (; b2 * x <= a2 * y; x++) {
            r.turnOn(this.x + x, this.y + y);
            r.turnOn(this.x - x, this.y + y);
            r.turnOn(this.x + x, this.y - y);
            r.turnOn(this.x - x, this.y - y);
            if (sigma >= 0) {
                sigma += fa2 * (1 - y);
                y--;
            }
            sigma += b2 * ((4 * x) + 6);
        }

        // Second half of ellipse
        x = horizontal;
        y = 0;
        sigma = 2 * a2 + b2 * (1 - 2 * horizontal);

        for (; a2 * y <= b2 * x; y++) {
            r.turnOn(this.x + x, this.y + y);
            r.turnOn(this.x - x, this.y + y);
            r.turnOn(this.x + x, this.y - y);
            r.turnOn(this.x - x, this.y - y);
            if (sigma >= 0) {
                sigma += fb2 * (1 - x);
                x--;
            }
            sigma += a2 * ((4 * y) + 6);
        }

        fillEllipse(r);
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
        return raster.isTurnedOn(x, y);
    }

    /**
     * Fills ellipse if outline has been properly drawn on given raster before
     * calling this method.
     * 
     * @param r
     *            {@code Raster} on which this method will draw
     */
    private void fillEllipse(BWRaster r) {
        for (int fillY = this.y - this.vertical; fillY < this.y
                + this.vertical; fillY++) {
            for (int fillX = 0; !r.isTurnedOn(this.x + fillX, fillY); fillX++) {
                r.turnOn(this.x + fillX, fillY);
                r.turnOn(this.x - fillX, fillY);
            }
        }
    }
}
