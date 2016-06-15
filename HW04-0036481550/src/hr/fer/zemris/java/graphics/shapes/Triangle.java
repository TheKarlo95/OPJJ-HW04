package hr.fer.zemris.java.graphics.shapes;

import hr.fer.zemris.java.graphics.raster.BWRaster;

/**
 * The {@code Triangle} class represents a triangle. In order to construct it
 * you must provide coordinates of its three corners.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class Triangle extends GeometricShape {

    /**
     * The X coordinate of the first corner.
     */
    private int x1;

    /**
     * The Y coordinate of the first corner.
     */
    private int y1;

    /**
     * The X coordinate of the second corner.
     */
    private int x2;

    /**
     * The Y coordinate of the second corner.
     */
    private int y2;

    /**
     * The X coordinate of the third corner.
     */
    private int x3;

    /**
     * The Y coordinate of the third corner.
     */
    private int y3;

    /**
     * Constructs a new {@code Triangle} object from coordinates of its three
     * corners.
     * 
     * @param x1
     *            the X coordinate of the first corner.
     * @param y1
     *            the Y coordinate of the first corner.
     * @param x2
     *            the X coordinate of the second corner.
     * @param y2
     *            the Y coordinate of the second corner.
     * @param x3
     *            the X coordinate of the third corner.
     * @param y3
     *            the Y coordinate of the third corner.
     */
    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;

        this.switchCornersByY();
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
        /* here we know that v1.y <= v2.y <= v3.y */
        /* check for trivial case of bottom-flat triangle */
        if (y2 == y3) {
            fillBottomFlatTriangle(r, x1, y1, x2, y2, x3, y3);
        }
        /* check for trivial case of top-flat triangle */
        else if (y1 == y2) {
            fillTopFlatTriangle(r, x1, y1, x2, y2, x3, y3);
        } else {
            /*
             * general case - split the triangle in a top-flat and bottom-flat
             * one
             */
            int x4 = (int) (x1
                    + ((double) (y2 - y1) / (double) (y3 - y1)) * (x3 - x1));
            int y4 = y2;

            fillBottomFlatTriangle(r, x1, y1, x2, y2, x4, y4);
            fillTopFlatTriangle(r, x2, y2, x4, y4, x3, y3);
        }
    }

    /**
     * Draws a filled triangle which bottom side is flat.
     * 
     * @param r
     *            raster on which this method will draw
     * @param x1
     *            the X coordinate of the first corner.
     * @param y1
     *            the Y coordinate of the first corner.
     * @param x2
     *            the X coordinate of the second corner.
     * @param y2
     *            the Y coordinate of the second corner.
     * @param x3
     *            the X coordinate of the third corner.
     * @param y3
     *            the Y coordinate of the third corner.
     */
    private void fillBottomFlatTriangle(
            BWRaster r,
            int x1,
            int y1,
            int x2,
            int y2,
            int x3,
            int y3) {
        double invslope1 = ((double) (x2 - x1)) / ((double) (y2 - y1));
        double invslope2 = ((double) (x3 - x1)) / ((double) (y3 - y1));

        double curx1 = x1;
        double curx2 = x1;

        for (int scanlineY = y1; scanlineY <= y2; scanlineY++) {
            new Line((int) curx1, scanlineY, (int) curx2, scanlineY).draw(r);
            curx1 += invslope1;
            curx2 += invslope2;
        }
    }

    /**
     * Draws a filled triangle which top side is flat.
     * 
     * @param r
     *            raster on which this method will draw
     * @param x1
     *            the X coordinate of the first corner.
     * @param y1
     *            the Y coordinate of the first corner.
     * @param x2
     *            the X coordinate of the second corner.
     * @param y2
     *            the Y coordinate of the second corner.
     * @param x3
     *            the X coordinate of the third corner.
     * @param y3
     *            the Y coordinate of the third corner.
     */
    private void fillTopFlatTriangle(
            BWRaster r,
            int x1,
            int y1,
            int x2,
            int y2,
            int x3,
            int y3) {
        double invslope1 = ((double) (x3 - x1)) / ((double) (y3 - y1));
        double invslope2 = ((double) (x3 - x2)) / ((double) (y3 - y2));

        double curx1 = x3;
        double curx2 = x3;

        for (int scanlineY = y3; scanlineY > y1; scanlineY--) {
            curx1 -= invslope1;
            curx2 -= invslope2;
            new Line((int) curx1, scanlineY, (int) curx2, scanlineY).draw(r);
        }
    }

    /**
     * Sorts corners by putting the one with highest Y coordinate in pixel 1(x1,
     * y1) and so on.
     */
    private void switchCornersByY() {
        if (y3 < y2) {
            int tmpX = x3;
            int tmpY = y3;

            x3 = x2;
            y3 = y2;

            x2 = tmpX;
            y2 = tmpY;
        }
        if (y2 < y1) {
            int tmpX = x2;
            int tmpY = y2;

            x2 = x1;
            y2 = y1;

            x1 = tmpX;
            y1 = tmpY;
        }
        if (y3 < y2) {
            int tmpX = x3;
            int tmpY = y3;

            x3 = x2;
            y3 = y2;

            x2 = tmpX;
            y2 = tmpY;
        }
    }
}
