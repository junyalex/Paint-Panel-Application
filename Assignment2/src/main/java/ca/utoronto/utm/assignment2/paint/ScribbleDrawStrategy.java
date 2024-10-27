package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ScribbleDrawStrategy implements DrawStrategy {

    public Scribble scribble;

    public ScribbleDrawStrategy() {
        super();
    }

    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        Point startPoint = new Point(e.getX(), e.getY());
        ArrayList<Point> point_list = new ArrayList<>();
        point_list.add(startPoint);
        this.scribble = new Scribble(point_list);
    }

    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        ArrayList<Point> points = this.scribble.points;
        double curx = e.getX();
        double cury = e.getY();

        points.add(new Point(curx, cury));
        model.executeCommand(new DrawScribbleCommand(model, scribble));
    }

    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        double currx = e.getX();
        double curry = e.getY();

        this.scribble.points.add(new Point(currx, curry));
        model.addShape(this.scribble);
        this.scribble = null;
    }
    @Override
    public void draw(Shape shape, GraphicsContext g2d) {
        Scribble scribble = (Scribble) shape;
        g2d.setFill(Color.GREEN);
        ArrayList<Point> points = scribble.getPoints();
            for (int i = 0; i < points.size() - 1 ; i++){
                Point p1 = points.get(i);
                Point p2 = points.get(i+1);
                g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

