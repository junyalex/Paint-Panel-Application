package ca.utoronto.utm.assignment2.paint;

public class DrawPolylineCommand implements Command {
    private PaintModel model;
    private Polyline polyline;

    public DrawPolylineCommand(PaintModel model, Polyline polyline) {
        this.model = model;
        this.polyline = polyline;
    }

    @Override
    public void execute(){
        model.addShape(polyline);
    }

    @Override
    public void undo(){
        model.removeShape();
    }
}
