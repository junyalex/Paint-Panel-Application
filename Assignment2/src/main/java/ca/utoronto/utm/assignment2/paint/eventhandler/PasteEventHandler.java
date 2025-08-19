package ca.utoronto.utm.assignment2.paint.eventhandler;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.panel.PaintPanel;
import ca.utoronto.utm.assignment2.paint.command.Command;
import ca.utoronto.utm.assignment2.paint.command.PasteCommand;
import ca.utoronto.utm.assignment2.paint.shape.Shape;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PasteEventHandler implements EventHandler<ActionEvent> {

    private PaintModel model;
    private PaintPanel panel;

    public PasteEventHandler(PaintModel model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        Shape toBePasted = this.model.getToBePasted().clone();
        toBePasted.move(PaintModel.toBePastedStack, PaintModel.toBePastedStack);

        if(toBePasted != null) {
            Command command = new PasteCommand(this.model, toBePasted);
            this.model.executeCommand(command);
        }

        System.out.println("Shape pasted");
    }

}
