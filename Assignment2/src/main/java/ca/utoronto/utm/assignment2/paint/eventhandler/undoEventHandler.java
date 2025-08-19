package ca.utoronto.utm.assignment2.paint.eventhandler;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class undoEventHandler implements EventHandler<ActionEvent> {
    private PaintModel model;

    public undoEventHandler(PaintModel model) {
        this.model = model;
    }
    @Override
    public void handle(ActionEvent e) {
        model.undo();
    }
}
