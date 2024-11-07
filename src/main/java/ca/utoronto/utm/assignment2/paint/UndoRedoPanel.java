package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class UndoRedoPanel extends GridPane {

    private View view;
    private ArrayList<Button> buttons;

    public UndoRedoPanel(View view) {

        this.view = view;
        buttons = new ArrayList<>();

        Button undo = new Button("↶");
        Button redo = new Button("↷");
        buttons.add(undo);
        buttons.add(redo);

       undo.setMaxHeight(30);
       undo.setMaxWidth(30);
       undo.setMinHeight(30);
       undo.setMinWidth(30);

       redo.setMaxHeight(30);
       redo.setMaxWidth(30);
       redo.setMinHeight(30);
       redo.setMinWidth(30);

       this.add(undo, 0, 0);
       this.add(redo, 1, 0);

        undo.setOnAction(new undoEventHandler(view.getPaintModel()));
        redo.setOnAction(new redoEventHandler(view.getPaintModel()));
    }

}
