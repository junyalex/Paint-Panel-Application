package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class FunctionChooserPanel  extends GridPane implements EventHandler<ActionEvent> {

    private View view;
    private Button highlightedButton = null;
    private ArrayList<Button> buttons = new ArrayList<>();
    private static Button lastButton = null;

    public FunctionChooserPanel(View view) {
        this.view = view;

        Button select = new Button("Select");
        select.setStyle("-fx-font-size: 8px;");
        buttons.add(select);
        Button copy = new Button("\uD83D\uDCCB");
        buttons.add(copy);
        Button cut = new Button("✂");
        buttons.add(cut);
        Button paste = new Button("\uD83D\uDCE5");
        buttons.add(paste);

        for (Button button : buttons) {
            button.setMinWidth(33);
            button.setMinHeight(33);
            button.setMaxWidth(33);
            button.setMaxHeight(33);
            button.setOnAction(this);
            button.setPadding(new Insets(0));
        }
        this.add(select, 0, 0);
        this.add(copy, 1, 0);
        this.add(paste, 0, 1);
        this.add(cut, 1, 1);

    }

    @Override
    public void handle(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String text=  clickedButton.getText();

        if (lastButton != null) {
            if (lastButton.getText().equals("Select")) {
                lastButton.setStyle("-fx-font-size: 8px;");
            } else {
                lastButton.setStyle("");
            }
        }

        if (text.equals("Select")) {
            clickedButton.setStyle("-fx-background-color: lightgreen; -fx-font-size: 8px;");
        } else {
            clickedButton.setStyle("-fx-background-color: lightgreen;");
        }

        lastButton = clickedButton;
    }
}
