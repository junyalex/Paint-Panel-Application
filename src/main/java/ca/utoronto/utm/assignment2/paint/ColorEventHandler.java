package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
    import javafx.event.EventHandler;
    import javafx.scene.control.Button;
    import javafx.scene.control.MenuItem;
    import javafx.scene.paint.Color;

    public class ColorEventHandler implements EventHandler<ActionEvent> {

        private static Button last = null;

        public ColorEventHandler() {
        }

        @Override
        public void handle(ActionEvent event) {

            if (last != null) {
                last.setStyle("-fx-background-radius: 15; -fx-background-color: " +
                        last.getText().toLowerCase() + ";");
            }

            Button button = (Button) event.getSource();
            button.setStyle("-fx-border-color: grey; -fx-border-width: 3; -fx-border-radius: 15;");

            last = button;
            PaintPanel.color = ShapeFactory.colors.get(button.getText());

        }
    }
