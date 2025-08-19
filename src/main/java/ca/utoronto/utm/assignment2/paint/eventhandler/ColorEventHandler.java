package ca.utoronto.utm.assignment2.paint.eventhandler;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.panel.PaintPanel;
import ca.utoronto.utm.assignment2.paint.SelectMode;
import ca.utoronto.utm.assignment2.paint.ShapeFactory;
import javafx.event.ActionEvent;
    import javafx.event.EventHandler;
    import javafx.scene.control.Button;

public class ColorEventHandler implements EventHandler<ActionEvent> {

        private static Button last = null;
        private PaintModel model;

        public ColorEventHandler(PaintModel model) {
            this.model = model;
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

            if(SelectMode.getSelectedShape() != null) {
                SelectMode.getSelectedShape().setColor(PaintPanel.color);
                model.setSelectedShape(SelectMode.getSelectedShape());
            }
        }
    }
