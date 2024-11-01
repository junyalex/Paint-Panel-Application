package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PolylineDrawStrategy implements DrawStrategy {
    private Polyline polyline;
    private List<Point>points;

    public PolylineDrawStrategy(){
        super();
        points = new ArrayList<>();
    }

    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        System.out.println("Started Polyline");
        points.add(new Point(e.getX(), e.getY()));
        this.polyline = new Polyline(points);
        this.polyline.setColor(PaintPanel.color);
    }

    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        if (this.polyline != null) {
            points.add(new Point(e.getX(), e.getY()));
            model.addShapePreview(this.polyline);
        }
    }

    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        if (this.polyline != null) {
            System.out.println("Created Polyline");
            points.add(new Point(e.getX(), e.getY()));
            model.executeCommand(new DrawPolylineCommand(model, polyline));
            this.polyline = null;
        }
    }

    @Override
    public void draw(Shape shape, GraphicsContext g2d, String currStyle){
        Polyline p = (Polyline) shape;
        List<Point> points = p.getPoints();

        for(int i =0; i < points.size() - 1; i++){
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);

            if(currStyle.equals("Outlined")){
                g2d.setStroke(p.getColor());
                g2d.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            }
            else if(currStyle.equals("Filled")) {
                g2d.setFill(p.getColor());
                g2d.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            }
        }
    }
}
