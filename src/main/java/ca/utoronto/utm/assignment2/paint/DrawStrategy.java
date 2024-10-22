package ca.utoronto.utm.assignment2.paint;

import java.awt.event.MouseEvent;

public interface DrawStrategy {
    void onMousePressed(MouseEvent e,PaintModel model);
    void onMouseDragged(MouseEvent e,PaintModel model);
    void onMouseReleased(MouseEvent e,PaintModel model);
}
