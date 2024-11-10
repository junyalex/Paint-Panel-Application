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

    }
}
