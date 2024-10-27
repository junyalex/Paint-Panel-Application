package ca.utoronto.utm.assignment2.paint;

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
}
