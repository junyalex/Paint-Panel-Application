package ca.utoronto.utm.assignment2.paint;

public class SelectMode {
    private static SelectMode instance;
    private static Shape selectedShape;

    private SelectMode() {}

    public static SelectMode getInstance(){
        if(instance==null){
            instance=new SelectMode();
        }
        return instance;
    }

    public static void setSelectedShape(Shape shape, PaintModel model) {
        if(selectedShape != null && selectedShape != shape){
            selectedShape.setSelected(false);
        }
        selectedShape = shape;
        selectedShape.setSelected(true);
        model.setSelectedShape(shape);
    }

    public static Shape getSelectedShape() {
        return selectedShape;
    }

    public static void clearSelection(PaintModel model) {
        if(selectedShape != null){
            selectedShape.setSelected(false);
        }
        selectedShape = null;
        model.setSelectedShape(null);
    }
}
