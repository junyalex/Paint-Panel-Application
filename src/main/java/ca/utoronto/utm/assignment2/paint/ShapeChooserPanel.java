package ca.utoronto.utm.assignment2.paint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

        private View view;
        private Button highlightedButton = null;
        private ArrayList<Button> buttons;

        public ShapeChooserPanel(View view) {

                this.view = view;
                buttons = new ArrayList<>();
                String[] buttonLabels = { "select", "◯", "▭", "□", "⬭","△", "Squiggle (〜)", "Polyline (└───┐)"};

                int row = 0;
                for (String label : buttonLabels) {
                        Button button = new Button(label);
                        button.setMinWidth(100);
                        this.add(button, 0, row);
                        buttons.add(button);
                        row++;
                        button.setOnAction(this);
                }
        }


        @Override
        public void handle(ActionEvent event) {
                Button clickedButton = (Button) event.getSource();
                String command = clickedButton.getText();

                if (!PaintPanel.getMode().equals("Squiggle (〜)") || !command.equals("Squiggle")) {
                    Polyline.newPoly = true;
                } else {Polyline.newPoly = false;}


                for(Button button: buttons){
                        button.setStyle("");
                }

                clickedButton.setStyle("-fx-background-color: lightgreen");
                highlightedButton = clickedButton;

                view.setMode(command);
                System.out.println(command);
        }
}


