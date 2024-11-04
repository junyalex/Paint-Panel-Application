package ca.utoronto.utm.assignment2.paint;

public class SelectMode {

    private static Shape shape;

    public SelectMode(Shape shape) {
        this.shape = shape;
    }

    public static Shape getSelectedShape() {
        return shape;
    }

    public static void clearSelection() {
        shape = null;
    }
}
