package ca.utoronto.utm.assignment2.paint;

public class DrawCircleCommand implements Command {
    private PaintModel model;
    private Circle circle;

    public DrawCircleCommand(PaintModel model, Circle circle) {
        this.model = model;
        this.circle = circle;
    }

    @Override
    public void execute() {
        model.addShape(circle);  // Adds triangle to the model
    }
}