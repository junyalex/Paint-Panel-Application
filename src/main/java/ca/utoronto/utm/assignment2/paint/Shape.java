package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class Shape {
    private Color color;
    private String fillStyle;
    private int thickness;
    private boolean selected;
    protected ArrayList<Point> pointsHistory = new ArrayList<>();
    protected int current = 0;

    public Shape() {
        fillStyle = FillStyleManager.getInstance().getStyle();
        this.selected = false;
    }

    public abstract DrawStrategy getDrawStrategy();

    public Color getColor() {
        return this.color;
    }

    public String getFillStyle() {
        return this.fillStyle;
    }
    public void setFillStyle(String style){
        this.fillStyle = style;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public int getThickness() {
        return this.thickness;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public abstract boolean contains(Point selectPoint);

    public void move(double deltaX, double deltaY) {
    }

    public Shape clone(){
        return this;
    }


}

