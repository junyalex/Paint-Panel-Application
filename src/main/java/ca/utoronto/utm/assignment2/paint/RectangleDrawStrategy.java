package ca.utoronto.utm.assignment2.paint;


import javafx.scene.input.MouseEvent;

public class RectangleDrawStrategy implements DrawStrategy{

    public Rectangle rectangle;

    public RectangleDrawStrategy() {
        super();
    }

    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        System.out.println("Started Rectangle");
        Point corner1 = new Point(e.getX(), e.getY()); // left_top
        Point corner2 = new Point(0, 0);   // right_bot
        this.rectangle = new Rectangle(corner1, corner2);

    }

    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        this.rectangle.setCorner2(new Point(e.getX(), e.getY()));
        model.addShape(this.rectangle);
    }

    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        this.rectangle.setCorner2(new Point(e.getX(), e.getY()));
        this.rectangle = null;
    }
}
