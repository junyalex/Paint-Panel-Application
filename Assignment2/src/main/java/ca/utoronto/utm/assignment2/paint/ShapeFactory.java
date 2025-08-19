package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.paint.strategy.*;
import javafx.scene.paint.Color;

import java.util.HashMap;

/**
 * The shape factory is responsible for creating and managing shape
 * drawing strategies. It provides a collection of draw strategies and
 * color mappings for creating various shapes.
 */
public class ShapeFactory {

    public static HashMap<String, DrawStrategy> shapes = new HashMap<>();

    /**
    A map that holds predefined color mappings
     */
    public static final HashMap<String, Color> colors = new HashMap<>() {{
        put("Red", Color.RED);
        put("Orange", Color.ORANGE);
        put("Yellow", Color.YELLOW);
        put("Green", Color.GREEN);
        put("Blue", Color.BLUE);
        put("Black", Color.BLACK);
    }};

    /**
     * A map with the different drawing strategies
     */
    static {
        shapes.put("◯", new CircleDrawStrategy());
        shapes.put("ㅁ", new RectangleDrawStrategy());
        shapes.put("□", new SquareDrawStrategy());
        shapes.put("⬭", new OvalDrawStrategy());
        shapes.put("△", new TriangleDrawStrategy());
        shapes.put("〜", new ScribbleDrawStrategy());
        shapes.put("ㄴ", new PolylineDrawStrategy());
    }

    /**
     *  returns a drawing strategy for the specifies shape
     * @param s type of shape
     * @return instance of Draw Strategy for the requested shape
     */
    public DrawStrategy makeStrategy(String s){
        return shapes.get(s);
    }
}
