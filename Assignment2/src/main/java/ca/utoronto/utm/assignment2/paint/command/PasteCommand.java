package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.SelectMode;
import ca.utoronto.utm.assignment2.paint.shape.Shape;

public class PasteCommand implements Command {

    private PaintModel model;
    private Shape shape;

    public PasteCommand(PaintModel model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        PaintModel.toBePastedStack += 5;
        SelectMode.setSelectedShape(this.shape, this.model);
        this.model.getShapes().add(this.shape);
    }

    @Override
    public void undo() {
        this.model.removeShape();
    }
}
