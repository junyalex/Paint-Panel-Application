package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class FunctionChooserPanel  extends VBox implements EventHandler<ActionEvent> {

    private View view;
    private Button highlightedButton = null;
    private ArrayList<Button> buttons = new ArrayList<>();
    private static Button lastButton = null;

    public FunctionChooserPanel(View view) {
        this.view = view;
        Button copy = new Button("\uD83D\uDCCB");
        buttons.add(copy);
        Button cut = new Button("✂");
        buttons.add(cut);
        Button paste = new Button("\uD83D\uDCE5");
        buttons.add(paste);

        for (Button button : buttons) {
            button.setMinWidth(30);
            button.setMinHeight(30);
            button.setMaxWidth(30);
            button.setMaxHeight(30);
            button.setOnAction(this);
            button.setPadding(new Insets(0));
        }
        this.getChildren().addAll(copy, cut, paste);
    }

    @Override
    public void handle(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        if (lastButton != null) {
            lastButton.setStyle("");
        }

        clickedButton.setStyle("-fx-background-color: lightgreen;");
        lastButton = clickedButton;
    }
}
