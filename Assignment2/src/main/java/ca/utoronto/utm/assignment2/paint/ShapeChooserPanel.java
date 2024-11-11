package ca.utoronto.utm.assignment2.paint;
import javafx.animation.AnimationTimer;
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
                String[] buttonLabels = {"◯", "ㅁ", "□", "⬭","△", "〜", "ㄴ"};

                Button select = new Button("select");
                select.setMinSize(60, 30);
                this.add(select, 0, 0, 2, 1);
                buttons.add(select);
                select.setOnAction(this);

                Button delete = new Button("delete");
                delete.setMinSize(60, 30);
                this.add(delete, 0, 1, 2, 1);
                buttons.add(delete);

                delete.setOnAction(event -> {
                        delete.setStyle("-fx-background-color: lightgray");

                        new AnimationTimer() {
                                private long startTime = -1;
                                @Override
                                public void handle(long now) {
                                        if (startTime < 0) {
                                                startTime = now;
                                        }
                                        if (now - startTime > 150_000_000) {
                                                delete.setStyle("");
                                                stop();
                                        }
                                }
                        }.start();
                        view.getPaintModel().executeCommand(new DeleteSelectedCommand(view.getPaintModel()));
                        System.out.println("Shape Deleted");
                });

                for (int i = 0; i < buttonLabels.length; i++) {
                        Button button = new Button(buttonLabels[i]);
                        button.setMinSize(30, 30);
                        button.setMaxSize(30, 30);

                        int row = (i/2) + 2;
                        int col = i % 2; // 0 or 1

                        this.add(button, col, row);
                        buttons.add(button);
                        button.setOnAction(this);
                }
        }


        @Override
        public void handle(ActionEvent event) {
                Button clickedButton = (Button) event.getSource();
                String command = clickedButton.getText();


                if (!PaintPanel.getMode().equals("〜") || !command.equals("〜")) {
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


