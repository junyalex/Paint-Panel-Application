package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class ColorEventHandler implements EventHandler<ActionEvent> {

    public ColorEventHandler() {
    }

    @Override
    public void handle(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        PaintPanel.color = ShapeFactory.colors.get(menuItem.getText());
    }
}
