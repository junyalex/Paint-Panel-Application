package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;

public interface DrawStrategy {

    void onMousePressed(MouseEvent e,PaintModel model);
    void onMouseDragged(MouseEvent e,PaintModel model);
    void onMouseReleased(MouseEvent e,PaintModel model);
}
