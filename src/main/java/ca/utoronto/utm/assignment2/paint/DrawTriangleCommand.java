package ca.utoronto.utm.assignment2.paint;

public class DrawTriangleCommand implements Command {
    private PaintModel model;
    private Triangle triangle;

    public DrawTriangleCommand(PaintModel model, Triangle triangle) {
        this.model = model;
        this.triangle = triangle;
    }

    @Override
    public void execute() {
        model.addShape(triangle);  // Adds circle to the model
    }
}
