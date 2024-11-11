package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CutEventHandler implements EventHandler<ActionEvent> {

    private PaintModel model;


    public CutEventHandler(PaintModel model) {
        this.model = model;
    }
    @Override
    public void handle(ActionEvent event) {
        Shape shape = model.getSelectedShape();
        this.model.setToBePasted(shape);

        if (shape != null) {
            Command command = new CutCommand(model, shape);
            model.executeCommand(command);

        }

        System.out.println("Shape cut");
    }
}
