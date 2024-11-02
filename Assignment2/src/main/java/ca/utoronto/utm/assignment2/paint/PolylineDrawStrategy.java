package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PolylineDrawStrategy implements DrawStrategy {
    private Polyline polyline;


    public PolylineDrawStrategy(){
        super();
    }

    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        System.out.println("Started Polyline");
        if(Polyline.newPoly) Polyline.last_points.add(null);
        Polyline.newPoly = false;

        this.polyline = new Polyline();
        this.polyline.setColor(PaintPanel.color);
        this.polyline.setThickness(PaintPanel.thickness);
        this.polyline.getPoints().add(new Point(e.getX(), e.getY()));
        if (!Polyline.last_points.isEmpty()) {
            this.polyline.getPoints().add(Polyline.last_points.get(Polyline.last_points.size() - 1));
        }
        this.polyline.setColor(PaintPanel.color);
    }

    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        if (this.polyline != null) {
            this.polyline.getPoints().removeLast();
            this.polyline.getPoints().add(new Point(e.getX(), e.getY()));
            model.addShapePreview(this.polyline);
        }
    }

    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        if (this.polyline != null) {
            System.out.println("Created Polyline");
            this.polyline.getPoints().add(new Point(e.getX(), e.getY()));
            Polyline.last_points.add(new Point(e.getX(), e.getY()));
            model.executeCommand(new DrawPolylineCommand(model, polyline));

        }
    }

    @Override
    public void draw(Shape shape, GraphicsContext g2d, String currStyle){
        Polyline p = (Polyline) shape;
        ArrayList<Point> points = p.getPoints();
        g2d.setStroke(p.getColor());
        g2d.setLineWidth(p.getThickness());
        for(int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            if(p1 == null) continue;
            Point p2 = points.get(i + 1);
            if(p2 == null) continue;
            g2d.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }


    }
}
