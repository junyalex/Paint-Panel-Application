package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.SelectMode;
import ca.utoronto.utm.assignment2.paint.shape.Shape;

public class DeleteSelectedCommand implements Command {
    private PaintModel model;
    private Shape deletedShape;
    private int deletedIndex;

    public DeleteSelectedCommand(PaintModel model) {
        this.model = model;
        this.deletedShape = model.getSelectedShape();
        this.deletedIndex = model.getShapes().indexOf(deletedShape);
    }

    @Override
    public void execute() {
        if(deletedShape != null) {
            SelectMode.deleteSelectedShape(this.model);
        }
    }

    @Override
    public void undo() {
        if(deletedShape != null) {
            model.getShapes().add(deletedIndex, deletedShape);
            SelectMode.setSelectedShape(deletedShape, this.model);
        }
    }
}
