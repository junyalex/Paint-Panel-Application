/**
 * The ColorChooserPanel class is a custom JavaFX component that provides a color selection panel for the user.
 * This panel includes a grid of buttons representing different colors. When a button is clicked, the associated
 * color is set as the current drawing color in the PaintModel.
 *
 * This class extends GridPane, allowing for a structured layout of the color buttons.
 */
package ca.utoronto.utm.assignment2.paint.panel;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.View;
import ca.utoronto.utm.assignment2.paint.eventhandler.ColorEventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * Represents a panel of color buttons for selecting the current paint color.
 */
public class ColorChooserPanel extends GridPane {
    private View view; // Reference to the View object.
    private ArrayList<Button> buttons; // List of buttons for color selection.
    private PaintModel model; // Reference to the PaintModel for color updates.

    /**
     * Constructs a ColorChooserPanel with the specified View and PaintModel.
     *
     * @param view  the View associated with this color chooser panel.
     * @param model the PaintModel that handles the current paint color.
     */
    public ColorChooserPanel(View view, PaintModel model) {
        this.view = view;
        this.model = model;
        buttons = new ArrayList<>();

        // Create color buttons with their respective properties
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

        // Array of buttons for easier iteration
        Button[] Buttons = {red, orange, yellow, green, blue, black};

        // Configure button properties and event handlers
        for (Button button : Buttons) {
            button.setMinSize(30, 30);
            button.setMaxSize(30, 30);
            buttons.add(button);
            button.setOnAction(new ColorEventHandler(this.model)); // Attach event handler for color selection
        }

        // Set spacing between buttons in the grid
        this.setHgap(5);
        this.setVgap(5);

        // Add buttons to the grid layout
        this.add(red, 0, 0);
        this.add(orange, 1, 0);
        this.add(yellow, 0, 1);
        this.add(green, 1, 1);
        this.add(blue, 0, 2);
        this.add(black, 1, 2);
    }
}
