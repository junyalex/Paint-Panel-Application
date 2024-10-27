package ca.utoronto.utm.assignment2.paint;

public class DrawOvalCommand implements Command {
    private PaintModel model;
    private Oval oval;

    public DrawOvalCommand(PaintModel model, Oval oval) {
        this.model = model;
        this.oval = oval;
    }

    @Override
    public void execute() {
        model.addShape(oval);
    }
}
