package ca.utoronto.utm.assignment2.paint;

public class DrawScribbleCommand implements Command {
    private PaintModel model;
    private Scribble scribble;

    public DrawScribbleCommand(PaintModel model, Scribble scribble) {
        this.model = model;
        this.scribble = scribble;
    }

    @Override
    public void execute() {
        model.addShape(scribble);
    }

    @Override
    public void undo() {
        model.removeShape();
    }
}
