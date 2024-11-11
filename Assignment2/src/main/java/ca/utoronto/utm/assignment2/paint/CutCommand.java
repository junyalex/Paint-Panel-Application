package ca.utoronto.utm.assignment2.paint;

public class CutCommand implements Command{

    private PaintModel model;
    private Shape shape;
    private Shape selectedShape;

    public CutCommand(PaintModel model, Shape shape) {
        this.model = model;
        this.shape = shape;
        this.selectedShape = this.model.getSelectedShape();
    }

    @Override
    public void execute() {
        if (selectedShape != null) {
            this.model.getShapes().remove(this.selectedShape);
            this.model.setSelectedShape(this.selectedShape);
        }
    }

    @Override
    public void undo() {
        this.model.addShape(this.selectedShape);
        this.model.setSelectedShape(this.selectedShape);
    }
}
