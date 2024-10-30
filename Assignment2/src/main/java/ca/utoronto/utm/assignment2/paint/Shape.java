package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    private Color color;
    private final String fillStyle;

    public Shape(){
        fillStyle = FillStyleManager.getInstance().getStyle();
    }

    public abstract DrawStrategy getDrawStrategy();

    public Color getColor() {
        return this.color;
    }

    public String getFillStyle() {return this.fillStyle;}

    public void setColor(Color color) {
        this.color = color;
    }
}
