package ca.utoronto.utm.assignment2.paint;

public class CutCommand implements Command{

    private PaintModel model;
    private Shape shape;
    private Shape selectedShape;
    private Shape cutteddShape;
    private int cuttedIndex;

    public CutCommand(PaintModel model, Shape shape) {
        this.model = model;
        this.shape = shape;
        this.selectedShape = this.model.getSelectedShape();
        this.cutteddShape = model.getSelectedShape();
        this.cuttedIndex = model.getShapes().indexOf(this.cutteddShape);
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
        this.model.getShapes().add(this.cuttedIndex, this.cutteddShape);
        this.model.setSelectedShape(this.selectedShape);
    }
}
