package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.shape.Square;

public class DrawSquareCommand implements Command {
    private PaintModel model;
    private Square square;

    public DrawSquareCommand(PaintModel model, Square square) {
        this.model = model;
        this.square = square;
    }

    @Override
    public void execute() {
        model.addShape(square);
    }

    @Override
    public void undo() {
        model.removeShape();
    }
}
