package ca.utoronto.utm.assignment2.paint.eventhandler;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class redoEventHandler implements EventHandler<ActionEvent> {
    private PaintModel model;

    public redoEventHandler(PaintModel model) {
        this.model = model;
    }
    @Override
    public void handle(ActionEvent e) {
        model.redo();
    }
}