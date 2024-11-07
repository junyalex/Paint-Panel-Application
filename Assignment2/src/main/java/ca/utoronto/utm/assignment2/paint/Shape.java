package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    private Color color;
    private final String fillStyle;
    private int thickness;
    private boolean selected;

    public Shape(){
        fillStyle = FillStyleManager.getInstance().getStyle();
        this.selected = false;
    }

    public abstract DrawStrategy getDrawStrategy();

    public Color getColor() {
        return this.color;
    }

    public String getFillStyle() {return this.fillStyle;}

    public void setColor(Color color) {
        this.color = color;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }
    public int getThickness() {
        return this.thickness;
    }

    public boolean isSelected() {return this.selected;}

    public void setSelected(boolean selected) {this.selected = selected;}

    public abstract boolean contains(Point selectPoint);
}
