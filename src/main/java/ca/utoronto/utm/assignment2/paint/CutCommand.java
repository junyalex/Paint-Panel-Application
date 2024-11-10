package ca.utoronto.utm.assignment2.paint;

public class CutCommand implements Command{

    private PaintModel model;
    private Shape shape;
    private Shape selectedShape;

    public CutCommand(PaintModel model, Shape shape) {
        this.model = model;
        this.shape = shape;
        this.selectedShape = this.model.getSelectedShape();
    }

    @Override
    public void execute() {
        for (Shape s : model.getShapes()){
            if(s.equals(selectedShape)){
                
            }
        }
    }

    @Override
    public void undo() {

    }
}
