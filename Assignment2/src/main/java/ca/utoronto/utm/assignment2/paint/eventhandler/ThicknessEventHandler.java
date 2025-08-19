package ca.utoronto.utm.assignment2.paint.eventhandler;

import ca.utoronto.utm.assignment2.paint.panel.PaintPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class ThicknessEventHandler implements EventHandler<ActionEvent> {


    @Override
    public void handle(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        PaintPanel.thickness = Integer.parseInt(menuItem.getText());
    }
}

