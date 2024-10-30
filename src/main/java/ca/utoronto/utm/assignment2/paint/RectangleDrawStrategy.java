package ca.utoronto.utm.assignment2.paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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
        this.rectangle.setColor(PaintPanel.color);
    }

    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        this.rectangle.setCorner2(new Point(e.getX(), e.getY()));
        model.addShapePreview(this.rectangle);
    }

    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        this.rectangle.setCorner2(new Point(e.getX(), e.getY()));
        model.executeCommand(new DrawRectangleCommand(model, rectangle));
        this.rectangle = null;
        System.out.println("Created Rectangle");
    }

    @Override
    public void draw(Shape shape, GraphicsContext g2d, String currStyle) {
        //Rectangle r = (Rectangle )model.getShape().getLast();
        Rectangle r = (Rectangle) shape;
        //added

        double corner_x = Math.min(r.getCorner1().getX(), r.getCorner2().getX());
        double corner_y = Math.min(r.getCorner1().getY(), r.getCorner2().getY());
        double width = r.getWidth();
        double height = r.getHeight();
        if(currStyle.equals("Outlined")){
            g2d.setStroke(r.getColor());
            g2d.strokeRect(corner_x, corner_y, width, height);
        }
        else if(currStyle.equals("Filled")) {
            g2d.setFill(r.getColor());
            g2d.fillRect(corner_x, corner_y, width, height);
        }
    }

}
