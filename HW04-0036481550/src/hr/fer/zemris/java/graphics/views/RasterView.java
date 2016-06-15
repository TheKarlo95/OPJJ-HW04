package hr.fer.zemris.java.graphics.views;

import hr.fer.zemris.java.graphics.raster.BWRaster;

/**
 * Represents a raster view that accepts one argument of type {@code BWRaster}
 * and produces a result of type {@code Object}.
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public interface RasterView {

    /**
     * Applies this raster view to the given argument.
     * 
     * @param raster
     *            raster to be viewed
     * @return the raster view result
     */
    Object produce(BWRaster raster);

}
