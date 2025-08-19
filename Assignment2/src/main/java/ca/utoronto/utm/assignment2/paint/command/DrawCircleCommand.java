package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.shape.Circle;

public class DrawCircleCommand implements Command {
    private PaintModel model;
    private Circle circle;

    public DrawCircleCommand(PaintModel model, Circle circle) {
        this.model = model;
        this.circle = circle;
    }

    @Override
    public void execute() {
        model.addShape(circle);
    }

    @Override
    public void undo() {
        model.removeShape();
    }

}