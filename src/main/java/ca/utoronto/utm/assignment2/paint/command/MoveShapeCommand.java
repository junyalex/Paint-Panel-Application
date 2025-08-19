package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.shape.Shape;

public class MoveShapeCommand implements Command {

    private Shape shape;
    private PaintModel model;


    public MoveShapeCommand(PaintModel model, Shape shape){

    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
