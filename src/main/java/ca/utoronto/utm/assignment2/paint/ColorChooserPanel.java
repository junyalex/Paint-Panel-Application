package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ColorChooserPanel extends GridPane {

private View view;
    private ArrayList<Button> buttons;
    private PaintModel model;

    public ColorChooserPanel(View view, PaintModel model) {
        this.view = view;
        this.model = model;
        buttons = new ArrayList<>();

        Button red = new Button("Red");
        red.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(15), null)));
        red.setTextFill(Color.RED);
        Button orange = new Button("Orange");
        orange.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(15), null)));
        orange.setTextFill(Color.ORANGE);
        Button yellow = new Button("Yellow");
        yellow.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(15), null)));
        yellow.setTextFill(Color.YELLOW);
        Button green = new Button("Green");
        green.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(15), null)));
        green.setTextFill(Color.GREEN);
        Button blue = new Button("Blue");
        blue.setBackground(new Background(new BackgroundFill(Color.BLUE, new CornerRadii(15), null)));
        blue.setTextFill(Color.BLUE);
        Button black = new Button("Black");
        black.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(15), null)));
        black.setTextFill(Color.BLACK);


        Button[] Buttons = {red, orange, yellow, green, blue, black};


        for (Button button : Buttons) {
            button.setMinSize(30, 30);
            button.setMaxSize(30, 30);
            buttons.add(button);
            button.setOnAction(new ColorEventHandler(this.model));
        }

        this.setHgap(5);
        this.setVgap(5);

        this.add(red, 0, 0);
        this.add(orange, 1, 0);
        this.add(yellow, 0, 1);
        this.add(green, 1, 1);
        this.add(blue, 0, 2);
        this.add(black, 1, 2);
    }

}



