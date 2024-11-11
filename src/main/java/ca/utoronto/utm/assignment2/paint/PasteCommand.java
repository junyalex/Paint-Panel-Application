package ca.utoronto.utm.assignment2.paint;

public class PasteCommand implements Command {

    private PaintModel model;
    private Shape shape;

    public PasteCommand(PaintModel model, Shape shape){
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
