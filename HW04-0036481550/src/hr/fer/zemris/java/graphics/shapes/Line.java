package hr.fer.zemris.java.graphics.shapes;

import hr.fer.zemris.java.graphics.raster.BWRaster;

/**
 * The {@code Line} class represents a line. In order to construct it you must
 * provide coordinates of two points.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class Line extends GeometricShape {

    /**
     * The X coordinate of the first pixel.
     */
    private int x1;

    /**
     * The Y coordinate of the first pixel.
     */
    private int y1;

    /**
     * The X coordinate of the second pixel.
     */

    private int x2;
    /**
     * The Y coordinate of the second pixel.
     */
    private int y2;

    /**
     * Constructs a {@code Line} object with specified coordinates of two
     * pixels.
     * 
     * @param x1
     *            the X coordinate of the first pixel.
     * @param y1
     *            the Y coordinate of the first pixel.
     * @param x2
     *            the X coordinate of the second pixel.
     * @param y2
     *            the Y coordinate of the second pixel.
     */
    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
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
        // delta of exact value and rounded value of the dependant variable
        int d = 0;

        int dy = Math.abs(y2 - y1);
        int dx = Math.abs(x2 - x1);

        int dy2 = (dy << 1); // slope scaling factors to avoid floating
        int dx2 = (dx << 1); // point

        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;

        if (dy <= dx) {
            for (;;) {
                if (x == x1 && y == y1) {
                    return true;
                }

                if (x1 == x2) {
                    break;
                }

                x1 += ix;
                d += dy2;

                if (d > dx) {
                    y1 += iy;
                    d -= dx2;
                }
            }
        } else {
            for (;;) {
                if (x == x1 && y == y1) {
                    return true;
                }

                if (y1 == y2) {
                    break;
                }

                y1 += iy;
                d += dx2;

                if (d > dy) {
                    x1 += ix;
                    d -= dy2;
                }
            }
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
        // delta of exact value and rounded value of the dependant variable
        int d = 0;

        int dy = Math.abs(y2 - y1);
        int dx = Math.abs(x2 - x1);

        int dy2 = (dy << 1); // slope scaling factors to avoid floating
        int dx2 = (dx << 1); // point

        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;

        if (dy <= dx) {
            for (;;) {
                r.turnOn(x1, y1);

                if (x1 == x2) {
                    break;
                }

                x1 += ix;
                d += dy2;

                if (d > dx) {
                    y1 += iy;
                    d -= dx2;
                }
            }
        } else {
            for (;;) {
                r.turnOn(x1, y1);

                if (y1 == y2) {
                    break;
                }

                y1 += iy;
                d += dx2;

                if (d > dy) {
                    x1 += ix;
                    d -= dy2;
                }
            }
        }
    }

}
