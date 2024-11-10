package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PasteEventHandler implements EventHandler<ActionEvent> {

    private PaintModel model;


    public PasteEventHandler(PaintModel model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {

    }

}
