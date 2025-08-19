package ca.utoronto.utm.assignment2.paint.command;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.shape.Polyline;

public class DrawPolylineCommand implements Command {
    private PaintModel model;
    private Polyline polyline;

    public DrawPolylineCommand(PaintModel model, Polyline polyline) {
        this.model = model;
        this.polyline = polyline;
    }

    @Override
    public void execute() {
        model.addShape(polyline);
    }

    @Override
    public void undo(){
        Polyline.last_points.removeLast();
        model.removeShape();
    }
}
