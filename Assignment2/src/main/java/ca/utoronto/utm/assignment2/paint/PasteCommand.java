package ca.utoronto.utm.assignment2.paint;

public class PasteCommand implements Command {

    private PaintModel model;
    private Shape shape;

    public PasteCommand(PaintModel model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        PaintModel.toBePastedStack += 5;
        SelectMode.setSelectedShape(this.shape, this.model);
        this.model.getShapes().add(this.shape);
    }

    @Override
    public void undo() {
        this.model.removeShape();
    }
}
