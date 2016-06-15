package hr.fer.zemris.java.graphics.raster;

/**
 * Black-and-White raster of fixed width and height for which each pixel can be
 * painted with only two colors: black (when pixel is turned off) and white
 * (when pixel is turned on).
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class BWRasterMem implements BWRaster {

    /**
     * Boolean matrix containing information about the state of every pixel(
     * {@code true} if pixel is on; {@code false} otherwise).
     */
    private boolean pixel[][];

    /**
     * Width of the raster.
     */
    private int width;

    /**
     * Height of the raster.
     */
    private int height;

    /**
     * Flag showing if raster is currently in flip mode
     */
    private boolean flip;

    /**
     * Constructs a {@code BWRasterMem} with specified width and height.
     * 
     * @param width
     *            width of the raster
     * @param height
     *            height of the raster
     */
    public BWRasterMem(int width, int height) {
        if (width <= 0) {
            throw new IndexOutOfBoundsException("Width can't be negative!");
        } else if (height <= 0) {
            throw new IndexOutOfBoundsException("Heigth can't be negative!");
        }

        this.pixel = new boolean[height][width];
        this.width = width;
        this.height = height;
        this.flip = false;
    }

    /**
     * Constructs a {@code BWRasterMem} from specified boolean matrix containing
     * information about state of pixels({@code true} if pixel is on;
     * {@code false} otherwise).
     * 
     * @param pixel
     *            boolean matrix containing information about state of pixels(
     *            {@code true} if pixel is on; {@code false} otherwise)
     */
    public BWRasterMem(boolean[][] pixel) {
        if (pixel == null) {
            throw new IllegalArgumentException(
                    "You can't provide null as argument!");
        }

        this.pixel = pixel;
        this.width = pixel[0].length;
        this.height = pixel.length;
        this.flip = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        pixel = new boolean[height][width];

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void turnOn(int x, int y) {
        if (!isValidPixel(x, y)) {
            return;
        }

        if (flip == false) {
            pixel[y][x] = true;
        } else {
            pixel[y][x] = isTurnedOn(x, y) ? false : true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void turnOff(int x, int y) {
        if (!isValidPixel(x, y)) {
            return;
        }

        pixel[y][x] = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableFlipMode() {
        flip = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disableFlipMode() {
        flip = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTurnedOn(int x, int y) {
        if (!isValidPixel(x, y)) {
            return false;
        }

        return pixel[y][x];
    }

    /**
     * Checks if pixel at specified coordinates is in boundaries of this raster.
     * 
     * @param x
     *            the X coordinate of the pixel location
     * @param y
     *            the Y coordinate of the pixel location
     * @return {@code true} if pixel is in boundaries of this raster;
     *         {@code false} otherwise
     */
    private boolean isValidPixel(int x, int y) {
        return (x >= 0 && x < this.width) || (y >= 0 && y < this.height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        int width = getWidth();
        int height = getHeight();

        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < height; y++) {
            builder.append(System.lineSeparator());
            for (int x = 0; x < width; x++) {
                if (isTurnedOn(x, y)) {
                    builder.append("*");
                } else {
                    builder.append(".");
                }
            }
        }

        return builder.toString();
    }

}
