package ca.utoronto.utm.assignment2.paint;

import java.util.HashMap;

public class ShapeFactory {

    public static HashMap<String, DrawStrategy> shapes = new HashMap<>();

    static {
        shapes.put("◯", new CircleDrawStrategy());
        shapes.put("▭", new RectangleDrawStrategy());
        shapes.put("□", new SquareDrawStrategy());
        shapes.put("⬭", new OvalDrawStrategy());
        shapes.put("△", new TriangleDrawStrategy());
        shapes.put("Squiggle (〜)", new ScribbleDrawStrategy());
        // shapes.put("Polyline (└───┐)", new PolylineDrawStrategy());
    }

    public DrawStrategy makeStrategy(String s){
        return shapes.get(s);
    }
}
