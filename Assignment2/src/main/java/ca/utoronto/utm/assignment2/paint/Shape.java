package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * This is an abstract class for all the shapes.
 * It provides all the methods that are common to all shapes such as
 * colour, fill style, thickness, selection, points and moving.
 */
public abstract class Shape {
    private Color color;
    private String fillStyle;
    private int thickness;
    private boolean selected;
    protected ArrayList<Point> pointsHistory = new ArrayList<>();
    protected int current = 0;

    /**
     *
     * Constructs a Shape object with default settings
     * Initializes the fill style and sets the initial selection state
     * to false
     */
    public Shape() {
        fillStyle = FillStyleManager.getInstance().getStyle();
        this.selected = false;
    }

    /**
     *
     * @return a shape DrawStrategy object used to draw the shape
     */
    public abstract DrawStrategy getDrawStrategy();

    /**
     * Gets the colour of the shape
     * @return the colour of the shape
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Gets the fill style of the shape
     *
     * @return the fill style of the shape
     */
    public String getFillStyle() {
        return this.fillStyle;
    }

    /**Sets the fill style of the shape
     *
     * @param style the fill style to set the shape
     */
    public void setFillStyle(String style){
        this.fillStyle = style;
    }

    /**
     * Sets the colour of the shape
     * @param color the color to set the shape
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the thickness of the outline of the shape
     * @param thickness
     */
    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    /**
     * Gets the thickness of the shape's outline
     * @return the thickness of the shape's outline
     */
    public int getThickness() {
        return this.thickness;
    }

    /**
     * Checks is the shape is currently selected
     * @return boolean value; true if selected , false otherwise
     */
    public boolean isSelected() {
        return this.selected;
    }

    /**
     * Sets the selection state of the shape.
     *
     * @param selected sets the selection state
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Checks if a given point is contained withing the shape
     *
     * @param selectPoint
     * @return boolean value: True if the point is within the shape, false otherwise
     */
    public abstract boolean contains(Point selectPoint);

    /**
     * Moves the shape by specified x and y amounts
     *
     * @param deltaX the amount to move in the x direction
     * @param deltaY the amount to move in the y direction
     */
    public void move(double deltaX, double deltaY) {
    }

    /**
     * Creates a clone of the shape
     *
     * @return a new shape Object that is a copy of this shape
     */
    public Shape clone(){
        return this;
    }


}

