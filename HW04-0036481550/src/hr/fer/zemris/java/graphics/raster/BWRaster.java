package hr.fer.zemris.java.graphics.raster;

/**
 * Interface for Black-and-White raster. This is an abstraction for all raster
 * devices of fixed width and height for which each pixel can be painted with
 * only two colors: black (when pixel is turned off) and white (when pixel is
 * turned on).
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public interface BWRaster {

    /**
     * Returns the width of the raster.
     * 
     * @return the width of the raster
     */
    int getWidth();

    /**
     * Returns the height of the raster.
     * 
     * @return the height of the raster
     */
    int getHeight();

    /**
     * Turns off all pixels.
     */
    void clear();

    /**
     * If flipping mode of raster is disabled, then the call of the turnOn
     * method turns on the pixel at specified location (again, if location is
     * valid). However, if flipping mode is enabled, then the call of the turnOn
     * method flips the pixel at the specified location (if it was turned on, it
     * must be turned off, and if it was turned off, it must be turned on).
     * 
     * @param x
     *            the X coordinate of the pixel location
     * @param y
     *            The Y coordinate of the pixel location
     */
    void turnOn(int x, int y);

    /**
     * Turns off pixel specified with X and Y coordinates.
     * 
     * @param x
     *            the X coordinate of the pixel location
     * @param y
     *            The Y coordinate of the pixel location
     */
    void turnOff(int x, int y);

    /**
     * Enables flip mode.
     */
    void enableFlipMode();

    /**
     * Disables flip mode.
     */
    void disableFlipMode();

    /**
     * Checks if pixel specified with X and Y coordinates is turned on.
     * 
     * @param x
     *            the X coordinate of the pixel location
     * @param y
     *            The Y coordinate of the pixel location
     * @return {@code true} if pixel specified with X and Y coordinates is on;
     *         {@code false} otherwise
     */
    boolean isTurnedOn(int x, int y);

}
