package ca.utoronto.utm.assignment2.paint;

public class CopyCommand implements Command {
    private PaintModel model;

    public CopyCommand(PaintModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        this.model.setToBePasted(this.model.getSelectedShape());
    }

    @Override
    public void undo() {}
}
