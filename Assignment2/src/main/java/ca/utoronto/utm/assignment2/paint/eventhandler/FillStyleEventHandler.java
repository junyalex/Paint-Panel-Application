package ca.utoronto.utm.assignment2.paint.eventhandler;

import ca.utoronto.utm.assignment2.paint.FillStyleManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class FillStyleEventHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        MenuItem currentMenu = (MenuItem) event.getSource();
        String currentMode = currentMenu.getText();

        FillStyleManager.getInstance().setStyle(currentMode);
    }
}
