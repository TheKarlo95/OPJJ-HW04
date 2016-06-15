package hr.fer.zemris.java.graphics.views;

import hr.fer.zemris.java.graphics.raster.BWRaster;

/**
 * {@code SimpleRasterView} produces visual representation of given raster and
 * outputs it through standard output.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class SimpleRasterView implements RasterView {

    /**
     * Character representing turned on pixel.
     */
    private char onSign;

    /**
     * Character representing turned off pixel.
     */
    private char offSign;

    /**
     * Creates a new {@code SimpleRasterView} object from on and off signs.
     * 
     * @param onSign
     *            character representing turned on pixel.
     * @param offSign
     *            character representing turned off pixel.
     */
    public SimpleRasterView(char onSign, char offSign) {
        this.onSign = onSign;
        this.offSign = offSign;
    }

    /**
     * Creates a new {@code SimpleRasterView} object with '*' as on and '.' as
     * off sign.
     */
    public SimpleRasterView() {
        this('*', '.');
    }

    /**
     * Produces a visual representation of the raster
     * 
     * @param raster
     *            Raster to be visualized
     * @return {@code null} for all inputs
     */
    @Override
    public Object produce(BWRaster raster) {
        int width = raster.getWidth();
        int height = raster.getHeight();

        for (int y = 0; y < height; y++) {
            System.out.println();
            for (int x = 0; x < width; x++) {
                if (raster.isTurnedOn(x, y)) {
                    System.out.print(onSign);
                } else {
                    System.out.print(offSign);
                }
            }
        }
        return null;
    }

}
