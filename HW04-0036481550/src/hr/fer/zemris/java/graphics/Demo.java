package hr.fer.zemris.java.graphics;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import hr.fer.zemris.java.graphics.raster.BWRaster;
import hr.fer.zemris.java.graphics.raster.BWRasterMem;
import hr.fer.zemris.java.graphics.shapes.Circle;
import hr.fer.zemris.java.graphics.shapes.Ellipse;
import hr.fer.zemris.java.graphics.shapes.Rectangle;
import hr.fer.zemris.java.graphics.shapes.Square;
import hr.fer.zemris.java.graphics.shapes.Triangle;
import hr.fer.zemris.java.graphics.views.SimpleRasterView;
import hr.fer.zemris.java.graphics.shapes.GeometricShape;

/**
 * {@code Demo} is a simple class used by user who is going to input what he
 * shapes he/she wants to draw. You should call this program with 2 or 1
 * arguments which represent size of the raster. After that you should input to
 * standard input a number of shapes you want to draw and the type the shape.
 * <p>
 * Shapes: <br>
 * - RECTANGLE "x" "y" "width" "height" <br>
 * - SQUARE "x" "y" "size" <br>
 * - ELLIPSE "x" "y" "horizontal radius" "vertical radius" <br>
 * - CIRCLE "x" "y" "radius" <br>
 * - TRIANGLE "x1" "y1" "x2" "y2" "x3" "y3"
 * 
 * @author Karlo Vrbić
 * @version 1.0
 */
public class Demo {

    /** Shows if flip mode is turned on or off. */
    private static boolean flip = false;

    /**
     * Starting point of a program.
     * 
     * @param args
     *            Command-line argument
     * @throws IOException
     *             if I/O exception occurred
     */
    public static void main(String[] args) throws IOException {
        BWRaster raster = null;
        System.setIn(
                new FileInputStream(
                        "E:\\OPJJ\\HW04-0036481550\\examples\\example1.txt"));

        // Process command line arguments
        if (args.length == 2) {
            try {
                raster = new BWRasterMem(
                        Integer.parseInt(args[0]),
                        Integer.parseInt(args[1]));
            } catch (NumberFormatException e) {
                System.err.println("Arguments must be of integer type!");
                System.exit(2);
            }
        } else if (args.length == 1) {
            try {
                raster = new BWRasterMem(
                        Integer.parseInt(args[0]),
                        Integer.parseInt(args[0]));
            } catch (NumberFormatException e) {
                System.err.println("Argument must be of integer type!");
                System.exit(3);
            }
        } else {
            System.err.println("You need to provide 1 or 2 arguments!");
            System.exit(1);
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Get number of shapes
        int numOfShapes = 0;

        try {
            numOfShapes = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.err.println(
                    "You need to provide integer number that "
                            + "represents number of shapes");
            System.exit(4);
        }

        // Get shapes from standard input
        GeometricShape[] shapes = new GeometricShape[numOfShapes];

        for (int i = 0; i < numOfShapes; i++) {
            String line = reader.readLine().trim();

            shapes[i] = makeShapeFromString(line.split(" "));
        }

        reader.close();

        // Draw shapes
        for (GeometricShape shape : shapes) {
            if (shape == null) {
                toggleFlip(raster);
            } else if (shape instanceof Triangle && flip) {
                toggleFlip(raster);
                shape.draw(raster);
                toggleFlip(raster);
            } else {
                shape.draw(raster);
            }
        }

        SimpleRasterView view = new SimpleRasterView();
        view.produce(raster);
    }

    /**
     * Returns a new {@code GeometricShape}
     * 
     * @param args
     *            string describing a geometric shape
     * @return a new {@code GeometricShape}
     */
    private static GeometricShape makeShapeFromString(String... args) {
        try {
            switch (args[0].toLowerCase()) {
                case "rectangle":
                    return new Rectangle(
                            Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]),
                            Integer.parseInt(args[3]),
                            Integer.parseInt(args[4]));
                case "square":
                    return new Square(
                            Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]),
                            Integer.parseInt(args[3]));
                case "ellipse":
                    return new Ellipse(
                            Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]),
                            Integer.parseInt(args[3]),
                            Integer.parseInt(args[4]));
                case "circle":
                    return new Circle(
                            Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]),
                            Integer.parseInt(args[3]));
                case "triangle":
                    return new Triangle(
                            Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]),
                            Integer.parseInt(args[3]),
                            Integer.parseInt(args[4]),
                            Integer.parseInt(args[5]),
                            Integer.parseInt(args[6]));
                case "flip":
                    return null;
                default:
                    throw new IllegalArgumentException(
                            "Shape \"" + args[0] + "\" doesn't exists!");
            }
        } catch (NumberFormatException e) {
            System.err.println("You need to enter arguments in integer type!");
            System.exit(5);
        }

        return null;
    }

    /**
     * Toggles flip mode of the raster.
     * 
     * @param raster
     *            raster which we use for drawing
     */
    private static void toggleFlip(BWRaster raster) {
        if (flip) {
            raster.disableFlipMode();
            flip = false;
        } else {
            raster.enableFlipMode();
            flip = true;
        }
    }
}
