package ca.utoronto.utm.assignment2.paint.eventhandler;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.command.CopyCommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CopyEventHandler implements EventHandler<ActionEvent>{

    private PaintModel model;

    public CopyEventHandler(PaintModel model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        PaintModel.toBePastedStack = 5;
        model.executeCommand(new CopyCommand(this.model));
        System.out.println("Shape copied");
        System.out.println(this.model.getSelectedShape());
    }
}
