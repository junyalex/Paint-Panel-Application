package ca.utoronto.utm.assignment2.paint;

import javafx.scene.paint.Color;

import java.util.HashMap;

public class ShapeFactory {

    public static HashMap<String, DrawStrategy> shapes = new HashMap<>();

    public static final HashMap<String, Color> colors = new HashMap<>() {{
        put("Red", Color.RED);
        put("Orange", Color.ORANGE);
        put("Yellow", Color.GOLDENROD);
        put("Green", Color.GREEN);
        put("Blue", Color.BLUE);
    }};

    static {
        shapes.put("◯", new CircleDrawStrategy());
        shapes.put("▭", new RectangleDrawStrategy());
        shapes.put("□", new SquareDrawStrategy());
        shapes.put("⬭", new OvalDrawStrategy());
        shapes.put("△", new TriangleDrawStrategy());
        shapes.put("Squiggle (〜)", new ScribbleDrawStrategy());
        shapes.put("Polyline (└───┐)", new PolylineDrawStrategy());
    }

    public DrawStrategy makeStrategy(String s){
        return shapes.get(s);
    }
}
