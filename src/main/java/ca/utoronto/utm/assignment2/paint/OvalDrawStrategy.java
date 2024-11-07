package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public class OvalDrawStrategy implements DrawStrategy {
    private Oval oval;
    private Point startPoint;

    @Override
    public void onMousePressed(MouseEvent e, PaintModel model) {
        System.out.println("Started Oval");
        startPoint = new Point(e.getX(), e.getY());
        this.oval = new Oval(startPoint, 0, 0);
        this.oval.setColor(PaintPanel.color);
        this.oval.setThickness(PaintPanel.thickness);
        this.oval.setThickness(PaintPanel.thickness);
    }

    @Override
    public void onMouseDragged(MouseEvent e, PaintModel model) {
        if (this.oval != null) {
            double left = Math.min(startPoint.getX(), e.getX());
            double right = Math.max(startPoint.getX(), e.getX());
            double top = Math.min(startPoint.getY(), e.getY());
            double bottom = Math.max(startPoint.getY(), e.getY());

            this.oval.setCentre(new Point((left + right) / 2, (top + bottom) / 2));
            this.oval.setRadiusX((right - left) / 2);
            this.oval.setRadiusY((bottom - top) / 2);
            model.addShapePreview(this.oval);


        }
    }

    @Override
    public void onMouseReleased(MouseEvent e, PaintModel model) {
        if (this.oval != null) {
            model.executeCommand(new DrawOvalCommand(model, oval));
            this.oval = null;
            System.out.println("Created Oval");
        }
    }

    @Override
    public void draw(Shape shape, GraphicsContext g2d, String currStyle) {
        Oval o = (Oval) shape;

        if(o.getFillStyle().equals("Outlined")) {
            if(o.isSelected()){g2d.setLineDashes(5, o.getThickness() * 2);}

            g2d.setStroke(o.getColor());
            g2d.setLineWidth(o.getThickness());
            g2d.strokeOval(
                    o.getCentre().getX() - o.getRadiusX(),
                    o.getCentre().getY() - o.getRadiusY(),
                    o.getRadiusX() * 2,
                    o.getRadiusY() * 2
            );
        }
        else if (o.getFillStyle().equals("Filled")) {
            g2d.setFill(o.getColor());
            g2d.fillOval(
                    o.getCentre().getX() - o.getRadiusX(),
                    o.getCentre().getY() - o.getRadiusY(),
                    o.getRadiusX() * 2,
                    o.getRadiusY() * 2
            );
            if(o.isSelected()){
                g2d.setLineDashes(5, 5);
                g2d.setStroke(o.getColor().darker());
                g2d.setLineWidth(3);
                g2d.strokeOval(
                        o.getCentre().getX() - o.getRadiusX(),
                        o.getCentre().getY() - o.getRadiusY(),
                        o.getRadiusX() * 2,
                        o.getRadiusY() * 2
                );
            }
        }
        g2d.setLineDashes();
    }
}
