package ca.utoronto.utm.assignment2.paint;

public class DrawRectangleCommand implements Command {
    private PaintModel model;
    private Rectangle rectangle;

    public DrawRectangleCommand(PaintModel model, Rectangle rectangle) {
        this.model = model;
        this.rectangle = rectangle;
    }

    @Override
    public void execute() {
        model.addShape(rectangle);
    }

    @Override
    public void undo() {
        model.removeShape();
    }
}
