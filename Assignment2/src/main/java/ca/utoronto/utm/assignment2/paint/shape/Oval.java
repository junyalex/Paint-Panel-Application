package ca.utoronto.utm.assignment2.paint.shape;


import ca.utoronto.utm.assignment2.paint.strategy.DrawStrategy;
import ca.utoronto.utm.assignment2.paint.strategy.OvalDrawStrategy;

/**
 * Represents an oval shape define a center point and two radii.
 * It extends the abstract Shape class
 *
 */
public class Oval extends Shape {
    private Point centre;
    private double radiusX;
    private double radiusY;

    /**
     * Constructs an Oval object with a specified center and radii
     * @param centre the center point of the oval
     * @param radiusX the horizontal radius of the oval
     * @param radiusY the vertical radius of the oval
     */
    public Oval(Point centre, double radiusX, double radiusY) {
        super();
        this.centre = centre;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    /**
     * Gets the center point of the oval
     *
     * @return the center point of the oval
     */
    public Point getCentre() {
        return centre;
    }

    /**
     * Sets the centre point of the oval
     * @param centre the new center point of the ova;
     */
    public void setCentre(Point centre) {
        this.centre = centre;
    }

    /**
     * Gets the horizontal radius of the oval
     * @return the horizontal radius of the oval
     */
    public double getRadiusX() {
        return radiusX;
    }

    /**
     * Sets the horizontal radius of the oval
     * @param radiusX the new horizonatl radius of the oval
     */
    public void setRadiusX(double radiusX) {
        this.radiusX = radiusX;
    }

    /**
     * Returns the vertical radius of the oval
     * @return the vertical radius of the oval
     */
    public double getRadiusY() {
        return radiusY;
    }

    /**
     * Sets the vertical radius of the oval
     * @param radiusY the vertical radius of the oval
     */
    public void setRadiusY(double radiusY) {
        this.radiusY = radiusY;
    }

    /**
     * Determines whether a given point is contained within
     * the oval.
     * @param point the point to check
     * @return true if the point is inside or false otherwise
     */
    @Override
    public boolean contains(Point point){
        double X = (point.x - centre.x) / radiusX;
        double Y = (point.y - centre.y) / radiusY;
        double distanceSquared = Math.pow(X, 2) + Math.pow(Y, 2);

//        if (this.getFillStyle().equals("Outlined")){
//            double outerBoundary = Math.pow(1 + (this.getThickness()*3.5) / (2 * radiusX), 2);
//            double innerBoundary = Math.pow(1 - (this.getThickness()*3.5) / (2 * radiusX), 2);
//
//            return distanceSquared <= outerBoundary && distanceSquared >= innerBoundary;
//        }
//        else{
//            return  distanceSquared <= 1;
//        }
        return distanceSquared <= 1;
    }

    /**
     * Returns the drawing strategy for the oval
     * @return an oval drawn with the DrawStrategy
     */
    @Override
    public DrawStrategy getDrawStrategy() {
        return new OvalDrawStrategy();
    }

    /**
     * Moves the oval by a specifies distance in the x and y directions
     * @param x the amount to move in the x direction
     * @param y the amount to move in the y direction
     */
    @Override
    public void move(double x, double y) {
            this.centre.x += x;
            this.centre.y += y;
        }

    /**
     * Creates and returns a copy of this Oval object
     *
     * @return a new Oval object that is a copy of this oval.
     */
    @Override
    public Shape clone(){
        Shape clone = new Oval(new Point(this.centre.getX(), this.centre.getY())
                ,this.radiusX,this.radiusY);
        clone.setColor(this.getColor());
        clone.setThickness(this.getThickness());
        clone.setSelected(this.isSelected());
        return clone;
    }
}
